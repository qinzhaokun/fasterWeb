package com.fasterweb.controller;

import com.fasterweb.model.ResponseData;
import com.fasterweb.model.UserTest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProcessJSONController {
    /**
     *
     * @return
     * @Description 返回Json字符串
     */

    @RequestMapping("/getuser")
    @ResponseBody
    public UserTest getUser(){
        UserTest user = new UserTest();
        user.setPassword("123");
        user.setUserName("jack");
        return user;
    }


    /**
     *
     * @param userInfo
     * @return
     * @Description post JSON字符串，并自动装箱
     */

    // http 请求的context-type必须是application/json;charset=utf-8
    @RequestMapping(value="setuser",method= RequestMethod.POST)
    @ResponseBody
    public ResponseData getUser1(@RequestBody @Valid UserTest userInfo, BindingResult bingingresult){
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
    }}
