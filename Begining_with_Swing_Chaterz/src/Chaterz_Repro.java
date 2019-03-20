import javax.swing.*;

import static javafx.application.Application.launch;

public class Chaterz_Repro extends JFrame {
    private JButton boton;
    static private final String newline = "\n";
    private static JButton openButton;
    JButton  saveButton;
    JTextArea log;
    JFileChooser fc;

    private static void chaterz() {

        JFrame chaterz = new JFrame("Reproductor de Musica");
        chaterz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //chaterz.add(new Chaterz_Repro());
        JLabel label0 = new JLabel("******************************");
        JLabel label1 = new JLabel("Play your Favourite Song Here");
        JLabel label2 = new JLabel("Play ♫ | Stop █ | Next «");

        chaterz.getContentPane().add(label0);
        chaterz.getContentPane().add(label1);
        chaterz.getContentPane().add(label2);

        //Display the window.
        chaterz.pack();
        chaterz.setVisible(true);


        /*****************/

    }

    public static void main(String[] args) {

        //boton = new JButton();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                chaterz();
            }
        });
        launch(args);
    }




}