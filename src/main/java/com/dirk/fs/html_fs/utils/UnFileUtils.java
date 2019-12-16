package com.dirk.fs.html_fs.utils;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileInputStream;
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
    public static String unZip(File zip,
                               String ftpHost,
                               Integer ftpPort,
                               Integer nginxPort,
                               String ftpUsername,
                               String ftpPassword,
                               String ftpHome,
                               String username) throws IOException {
        String url = null;
        ZipFile zipFile = new ZipFile(zip);
        String filePath;
        InputStream is;
        String filename;
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            filePath = "/" + username;
            is = null;
            filename = null;
            String[] split = zipEntry.getName().split("/");
            int splen = split.length;
            if (split[split.length - 1].contains(".")) {
                is = zipFile.getInputStream(zipEntry);
                filename = split[split.length - 1];
                splen--;
            }
            for (int i = 0; i < splen; i++) {
                filePath += "/" + split[i];
            }
            if ("index.html".equals(filename) || "index.htm".equals(filename)) {
                url = new String(ftpHost + ":" + nginxPort + filePath);
                System.out.println("url = " + url);
            }
            boolean b = FileUtils.uploadFile(ftpHost,
                    ftpPort,
                    ftpUsername,
                    ftpPassword,
                    ftpHome,
                    filePath,
                    filename,
                    is);
            System.out.println("filePath = " + filePath);
            System.out.println("filename = " + filename);
            System.out.println("b = " + b);
        }

        return url;
    }

    /**
     * 传入rar文件，解压
     *
     * @param rar
     */
    public static String unRar(File rar,
                               String ftpHost,
                               Integer ftpPort,
                               Integer nginxPort,
                               String ftpUsername,
                               String ftpPassword,
                               String ftpHome,
                               String username) throws Exception {
        Archive archive = new Archive(new FileInputStream(rar));
        String url = null;
        String filePath;
        InputStream is;
        String filename;
        FileHeader fileHeader;
        while ((fileHeader = archive.nextFileHeader()) != null) {
            filePath = "/" + username;
            is = null;
            filename = null;
            if (fileHeader.isDirectory()) {
                filePath += "/" + fileHeader.getFileNameString().replaceAll("\\\\", "/");
                FileUtils.uploadFile(ftpHost,
                        ftpPort,
                        ftpUsername,
                        ftpPassword,
                        ftpHome,
                        filePath,
                        filename,
                        is);
            } else {

                String[] split = fileHeader.getFileNameString().replaceAll("\\\\", "/").split("/");
                for (int i = 0; i < split.length - 1; i++) {
                    filePath += "/" + split[i];
                }
                filename = split[split.length - 1];
                is = archive.getInputStream(fileHeader);
                boolean b = FileUtils.uploadFile(ftpHost,
                        ftpPort,
                        ftpUsername,
                        ftpPassword,
                        ftpHome,
                        filePath,
                        filename,
                        is);
                if ("index.html".equals(filename) || "index.htm".equals(filename)) {
                    url = new String(ftpHost + ":" + nginxPort + filePath);
                    System.out.println("url = " + url);
                }
                System.out.println("filePath = " + filePath);
                System.out.println("filename = " + filename);
                System.out.println("b = " + b);
            }
        }
        archive.close();
        return url;
    }
}
