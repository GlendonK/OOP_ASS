import org.jfree.ui.RefineryUtilities;
import java.awt.Desktop;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class MilkPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public MilkPage() throws Exception {
        getContentPane().setBackground(Color.LIGHT_GRAY);
        getContentPane().setLayout(null);

        String[] choice = {"Carousell","Qoo10","Rakuten"}; // dropdown list
        JComboBox<?> field1 = new JComboBox<Object>(choice);
        field1.setSelectedIndex(0);
        field1.setBounds(800, 725, 126, 21);
        getContentPane().add(field1);

        JButton btnNewButton = new JButton("Generate"); // button to do stuff
        btnNewButton.addActionListener(new ActionListener() { // performs a certain action when interacted
            public void actionPerformed(ActionEvent a) {
                String msg = field1.getSelectedItem().toString();
                switch(msg) {
                    case "Carousell":
                        try {
                            HistoItems histoSocks = new HistoItems("Milk", "MilkCarousell", "MilkQoo10", "MilkRakuten"); // generates graph
                            histoSocks.pack();
                            RefineryUtilities.centerFrameOnScreen(histoSocks);
                            histoSocks.setVisible(true);

                            final NormalDist normal = new NormalDist("MilkCarousell","Socks", "MilkCarousell"); // generates graph
                            normal.pack();
                            RefineryUtilities.centerFrameOnScreen(normal);
                            normal.setVisible(true);
                            break;

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                    case "Qoo10":
                        try {
                            HistoItems histoSocks = new HistoItems("Milk", "MilkCarousell", "MilkQoo10", "MilkRakuten");
                            histoSocks.pack();
                            RefineryUtilities.centerFrameOnScreen(histoSocks);
                            histoSocks.setVisible(true);

                            final NormalDist normal = new NormalDist("MilkQoo10","Socks", "MilkQoo10");
                            normal.pack();
                            RefineryUtilities.centerFrameOnScreen(normal);
                            normal.setVisible(true);
                            break;

                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }


                    case "Rakuten":
                        try {
                            HistoItems histoSocks = new HistoItems("Milk", "MilkCarousell", "MilkQoo10", "MilkRakuten");
                            histoSocks.pack();
                            RefineryUtilities.centerFrameOnScreen(histoSocks);
                            histoSocks.setVisible(true);

                            final NormalDist normal = new NormalDist("MilkRakuten","Socks", "MilkRakuten");
                            normal.pack();
                            RefineryUtilities.centerFrameOnScreen(normal);
                            normal.setVisible(true);
                            break;

                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }

                }
            }
        });

        btnNewButton.setBounds(800, 750, 126, 21);
        getContentPane().add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel(new ImageIcon("Price-icon.png")); // to produce the image icon for our application
        lblNewLabel_2.setBounds(80, 250, 300, 300);
        getContentPane().add(lblNewLabel_2);


        CallDatabase callCarousell = new CallDatabase(); // instantiate the calldatabase object
        CallDatabase callQoo10 = new CallDatabase();
        CallDatabase callRakuten = new CallDatabase();

        DefaultTableModel model = new DefaultTableModel(0, 0); // creates a reference for the table
        model.addColumn("Title");
        model.addColumn("Price");
        model.addColumn("Url");
        model.addColumn("Source");
        DefaultTableModel model2 = new DefaultTableModel(0, 0);
        model2.addColumn("Source");
        model2.addColumn("Mean");
        model2.addColumn("SD");
        model2.addColumn("Median");
        callCarousell.query("MilkCarousell"); // call method for query

        Calculations cal = new Calculations(callCarousell.floatarry); // instantiate to get the filtered arraylist

        for(int j = 0; j< CallDatabase.headerArrayList.size(); j++){
            if(CallDatabase.floatarry.get(j) > cal.getLower() && CallDatabase.floatarry.get(j) < cal.getUpper()) {
                model.addRow(new Object[]{CallDatabase.headerArrayList.get(j), "$" + CallDatabase.priceArrayList.get(j), CallDatabase.linkArrayList.get(j), CallDatabase.sourceArrayList.get(j)});
            } // loop through to get all data of the database to input into the table

        }


        Calculations formulaCarousell = new Calculations(CallDatabase.floatarry); // instantiate to get the filtered array list for calculations
        model2.addRow(new Object[] {"Carousell",formulaCarousell.getMean(),formulaCarousell.getSD(),formulaCarousell.getMedian()});
        callQoo10.query("MilkQoo10");

        Calculations cal1 = new Calculations(callQoo10.floatarry); // instantiate to get the filtered array list for calculations

        for(int j = 0; j< CallDatabase.headerArrayList.size(); j++) {
            if (CallDatabase.floatarry.get(j) > cal1.getLower() && CallDatabase.floatarry.get(j) < cal1.getUpper()) {
                model.addRow(new Object[]{CallDatabase.headerArrayList.get(j), "$" + CallDatabase.priceArrayList.get(j), CallDatabase.linkArrayList.get(j), CallDatabase.sourceArrayList.get(j)});
            } // loop through to get all data of the database to input into the table

        }
        Calculations formulaQoo10 = new Calculations(CallDatabase.floatarry); // instantiate to get the filtered array list for calculations
        model2.addRow(new Object[] {"Qoo10",formulaQoo10.getMean(),formulaQoo10.getSD(),formulaQoo10.getMedian()});
        callRakuten.query("MilkRakuten");

        Calculations cal2 = new Calculations(callRakuten.floatarry); // instantiate to get the filtered array list for calculations
        for(int j = 0; j< CallDatabase.headerArrayList.size(); j++) {
            if (CallDatabase.floatarry.get(j) > cal2.getLower() && CallDatabase.floatarry.get(j) < cal2.getUpper()){
                model.addRow(new Object[]{CallDatabase.headerArrayList.get(j), "$" + CallDatabase.priceArrayList.get(j), CallDatabase.linkArrayList.get(j), CallDatabase.sourceArrayList.get(j)});
            } // loop through to get all data of the database to input into the table

        }
        Calculations formulaRakuten = new Calculations(CallDatabase.floatarry); // instantiate to get the filtered array list for calculations
        model2.addRow(new Object[] {"Rakuten",formulaRakuten.getMean(),formulaRakuten.getSD(),formulaRakuten.getMedian()});



        JTable table = new JTable(model){ //creates table
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        table.addMouseListener(new MouseAdapter() { // allow links to be clickable
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                String link = model.getValueAt(row, col).toString();

                //build your address / link

                URI uri;
                try {
                    uri = new URI(link);
                    open(uri);
                } catch (URISyntaxException e1) {
                    System.out.println("Please Click The Link");
                }

                //see below

            }

            private void open(URI uri) { // method to open url links
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(uri);
                    } catch (IOException e){
                        System.out.println("Please Click The Link");
                    }
                } else{
                    System.out.println("Error Opening");
                }
            }
        });



        JScrollPane scrollPane = new JScrollPane(table); // to make table scrollable
        scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        JLabel lblNewLabel = new JLabel("Generate graph -->");
        lblNewLabel.setBounds(680, 750, 126, 21);
        getContentPane().add(lblNewLabel);
        table.setFillsViewportHeight(true);
        scrollPane.setBounds(374, 108, 1000, 600);
        getContentPane().add(scrollPane);





        JTable table1 = new JTable(model2){ // creates another table
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        JScrollPane scrollPane2 = new JScrollPane(table1);
        scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane2.setBounds(1550, 300, 300, 100); //change the value here to see where you want place it.
        getContentPane().add(scrollPane2);



    }
}
