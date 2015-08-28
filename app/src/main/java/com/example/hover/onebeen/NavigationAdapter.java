package com.example.hover.onebeen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
    Context mContext;//추가

    public NavigationAdapter(String[] titles, int[] icons, User user) {
        mNavTitles = titles;
        mIcons = icons;
        name = user.getName();
        userId = user.getId();

    }
    /*추가된 생성자*/
    public NavigationAdapter(String[] titles, int[] icons, User user,Context context) {
        mNavTitles = titles;
        mIcons = icons;
        name = user.getName();
        userId = user.getId();
        mContext = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener{
        int Holderid;

        TextView textView;
        ImageView imageView;
        ProfilePictureView profilePictureView;
        TextView Name;
        TextView email;
        private Context mContext;//추가

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
        /*추가*/
        public ViewHolder(View itemView, int ViewType, String userId,Context context) {
            super(itemView);
            /*추가된 부분*/
            mContext = context;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
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
        /*추가된 부분*/
        @Override
        public void onClick(View v) {
            /*TravelDiaryListFragment travelDiaryListFragment = null;
            if(getItemViewType() == TYPE_ITEM){
                switch(getPosition()){
                    case 1: //여행 시작하기
                        Intent intent = new Intent(mContext, MakeDiary.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "시작하기", Toast.LENGTH_SHORT).show();
                        break;
                    case 2: //다녀온 여행지
                        travelDiaryListFragment = new TravelDiaryListFragment();

                        Bundle args = new Bundle();
                        args.putString("travelStatus", TravelStatus.ONGOING.getValue());

                        travelDiaryListFragment.setArguments(args);

                        *//*fragmentManager.beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_light, R.anim.slide_out_left)
                                .replace(R.id.container, travelDiaryListFragment)
                                .commit();*//*
                        break;
                    case 3: //진행중 여행지
                        Toast.makeText(v.getContext(), "진행중", Toast.LENGTH_SHORT).show();
                        break;
                    case 4: //계획중 여행지
                        Toast.makeText(v.getContext(), "계획중", Toast.LENGTH_SHORT).show();
                        break;
                    default: break;
                }
            }*/

        }
    }

    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_item, parent, false);
            //ViewHolder vhItem = new ViewHolder(v, viewType, userId);
            ViewHolder vhItem = new ViewHolder(v,viewType,userId,mContext);
            return vhItem;

        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_header, parent, false);
            //ViewHolder vhHeader = new ViewHolder(v, viewType, userId);
            ViewHolder vhHeader = new ViewHolder(v, viewType, userId,mContext);
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
