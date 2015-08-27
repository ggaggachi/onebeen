package com.example.hover.onebeen.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MakePuzzleFragment extends Fragment {
    private Puzzle puzzle;
    private View buttonView;

//    private Typeface mTypeface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_make_puzzle, null);

//        mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "NotoSansCJKkr-Black.otf");
        ViewGroup viewById = (ViewGroup) root.findViewById(android.R.id.content);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "NotoSansCJKkr-DemiLight.otf");
        TextView viewById1 = (TextView) root.findViewById(R.id.puzzle_place);
        viewById1.setTypeface(typeface);

        Intent intent = getActivity().getIntent();
//        puzzle = intent.getParcelableExtra("puzzle");
        puzzle = new Puzzle();
        puzzle.setPlace("테스트테스트");
        puzzle.setTodo("테스트테스트");

        ((TextView) root.findViewById(R.id.puzzle_place)).setText(puzzle.getPlace());
        ((TextView) root.findViewById(R.id.puzzle_item_todo)).setText(puzzle.getTodo());

        root.findViewById(R.id.image_add_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryEvent(v, 0);
            }
        });
        root.findViewById(R.id.image_add_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryEvent(v, 1);
            }
        });
        root.findViewById(R.id.image_add_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryEvent(v, 2);
            }
        });

        root.findViewById(R.id.puzzle_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText description = (EditText) root.findViewById(R.id.puzzle_description);

//                MakePuzzleFragment.this.puzzle.setDescription(description.getText().toString());

                PuzzleDataSource puzzleDataSource = new PuzzleDataSource(getActivity());
                long insertedRow = puzzleDataSource.addPuzzle(MakePuzzleFragment.this.puzzle);

                Intent intent = new Intent(getActivity(), ShowPuzzleActivity.class);
                intent.putExtra("insertedRow", insertedRow);
                startActivity(intent);

//                getActivity().finish();
            }
        });

//        View galleryBtn = root.findViewById(R.id.gallery_button);
//        galleryBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//
//                startActivityForResult(intent, 1);
//            }
//        });
//
//        View addBtn = root.findViewById(R.id.puzzle_add_button);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText title = (EditText) root.findViewById(R.id.puzzle_title);
//                EditText description = (EditText) root.findViewById(R.id.puzzle_description);
//
////				puzzle.setType("puzzle");
//                puzzle.setTitle(title.getText().toString());
//                puzzle.setDescription(description.getText().toString());
//
//                PuzzleDataSource puzzleDataSource = new PuzzleDataSource(getApplicationContext());
//                puzzleDataSource.addPuzzle(puzzle);
//
//                Toast.makeText(getApplicationContext(), "디비 저장 성공", Toast.LENGTH_LONG).show();
//                finish();
//            }
//        });
        return root;
    }

//    void setGlobalFont(ViewGroup root) {
//        for (int i = 0; i < root.getChildCount(); i++) {
//            View child = root.getChildAt(i);
//            if (child instanceof TextView)
//                ((TextView)child).setTypeface(mTypeface);
//            else if (child instanceof ViewGroup)
//                setGlobalFont((ViewGroup)child);
//        }
//    }


    private void galleryEvent(View v, int order) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (intent.resolveActivity(getActivity().getPackageManager()) == null) {
            Toast.makeText(getActivity(), "지원하지 않는 단말입니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivityForResult(intent, order);
        buttonView = v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getActivity(), "이미지를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
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
                ((ImageButton) getActivity().findViewById(R.id.image_add_button1)).setImageBitmap(resizedBitmap);
                break;
            case 1:
                puzzle.setImagePath2(fileName);
                ((ImageButton) getActivity().findViewById(R.id.image_add_button2)).setImageBitmap(resizedBitmap);
                break;
            case 2:
                puzzle.setImagePath3(fileName);
                ((ImageButton) getActivity().findViewById(R.id.image_add_button3)).setImageBitmap(resizedBitmap);
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
            cursor = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
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
        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns._ID}, MediaStore.MediaColumns.DATA + "=?", new String[]{path}, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));

                Matrix matrix = new Matrix();
                matrix.postRotate(getImageOrientationDegree(path));

                Bitmap originalThumbnail = MediaStore.Images.Thumbnails.getThumbnail(getActivity().getContentResolver(), id,
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
