package com.qinzhaokun.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


class User{
    @NotEmpty(message = "{user.username.null}")
    private String userName;

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}

class ResponseData{
    private String result;
    private String reason;

    public String getReason() {
        return reason;
    }

    public String getResult() {
        return result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

    /**
     *
     * @return
     * @Description 返回Json字符串
     */

    @RequestMapping("/getuser")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setPassword("123");
        user.setUserName("jack");
        return user;
    }


    /**
     *
     * @param userInfo
     * @return
     * @Description post JSON类型数据，自动解析对应的类
     */

    // http 请求的context-type必须是application/json;charset=utf-8
    @RequestMapping(value="setuser",method=RequestMethod.POST)
    @ResponseBody
    public ResponseData getUser1(@RequestBody @Valid User userInfo, BindingResult bingingresult){
        if (bingingresult.hasErrors()) {
            ResponseData re = new ResponseData();
            List<ObjectError> list = bingingresult.getAllErrors();
            String errorInfo = "";
            for(ObjectError error: list){
                errorInfo += error.getDefaultMessage();//验证信息
            }
            re.setResult(errorInfo);
            re.setReason("error");
            return re;
        }
        System.out.println(userInfo.getPassword());
        System.out.println(userInfo.getUserName());

        ResponseData re = new ResponseData();
        re.setResult("ok");
        re.setReason("ok");

        return re;
    }

    /**
     *
     * @param userName
     * @return
     * @Description restFul API
     */

    @RequestMapping(value="/user/{userName}",method=RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable("userName") String userName){

        return "hello " + userName;
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
