package testing;

import javax.swing.SwingUtilities;
import mainmenu.MainMenu;

public class MainTester {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                new MainMenu();
                
            }
        });
    }
}
