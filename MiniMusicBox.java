//Importing all necessary libraries and components
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

class MiniMusicBox {

    static JFrame frame = new JFrame("Mini music box");
    static GraphPanel panel;//place for panel subclas, after creation

    public static void main(String[] args) {
        MiniMusicBox minibox = new MiniMusicBox(); //createing new object MiniMusicBox
        minibox.getMusic(); //calling getMusic() method
    }

    //Method for setting GUI
    public void setGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GraphPanel();
        frame.setContentPane(panel);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
    }

    public void getMusic() {
        setGUI();

            //Creating sequencer and sequenc with track, trying to catch possible exception
        try {
            Sequencer sequencer = MidiSystem.getSequencer(); //creating sequencer
            sequencer.open(); //opening sequencer
            sequencer.addControllerEventListener(panel, new int[] {127}); //adding controller event listener
            Sequence seq = new Sequence(Sequence.PPQ, 4); //creating new Sequence objecy
            Track track = seq.createTrack(); //Setting track.

            //for loop for creating random sound event
            int r = 0;
            for (int i = 5; i < 60; i += 4) {
                r = (int) ((Math.random() * 50) + 1);
                track.add(createEvent(144, 1, r, 100, i));
                track.add(createEvent(176, 1, 127, 0, i));
                track.add(createEvent(128, 1, r, 100, i + 2));
            }

            //activating sequencer
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static MidiEvent createEvent(int plc, int chnl, int first, int second, int tact) {
        MidiEvent event = null;
        try {
            ShortMessage m = new ShortMessage();
            m.setMessage(plc, chnl, first, second);
            event = new MidiEvent(m, tact);
        } catch (Exception ex) {}
        return event;
    }

    class GraphPanel extends JPanel implements ControllerEventListener {

    }
}

