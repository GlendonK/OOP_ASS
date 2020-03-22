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

public class SocksPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public SocksPage() throws Exception {
        getContentPane().setBackground(Color.LIGHT_GRAY); // sets background theme
        getContentPane().setLayout(null);

        String[] choice = {"Carousell","Qoo10","Rakuten"}; // dropdown list content
        JComboBox<?> field1 = new JComboBox<Object>(choice);  //creates drop down list
        field1.setSelectedIndex(0); //configs drop down list visual settings
        field1.setBounds(800, 725, 126, 21);
        getContentPane().add(field1);

        JButton btnNewButton = new JButton("Generate"); // button to do stuff
        btnNewButton.addActionListener(new ActionListener() { // do stuff when action is performed
            public void actionPerformed(ActionEvent a) {
                String msg = field1.getSelectedItem().toString();
                switch(msg) {
                    case "Carousell":
                        try {
                            HistoItems histoSocks = new HistoItems("Socks", "SocksCarousell", "SocksQoo10", "SocksRakuten"); // generates graph
                            histoSocks.pack();
                            RefineryUtilities.centerFrameOnScreen(histoSocks);
                            histoSocks.setVisible(true);

                            final NormalDist normal = new NormalDist("SocksCarousell","Socks", "SocksCarousell");
                            normal.pack();
                            RefineryUtilities.centerFrameOnScreen(normal);
                            normal.setVisible(true);
                            break;

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                    case "Qoo10":
                        try {
                            HistoItems histoSocks = new HistoItems("Socks", "SocksCarousell", "SocksQoo10", "SocksRakuten"); // generate graph
                            histoSocks.pack();
                            RefineryUtilities.centerFrameOnScreen(histoSocks);
                            histoSocks.setVisible(true);

                            final NormalDist normal = new NormalDist("SocksQoo10","Socks", "SocksQoo10");
                            normal.pack();
                            RefineryUtilities.centerFrameOnScreen(normal);
                            normal.setVisible(true);
                            break;

                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }


                    case "Rakuten":
                        try {
                            HistoItems histoSocks = new HistoItems("Socks", "SocksCarousell", "SocksQoo10", "SocksRakuten"); // generate graph
                            histoSocks.pack();
                            RefineryUtilities.centerFrameOnScreen(histoSocks);
                            histoSocks.setVisible(true);

                            final NormalDist normal = new NormalDist("SocksRakuten","Socks", "SocksRakuten");
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

        JLabel lblNewLabel_2 = new JLabel(new ImageIcon("Price-icon.png")); // image icon for the page
        lblNewLabel_2.setBounds(80, 250, 300, 300);
        getContentPane().add(lblNewLabel_2);




        CallDatabase callCarousell = new CallDatabase(); // instantiate object
        CallDatabase callQoo10 = new CallDatabase();
        CallDatabase callRakuten = new CallDatabase();

        DefaultTableModel model = new DefaultTableModel(0, 0);// create a model for the table
        model.addColumn("Title"); //creates the headers for the tables
        model.addColumn("Price");
        model.addColumn("Url");
        model.addColumn("Source");
        DefaultTableModel model2 = new DefaultTableModel(0, 0); // another model for another table
        model2.addColumn("Source");
        model2.addColumn("Mean");
        model2.addColumn("SD");
        model2.addColumn("Median");
        callCarousell.query("SocksCarousell"); // call method

        Calculations cal = new Calculations(callCarousell.floatarry); // instantiate object

        for(int j = 0; j< CallDatabase.headerArrayList.size(); j++){
            if(CallDatabase.floatarry.get(j) > cal.getLower() && CallDatabase.floatarry.get(j) < cal.getUpper()) {
                model.addRow(new Object[]{CallDatabase.headerArrayList.get(j), "$" + CallDatabase.priceArrayList.get(j), CallDatabase.linkArrayList.get(j), CallDatabase.sourceArrayList.get(j)});
            } // loop through to get values of carousell socks for table
        }
        Calculations formulaCarousell = new Calculations(CallDatabase.floatarry);
        model2.addRow(new Object[] {"Carousell",formulaCarousell.getMean(),formulaCarousell.getSD(),formulaCarousell.getMedian()});
        callQoo10.query("SocksQoo10"); // call method

        Calculations cal1 = new Calculations(callQoo10.floatarry);
        for(int j = 0; j< CallDatabase.headerArrayList.size(); j++) {
            if (CallDatabase.floatarry.get(j) > cal1.getLower() && CallDatabase.floatarry.get(j) < cal1.getUpper()){
                model.addRow(new Object[]{CallDatabase.headerArrayList.get(j), "$" + CallDatabase.priceArrayList.get(j), CallDatabase.linkArrayList.get(j), CallDatabase.sourceArrayList.get(j)});
            }// loop through to get values of qoo socks for table
        }
        Calculations formulaQoo10 = new Calculations(CallDatabase.floatarry);
        model2.addRow(new Object[] {"Qoo10",formulaQoo10.getMean(),formulaQoo10.getSD(),formulaQoo10.getMedian()});
        callRakuten.query("SocksRakuten");

        Calculations cal2 = new Calculations(callRakuten.floatarry); // call method
        for(int j = 0; j< CallDatabase.headerArrayList.size(); j++){
            if(CallDatabase.floatarry.get(j) > cal2.getLower() && CallDatabase.floatarry.get(j) < cal2.getUpper()) {
                model.addRow(new Object[]{CallDatabase.headerArrayList.get(j), "$" + CallDatabase.priceArrayList.get(j), CallDatabase.linkArrayList.get(j), CallDatabase.sourceArrayList.get(j)});
            }// loop through to get values of rakuten socks for table
        }
        Calculations formulaRakuten = new Calculations(CallDatabase.floatarry);
        model2.addRow(new Object[] {"Rakuten",formulaRakuten.getMean(),formulaRakuten.getSD(),formulaRakuten.getMedian()});



        JTable table = new JTable(model){ // create table
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        table.addMouseListener(new MouseAdapter() { // allow links to be clickable with mouse interaction for table.
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

            private void open(URI uri) { // method to link to url
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



        JScrollPane scrollPane = new JScrollPane(table); // change the table to be scrollable
        scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        JLabel lblNewLabel = new JLabel("Generate graph -->");
        lblNewLabel.setBounds(680, 750, 126, 21);
        getContentPane().add(lblNewLabel);
        table.setFillsViewportHeight(true);
        scrollPane.setBounds(374, 108, 1000, 600);
        getContentPane().add(scrollPane);





        JTable table1 = new JTable(model2){
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