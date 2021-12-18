package sspu.informationsystem.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Component
public class FtpUtil {

//    public String upload(MultipartFile file){
//        if (file.isEmpty()){
//            return  null;
//        }
//        String fileName = file.getOriginalFilename();
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        String filePath = "D://study//file";
//        fileName = UUID.randomUUID()+suffixName;
//        File dest = new File(filePath + fileName);
//        if (!dest.getParentFile().exists()){
//            dest.getParentFile().mkdir();
//        }
//        try {
//            file.transferTo(dest);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        String filename = "/file/"+fileName;
//        return "file";
//    }

    public String uplaod(MultipartFile file) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        String fileName1 = "";
        try {

            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            fileName1 = fileName;
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
//            String destFileName = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
            String destFileName = "D:\\idea project\\information-system\\src\\main\\resources\\static\\images\\"+fileName;
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }

        return fileName1;
    }
}