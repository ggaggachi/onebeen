package com.example.hover.onebeen.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.diary.TravelDiaryActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SavePuzzleActivity extends AppCompatActivity {

    Puzzle puzzle = new Puzzle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_puzzle);
        setActionbar();

        Intent intent = getIntent();
        String puzzleId = intent.getExtras().getString("puzzleId");

        PuzzleDataSource puzzleDataSource = new PuzzleDataSource(this);
        Puzzle localPuzzle = puzzleDataSource.getPuzzle(Long.valueOf(puzzleId));

        Log.e("SavePuzzleActivity", "PuzzleStatus에 따른 이벤트 시작!");
        Log.e("SavePuzzleActivity", "puzleStatus:" + localPuzzle.getStatus());
        Log.e("SavePuzzleActivity", "localPuzzle:" + localPuzzle.toString());

        setDefaultPuzzleData(localPuzzle);
        setUpdatePuzzleStatusEvent(PuzzleStatus.BEEN);

        setClickImageRegisterButtonEvent();
    }

    private void setClickImageRegisterButtonEvent() {
        findViewById(R.id.image_add_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryEvent(v, 0);
            }
        });
        findViewById(R.id.image_add_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryEvent(v, 1);
            }
        });
        findViewById(R.id.image_add_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryEvent(v, 2);
            }
        });
    }

    @NonNull
    private void setDefaultPuzzleData(Puzzle localPuzzle) {
        Log.e("SavePuzzleActivity", "DefaultPuzzle");
        Log.e("SavePuzzleActivity", String.valueOf(localPuzzle.getId()));
        Log.e("SavePuzzleActivity", localPuzzle.toString());

        ((TextView) findViewById(R.id.puzzle_place)).setText(localPuzzle.getPlace());
        ((TextView) findViewById(R.id.puzzle_todo)).setText(localPuzzle.getTodo());

        SavePuzzleActivity.this.puzzle.setId(localPuzzle.getId());
        SavePuzzleActivity.this.puzzle.setTravelDiaryId(localPuzzle.getTravelDiaryId());
        SavePuzzleActivity.this.puzzle.setStatus(localPuzzle.getStatus());
        SavePuzzleActivity.this.puzzle.setPlace(localPuzzle.getPlace());
        SavePuzzleActivity.this.puzzle.setTodo(localPuzzle.getTodo());

        // 디폴트로 장소와 하고 싶은 일은 다른 액티비티에서 저장이 되어 오지만, Description은 이곳에서 저장하고 다시
        // 이 액티비티를 들어오지 않는 이상 데이터가 없을 수도 있으므로 Null Check를 한다.
        if (isRegisteredPreviously(localPuzzle)) {
            ((EditText) findViewById(R.id.puzzle_description)).setText(localPuzzle.getDescription());
            SavePuzzleActivity.this.puzzle.setDescription(localPuzzle.getDescription());
        }
        if (localPuzzle.getImagePath1() != null) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.image_add_button1);
            imageButton.setImageURI(Uri.parse(localPuzzle.getImagePath1()));
            SavePuzzleActivity.this.puzzle.setImagePath1(localPuzzle.getImagePath1());
        }
        if (localPuzzle.getImagePath2() != null) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.image_add_button2);
            imageButton.setImageURI(Uri.parse(localPuzzle.getImagePath2()));
            SavePuzzleActivity.this.puzzle.setImagePath2(localPuzzle.getImagePath2());
        }
        if (localPuzzle.getImagePath3() != null) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.image_add_button3);
            imageButton.setImageURI(Uri.parse(localPuzzle.getImagePath3()));
            SavePuzzleActivity.this.puzzle.setImagePath3(localPuzzle.getImagePath3());
        }
    }

    private boolean isRegisteredPreviously(Puzzle puzzle) {
        return puzzle != null;
    }

    private void setUpdatePuzzleStatusEvent(final PuzzleStatus puzzleStatus) {
        Log.e("SavePuzzleActivity", "defaultClickUpdatePuzzleButtonEvent");

        findViewById(R.id.puzzle_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText description = (EditText) findViewById(R.id.puzzle_description);

                SavePuzzleActivity.this.puzzle.setDescription(description.getText().toString());
                SavePuzzleActivity.this.puzzle.setStatus(String.valueOf(puzzleStatus));

                Log.e("SavePuzzleActivity", description.getText().toString());
                Log.e("SavePuzzleActivity", SavePuzzleActivity.this.puzzle.toString());

                PuzzleDataSource puzzleDataSource = new PuzzleDataSource(getApplicationContext());
                long puzzleId = puzzleDataSource.updatePuzzle(SavePuzzleActivity.this.puzzle);

                Log.e("getPuzzle", puzzleDataSource.getPuzzle(puzzleId).toString());

                callTravelDiaryActivity();

                finish();
            }
        });
    }

    private void callTravelDiaryActivity() {
        Log.e("SavePuzzleActivity", "callTravelDiaryActivity");

        Intent intent = new Intent(SavePuzzleActivity.this, TravelDiaryActivity.class);
        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(getApplicationContext());

        Log.e("SavePuzzleActivity", "callTravelDiaryActivity travelDiaryId:"+ SavePuzzleActivity.this.puzzle.getTravelDiaryId());

        TravelDiary travelDiary = travelDiaryDataSource.getTravelDiary(Long.valueOf(SavePuzzleActivity.this.puzzle.getTravelDiaryId()));

        Log.e("SavePuzzleActivity", "callTravelDiaryActivity travelDiary:"+travelDiary.toString());

        intent.putExtra("travelDiaryId", String.valueOf(travelDiary.getId()));
        intent.putExtra("travelStatus", travelDiary.getTravelStatus().getValue());

        startActivityForResult(intent, 1);
    }

    private void setActionbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("추억 간직하기");
    }

    private void galleryEvent(View v, int order) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "지원하지 않는 단말입니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivityForResult(intent, order);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "이미지를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri imageUri = null;
        boolean fromGalley = false;

        imageUri = data.getData();
        fromGalley = true;

        Bitmap resizedBitmap = getResizedBitmap(fromGalley, imageUri);
        if (resizedBitmap == null) {
            return;
        }

        String fileName = String.valueOf(System.currentTimeMillis()) + ".png";
        File f = new File(Environment.getExternalStorageDirectory(), fileName);
        OutputStream out = null;
        try {
            f.createNewFile();//파일생성
            out = new FileOutputStream(f);
            resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            //bitmap = 갤러리또는 리소스에서 불러온 비트맵 파일에 포맷
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        switch (requestCode) {
            case 0:
                puzzle.setImagePath1(fileName);
                ((ImageButton) findViewById(R.id.image_add_button1)).setImageBitmap(resizedBitmap);
                break;
            case 1:
                puzzle.setImagePath2(fileName);
                ((ImageButton) findViewById(R.id.image_add_button2)).setImageBitmap(resizedBitmap);
                break;
            case 2:
                puzzle.setImagePath3(fileName);
                ((ImageButton) findViewById(R.id.image_add_button3)).setImageBitmap(resizedBitmap);
                break;
        }

//        Bitmap thumbnailFromPath = getThumbnailFromPath(getFilePathFromUri(imageUri));
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setImageBitmap(thumbnailFromPath);

//        LinearLayout imageBox = (LinearLayout) findViewById(R.id.puzzle_image_box);
//        ImageView puzzleImage = new ImageView(getApplicationContext());
//        puzzleImage.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
//        puzzleImage.setImageBitmap(thumbnailFromPath);
//        puzzleImage.setAdjustViewBounds(true);
//        puzzleImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

//        imageBox.addView(puzzleImage, requestCode);

//        buttonView.setVisibility(View.GONE);


//        try {
//            ImageView imageView = (ImageView) findViewById(R.id.imageView);
//            Uri uri1 = data.getData();
//            String s = uri1.toString();
//            Uri uri = Uri.parse(s);
//
//            Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
//
//            Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//            Bitmap resized = Bitmap.createScaledBitmap(photo, photo.getWidth(), photo.getHeight(), true);
//            imageView.setBackgroundResource(0);
//            imageView.setImageBitmap(resized);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//            String path = uri.toString();
//
////			puzzle.setMediaUri(path);
//
//        } catch (IOException e) {
//            Toast.makeText(getApplicationContext(), "갤러리 이미지를 가져오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
//        }
    }

    private Bitmap getResizedBitmap(boolean fromGalley, Uri uri) {
        File file = null;

        try {
            file = getFileFromUri(uri);
            if (file == null || file.length() == 0) {
                return null;
            }

            return getResizedBitmapFromFile(file);
        } catch (Exception e) {
            return null;
        } finally {
            if (!fromGalley && file != null) {
                file.delete();
            }
        }
    }

    public String getFilePathFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }

        Cursor cursor = null;

        try {
            cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();

            return path;
        } catch (Exception e) {
            if (uri.toString().startsWith("file://")) {
                return uri.toString().substring("file://".length());
            }

            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public File getFileFromUri(Uri uri) {
        String filePath = getFilePathFromUri(uri);
        if (filePath == null || filePath.length() == 0) {
            return null;
        }

        return new File(filePath);
    }

    public Bitmap getResizedBitmapFromFile(File file) {
        try {
            if (file == null || file.length() == 0) {
                return null;
            }

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float ratio = (float) outHeight / (float) outWidth;

            int targetWidth = 1024;
            int targetHeight = (int) (targetWidth * ratio);

            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

            Matrix matrix = new Matrix();
            matrix.postRotate(getImageOrientationDegree(file.getAbsolutePath()));

            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            return null;
        }
    }

    public int getImageOrientationDegree(String absoluteFilePath) {
        int degree;
        int orientation = -1;

        try {
            ExifInterface exifInterface = new ExifInterface(absoluteFilePath);
            orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        } catch (IOException e) {
            Log.e("", e.toString());
        }

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
            default:
                degree = 0;
                break;
        }

        return degree;
    }

    public Bitmap getThumbnailFromPath(String path) {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns._ID}, MediaStore.MediaColumns.DATA + "=?", new String[]{path}, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));

                Matrix matrix = new Matrix();
                matrix.postRotate(getImageOrientationDegree(path));

                Bitmap originalThumbnail = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id,
                        MediaStore.Images.Thumbnails.MICRO_KIND, null);

                return Bitmap.createBitmap(originalThumbnail, 0, 0, originalThumbnail.getWidth(), originalThumbnail.getHeight(), matrix, true);
            }
        } catch (Exception e) {
            Log.e("", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }
}
