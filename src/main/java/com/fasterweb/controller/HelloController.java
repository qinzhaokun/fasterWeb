package com.fasterweb.controller;

import com.mysql.jdbc.Statement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;


@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?user=root&password=123";
            Connection con = DriverManager.getConnection(url);
            //Statement statement = con.createStatement();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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
