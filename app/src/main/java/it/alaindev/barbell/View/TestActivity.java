package it.alaindev.barbell.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.snapshot.BooleanNode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import it.alaindev.barbell.Exercise;
import it.alaindev.barbell.R;

public class TestActivity extends AppCompatActivity {

    private String TAG = "TestActivity";
    private FirebaseUser user;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView tv = (TextView) findViewById(R.id.textView);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        DatabaseReference ref_exercises = database.getReference("exercises");

//        tv.setText("Ciao "+user.getDisplayName()+" "+user.getEmail()+" "+user.getProviderId()+"!!!");

        ref_exercises
                .orderByChild("biglift")
                .equalTo(true)
                .addValueEventListener(new ValueEventListener() {
            @Override
            // This method is called once with the initial value and again
            // whenever data at this location is updated
            public void onDataChange(DataSnapshot dataSnapshot) {
                String res = "";
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Exercise ex = postSnapshot.getValue(Exercise.class);
                    res += ex.getName() + " " + ex.getType() + "\n";
                }
                try {tv.setText(res);}
                catch (Exception e) {Log.d(TAG,"");}
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //addExercise(2,true,"qwerty");

//        Map<String, Exercise> exs = new HashMap<>();
//        exs.put("", new Exercise(2,true,"qwerty"));
//        exs.put("", new Exercise(3,false,"zxcvb"));
//
//        ref_exercises.setValue(exs);


    }

    private void addExercise(int type, boolean biglift, String name) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        DatabaseReference ref_exercises = database.getReference("exercises");
        String key = ref_exercises.push().getKey();
        Exercise newex = new Exercise(2,true,"qwerty");
        Map<String, Object> postValues = newex.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, postValues);

        ref_exercises.updateChildren(childUpdates);
    }

}
