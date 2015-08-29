package com.example.hover.onebeen.diary;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.puzzle.PuzzleStatus;

import java.util.ArrayList;

public class PuzzleListViewAdapter extends BaseAdapter {
    private final ArrayList<String> colors;

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Puzzle> data;

    public PuzzleListViewAdapter(LayoutInflater inflater, int layout, ArrayList<Puzzle> data) {
        this.inflater = inflater;
        this.layout = layout;
        this.data = data;

        colors = new ArrayList<>();
        colors.add("#4dc0e9");
        colors.add("#29aed8");
        colors.add("#279ecd");
        colors.add("#239abf");
        colors.add("#2295b1");
        colors.add("#117f9f");
        colors.add("#1b6b8f");
        colors.add("#645588");
        colors.add("#614571");
        colors.add("#473159");
        colors.add("#39274c");
        colors.add("#2f2243");
        colors.add("#2c1c3d");
        colors.add("#211433");
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Puzzle getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        if (position >= colors.size()) {
            convertView.findViewById(R.id.puzzle_item_background).setBackgroundColor(Color.parseColor(colors.get(colors.size()-1)));
        } else {
            convertView.findViewById(R.id.puzzle_item_background).setBackgroundColor(Color.parseColor(colors.get(position)));
        }

        Puzzle puzzle = data.get(position);

        if (puzzle == null) {
            ((ImageView) convertView.findViewById(R.id.puzzle_route_img)).setImageResource(R.drawable.img_route_vertical_add);
            ((TextView) convertView.findViewById(R.id.puzzle_item_place)).setText("가고싶은곳 추가");
            ((TextView) convertView.findViewById(R.id.puzzle_item_todo)).setVisibility(View.GONE);
            ((TextView) convertView.findViewById(R.id.puzzle_subtitle)).setVisibility(View.GONE);

            return convertView;
        }

        ((TextView) convertView.findViewById(R.id.puzzle_item_place)).setText(puzzle.getPlace());
        ((TextView) convertView.findViewById(R.id.puzzle_item_todo)).setText(puzzle.getTodo());

        if (puzzle.getDescription() != null) {
            ((TextView) convertView.findViewById(R.id.puzzle_subtitle)).setText(puzzle.getDescription());
        }

        if (0 < puzzle.getImagePathCount()) {
            Log.e("PuzzleListViewAdapter", "ImageCount:" + puzzle.getImagePathCount());
            ((TextView) convertView.findViewById(R.id.img_piece_count)).setText(String.valueOf(puzzle.getImagePathCount()));
            ImageView imageView = (ImageView) convertView.findViewById(R.id.img_piece);
            imageView.setImageResource(R.drawable.img_piece);
        }

        String status = puzzle.getStatus();

        if (PuzzleStatus.BEEN.toString().equals(status)) {
            ((ImageView) convertView.findViewById(R.id.puzzle_route_img)).setImageResource(R.drawable.img_route_vertical_route);
        } else if (PuzzleStatus.CURRENT.toString().equals(status)){
            ((ImageView) convertView.findViewById(R.id.puzzle_route_img)).setImageResource(R.drawable.img_route_vertical_current);
        } else {
            ((ImageView) convertView.findViewById(R.id.puzzle_route_img)).setImageResource(R.drawable.img_route_vertical_noroute);
        }

        convertView.setTag(status);
        convertView.setTag("puzzleId".hashCode(), puzzle.getId());

        return convertView;
    }
}
