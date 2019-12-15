package com.dirk.fs.html_fs.utils;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Ranger
 * @create 2019-12-15 10:28
 */
public class UnFileUtils {
    /**
     * 传入zip文件，解压
     *
     * @param zip
     * @throws IOException
     */
    public static void unZip(File zip) throws IOException {
        ZipFile zipFile = new ZipFile(zip);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            if (zipEntry.isDirectory()) {
                File f = new File("d:\\zf\\" + zipEntry.getName());
                if (!f.exists())
                    f.mkdirs();
            } else {
                File f = new File("d:\\zf\\" + zipEntry.getName());
                if (!f.exists())
                    f.createNewFile();
                InputStream is = zipFile.getInputStream(zipEntry);
                OutputStream os = new FileOutputStream(f);
                byte[] buf = new byte[2048];
                int len;
                while ((len = is.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.close();
                is.close();
            }
        }
    }

    /**
     * 传入rar文件，解压
     *
     * @param rar
     */
    public static void unRar(File rar) throws Exception {
        Archive archive = new Archive(new FileInputStream(rar));
        FileHeader fileHeader;
        while ((fileHeader = archive.nextFileHeader()) != null) {
            if (fileHeader.isDirectory()) {
                File dir = new File("d:\\zf\\" + fileHeader.getFileNameString());
                if (dir.isDirectory())
                    dir.mkdirs();
            } else {
                File out = new File("d:\\zf\\" + fileHeader.getFileNameString());
                if (!out.exists())
                    out.createNewFile();
                FileOutputStream os = new FileOutputStream(out);
                archive.extractFile(fileHeader, os);
                os.close();
            }
        }
        archive.close();
    }

}
