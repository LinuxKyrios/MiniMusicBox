//Importing all necessary libraries and components
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

class MiniMusicBox {

    static JFrame frame = new JFrame("Mini music box");
    static GraphPanel panel;//place for panel subclas, after creation

    public static void main(String[] args) {
        MiniMusicBox minibox = new MiniMusicBox();
        minibox.getMusic();
    }

    public void setGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GraphPanel();
        frame.setContentPane(panel);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
    }
}