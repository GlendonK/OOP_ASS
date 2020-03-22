import com.google.firebase.database.*;
import com.google.firebase.internal.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public class CallDatabase {
    private String src;
    private String words;
    private String cost;
    private String link;
    public int count = 0;
    public static ArrayList<Float> floatarry;
    private DatabaseReference reference;
    public static ArrayList<String> headerArrayList;
    public static ArrayList<String> priceArrayList;
    public static ArrayList<String> linkArrayList;
    public static ArrayList<String> sourceArrayList;

    public HashMap<Integer, ExploreModelClass> dataMap;

    public CallDatabase(){

    }


    public ArrayList<Float> query(String location) throws Exception {
        DatabaseHandler adder = new DatabaseHandler(); // call DatabaseHandler to get methods to push and trigger firebase

        floatarry = new ArrayList<Float>();
        headerArrayList = new ArrayList<String>();
        priceArrayList = new ArrayList<String>();
        linkArrayList = new ArrayList<String>();
        sourceArrayList = new ArrayList<String>();
        dataMap = new HashMap<Integer, ExploreModelClass>();

        reference = FirebaseDatabase.getInstance().getReference().child(location);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot eachData : dataSnapshot.getChildren()) { // datasnapshot contains all the data from the database.
                        ExploreModelClass dataStore = eachData.getValue(ExploreModelClass.class); // store each datasnapshot into ExploreModelClass

                        dataMap.put(count, dataStore); //add data into hashmap

                        if(dataMap.size() <= 300){ // filter for the first 300 results
                            floatarry.add(Float.parseFloat(dataMap.get(count).getprice())); // get price from string into float
                            headerArrayList.add(dataMap.get(count).getheader()); // get all header
                            priceArrayList.add(dataMap.get(count).getprice()); //get all price strings
                            linkArrayList.add(dataMap.get(count).getlink()); // get all link
                            sourceArrayList.add(dataMap.get(count).getsource()); // get all source
                        }
                        count++;

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error in Query");
            }

        });

        ///////////////////Change value in database to trigger the data callback/////////////////////
        try {
            words = "QUERY"; // for triggering query
            cost = "-99999";
            link = "QUERYLINK";
            src = "QUERY";


            System.out.println(words);
            cost = cost.replace("S$", ""); // remove dollar sign
            System.out.println(cost);
            System.out.println(link);
            adder.triggerDatabase(words, cost, src, link, location); // trigger database

        } catch (Exception e) {
            System.out.println("Error in query");
        }

        return floatarry;

    }
}