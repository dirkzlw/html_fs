package com.dirk.fs.html_fs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ranger
 * @create 2019-12-15 11:52
 */
public class FileUtils {
    public static void inputStreamToFile(InputStream is,File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int len;
        byte[] b = new byte[2048];
        while ((len=is.read(b))!=-1){
            os.write(b, 0,len );
        }
    }
}
