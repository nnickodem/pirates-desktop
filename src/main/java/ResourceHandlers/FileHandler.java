package ResourceHandlers;

import dto.Loot;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Handles reading/writing of various external resources including save files, level files, and fonts
 */
public class FileHandler {

    private static final Logger logger = Logger.getLogger("errorLogger");
    private static final String saveFilePath = ".xml";
    private static final List<String> rarities = Arrays.asList("crude", "common", "rare", "famed", "legendary");

    /**
     * Writes a new empty save file xml
     */
    public static void writeSaveFile(final String userName, final String bossName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement(bossName.replaceAll(" ", ""));
            document.appendChild(root);

            Element user = document.createElement("user");
            user.appendChild(document.createTextNode(userName));
            root.appendChild(user);
            Element boss = document.createElement("boss");
            boss.appendChild(document.createTextNode(bossName));
            root.appendChild(boss);
            Element kc = document.createElement("kills");
            kc.appendChild(document.createTextNode("0"));
            root.appendChild(kc);
            for (String r : rarities) {
                Element rarity = document.createElement(r);
                rarity.appendChild(document.createTextNode("0"));
                root.appendChild(rarity);
            }

            saveXML(document, bossName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to write save file!", e);
        }
    }


    public static void updateSave(final String value, final String result, final String userName, final String bossName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(bossName.replaceAll(" ", "") + saveFilePath);

            Node object = doc.getElementsByTagName(value.toLowerCase()).item(0);
            object.setTextContent(result);

            saveXML(doc, bossName);
        } catch (IOException i) {
            logger.log(Level.WARNING, "Failed to read save file, attempting to write one...", i);
            writeSaveFile(userName, bossName);
            updateSave(value, result, userName, bossName);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update save file!", e);
        }
    }

    /**
     * Gets all save information
     *
     * @return List<String> where each entry is a level result
     */
    public static Loot getSave(final String bossName) {
        try {
            File fXmlFile = new File(bossName.replaceAll(" ", "") + saveFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            List<Integer> storedValues = new ArrayList<>();
            storedValues.add(Integer.valueOf(doc.getElementsByTagName("kills").item(0).getTextContent()));
            rarities.forEach(r -> {
                storedValues.add(Integer.valueOf(doc.getElementsByTagName(r).item(0).getTextContent()));
            });
            Loot loot = new Loot(storedValues.get(0), storedValues.get(1), storedValues.get(2), storedValues.get(3),
                    storedValues.get(4), storedValues.get(5));

            return loot;
        } catch (IOException i) {
            logger.log(Level.WARNING, "Failed to read save file, doesnt exist", i);
            return new Loot();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to read save file!", e);
            return new Loot();
        }
    }

    public static void deleteXML(final String bossName) {
        File file = new File(bossName.replaceAll(" ", "") + saveFilePath);
        file.delete();
    }

    /**
     * Saves XML
     *
     * @param doc document
     */
    private static void saveXML(final Document doc, final String bossName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(bossName.replaceAll(" ", "") + saveFilePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to save XML!", e);
        }
    }
}