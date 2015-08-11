package com.example.hover.onebeen.puzzle;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;

import java.io.IOException;

public class MakePuzzleActivity extends Activity {
	private Puzzle dummyPuzzle;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.make_puzzle);

		dummyPuzzle = new Puzzle();

		View galleryBtn = findViewById(R.id.gallery_button);
		galleryBtn.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");

				startActivityForResult(intent, 1);
			}
		});

		View addBtn = findViewById(R.id.puzzle_add_button);
		addBtn.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				EditText title = (EditText) findViewById(R.id.puzzle_title);
				EditText description = (EditText) findViewById(R.id.puzzle_description);

				dummyPuzzle.setType("puzzle");
				dummyPuzzle.setTitle(title.getText().toString());
				dummyPuzzle.setDescription(description.getText().toString());

				PuzzleDataSource puzzleDataSource = new PuzzleDataSource(getApplicationContext());
				puzzleDataSource.insertPuzzle(dummyPuzzle);

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

//			try {
//				ImageView imageView = (ImageView) findViewById(R.id.imageView);
//				Uri uri = Uri.parse(mediaUri);
//
//				Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//				Bitmap resized = Bitmap.createScaledBitmap(photo, photo.getWidth(), photo.getHeight(), true);
//				imageView.setBackgroundResource(0);
//				imageView.setImageBitmap(resized);
//				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//				Toast.makeText(getApplicationContext(), "퍼즐 로딩 성공", Toast.LENGTH_LONG).show();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}

	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			Toast.makeText(getApplicationContext(), "실패 받아오기!", Toast.LENGTH_LONG).show();
			return;
		}

		try {
			ImageView imageView = (ImageView) findViewById(R.id.imageView);
			Uri uri = data.getData();

			Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
			Bitmap resized = Bitmap.createScaledBitmap(photo, photo.getWidth(), photo.getHeight(), true);
			imageView.setBackgroundResource(0);
			imageView.setImageBitmap(resized);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);

			dummyPuzzle.setMediaUri(uri.toString());

		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "갤러리 이미지를 가져오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
		}
	}
}
