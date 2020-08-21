package ResourceHandlers;

import gui.SecondGUI;
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

        if(nativeKeyEvent.getKeyCode() == 42 || nativeKeyEvent.getKeyCode() == 3638) {
            System.out.println("do shift things");
            secondGUI.killIncrement();
        }
        if(nativeKeyEvent.getKeyCode() == 56){
            System.out.println("reversing shift things");
            secondGUI.killDecrement();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        //do nothing
    }
}
