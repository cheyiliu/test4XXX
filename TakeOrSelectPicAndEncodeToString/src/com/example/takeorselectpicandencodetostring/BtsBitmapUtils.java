/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.takeorselectpicandencodetostring;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.FloatMath;
import android.util.Log;

public class BtsBitmapUtils {
    private static final String TAG = "BitmapUtils";
    private static final int DEFAULT_JPEG_QUALITY = 90;
    public static final int UNCONSTRAINED = -1;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;

    private BtsBitmapUtils() {
    }

    /*
     * Compute the sample size as a function of minSideLength and maxNumOfPixels. minSideLength is
     * used to specify that minimal width or height of a bitmap. maxNumOfPixels is used to specify
     * the maximal size in pixels that is tolerable in terms of memory usage.
     * 
     * The function returns a sample size based on the constraints. Both size and minSideLength can
     * be passed in as UNCONSTRAINED, which indicates no care of the corresponding constraint. The
     * functions prefers returning a sample size that generates a smaller bitmap, unless
     * minSideLength = UNCONSTRAINED.
     * 
     * Also, the function rounds up the sample size to a power of 2 or multiple of 8 because
     * BitmapFactory only honors sample size this way. For example, BitmapFactory downsamples an
     * image by 2 even though the request is 3. So we round up the sample size to avoid OOM.
     */
    public static int computeSampleSize(int width, int height, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(width, height, minSideLength, maxNumOfPixels);

        return initialSize <= 8 ? nextPowerOf2(initialSize) : (initialSize + 7) / 8 * 8;
    }

    private static int computeInitialSampleSize(int w, int h, int minSideLength, int maxNumOfPixels) {
        if (maxNumOfPixels == UNCONSTRAINED && minSideLength == UNCONSTRAINED)
            return 1;

        int lowerBound = (maxNumOfPixels == UNCONSTRAINED) ? 1 : (int) FloatMath.ceil(FloatMath.sqrt((float) (w * h) / maxNumOfPixels));

        if (minSideLength == UNCONSTRAINED) {
            return lowerBound;
        } else {
            int sampleSize = Math.min(w / minSideLength, h / minSideLength);
            return Math.max(sampleSize, lowerBound);
        }
    }

    // This computes a sample size which makes the longer side at least
    // minSideLength long. If that's not possible, return 1.
    public static int computeSampleSizeLarger(int w, int h, int minSideLength) {
        int initialSize = Math.max(w / minSideLength, h / minSideLength);
        if (initialSize <= 1)
            return 1;

        return initialSize <= 8 ? prevPowerOf2(initialSize) : initialSize / 8 * 8;
    }

    // Find the min x that 1 / x >= scale
    public static int computeSampleSizeLarger(float scale) {
        int initialSize = (int) FloatMath.floor(1f / scale);
        if (initialSize <= 1)
            return 1;

        return initialSize <= 8 ? prevPowerOf2(initialSize) : initialSize / 8 * 8;
    }

    // Find the max x that 1 / x <= scale.
    public static int computeSampleSize(float scale) {
        assertTrue(scale > 0);
        int initialSize = Math.max(1, (int) FloatMath.ceil(1 / scale));
        return initialSize <= 8 ? nextPowerOf2(initialSize) : (initialSize + 7) / 8 * 8;
    }

    public static Bitmap resizeBitmapByScale(Bitmap bitmap, float scale, boolean recycle) {
        int width = Math.round(bitmap.getWidth() * scale);
        int height = Math.round(bitmap.getHeight() * scale);
        if (width == bitmap.getWidth() && height == bitmap.getHeight())
            return bitmap;
        Bitmap target = Bitmap.createBitmap(width, height, getConfig(bitmap));
        Canvas canvas = new Canvas(target);
        canvas.scale(scale, scale);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle)
            bitmap.recycle();
        return target;
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return config;
    }

    public static Bitmap resizeDownBySideLength(Bitmap bitmap, int maxLength, boolean recycle) {
        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();
        float scale = Math.min((float) maxLength / srcWidth, (float) maxLength / srcHeight);
        if (scale >= 1.0f)
            return bitmap;
        return resizeBitmapByScale(bitmap, scale, recycle);
    }

    public static Bitmap resizeAndCropCenter(Bitmap bitmap, int size, boolean recycle) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        if (w == size && h == size)
            return bitmap;

        // scale the image so that the shorter side equals to the target;
        // the longer side will be center-cropped.
        float scale = (float) size / Math.min(w, h);

        Bitmap target = Bitmap.createBitmap(size, size, getConfig(bitmap));
        int width = Math.round(scale * bitmap.getWidth());
        int height = Math.round(scale * bitmap.getHeight());
        Canvas canvas = new Canvas(target);
        canvas.translate((size - width) / 2f, (size - height) / 2f);
        canvas.scale(scale, scale);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle)
            bitmap.recycle();
        return target;
    }

    public static Bitmap resizeRotateAndCropCenter(Bitmap bitmap, int size, int degree, boolean recycle) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        if (w == size && h == size)
            return bitmap;

        // scale the image so that the shorter side equals to the target;
        // the longer side will be center-cropped.
        float scale = (float) size / Math.min(w, h);

        Bitmap target = Bitmap.createBitmap(size, size, getConfig(bitmap));
        int width = Math.round(scale * bitmap.getWidth());
        int height = Math.round(scale * bitmap.getHeight());
        Canvas canvas = new Canvas(target);
        canvas.translate((size - width) / 2f, (size - height) / 2f);
        canvas.scale(scale, scale);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle)
            bitmap.recycle();
        target = rotateBitmap(target, degree, true);
        return target;
    }

    public static Bitmap resizeRotateAndCropFace(Bitmap bitmap, int size, int degree, float centerX, float centerY, boolean recycle) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        if (w == size && h == size)
            return bitmap;

        // scale the image so that the shorter side equals to the target;
        // the longer side will be center-cropped.
        float scale = (float) size / Math.min(w, h);

        Bitmap target = Bitmap.createBitmap(size, size, getConfig(bitmap));
        int width = Math.round(scale * bitmap.getWidth());
        int height = Math.round(scale * bitmap.getHeight());
        Canvas canvas = new Canvas(target);
        // canvas.translate((size - width) / 2f, (size - height) / 2f;
        float dx = -centerX * scale + width / 2f;
        float dy = -centerY * scale + height / 2f;
        dx = cropXY(dx, (size - width) / 2f);
        dy = cropXY(dy, (size - height) / 2f);
        canvas.translate(dx, dy);
        canvas.scale(scale, scale);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle)
            bitmap.recycle();
        target = rotateBitmap(target, degree, true);
        return target;
    }

    private static float cropXY(float d, float s) {
        if (s >= 0) {
            if (d <= 0) {
                d = 0;
            } else {
                d = Math.min(d, s);
            }
        } else {
            if (d >= 0) {
                d = 0;
            } else {
                d = Math.max(d, s);
            }

        }
        return d;
    }

    public static Bitmap proResizeAndCropCenter(Bitmap bitmap, int width, int height, boolean recycle) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        if (w <= width) {
            if (h > height) {
                return cropCenter(bitmap, w, height, recycle);
            } else {
                return cropCenter(bitmap, w, h, recycle);
            }
        }

        // scale the image so that the shorter side equals to the target;
        // the longer side will be center-cropped.
        float scale = (float) width / w;
        Bitmap target = resizeBitmapByScale(bitmap, scale, recycle);
        w = target.getWidth();
        h = target.getHeight();
        if (h > height) {
            return cropCenter(target, w, height, true);
        } else {
            return cropCenter(target, w, h, recycle);
        }
    }

    public static Bitmap resizeAndCropCenter(Bitmap bitmap, int size, boolean recycle, int degrees) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        // if (w == size && h == size) return bitmap;

        // scale the image so that the shorter side equals to the target;
        // the longer side will be center-cropped.
        float scale = (float) size / Math.min(w, h);

        Bitmap target = Bitmap.createBitmap(size, size, getConfig(bitmap));
        int width = Math.round(scale * bitmap.getWidth());
        int height = Math.round(scale * bitmap.getHeight());
        Canvas canvas = new Canvas(target);

        canvas.translate((size - width) / 2f, (size - height) / 2f);
        canvas.scale(scale, scale);
        canvas.rotate(degrees, size / 2f, size / 2f);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle)
            bitmap.recycle();
        return target;
    }

    public static void recycleSilently(Bitmap bitmap) {
        if (bitmap == null)
            return;
        try {
            bitmap.recycle();
        } catch (Throwable t) {
            Log.w(TAG, "unable recycle bitmap", t);
        }
    }

    public static Bitmap rotateBitmap(Bitmap source, int rotation, boolean recycle) {
        if (rotation == 0)
            return source;
        int w = source.getWidth();
        int h = source.getHeight();
        Matrix m = new Matrix();
        m.postRotate(rotation);
        Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, w, h, m, true);
        if (recycle)
            source.recycle();
        return bitmap;
    }

    public static Bitmap createVideoThumbnail(String filePath) {
        // MediaMetadataRetriever is available on API Level 8
        // but is hidden until API Level 10
        Class<?> clazz = null;
        Object instance = null;
        try {
            clazz = Class.forName("android.media.MediaMetadataRetriever");
            instance = clazz.newInstance();

            Method method = clazz.getMethod("setDataSource", String.class);
            method.invoke(instance, filePath);

            // The method name changes between API Level 9 and 10.
            if (Build.VERSION.SDK_INT <= 9) {
                return (Bitmap) clazz.getMethod("captureFrame").invoke(instance);
            } else {
                byte[] data = (byte[]) clazz.getMethod("getEmbeddedPicture").invoke(instance);
                if (data != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    if (bitmap != null)
                        return bitmap;
                }
                return (Bitmap) clazz.getMethod("getFrameAtTime").invoke(instance);
            }
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } catch (InstantiationException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } finally {
            try {
                if (instance != null) {
                    clazz.getMethod("release").invoke(instance);
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public static byte[] compressToBytes(Bitmap bitmap) {
        return compressToBytes(bitmap, DEFAULT_JPEG_QUALITY);
    }

    public static byte[] compressToBytes(Bitmap bitmap, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(65536);
        bitmap.compress(CompressFormat.JPEG, quality, baos);
        return baos.toByteArray();
    }

    public static boolean isSupportedByRegionDecoder(String mimeType) {
        if (mimeType == null)
            return false;
        mimeType = mimeType.toLowerCase();
        return mimeType.startsWith("image/") && (!mimeType.equals("image/gif") && !mimeType.endsWith("bmp"));
    }

    public static boolean isRotationSupported(String mimeType) {
        if (mimeType == null)
            return false;
        mimeType = mimeType.toLowerCase();
        return mimeType.equals("image/jpeg");
    }

    public static Bitmap createRoundBitmap(Bitmap bitmap) {
        if (bitmap == null)
            return null;
        Bitmap rounder = createRoundBitmapNoRecycle(bitmap);
        bitmap.recycle();
        return rounder;
    }

    public static Bitmap createRoundBitmapNoRecycle(Bitmap bitmap) {
        if (bitmap == null)
            return null;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap rounder = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(rounder);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        canvas.drawRoundRect(new RectF(0, 0, w, h), 20.0f, 20.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, new Rect(0, 0, w, h), new Rect(0, 0, w, h), paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return rounder;
    }

    public static Bitmap copyBitmap(Bitmap bitmap) {
        if (bitmap == null)
            return null;
        try {
            Bitmap copy = Bitmap.createBitmap(bitmap);
            return copy;
        } catch (OutOfMemoryError error) {
            return null;
        }
    }

    public static Bitmap resizeFromTop(Bitmap bitmap, int width, int height) {
        Bitmap tempBitmap;
        float dstRatio = (float) width / height;
        float srcRatio = (float) (bitmap.getWidth()) / (bitmap.getHeight());

        if (Math.abs(dstRatio - srcRatio) < 0.01f) {
            return resizeBitmapByScale(bitmap, (float) width / bitmap.getWidth(), false);
        } else {
            if (width >= bitmap.getWidth() || height >= bitmap.getHeight()) {
                tempBitmap = resizeBitmapByScale(
                        bitmap, Math.max((float) width / bitmap.getWidth(), (float) height / bitmap.getHeight()), false);
            } else {
                tempBitmap = resizeBitmapByScale(
                        bitmap, Math.max((float) width / bitmap.getWidth(), (float) height / bitmap.getHeight()), false);
            }
            if (tempBitmap == null)
                return null;
            return cropCenter(tempBitmap, width, height, tempBitmap != bitmap);
        }
    }

    private static Bitmap cropCenter(Bitmap bitmap, int dstWidth, int dstHeight, boolean recycle) {
        long startTime = System.currentTimeMillis();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap target = null;
        boolean createSucess = false;
        try {
            target = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888);
            createSucess = true;
        } catch (OutOfMemoryError error) {
        }

        if (!createSucess) {
            try {
                target = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.RGB_565);
                createSucess = true;
            } catch (OutOfMemoryError error) {
            }
        }

        if (!createSucess)
            return null;

        Canvas canvas = new Canvas(target);
        canvas.translate((dstWidth - width) / 2, (dstHeight - height) / 2);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle)
            bitmap.recycle();
        int twidth = target.getWidth();
        int theight = target.getHeight();
        return target;
    }

    // Returns the next power of two.
    // Returns the input if it is already power of 2.
    // Throws IllegalArgumentException if the input is <= 0 or
    // the answer overflows.
    public static int nextPowerOf2(int n) {
        if (n <= 0 || n > (1 << 30))
            throw new IllegalArgumentException("n is invalid: " + n);
        n -= 1;
        n |= n >> 16;
        n |= n >> 8;
        n |= n >> 4;
        n |= n >> 2;
        n |= n >> 1;
        return n + 1;
    }

    // Returns the previous power of two.
    // Returns the input if it is already power of 2.
    // Throws IllegalArgumentException if the input is <= 0
    public static int prevPowerOf2(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        return Integer.highestOneBit(n);
    }

    public static void assertTrue(boolean cond) {
        if (!cond) {
            throw new AssertionError();
        }
    }

    /**
     * 读取图片属性：旋转的角度.
     * 
     * @param path
     *            图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static String getRealPathFromUri(ContentResolver resolver, Uri contentURI) {
        String result;
        Cursor cursor = resolver.query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
