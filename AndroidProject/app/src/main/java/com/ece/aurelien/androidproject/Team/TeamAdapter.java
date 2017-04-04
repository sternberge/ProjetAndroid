package com.ece.aurelien.androidproject.Team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ece.aurelien.androidproject.R;

import java.util.List;

/**
 * Created by Aurélien on 03/04/2017.
 */

public class TeamAdapter extends ArrayAdapter<Team> {

    //teams est la liste des models à afficher
    public TeamAdapter(Context context, List<Team> teams) {
        super(context, 0, teams);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_team,parent, false);
        }

        TeamViewHolder viewHolder = (TeamViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TeamViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.location = (TextView) convertView.findViewById(R.id.location);
            viewHolder.classement = (TextView) convertView.findViewById(R.id.classement);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Team> teams
        Team myteam = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(myteam.getName());
        viewHolder.location.setText(myteam.getLocation());
        if(myteam.getClassement() == 0) {
            viewHolder.classement.setText("test");
        }

        else{
            viewHolder.classement.setText(myteam.getClassement());
        }

        return convertView;
    }

    private class TeamViewHolder{
        public TextView name;
        public TextView location;
        public TextView classement;
    }
}
