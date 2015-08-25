package com.example.hover.onebeen;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by seojin on 2015-08-26.
 */
public class VisitedAdapter extends ArrayAdapter<VisitedlistItem> {


    Context context;
    int layoutResourceId;
    VisitedlistItem[] data;
    String TripTitle;
    String TripDate;

    public VisitedAdapter(Context context, int layoutResourceId, VisitedlistItem[] data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        VisitedHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new VisitedHolder();
            holder.titletv = (TextView) row.findViewById(R.id.titletv);
            holder.datetv = (TextView) row.findViewById(R.id.datetv);

            row.setTag(holder);
        } else {
            holder = (VisitedHolder) row.getTag();
        }

        VisitedlistItem visitedlistItem = data[position];
        holder.titletv.setText(visitedlistItem.getTripTitle());
        holder.datetv.setText(visitedlistItem.getTripDate());

        return row;
    }

    static class VisitedHolder {
        TextView titletv;
        TextView datetv;
    }
}


class VisitedlistItem {
    private String TripTitle = "";
    private String TripDate = "";

    public VisitedlistItem(String TripTitle, String TripDate) {
        super();
        this.TripTitle = TripTitle;
        this.TripDate = TripDate;
    }

    /**
     * ******** Set Methods *****************
     */

    public void setTripTitle(String TripTitle) {
        this.TripTitle = TripTitle;
    }

    public void setTripDate(String TripDate) {
        this.TripDate = TripDate;
    }

    /**
     * ******** Get Methods ***************
     */

    public String getTripTitle() {
        return this.TripTitle;
    }

    public String getTripDate() {
        return this.TripDate;
    }
}
