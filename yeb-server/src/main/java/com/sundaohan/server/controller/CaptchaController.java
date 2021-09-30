package com.sundaohan.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sundaohan.server.utils.FastDFSUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.controller
 * @Title CaptchaController
 * @Description TODO
 * @Date 2021/7/24 下午9:27
 */
@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    private static Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    @ApiOperation(value = "验证码",produces = "image/jpeg")
    @GetMapping(value = "/captcha",produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        System.out.println("收到验证码请求,request = " + request + "" +"\n"+ "response = " + response);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control", "post-check=0,pre-check0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");
        System.out.println("responseDecontentType是 = " + response.getContentType());
        //------------------------生成验证码 begin-------------------------
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容" + text);
        logger.info("验证码内容{}",text);
        //将验证码内容放入session
        request.getSession().setAttribute("captcha",text);
        //根据文本验证码内容创建图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try{
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try{
                    outputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        //------------------------生成验证码 end---------------------------
    }
}
