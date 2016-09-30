package it.alaindev.barbell.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
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

import it.alaindev.barbell.Constants;
import it.alaindev.barbell.DescValAdapter;
import it.alaindev.barbell.R;
import it.alaindev.barbell.SecureConst;
import it.alaindev.barbell.User;
import it.alaindev.barbell.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        ArrayList<Map.Entry<String, String>> al = user.toMapEntryList();

        DescValAdapter adapter = new DescValAdapter(getActivity(), al);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    final Map.Entry<String, String> item = (Map.Entry<String, String>) parent.getItemAtPosition(position);

                    // TODO switch to discriminate what to do and what kind of type show in the dialog to edit

//                    int type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
                    int type = InputType.TYPE_NUMBER_FLAG_DECIMAL;

                    new MaterialDialog.Builder(getActivity())
                            .title("Daje aho!!")
                            .content(item.getKey()+" "+item.getValue())
                            .inputType(type)
                            .negativeText("Invalid data format")
                            .alwaysCallInputCallback()
                            .input("Hint", "Prefill sta nventa oh!", new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(MaterialDialog dialog, CharSequence input) {
                                    // If input inserted is not valid disable commit changes
                                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled( checkValidityInput(item.getKey(), input.toString()) );
                                    // TODO update firebase and CHECK if automatically update with the callback in getUserInfo()
                                    Toast.makeText(getActivity(), "toast", Toast.LENGTH_LONG).show();
                                }
                            }).show();
                } catch (Exception e) {
                    Log.d("", "");
                }


            }
        });
    }

    private boolean checkValidityInput (String key, String input) {
        boolean res = false;
        switch (key) {
            case Constants.USER_PARAM_NAME:
                res = (input.length() > 3 && input.length() < 20);
                break;
            case Constants.USER_PARAM_AGE:
                res = (Utils.isValidAge(input));
                break;
            case Constants.USER_PARAM_WEIGHT:
                break;
            case Constants.USER_PARAM_HEIGHT:
                break;
            case Constants.USER_PARAM_ACTIV:
                break;
            case Constants.USER_PARAM_NUMWOS:
                break;
            case Constants.USER_PARAM_MINUTES:
                break;
        }
        return res;
    }

}
