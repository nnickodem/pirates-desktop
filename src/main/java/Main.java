import ResourceHandlers.FileHandler;
import ResourceHandlers.LogHandler;
import dto.Boss;
import dto.Session;
import gui.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import Enum.ChestType;
import Enum.Rarity;

public class Main {
    private static final Logger logger = Logger.getLogger("errorLogger");

    public static void main(String[] args){
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> logger.log(Level.SEVERE, "Uncaught exception", new Exception(e)));
        LogHandler.setup();

/*        Boss boss = new Boss(1, "test");
        Session session = new Session("testMe", boss);
        session.alter(2, ChestType.LOOT_CHEST, Rarity.FAMED);
        session.alter(1, ChestType.SKULL_CHEST, Rarity.LEGENDARY);
        session.alter(5, ChestType.LOOT_POUCH, Rarity.COMMON);
        session.alter(10, ChestType.NONE, null);


        Session session1 = FileHandler.getSave("testMe", boss);*/

        new MainFrame();
    }
}
