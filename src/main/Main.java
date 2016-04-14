package main;

import javax.swing.SwingUtilities;
import mainmenu.MainMenu;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }

}
