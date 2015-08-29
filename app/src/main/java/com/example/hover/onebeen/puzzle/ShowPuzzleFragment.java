package com.example.hover.onebeen.puzzle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.dto.Puzzle;

public class ShowPuzzleFragment extends Fragment {
    Puzzle puzzle;

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    public static ShowPuzzleFragment create(Puzzle puzzle, int position) {
        ShowPuzzleFragment fragment = new ShowPuzzleFragment();
        fragment.setPuzzle(puzzle);
        fragment.setPosition(position);

        Log.i("position", String.valueOf(position));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_show_puzzle, null);

        if (puzzle.getDescription() != null && !"".equals(puzzle.getDescription())) {
            ((TextView) root.findViewById(R.id.puzzle_description)).setText(puzzle.getDescription());
        }

        if (position == 0) {
            if (puzzle.getImagePath1() != null) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + puzzle.getImagePath1();
                BitmapFactory.Options bo = new BitmapFactory.Options();
                bo.inSampleSize = 1;
                Bitmap bmp = BitmapFactory.decodeFile(path, bo);
                ((ImageView) root.findViewById(R.id.puzzle_image)).setImageBitmap(bmp);
            }
        } else if (position == 1) {
            if (puzzle.getImagePath2() != null) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + puzzle.getImagePath2();
                BitmapFactory.Options bo = new BitmapFactory.Options();
                bo.inSampleSize = 1;
                Bitmap bmp = BitmapFactory.decodeFile(path, bo);
                ((ImageView) root.findViewById(R.id.puzzle_image)).setImageBitmap(bmp);
            }
        } else if (position == 2 ) {
            if (puzzle.getImagePath3() != null) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + puzzle.getImagePath3();
                BitmapFactory.Options bo = new BitmapFactory.Options();
                bo.inSampleSize = 1;
                Bitmap bmp = BitmapFactory.decodeFile(path, bo);
                ((ImageView) root.findViewById(R.id.puzzle_image)).setImageBitmap(bmp);
            }
        }


        return root;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
}
