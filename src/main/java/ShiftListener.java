import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ShiftListener implements NativeKeyListener {

    private SecondGUI secondGUI;

    public ShiftListener(final SecondGUI secondGUI){
        this.secondGUI = secondGUI;
    }



    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //do nothing
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(nativeKeyEvent.getKeyCode() == 42) {
            System.out.println("do shift things");
            secondGUI.killIncrement();
        }


    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        //do nothing
    }
}
