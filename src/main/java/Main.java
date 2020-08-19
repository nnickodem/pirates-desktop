import ResourceHandlers.LogHandler;
import gui.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger("errorLogger");

    public static void main(String[] args){
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> logger.log(Level.SEVERE, "Uncaught exception", new Exception(e)));
        LogHandler.setup();
        new MainFrame();
    }
}
