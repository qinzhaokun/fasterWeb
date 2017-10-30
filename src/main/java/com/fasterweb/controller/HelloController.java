package com.fasterweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world";
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


}
