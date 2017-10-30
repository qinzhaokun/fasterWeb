package com.fasterweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
public class FileController {

    /**
     *
     * @param request
     * @param response
     * @param filename
     * @return
     * @throws IOException
     * @Description 下载文件
     */

    @RequestMapping(value = "download/{filename}", method = RequestMethod.GET)
    public String download(HttpServletRequest request, HttpServletResponse response, @PathVariable String filename) throws IOException{

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        System.out.println(request.getContextPath());
        String fullFileName = request.getServletContext().getRealPath("/")+ "/static/" + filename+".html";
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();

        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }

        in.close();
        out.close();

        return null;
    }

    /**
     *
     * @param req
     * @return
     * @throws Exception
     * @Description 上传文件
     */

    @RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest req) throws Exception{
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        System.out.println("receive file: " + fileName);

        return "ok";
    }
}

