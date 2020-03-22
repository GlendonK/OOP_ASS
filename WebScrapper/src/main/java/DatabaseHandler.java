import com.google.firebase.database.*;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class DatabaseHandler{

    private HashMap<String,ExploreModelClass> dataMap;

    public DatabaseHandler() {
        super();
    }
    public void pushDatabase(String header, String  price, String source, String link, String location){
        DatabaseReference databaseReference;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child(location).push(); // referencing to the database location & .push() make unique id for each entry
        dataMap = new HashMap<>();
        dataMap.put(header, new ExploreModelClass(price, header, source, link)); // header is used as key, ExploreModelClass for value which hold the price, header, source, link of the data crawled.
        data.setValueAsync(dataMap.get(header)); // put hashmap data into firebase
    }

    public void triggerDatabase(String header, String  price, String source, String link, String location) throws ExecutionException, InterruptedException { // method to trigger eventlistener to get values from firebase
        DatabaseReference databaseReference;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child(location).push(); // referencing to the database location & .push() make unique id for each entry
        DatabaseReference data1 = FirebaseDatabase.getInstance().getReference().child(location); // referencing to the database location & .push() make unique id for each entry
        String uid = data.getKey(); // unique id to reference to the data tha have been added
        System.out.println(uid);
        data.child("header").setValueAsync(header).get(); // push into Database
        data.child("price").setValueAsync(price).get();
        data.child("source").setValueAsync(source).get();
        data.child("link").setValueAsync(link).get();
        data1.child(uid).child("header").removeValueAsync().get(); // remove data from database
        data1.child(uid).child("price").removeValueAsync().get();
        data1.child(uid).child("source").removeValueAsync().get();
        data1.child(uid).child("link").removeValueAsync().get();
    }
}
