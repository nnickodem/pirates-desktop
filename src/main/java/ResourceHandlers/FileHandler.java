package ResourceHandlers;

import Enum.Rarity;
import Enum.ChestType;
import dto.Boss;
import dto.Loot;
import dto.Session;
import java.util.HashMap;
import java.util.Map;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NodeList;


/**
 * Handles reading/writing of various external resources including save files, level files, and fonts
 */
public class FileHandler {

    private static final Logger logger = Logger.getLogger("errorLogger");
    private static final String saveFilePath = ".xml";

    /**
     * Writes a new empty save file xml
     */
    public static void writeSaveFile(final String userName, final Boss boss) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement(boss.getName().replaceAll(" ", ""));
            document.appendChild(root);

            Element user = document.createElement("user");
            user.appendChild(document.createTextNode(userName));
            root.appendChild(user);
            Element bossName = document.createElement("bossName");
            bossName.appendChild(document.createTextNode(boss.getName()));
            root.appendChild(bossName);
            Element bossId = document.createElement("bossId");
            bossId.appendChild(document.createTextNode(String.valueOf(boss.getId())));
            root.appendChild(bossId);
            for(ChestType c : ChestType.values()) {
                Element chest = document.createElement("chest_" + c.getName());
                root.appendChild(chest);
                Element kc = document.createElement("kills");
                kc.appendChild(document.createTextNode("0"));
                chest.appendChild(kc);
                for (Rarity r : Rarity.values()) {
                    Element rarity = document.createElement(r.getName());
                    rarity.appendChild(document.createTextNode("0"));
                    chest.appendChild(rarity);
                }
            }

            saveXML(document, boss.getName());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to write save file!", e);
        }
    }


    public static void updateSave(final String userName, final Boss boss, final String chestType, final String name, final String value) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(boss.getName().replaceAll(" ", "") + saveFilePath);

            NodeList nodeList = doc.getElementsByTagName("chest_" + chestType).item(0).getChildNodes();
            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeName().equals(name)) {
                    node.setTextContent(value);
                }
            }

            saveXML(doc, boss.getName());
        } catch (IOException i) {
            logger.log(Level.WARNING, "Failed to read save file, attempting to write one...", i);
            writeSaveFile(userName, boss);
            updateSave(userName, boss, chestType, name, value);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update save file!", e);
        }
    }

    /**
     * Gets all save information
     *
     * @return List<String> where each entry is a level result
     */
    public static Session getSave(final String user, final Boss boss) {
        try {
            File fXmlFile = new File(boss.getName().replaceAll(" ", "") + saveFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Map<ChestType, Loot> lootMap = new HashMap<>();
            for(ChestType chestType : ChestType.values()) {
                Integer kc = 0;
                Integer crude = 0;
                Integer common = 0;
                Integer rare = 0;
                Integer famed = 0;
                Integer legendary = 0;
                NodeList nodeList = doc.getElementsByTagName("chest_" + chestType.getName()).item(0).getChildNodes();
                for(int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if(node.getNodeName().equals("kills")) {
                        kc = Integer.valueOf(node.getTextContent());
                    } else {
                        Rarity rarity = Rarity.valueOf(node.getNodeName().toUpperCase());
                        switch (rarity) {
                            case CRUDE:
                                crude = Integer.valueOf(node.getTextContent());
                                break;
                            case COMMON:
                                common = Integer.valueOf(node.getTextContent());
                                break;
                            case RARE:
                                rare = Integer.valueOf(node.getTextContent());
                                break;
                            case FAMED:
                                famed = Integer.valueOf(node.getTextContent());
                                break;
                            case LEGENDARY:
                                legendary = Integer.valueOf(node.getTextContent());
                                break;
                            default:
                                //do nothing
                        }
                    }
                }
                Loot loot = new Loot(kc, crude, common, rare, famed, legendary);
                lootMap.put(chestType, loot);
            }

            Session session = new Session(user, boss, lootMap);

            return session;
        } catch (IOException i) {
            logger.log(Level.WARNING, "Failed to read save file, doesnt exist", i);
            return new Session(user, boss);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to read save file!", e);
            return new Session(user, boss);
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