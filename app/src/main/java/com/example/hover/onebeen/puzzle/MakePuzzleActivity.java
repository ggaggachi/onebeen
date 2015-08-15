package com.example.hover.onebeen.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;

import java.io.IOException;

public class MakePuzzleActivity extends Activity {
    private Puzzle dummyPuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_puzzle);

        dummyPuzzle = new Puzzle();

        View galleryBtn = findViewById(R.id.gallery_button);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(intent, 1);
            }
        });

        View addBtn = findViewById(R.id.puzzle_add_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = (EditText) findViewById(R.id.puzzle_title);
                EditText description = (EditText) findViewById(R.id.puzzle_description);

//				dummyPuzzle.setType("puzzle");
                dummyPuzzle.setTitle(title.getText().toString());
                dummyPuzzle.setDescription(description.getText().toString());

                PuzzleDataSource puzzleDataSource = new PuzzleDataSource(getApplicationContext());
                puzzleDataSource.addPuzzle(dummyPuzzle);

                Toast.makeText(getApplicationContext(), "디비 저장 성공", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String description = extras.getString("description");
            String mediaUri = extras.getString("mediaUri");

            EditText titleArea = (EditText) findViewById(R.id.puzzle_title);
            EditText descriptionArea = (EditText) findViewById(R.id.puzzle_description);

            titleArea.setText(title);
            descriptionArea.setText(description);

            try {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                Uri uri = Uri.parse(mediaUri);

//				Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//				Bitmap resized = Bitmap.createScaledBitmap(photo, photo.getWidth(), photo.getHeight(), true);
//				imageView.setBackgroundResource(0);
//				imageView.setImageBitmap(resized);
//				imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                Log.e("mediaUri", mediaUri);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "실패 받아오기!", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            Uri uri1 = data.getData();
            String s = uri1.toString();
            Uri uri = Uri.parse(s);

            Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();

            Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            Bitmap resized = Bitmap.createScaledBitmap(photo, photo.getWidth(), photo.getHeight(), true);
            imageView.setBackgroundResource(0);
            imageView.setImageBitmap(resized);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            String path = uri.toString();

//			dummyPuzzle.setMediaUri(path);

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "갤러리 이미지를 가져오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
