package ResourceHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Boss;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class LootDAO {

    private static final Logger logger = Logger.getLogger("errorLogger");
    private static final String publicIP = "96.41.250.84";
    private static final String privateIP = "192.168.1.17";

    public static List<Boss> getBosses() {
        try {
            try {
                return getBosses(publicIP);
            } catch (final ConnectException c) {
                logger.log(Level.WARNING, "error on public ip, attempting private ip");
                return getBosses(privateIP);
            }
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "failed to get bosses", e);
            return new ArrayList<>();
        }
    }

    private static List<Boss> getBosses(final String host) throws Exception {
        List<Boss> bosses = new ArrayList<>();
        URL url = new URL("http://" + host + ":8080/bosses");
        URLConnection request = url.openConnection();
        request.connect();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject(mapper.readValue((InputStream) request.getContent(), Map.class));
        JSONArray jsonArray = jsonObject.getJSONArray("bosses");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject boss = jsonArray.getJSONObject(i);
            bosses.add(new Boss(boss.getInt("id"), boss.getString("name")));
        }
        return bosses;
    }

}
