package com.qdu.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdu.test.bean.Page;
import com.qdu.test.bean.Users;
import com.qdu.test.exception.LoginException;
import com.qdu.test.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/5/3.
 */
@Controller
@RequestMapping("/users")
@Slf4j
public class UsersController {

    private static final String DEST_FILE_DIR = "d:/upload";

    @Autowired
    UsersService usersService;

    @RequestMapping("/to_add")
    public String toAdd(){
        return "users/add";
    }

    @RequestMapping("/add")
    public String addUsers(Users users){
        usersService.addUsers(users);
        return "redirect:/users/to_login";
    }

    @RequestMapping("/to_list")
    public String toList(){
        return "users/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Page<Users> queryUsers(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "5") int pageSize
    ){

        return  usersService.queryUsers(pageNum,pageSize);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") int id){
                usersService.deleteById(id);
                return "redirect:/users/to_list";
    }
    @PutMapping("/{id}")
    public String updateUsers(Users users){
        usersService.updateUsers(users);
        return "redirect:/users/to_list";
    }

    @RequestMapping(value = "/checkUsers")
    @ResponseBody
    public  Boolean checkUsers(@RequestParam String name){
        return usersService.checkUsers(name);


    }



    @RequestMapping("/to_login")
    public String toLoginPage() {
        return "users/login";
    }



    @RequestMapping("/login")
    public String login(HttpServletRequest request, //
                        Users user,@RequestParam String verifyCode) {

        // 判断验证码
        HttpSession session = request.getSession();
        String code = String.valueOf(session.getAttribute("verifyCode"));
        if (!StringUtils.equalsIgnoreCase(verifyCode, code)) {
            throw new LoginException("验证码错误");
            // return "redirect:/user/to_login";
        }


        //String password = DigestUtils.md5Hex(user.getPassword());
        user = usersService.queryUser(user.getName(), user.getPassword());
        if (ObjectUtils.isEmpty(user)) {
            throw new LoginException("用户名或密码错误");
            //return "redirect:/user/to_login";
        }
        session.setAttribute("user", user);
        if (user.getLevel()==1){
            return "redirect:/users/admin";
        }
        return "redirect:/users/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        // 销毁session，这样的话，session中所有的数据都会被清空
        session.invalidate();
        return "redirect:/users/to_login";
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }
    @RequestMapping("/admin")
    public String toAdmin(){
        return "admin";
    }

    @PutMapping("/update")
    public String updateUser(Users users){
        usersService.updateUsers(users);
        return "redirect:/food/to_list";
    }





}
