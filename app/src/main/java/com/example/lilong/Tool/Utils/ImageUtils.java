package com.example.lilong.Tool.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Base64;

import com.example.lilong.R;
import com.example.lilong.Tool.APP.App;
import com.example.lilong.Tool.APP.Builds;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPOutputStream;

/**
 * Created by chen on 2016/8/15.
 */
public class ImageUtils {

    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）：从本地压缩后读入内存
     * @param imagePath 图片路径
     * @param size 压缩后的最大值，单位为kb
     */
    public static Bitmap compressImage(String imagePath, int size){

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath,newOpts);//此时返回bm为空
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        float hh = 800f;
        float ww = 480f;
        int be = 1;//
        if (w > h && w > ww) {
            //如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            //如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例

        newOpts.inJustDecodeBounds = false;

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(imagePath, newOpts);

//        return bitmap;
        return compressImageWithSize(bitmap,size);//压缩好比例大小后再进行质量压缩
    }


    /**
     * 按质量压缩图片，压缩后的图片大小不超过指定的大小
     * @param bitmap 图片
     * @param size 指定压缩后的图片最大值，单位为KB
     * @return
     */
    public static Bitmap compressImageWithSize(Bitmap bitmap, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;//
        while ( baos.toByteArray().length / 1024 > size && options > 0 ) {
            baos.reset();
            options -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        if (bitmap != null){
            bitmap.recycle();
        }
        //把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());

        return BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
    }


    /**
     * 上传图片前对图片的处理
     * @param path
     * @param imageName
     * @param text
     * @param subText
     * @return
     */
    public static String dealPicBeforeUpload(String path, String imageName, String text,
                                             String subText){

        String savePath = compressPic(path,imageName);

        Bitmap bitmap = drawTextToBitmap(savePath,text,subText);  //添加水印到bitmap图

        File file = FileUtils.createFile(Builds.IMAGE_WATERMASK_PATH,imageName);
        String wkPath = file.getAbsolutePath();

//        ImageCompress.nativeCompressBitmap(bitmap,80,wkPath,true);

        return wkPath;

    }



    public static String compressPic(String path, String imageName){
        Bitmap bitmap = null;
        try{
            File file=new File(path);
            String uri = file.getAbsolutePath();

            bitmap = App.getImageLoader().loadImageSync("file://" + uri,new ImageSize(1600,960));
        }catch (Exception e){
            e.printStackTrace();
        }
        if (bitmap == null){
            return null;
        }
        File file = FileUtils.createFile(Builds.IMAGE_COMPRESS_PATH,imageName);
        String destPath = file.getAbsolutePath();

//        ImageCompress.nativeCompressBitmap(bitmap, 30, destPath, true);

        if (bitmap != null){
            bitmap.recycle();
        }
        return destPath;
    }

//    /**
//     * 在上传图片之前对图片进行压缩
//     * @return
//     */
//    public static String compressImageBeforeUpload(String path,int size,String imageName) {
//
//        File file = FileUtils.createFile(Build.IMAGE_COMPRESS_PATH,imageName);
//
//        try {
//
//            Bitmap newBitmap = compressImage(path,size);
//
//            if (newBitmap !=null) {
//
//                if (file.exists()) {
//                    file.delete();
//                }
//                file.createNewFile();
//
//                OutputStream os = new FileOutputStream(file);
//
//                newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
//                os.close();
//                newBitmap.recycle();
//            }
//        } catch (Exception e) {
//            LogUtils.d("compressError-->"+e.getMessage());
//            e.printStackTrace();
//        }
//
//        return file.getAbsolutePath();
//    }


    /**
     * 图片Base64加密处理
     * @param path
     * @return
     */
    public static String imageToBase64(String path){
        String imageData = "";
        byte[] data = null;
        try{
            InputStream inputStream = new FileInputStream(path);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        }catch(IOException e){
            e.printStackTrace();

            String msg = "";
            if (e != null){
                msg = "message : " + e.getMessage() + " ; exception ：" + e.toString();
            }
        }

        if (data != null){
            imageData = Base64.encodeToString(data,0,data.length, Base64.DEFAULT);
        }

        return imageData;
    }

    /**
     * base64转gzip
     * @param base64
     * @return
     */
    public static String base64ToGZip(String base64) throws IOException {
        if (base64 == null || base64.length() == 0) {
            return base64;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip=null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(base64.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.toString("ISO-8859-1");
    }

    /**
     * 将Bitmap保存到文件中
     * @param bitmap 图片
     * @param dirPath  目录
     * @param fileName  文件名
     */
    public static String saveBitmapFile(Bitmap bitmap, String dirPath, String fileName){

        File file = FileUtils.createFile(dirPath,fileName);

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bitmap != null){
            bitmap.recycle();
        }

        return file.getAbsolutePath();
    }

    /**
     * 在图片上添加水印图片
     * @param bitmap
     * @param maskBitmap
     * @return
     */
    public static Bitmap createWaterMaskImage(Bitmap bitmap, Bitmap maskBitmap){
        Bitmap newBitmap = null;
        if (bitmap == null){
            return newBitmap;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        newBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(bitmap,0,0,null);  //从(0,0)坐标开始画原图bitmap
        canvas.drawBitmap(maskBitmap,20,20,null); //从(20,20)处开始画水印图片
        canvas.save(Canvas.ALL_SAVE_FLAG);   //保存
        canvas.restore();   //存储

        return newBitmap;
    }

    /**
     * 在图片上添加水印文字
     * @param text 水印的文字
     * @return
     */
    public static Bitmap drawTextToBitmap(String path, String text,
                                          String subText){

        Bitmap newBitmap = null;

        try{
            newBitmap = App.getImageLoader().loadImageSync("file://"+path);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (newBitmap == null){
            return null;
        }

        int width = newBitmap.getWidth();
        int height = newBitmap.getHeight();

//        Bitmap wkBitmap = newBitmap.copy(Bitmap.Config.RGB_565,true);

        Bitmap wkBitmap = copy(newBitmap);

        newBitmap.recycle();

        if (wkBitmap == null){
            return null;
        }

        Canvas canvas = new Canvas(wkBitmap);
        //根据画布的大小比例计算画笔的大小
        float canvasWidth = (float)canvas.getWidth() / 1334f;
        float canvasHeight = (float)canvas.getHeight() / 750f;
        float size = Math.min(canvasWidth,canvasHeight);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ResUtils.getColor(R.color.white_text));
        paint.setTextSize(24 * size);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));  //设置粗体
        paint.setDither(true); //获取跟清晰的图像采样
        paint.setFilterBitmap(true);//过滤一些
        paint.setShadowLayer(2, 1, 1, ResUtils.getColor(R.color.shadow_text));

        if (!TextUtils.isEmpty(text)){
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);

            canvas.drawText(text,width-bounds.width()-UIUtils.dp2Px(8)* size,height-bounds.height()
                    -UIUtils.dp2Px(16) * size, paint);
        }

        if (!TextUtils.isEmpty(subText)){
            Rect bounds2 = new Rect();
            paint.getTextBounds(subText, 0, subText.length(), bounds2);

            canvas.drawText(subText,width-bounds2.width()-UIUtils.dp2Px(8) * size,height-bounds2
                    .height(), paint);
        }

        canvas.save();
        canvas.restore();

        return wkBitmap;
    }


    /**
     * 复制bitmap
     * @param srcBmp
     * @return
     */
    public static Bitmap copy(Bitmap srcBmp){

        Bitmap destBmp = null;

        File file = FileUtils.createFile(Builds.LOCAL_HOST,"temp.text");

        try{
            //创建一个临时文件
            file.getParentFile().mkdirs();


            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");


            int width = srcBmp.getWidth();
            int height = srcBmp.getHeight();


            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, width*height*4);

            //将位图信息写进buffer
            srcBmp.copyPixelsToBuffer(map);

            //释放原位图占用的空间
            srcBmp.recycle();

            //创建一个新的位图
            destBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            map.position(0);

            //从临时缓冲中拷贝位图信息
            destBmp.copyPixelsFromBuffer(map);

            channel.close();
            randomAccessFile.close();
        }
        catch(Exception ex){

            destBmp=null;
        }

        FileUtils.deleteFile(file.getAbsolutePath());

        return destBmp;
    }

    public static Bitmap getBitmap(String imageUrl) {
        Bitmap mBitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            mBitmap = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mBitmap;
    }

}