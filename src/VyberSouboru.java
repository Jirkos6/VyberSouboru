import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class VyberSouboru extends JFrame {
    private JButton btn;
    private JTextArea textArea;

    private JPanel mainPanel;

    private JMenuBar menuBar;

    private JMenu menu;

    private JMenuItem menuItem;



    public VyberSouboru() {
        btn.addActionListener(new ActionListener()
        {
            @Override

            public void actionPerformed(ActionEvent e)

            {
                textArea.setText("");

                vyberSouboru();
            }
        });
    }
    public void vyberSouboru()

    {

        JFileChooser fileChooser = new JFileChooser(".");

        int result = fileChooser.showOpenDialog(this);

        if (result == fileChooser.APPROVE_OPTION){

            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String radek = reader.readLine();

                while(radek != null){

                    textArea.append(radek + "\n");

                    radek = reader.readLine();
                }
            } catch (FileNotFoundException e) {


                throw new RuntimeException(e);


            } catch (IOException e) {


                JOptionPane.showMessageDialog(null, "Vybraný soubor se nedá přečíst!");

            }
        }else {
            textArea.setText("Žádný soubor nebyl vybrán!");
        }
    }
    public static void main(String[] args) {

        VyberSouboru gui = new VyberSouboru();
        JMenuBar menuBar = new JMenuBar();
        gui.setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Otevřít soubor");
        menu.add(menuItem);
        menuItem.addActionListener(e-> gui.vyberSouboru());
        gui.setContentPane(gui.mainPanel);
        gui.pack();
        gui.setTitle("Filechooser");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);

    }

}