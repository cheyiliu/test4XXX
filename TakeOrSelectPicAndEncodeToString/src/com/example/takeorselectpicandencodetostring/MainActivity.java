package com.example.takeorselectpicandencodetostring;

import java.io.File;
import java.io.FileNotFoundException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private static final int REQ_ALBUM_ACTIVITY = 100;
    private static final int REQ_CAMERA_ACTIVITY = 101;
    public static final String IMAGE_UNSPECIFIED = "image/*";

    private File mOutPutFile;
    private ImageView mImageViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_select_photo).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dispatchPickPictureIntent();
            }
        });

        findViewById(R.id.btn_take_photo).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        mImageViewResult = (ImageView) findViewById(R.id.img_result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("test", "MainActivity, onActivityResult, data=" + data);
        switch (requestCode) {
        case REQ_CAMERA_ACTIVITY:
            handleTakePicResult(requestCode, resultCode, data);
            break;
        case REQ_ALBUM_ACTIVITY:
            handleSelectPicResult(requestCode, resultCode, data);
            break;

        default:
            return;
        }
    }

    @SuppressLint("NewApi")
    protected void handleTakePicResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (mOutPutFile != null /* && mOutPutFile.length() > 0 */) {
                Log.e("test", "MainActivity, onActivityResult, success to take picture, mOutPutFile=" + mOutPutFile);
                // 拍照成功，接下来进行大小调整并转换为字符串
                Uri uri = Uri.fromFile(mOutPutFile);
                Log.e("test", "REQ_CAMERA_ACTIVITY, uri = " + uri);

                Options opts = new Options();
                opts.inSampleSize = 5;

                try {

                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
                    // Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(), opts);
                    if (bitmap != null) {
                        Log.e("test", "REQ_CAMERA_ACTIVITY, bitmap.getByteCount() = " + bitmap.getByteCount());
                        Log.e("test", "REQ_CAMERA_ACTIVITY, bitmap = " + bitmap);
                        mImageViewResult.setImageBitmap(bitmap);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.e("test", "REQ_CAMERA_ACTIVITY, e = " + e);
                }
            } else {
                Log.e("test", "MainActivity, onActivityResult, fail to take picture");
                FileUtil.deleteFile(mOutPutFile);
                finish();
            }
        } else {
            Log.e("test", "MainActivity, onActivityResult, fail to take picture2");
            FileUtil.deleteFile(mOutPutFile);
            finish();
        }
    }

    protected void handleSelectPicResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                Log.e("test", "REQ_ALBUM_ACTIVITY, uri = " + uri);
                mImageViewResult.setImageURI(uri);
                Log.e("test", "MainActivity, onActivityResult, success to select picture, uri=" + uri);
            } else {
                Log.e("test", "MainActivity, onActivityResult, fail to select picture2");
            }
        } else {
            Log.e("test", "MainActivity, onActivityResult, fail to select picture");
        }
    }

    private void dispatchTakePictureIntent() {
        mOutPutFile = FileConfig.getPhotoOutputFile();
        Log.e("test", "MainActivity, dispatchTakePictureIntent, mOutPutFile=" + mOutPutFile);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            if (mOutPutFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mOutPutFile));
                startActivityForResult(takePictureIntent, REQ_CAMERA_ACTIVITY);
            }
        }
    }

    private void dispatchPickPictureIntent() {
        Log.e("test", "MainActivity, dispatchPickPictureIntent");
        Intent pickPictureIntent = new Intent(Intent.ACTION_PICK, null);
        if (pickPictureIntent.resolveActivity(this.getPackageManager()) != null) {
            pickPictureIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
            startActivityForResult(pickPictureIntent, REQ_ALBUM_ACTIVITY);
        }
    }
}
