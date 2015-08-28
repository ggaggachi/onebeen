package com.example.hover.onebeen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hover.onebeen.db.dto.User;
import com.facebook.login.widget.ProfilePictureView;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[];
    private int mIcons[];

    private String name;
    private String userId;

    public NavigationAdapter(String[] titles, int[] icons, User user) {
        mNavTitles = titles;
        mIcons = icons;
        name = user.getName();
        userId = user.getId();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int Holderid;

        TextView textView;
        ImageView imageView;
        ProfilePictureView profilePictureView;
        TextView Name;
        TextView email;

        public ViewHolder(View itemView, int ViewType, String userId) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Holderid = 1;
            } else {
                Name = (TextView) itemView.findViewById(R.id.name);
                profilePictureView = (ProfilePictureView) itemView.findViewById(R.id.circleView);
                profilePictureView.setProfileId(userId);
                Holderid = 0;
            }
        }

        @Override
        public void onClick(View v) {
            
        }
    }

    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_item, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType, userId);
            return vhItem;

        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_header, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType, userId);
            return vhHeader;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(NavigationAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == 1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position - 1]);
        } else {

            holder.Name.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
