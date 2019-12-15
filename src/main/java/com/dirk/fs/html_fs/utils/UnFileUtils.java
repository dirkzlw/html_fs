package com.dirk.fs.html_fs.utils;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public static void unZip(File zip,String host,String username,String password,String ftpHome) throws IOException {
        ZipFile zipFile = new ZipFile(zip);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            String[] split = zipEntry.getName().split("/");
            String filePath = "/html";
            InputStream is = null;
            String filename = null;
            int splen = split.length;
            if (split[split.length - 1].contains(".")) {
                is = zipFile.getInputStream(zipEntry);
                filename = split[split.length - 1];
                splen--;
            }
            for (int i = 0; i < splen ; i++) {
                filePath += "/" + split[i];
            }
            boolean b = FileUtils.uploadFile(host,
                    21,
                    username,
                    password,
                    ftpHome,
                    filePath,
                    filename,
                    is);
            System.out.println("filePath = " + filePath);
            System.out.println("filename = " + filename);
            System.out.println("b = " + b);
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
                if (!out.exists()) {
                    if (!out.getParentFile().exists()) {
                        out.getParentFile().mkdirs();
                    }
                    out.createNewFile();
                }
                FileOutputStream os = new FileOutputStream(out);
                archive.extractFile(fileHeader, os);
                os.close();
            }
        }
        archive.close();
    }
}
