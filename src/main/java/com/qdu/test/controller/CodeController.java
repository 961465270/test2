package com.qdu.test.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qdu.test.common.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CodeController {
    
    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.createImage();
        
        // 把图片上表达式的计算结果保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode", verifyCode.getResult());
        
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }
    
}
