import UserInterface.GameFrame;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.

        GameFrame frame = new GameFrame();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
