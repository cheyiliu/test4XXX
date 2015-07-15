package com.example.takeorselectpicandencodetostring;

import java.io.ByteArrayOutputStream;
import java.io.File;

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
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "test";
    private static final int REQ_ALBUM_ACTIVITY = 100;
    private static final int REQ_CAMERA_ACTIVITY = 1001;
    private static final String IMAGE_UNSPECIFIED = "image/*";

    private File mOutPutFile;

    private ImageView mImageViewResult;
    private ImageView mImageViewResult2;
    private TextView mTextViewResult;

    private String mEncodedStr;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
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
        mImageViewResult2 = (ImageView) findViewById(R.id.img_result2);
        mTextViewResult = (TextView) findViewById(R.id.tv_base64_result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult, data=" + data);
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
        Log.i(TAG, "handleTakePicResult");
        if (resultCode == RESULT_OK) {
            if (mOutPutFile != null /* && mOutPutFile.length() > 0 */) {
                Uri uri = Uri.fromFile(mOutPutFile);
                if (uri != null) {
                    processBitmapAsync(uri);
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
        Log.i(TAG, "handleSelectPicResult");
        if (resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    processBitmapAsync(uri);
                }
            } else {
            }
        } else {
        }
    }

    protected void processBitmapAsync(final Uri uri) {
        Log.i(TAG, "processBitmapAsync, uri=" + uri);
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "processBitmapAsync, run");
                processBitmapSync(uri);
            }
        }).start();

    }

    private void processBitmapSync(final Uri uri) {
        Log.i(TAG, "processBitmapSync, uri=" + uri);
        try {
            Options opts = new Options();
            opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
            opts.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
            // 图片太小，提示重新选择
            if (opts.outHeight < 500 || opts.outWidth < 500) {
                Toast.makeText(this, "图片太小，请重新选择", Toast.LENGTH_SHORT).show();
                return;
            }

            Bitmap srcBitmap = null;
            // 图片分辨率相对屏幕分辨率的最大倍数
            float maxRatio = 0.5f;
            {
                // 方案2，取inSampleSize
                
            }
            // 宽或高超过限定，进行缩放
            if (opts.outHeight > ScreenUtil.getHeight() * maxRatio || opts.outWidth > ScreenUtil.getWidth() * maxRatio) {
                int heightRatio = Math.round(opts.outHeight / ScreenUtil.getHeight() / maxRatio);
                int widthRatio = Math.round(opts.outWidth / ScreenUtil.getWidth() / maxRatio);
                Log.i(TAG, "heightRatio=" + heightRatio);
                Log.i(TAG, "widthRatio =" + widthRatio);
                opts.inJustDecodeBounds = false;
                opts.inSampleSize = Math.max(heightRatio, widthRatio);
                srcBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
            } else {
                // 原始大小
                srcBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
            }
            // 判断是否需要对图像进行旋转处理
            // int degree = BitmapUtils.readPictureDegree(BitmapUtils
            // .getRealPathFromUri(getContentResolver(), imageUri));
            int degree = ExifUtils.getExifOrientation(this, uri);
            if (degree != 0) {
                mBitmap = BtsBitmapUtils.rotateBitmap(srcBitmap, degree, true);
                // mImageViewResult.setImageBitmap(resBitmap);
                encodeBitmapToString(mBitmap);
            } else {
                // 不需要旋转处理
                // mImageViewResult.setImageBitmap(srcBitmap);
                mBitmap = srcBitmap;
                encodeBitmapToString(srcBitmap);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "文件无法打开", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
    }

    @SuppressLint("NewApi")
    private void encodeBitmapToString(Bitmap bitmap) {
        Log.i(TAG, "encodeBitmapToString");
        if (bitmap != null) {
            Log.i(TAG, "bitmap.getAllocationByteCount()=" + bitmap.getAllocationByteCount());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] b = baos.toByteArray();
            mEncodedStr = Base64.encode(b);
            updateUI();
            FileUtil.deleteFile(mOutPutFile);// finally delete the file
            
            {
                UiThreadHandler.postDelayed(new Runnable() {
                    
                    @Override
                    public void run() {
                        byte[] b2 = Base64.decode(mEncodedStr);
                        Bitmap bitmap2 = BitmapFactory.decodeByteArray(b2, 0, b2.length);
                        mImageViewResult2.setImageResource(R.drawable.ic_launcher);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mImageViewResult2.setImageBitmap(bitmap2);
                    }
                }, 3000);

            }
        }
    }

    private void updateUI() {
        Log.i(TAG, "updateUI");
        UiThreadHandler.post(new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "updateUI, run");
                mImageViewResult.setImageBitmap(mBitmap);
                // mTextViewResult.setText(mEncodedStr);// consume many time
                // Log.i(TAG, "mEncodedStr=" + mEncodedStr);
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Log.i(TAG, "dispatchTakePictureIntent");
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
        Log.i(TAG, "dispatchPickPictureIntent");
        Intent pickPictureIntent = new Intent(Intent.ACTION_PICK, null);
        if (pickPictureIntent.resolveActivity(this.getPackageManager()) != null) {
            pickPictureIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
            startActivityForResult(pickPictureIntent, REQ_ALBUM_ACTIVITY);
        }
    }
}
