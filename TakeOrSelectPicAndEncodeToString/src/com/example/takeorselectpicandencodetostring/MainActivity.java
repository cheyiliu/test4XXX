package com.example.takeorselectpicandencodetostring;

import java.io.ByteArrayOutputStream;
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
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = "test";
    private static final int REQ_ALBUM_ACTIVITY = 100;
    private static final int REQ_CAMERA_ACTIVITY = 1001;
    private static final String IMAGE_UNSPECIFIED = "image/*";

    private File mOutPutFile;
    private ImageView mImageViewResult;
    private TextView mTextViewResult;

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
        mTextViewResult = (TextView) findViewById(R.id.tv_base64_result);
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

    protected void handleTakePicResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (mOutPutFile != null /* && mOutPutFile.length() > 0 */) {
                Uri uri = Uri.fromFile(mOutPutFile);
                if (uri != null) {
                    processBitmap(uri);
                } else {
                    FileUtil.deleteFile(mOutPutFile);
                }
            } else {
                FileUtil.deleteFile(mOutPutFile);
            }
        } else {
            FileUtil.deleteFile(mOutPutFile);
        }
    }

    protected void handleSelectPicResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    processBitmap(uri);
                }
            } else {
            }
        } else {
        }
    }

    @SuppressLint("NewApi")
    protected void processBitmap(final Uri uri) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Options opts = new Options();
                opts.inSampleSize = 5;

                try {
                    final Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
                    // Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(), opts);
                    if (bitmap != null) {
                        // encode to string
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                        byte[] b = baos.toByteArray();
                        final String encodedBitmapAsString = Base64.encode(b);

                        UiThreadHandler.post(new Runnable() {

                            @Override
                            public void run() {

                                Log.i(TAG, "the encoded string = " + encodedBitmapAsString);
                                mTextViewResult.setText(encodedBitmapAsString);
                                mImageViewResult.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void dispatchTakePictureIntent() {
        mOutPutFile = FileConfig.getPhotoOutputFile();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            if (mOutPutFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mOutPutFile));
                startActivityForResult(takePictureIntent, REQ_CAMERA_ACTIVITY);
            }
        }
    }

    private void dispatchPickPictureIntent() {
        Intent pickPictureIntent = new Intent(Intent.ACTION_PICK, null);
        if (pickPictureIntent.resolveActivity(this.getPackageManager()) != null) {
            pickPictureIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
            startActivityForResult(pickPictureIntent, REQ_ALBUM_ACTIVITY);
        }
    }
}
