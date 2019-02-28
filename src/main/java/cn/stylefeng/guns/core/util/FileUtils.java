package cn.stylefeng.guns.core.util;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * Created by administartor on 2018/2/26.
 */
public class FileUtils {

    private static GunsProperties properties = SpringContextHolder.getBean(GunsProperties.class);

    public static String uploads(String dir, MultipartFile file) throws Exception {
        return uploads(properties.getFileUploadPath(), dir, null, new MultipartFile[]{file});
    }

    public static String uploads(String tempPath, String realPath, String fileType, MultipartFile[] files) throws Exception {
        String filePath = "";
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    if (StringUtil2.isEmpty(fileType)) {
                        String fileName = file.getOriginalFilename();
                        // 文件的后缀名
                        fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    String timeStamp = sdf.format(new Date());

                    // 时间戳命名文件
                    String newName = timeStamp + (int) (Math.random() * 900 + 100) + "." + fileType;
                    String dir = tempPath + "/" + realPath;

                    // 创建一个目录
                    File targetFile = new File(dir, newName);

                    // 如果目录不存在就创建
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    // 上传文件
                    file.transferTo(targetFile);

                    // 返回图片的真实地址用于数据库的保存
                    String file_path = realPath + "/" + newName;

                    filePath += file_path + ",";
                }
            }
            return filePath.substring(0, filePath.length() - 1);
        }
        return filePath;
    }
    public static void downLoadFile(String path, HttpServletResponse response, String fileName) {
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            File file = new File(path);
            String fileName1 = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName1 + ".zip");
            BufferedInputStream bis = null;
            CheckedInputStream checkedInputStream=new CheckedInputStream(new FileInputStream(file),new CRC32());
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(checkedInputStream);
                bos = new BufferedOutputStream(response.getOutputStream());

                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                //bos.flush();

            } catch (final IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    bis.close();
                    bis = null;
                }
                if (bos != null) {
                    bos.close();
                    bos = null;
                }
                checkedInputStream.close();
                System.out.println("后"+checkedInputStream.getChecksum().getValue());

            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
