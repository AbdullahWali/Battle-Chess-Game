import UserInterface.GameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.

        GameFrame frame = new GameFrame();
        frame.setTitle("Chess++");
        frame.setIconImage(new ImageIcon(Main.class.getClass().getResource("/assets/chess.png")).getImage());

    }
}
