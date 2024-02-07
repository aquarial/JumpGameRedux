package main;

import javax.swing.*;

import mainmenu.MainMenu;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }

}
