package com.dirk.fs.html_fs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Zip解压缩
 * @author Ranger
 * @create 2019-12-15 9:42
 */
public class ZipTest {
    public static void main(String[] args) throws IOException {
        ZipFile zipFile = new ZipFile(new File("d:\\zf\\z.zip"));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()){
            ZipEntry zipEntry = entries.nextElement();
            if(zipEntry.isDirectory()){
                File f = new File("d:\\zf\\" + zipEntry.getName());
                if(!f.exists())
                    f.mkdirs();
            }else {
                File f = new File("d:\\zf\\" + zipEntry.getName());
                if(!f.exists())
                    f.createNewFile();
                InputStream is = zipFile.getInputStream(zipEntry);
                OutputStream os = new FileOutputStream(f);
                byte[] buf = new byte[2048];
                int len;
                while ((len=is.read(buf))!=-1)
                    os.write(buf, 0, len);
                os.close();
                is.close();
            }
        }
    }
}
