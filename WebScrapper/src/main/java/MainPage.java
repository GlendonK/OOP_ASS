import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;


public class MainPage {

    private JFrame frame;

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    MainPage window = new MainPage(); // start up the jframe window
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    window.frame.setBounds(0,0,screenSize.width, screenSize.height);
                    window.frame.setLocationRelativeTo(null);
                    window.frame.setVisible(true);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }



    public MainPage() {
        initialize(); // run initialize method below
    }


    private void initialize() {
        frame = new JFrame(); // instantiate the frame , setting the settings for the frames
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setBounds(100, 100, 965, 512);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        String[] choice = {"Socks","Diapers","Milk"}; // drop down list selection choices
        JComboBox<?> field1 = new JComboBox<Object>(choice); // drop down list
        field1.setSelectedIndex(0); // default selection for dropdownlist
        field1.setBounds(134, 245, 87, 21);
        frame.getContentPane().add(field1);
        field1.setBounds(753, 440, 116, 28);
        frame.getContentPane().add(field1);

        JLabel lblNewLabel = new JLabel("Please Select Category"); // a field label
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(591, 447, 163, 13);
        frame.getContentPane().add(lblNewLabel);

        JLabel textField = new JLabel("Loading might take awhile. Please Wait."); // field text
        textField.setBounds(650, 550, 330, 19);
        frame.getContentPane().add(textField);

        JButton btnNewButton = new JButton("Enter"); // button
        btnNewButton.setBounds(902, 444, 85, 21);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() { // action listener to perform certain actions when interacted
            public void actionPerformed(ActionEvent e) {

                String msg = field1.getSelectedItem().toString();
                switch(msg) { // switch case depending on selection of drop down list
                    case "Socks":
                        SocksPage socks = null;
                        try {

                            socks = new SocksPage(); // creates a new frame to go to next page
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get screen size
                            socks.setBounds(500,0,screenSize.width, screenSize.height); // set screensize to fitscreen
                            socks.setLocationRelativeTo(null);
                            socks.setVisible(true);

                        } catch (Exception ex) {

                            ex.printStackTrace();

                        }
                        socks.setVisible(true);
                        break;

                    case "Diapers":
                        DiapersPage diapers = null;
                        try {

                            diapers = new DiapersPage();
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            diapers.setBounds(0,0,screenSize.width, screenSize.height);
                            diapers.setVisible(true);

                        } catch (Exception ex) {

                            ex.printStackTrace();

                        }
                        diapers.setVisible(true);
                        break;
                    case "Milk":
                        MilkPage milk = null;
                        try {

                            milk = new MilkPage();
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            milk.setBounds(0,0,screenSize.width, screenSize.height);
                            milk.setVisible(true);

                        } catch (Exception ex) {

                            ex.printStackTrace();

                        }
                        milk.setVisible(true);
                        //frame.dispose();
                        break;
                }
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Welcome to GIA Cost Comparison");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
        lblNewLabel_1.setBounds(568, 340, 513, 103);
        frame.getContentPane().add(lblNewLabel_1);


        JLabel lblNewLabel_2 = new JLabel(new ImageIcon("Price-icon.png"));
        lblNewLabel_2.setBounds(611, 70, 300, 300);
        frame.getContentPane().add(lblNewLabel_2);

    }
}