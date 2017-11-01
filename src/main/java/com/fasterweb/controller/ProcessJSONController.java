package com.fasterweb.controller;


import com.fasterweb.model.ResponseData;
import com.fasterweb.model.User;
import com.fasterweb.model.UserTest;
import com.fasterweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ProcessJSONController {

    @Autowired
    private UserService userService;
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

    @RequestMapping("/getuser/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    @RequestMapping("/getalluser")
    @ResponseBody
    public List<User> getAllUser(){
        return userService.getAllUser();
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

        User user = new User();
        user.setUserName(userInfo.getUserName());
        user.setPassword(userInfo.getPassword());

        userService.addUser(user);

        ResponseData re = new ResponseData();
        re.setResult("ok");
        re.setReason("ok");

        return re;
    }

    /**
     *
     * @param map
     * @return
     * @Description post JSON字符串，并自动装箱成HashMap对象
     */

    @RequestMapping("setuser2")
    @ResponseBody
    public ResponseData getUserWithMap(@RequestBody Map map){
        System.out.println(map.get("userName"));
        System.out.println(map.get("password"));
        ResponseData re = new ResponseData();
        re.setResult("ok");
        re.setReason("ok");
        return re;
    }

}
