package com.example.lilong.Tool.Utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by long on 2018-4-12.
 */
public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {

            try {
                io.close();

            } catch (IOException e) {

                LogUtils.e(e.toString());

            }
        }
        return true;
    }


}
