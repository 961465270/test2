package com.qdu.test.controller;

import com.qdu.test.bean.Food;
import com.qdu.test.bean.Page;
import com.qdu.test.bean.Ping;
import com.qdu.test.bean.Users;
import com.qdu.test.service.FoodService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2020/5/14.
 */
@Controller
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;
    @Value("${file.location}")
    public String location;

    @Value("${file.savePrefix}")
    public String savePrefix;

    @RequestMapping("/to_add")
    public String toAdd(){
        return "food/add";
    }


    @RequestMapping("/add")
    public String addFood(Food food,
                          @RequestParam("picture")MultipartFile file,
                          HttpServletRequest request) throws IOException {
        //写入用户名
        HttpSession session = request.getSession();
        Users users =  (Users) session.getAttribute("user");
        String userName = users.getName();
        food.setUserName(userName);
        //处理图片文件
        //1.获取文件名称,获取文件后缀
        String oldFileName = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(oldFileName);
        //2.创建一个新的文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String newFileName = uuid+"."+suffix;
        //3.创建新文件
        File destFile = new File(location,newFileName);
        //4.将上传的文件写道新文件上
        file.transferTo(destFile);

        food.setPic(savePrefix + newFileName);

        foodService.addFood(food);
        return "redirect:/food/to_list";


    }
    @GetMapping("/list")
    @ResponseBody
    public Page<Food> list(
            @RequestParam(required = false,defaultValue = "1")int pageNum,
            @RequestParam(required = false,defaultValue = "2")int pageSize,
            Food food
    ){
        return foodService.queryList(pageNum,pageSize,food);
    }

    @RequestMapping("/to_list")
    public String toListPage() {
        return "food/list";
    }

    @RequestMapping("/ping")
    @ResponseBody
    public List<Ping> queryPing( Integer foodId){
          return foodService.queryPing(foodId);
    }
    @RequestMapping("/insert")
    public String insert(Ping ping){
        foodService.insert(ping);
        return "redirect:/food/to_list";
    }


}
