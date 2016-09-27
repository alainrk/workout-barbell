package it.alaindev.barbell.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import it.alaindev.barbell.Exercise;
import it.alaindev.barbell.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ParamsFragment.OnFragmentInteractionListener {

    private FirebaseUser user;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                        try {
                            Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e) {}
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_exercises) {
            ExercisesFragment exercisesFragment = new ExercisesFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.contentMain_forFragments,
                    exercisesFragment,
                    exercisesFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_workouts) {
            WorkoutsFragment workoutsFragment = WorkoutsFragment.newInstance("Ciao","merda");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.contentMain_forFragments,
                    workoutsFragment,
                    workoutsFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_params) {
            ParamsFragment paramsFragment = ParamsFragment.newInstance("Porco","dio");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.contentMain_forFragments,
                    paramsFragment,
                    paramsFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
