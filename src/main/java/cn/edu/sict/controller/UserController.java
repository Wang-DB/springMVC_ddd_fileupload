package cn.edu.sict.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/fileUpload")
    public String fileUpload(HttpServletRequest request) throws Exception {
        System.out.println("文件上传..");
//        上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
//        判断路径是否存在
        File file =new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
//        解析request对象 获取上传文件项
        DiskFileItemFactory factory =new DiskFileItemFactory();
        ServletFileUpload upload =new ServletFileUpload(factory);
//        解析request
        List<FileItem> items = upload.parseRequest(request);
//       遍历
        for (FileItem item:items
        ) {
            if (item.isFormField()){
//                说明普通标单项
            }else {
//                说明文件上传项
                String name = item.getName();
//                设置唯一名称
                String replace = UUID.randomUUID().toString().replace("-", "");
                name = replace +"_"+name;
                item.write(new File(path,name));
                item.delete();
            }
        }
        return "success";
    }
    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("SpringMVC文件上传..");
//        上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
//        判断路径是否存在
        File file =new File(path);
        if (!file.exists()){
            file.mkdirs();
        }

//                说明文件上传项
        String filename = upload.getOriginalFilename();

//                设置唯一名称
                String replace = UUID.randomUUID().toString().replace("-", "");
                filename = replace +"_"+filename;
                upload.transferTo(new File(path,filename));

        return "success";
    }
    @RequestMapping("/fileUpload3")
    public String fileUpload3( MultipartFile upload) throws Exception {
        System.out.println("SpringMVC跨服务器文件上传..");
//        定义上传服务器路径
        String url ="http://localhost:9090/uploads/";

//                说明文件上传项
        String filename = upload.getOriginalFilename();

//                设置唯一名称
        String replace = UUID.randomUUID().toString().replace("-", "");
        filename = replace +"_"+filename;
//        创建客户端对象
        Client client = Client.create();
//        和图片服务器链接
        WebResource webResource = client.resource(url + filename);
//       文件上传
        webResource.put(upload.getBytes());

        return "success";
    }
}
