package it.alaindev.barbell.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.NumberPicker;
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
import it.alaindev.barbell.SharedUtils;
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(SecureConst.FIREBASE_USERS + "/" + SharedUtils.getFbUserIdx(getContext()));

        // Check if there is this user in firebase DB
        ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        fillUI(user);
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

                    new MaterialDialog.Builder(getActivity())
                            .title(User.convertParamsDesc(item.getKey()))
                            .content(User.getDescriptionParamsDesc(item.getKey()))
                            .inputType(User.getInputTypeParamsDesc(item.getKey()))
                            .alwaysCallInputCallback()
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    try {
                                        updateFirebaseField(item.getKey(), dialog.getInputEditText().getText().toString());
                                    } catch (NullPointerException e) {
                                        Log.d("Params Fragment", e.toString());
                                    }
                                    Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
                                }
                            })
                            .input("TODO Hint", item.getValue(), new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(MaterialDialog dialog, CharSequence input) {
                                    // Check input validity, if false disable OK button
                                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled( checkValidityInput(item.getKey(), input.toString()) );
                                }
                            }).show();
                } catch (Exception e) {
                    Log.d("", "");
                }


            }
        });
    }

    private void updateFirebaseField (String key, String input) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(SecureConst.FIREBASE_USERS + "/" + SharedUtils.getFbUserIdx(getContext()));
        Map<String, Object> userupdate = new HashMap<>();

        switch (key) {
            case User.USER_PARAM_NAME:
                userupdate.put(key, input);
                break;
            case User.USER_PARAM_AGE:
                userupdate.put(key, Integer.parseInt(input));
                break;
            case User.USER_PARAM_WEIGHT:
                userupdate.put(key, Integer.parseInt(input));
                break;
            case User.USER_PARAM_HEIGHT:
                userupdate.put(key, Integer.parseInt(input));
                break;
            case User.USER_PARAM_ACTIV:
                userupdate.put(key, Integer.parseInt(input));
                break;
            case User.USER_PARAM_NUMWOS:
                userupdate.put(key, Integer.parseInt(input));
                break;
            case User.USER_PARAM_MINUTES:
                userupdate.put(key, Integer.parseInt(input));
                break;
            case User.USER_PARAM_HARDWO:
                userupdate.put(key, Integer.parseInt(input));
                break;
        }

        ref.updateChildren(userupdate);
    }

    private boolean checkValidityInput (String key, String input) {
        boolean res = false;
        Integer i;
        switch (key) {
            case User.USER_PARAM_NAME:
                res = (input.length() > 3 && input.length() < 20);
                break;
            case User.USER_PARAM_AGE:
                i = (Utils.getIfValidInt(input));
                res = (i != null && (i > 0 && i < 121));
                break;
            case User.USER_PARAM_WEIGHT:
                i = (Utils.getIfValidInt(input));
                res = (i != null && (i > 29 && i < 351));
                break;
            case User.USER_PARAM_HEIGHT:
                i = (Utils.getIfValidInt(input));
                res = (i != null && (i > 29 && i < 301));
                break;
            case User.USER_PARAM_ACTIV:
                i = (Utils.getIfValidInt(input));
                res = (i != null && (i > 0 && i < 5));
                break;
            case User.USER_PARAM_NUMWOS:
                i = (Utils.getIfValidInt(input));
                res = (i != null && (i > 0 && i < 8));
                break;
            case User.USER_PARAM_MINUTES:
                i = (Utils.getIfValidInt(input));
                res = (i != null && (i > 0 && i < 361));
                break;
            case User.USER_PARAM_HARDWO:
                i = (Utils.getIfValidInt(input));
                res = (i != null && (i > 0 && i < 5));
                break;
        }
        return res;
    }

}
