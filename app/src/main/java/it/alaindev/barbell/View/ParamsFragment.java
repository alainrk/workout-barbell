package it.alaindev.barbell.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import it.alaindev.barbell.R;
import it.alaindev.barbell.SecureConst;
import it.alaindev.barbell.User;

import com.rey.material.widget.EditText;
import com.rey.material.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ParamsFragment extends Fragment {

    private OnParamsFragInteractionListener mListener;
    ListView lv;

    public ParamsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static ParamsFragment newInstance() {
        ParamsFragment fragment = new ParamsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_params, container, false);

        lv = (ListView) view.findViewById(R.id.paramslv);

        getUserInfo();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnParamsFragInteractionListener) {
            mListener = (OnParamsFragInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnParamsFragInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnParamsFragInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String s);
    }

    private void getUserInfo()  {
        final FirebaseUser fb_user = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference(SecureConst.FIREBASE_USERS);

        // Check if there is this user in firebase DB
        ref.orderByChild(SecureConst.FIREBASE_USER_UID)
                .equalTo(fb_user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data: dataSnapshot.getChildren()) {
                            User user = data.getValue(User.class);
                            fillUI(user);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void fillUI (User user) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        // TODO fill with couples <string, string> of user's data
        // TODO fill UI with DescAndValAdapter
    }
}
