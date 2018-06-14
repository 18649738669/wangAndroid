package com.example.lilong.Tool.Utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.example.lilong.Tool.APP.Builds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by long on 2018-4-12.
 * 功能：文件处理工具类
 */
public class FileUtils {

    /**
     * 取得SDCard路径
     * @return 返回sdCard路径 不管是否挂载都存在这个路径一般为 /mnt/sdcard
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 取得SDCard路径
     * @return 返回sdCard文件类型 不管是否挂载都存在这个路径一般为 /mnt/sdcard
     */
    public File getSDCard() {
        File sdcard = Environment.getExternalStorageDirectory();
        return sdcard == null ? null : sdcard;
    }

    /**
     * 检验SDCard 是否存在（已经挂载）
     * @return SDCard 是否存在（已经挂载）
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 检验SDCard 是否存在（已经挂载）且可写
     * @return SDCard 是否存在（已经挂载）且可写
     */
    public static boolean isSDCardWritable() {
        if (isSDCardExist()) {
            return !Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
        }
        return false;
    }

    /**
     * 根据路径和文件名称删除文件
     * @param dirPath 目录
     * @param name 文件名称
     * @return
     */
    public static boolean deleteFile(String dirPath, String name) {
        boolean isDelete = false;
        if (!TextUtils.isEmpty(name) && !"null".equals(name)) {
            File file = new File(dirPath, name);
            if (file != null && file.exists()) {
                isDelete = file.delete();
            }
        }
        return isDelete;
    }

    /**
     * 根据文件的绝对路径进行删除文件
     * @param filePath
     */
    public static boolean deleteFile(String filePath) {
        boolean isDelete = false;
        if (!TextUtils.isEmpty(filePath) && !"null".equals(filePath)){
            File file = new File(filePath);
            if (file != null && file.exists()){
                isDelete = file.delete();
            }
        }
        return isDelete;
    }

    /**
     * 根据文件的绝对路径进行删除文件
     * @param filePath
     */
    public static boolean isFileExist(String filePath) {
        boolean isExist = false;
        if (!TextUtils.isEmpty(filePath) && !"null".equals(filePath)){
            File file = new File(filePath);
            if (file != null && file.exists()){
                isExist = true;
            }
        }
        return isExist;
    }

    /**
     * 创建文件
     * @param dir 文件目录
     * @param name 文件名称
     * @return
     */
    public static File createFile(String dir, String name ){
        File dirFile = new File(dir);

        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = new File(dirFile, name);

        return file;
    }

    /**
     * 文件复制
     * @param fromFile
     * @param toFile
     * @param rewrite
     */
    public static void copyFile(File fromFile, File toFile, Boolean rewrite ){

        if(!fromFile.exists()){
            return;
        }

        if(!fromFile.isFile()){
            return;
        }
        if(!fromFile.canRead()){
            return;
        }
        if(!toFile.getParentFile().exists()){
            toFile.getParentFile().mkdirs();
        }
        if(toFile.exists() && rewrite){
            toFile.delete();
        }

        try {
            FileInputStream fosfrom = new FileInputStream(fromFile);
            FileOutputStream fosto = new FileOutputStream(toFile);

            byte[] bt = new byte[1024];
            int c;
            while((c=fosfrom.read(bt)) > 0){
                fosto.write(bt,0,c);
            }
            //关闭输入、输出流
            fosfrom.close();
            fosto.close();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 将bitmap 保存到本地SD卡
     * @param bitName  文件名字
     * @param mBitmap  文件
     */
    public static String saveBitmap(String bitName, Bitmap mBitmap){
        File f = new File( Builds.IMAGE_COMPRESS_PATH +"/"+ bitName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("保存压缩后的图片：","     在保存图片时出错："+e.toString()  );
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }

}
