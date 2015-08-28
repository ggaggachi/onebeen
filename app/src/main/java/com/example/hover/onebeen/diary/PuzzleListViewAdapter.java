package com.example.hover.onebeen.diary;

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

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Puzzle> data;

    public PuzzleListViewAdapter(LayoutInflater inflater, int layout, ArrayList<Puzzle> data) {
        this.inflater = inflater;
        this.layout = layout;
        this.data = data;
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
