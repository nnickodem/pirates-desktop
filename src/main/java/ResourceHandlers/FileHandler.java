package ResourceHandlers;

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
    private static final List<String> rarities = Arrays.asList("crude", "rare", "famed", "legendary");

    /**
     * Writes a new empty save file xml
     */
    public static void writeSaveFile(final String bossName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement(bossName);
            document.appendChild(root);


            for (String r : rarities) {
                Element rarity = document.createElement("Rarity_" + r);
                rarity.appendChild(document.createTextNode("0"));
                root.appendChild(rarity);
            }

            saveXML(document, bossName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to write save file!", e);
        }
    }


    public static void updateSave(final String rarity, final String result, final String bossName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(bossName + saveFilePath);

            Node level = doc.getElementsByTagName("Rarity_" + rarity).item(0);
            level.setTextContent(result);

            saveXML(doc, bossName);
        } catch (IOException i) {
            logger.log(Level.WARNING, "Failed to read save file, attempting to write one...", i);
            writeSaveFile(bossName);
            updateSave(rarity, result, bossName);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update save file!", e);
        }
    }

    /**
     * Gets all save information
     *
     * @return List<String> where each entry is a level result
     */
    public static List<String> getSave(final String bossName) {
        try {
            File fXmlFile = new File(bossName + saveFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            List<String> levelStatus = new ArrayList<>();
            for (String r : rarities) {
                Node level = doc.getElementsByTagName("Rarity_" + r).item(0);
                levelStatus.add(level.getTextContent());
            }

            return levelStatus;
        } catch (IOException i) {
            logger.log(Level.WARNING, "Failed to read save file, attempting to write one...", i);
            writeSaveFile(bossName);
            return getSave(bossName);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to read save file!", e);
            return null;
        }
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
            StreamResult result = new StreamResult(new File(bossName + saveFilePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to save XML!", e);
        }
    }
}