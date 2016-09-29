package it.alaindev.barbell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by narko on 29/09/16.
 */

public class DescValAdapter extends ArrayAdapter<HashMap<String, String>> {

    private Context context;

    public DescValAdapter(Context context, ArrayList<HashMap<String, String>> descval) {
        super(context, 0, descval);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HashMap<String, String> descval = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.desc_and_val, parent, false);
        }
        // Lookup view for data population
        TextView desctv = (TextView) convertView.findViewById(R.id.desctv);
        TextView valtv = (TextView) convertView.findViewById(R.id.valtv);
        // Populate the data into the template view using the data object
        // TODO fill with key and val
//        desctv.setText(descval.desc);
//        valtv.setText(descval.val);
        // Return the completed view to render on screen
        return convertView;
    }

}
