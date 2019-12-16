package com.dirk.fs.html_fs.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ranger
 * @create 2019-12-15 11:52
 */
public class FileUtils {
    /**
     * 输入流转为File
     *
     * @param is
     * @param file
     * @throws IOException
     */
    public static void inputStreamToFile(InputStream is, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int len;
        byte[] b = new byte[2048];
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
        if (null != os)
            os.close();
        if (null != is)
            is.close();
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param host     FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host,
                                     int port,
                                     String username,
                                     String password,
                                     String basePath,
                                     String filePath,
                                     String filename,
                                     InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(host, port);
            ftp.login(username, password);
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                            //单纯是创建目录
                            if (null == filename) {
                                return true;
                            }
                        }
                    }
                }
            }
            //为了加大上传文件速度，将InputStream转成BufferInputStream
            BufferedInputStream in = new BufferedInputStream(input);
            //加大缓存区
            ftp.setBufferSize(1024 * 1024);
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, in)) {
                return result;
            }
            in.close();
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }
}
