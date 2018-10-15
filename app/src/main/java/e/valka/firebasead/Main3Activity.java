package e.valka.firebasead;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        database = FirebaseDatabase.getInstance ();
        fetchData();
    }
    private void fetchData () {
        ArrayList<Song> songsList = new ArrayList<>();
        DatabaseReference root = database.getReference ();
        DatabaseReference songs = root.child ("songs");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item: dataSnapshot.getChildren ()) {
                    Song s = item.getValue (Song.class);
                    Log.w ("PKAT",""+item.getValue());
                    songsList.add(s);
                };
                SongsFragment fragment = new SongsFragment();
                fragment.setArray(songsList);
                getSupportFragmentManager ()
                        .beginTransaction ()
                        .replace (R.id.container,fragment)
                        .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit ();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError databaseError) {
                Log.w ("PKAT", databaseError.toException ());
            }

        };

        songs.addListenerForSingleValueEvent (valueEventListener);
    }
}
