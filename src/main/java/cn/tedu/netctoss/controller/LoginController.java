package cn.tedu.netctoss.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


import cn.tedu.netctoss.entity.Admin;
import cn.tedu.netctoss.entity.Cost;
import cn.tedu.netctoss.service.AppException;
import cn.tedu.netctoss.service.CostService;
import cn.tedu.netctoss.service.LoginService;
import cn.tedu.netctoss.util.ImageUtil;

@Controller
public class LoginController {
	@Resource(name="loginService")
	private LoginService ls;
	@RequestMapping("tologin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "login";
	}
	@RequestMapping("/checkcode.do")
	//输出一张图片
	public void checkcode(HttpServletResponse response,HttpSession session) throws IOException{
		Object[] objs = ImageUtil.createImage();
		BufferedImage image = (BufferedImage) objs[1];
		String number = (String) objs[0];
		System.out.println("number:"+number);
		session.setAttribute("imgcode",objs[0]);
		OutputStream output = response.getOutputStream();
		/*
		 * 将原始图片按照指定的压缩算法(jpeg)
		 * 进行压缩，然后发送给客户端。
		 * */
		javax.imageio.ImageIO.write(image,"jpeg",output);
		output.close();
	}
	@RequestMapping("/login.do")
	public String login(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws ServletException, IOException{
		System.out.println("login()");
		String adminCode = req.getParameter("adminCode");
		String pwd = req.getParameter("pwd");
		System.out.println("adminCode:"+adminCode);
		String imgcode = (String) session.getAttribute("imgcode");
		//将请求分发给业务层对象来处理
		//表示层需要处理业务层对象所抛出的异常
		if(imgcode==null || !imgcode.equalsIgnoreCase(req.getParameter("yzm"))){
			throw new AppException("验证码错误");
		}
//		try {
			Admin admin = ls.checkLogin(adminCode, pwd);
			session.setAttribute("admin",admin);
//		} catch (Exception e) {
//			e.printStackTrace();
//			if(e instanceof AppException){
//				//应用异常,
//				//明确提示用户采取正确的操作
//				request.setAttribute("login_failed",e.getMessage());
//				return "login";
//			}else{
//				//系统异常
//				//提示用户稍后重试
//				return "error";
//			}
//		}
		
		return "redirect:toIndex.do";
	}
	@ExceptionHandler
	public String handlerEx(Exception e,HttpServletRequest req){
		if(e instanceof AppException){
			req.setAttribute("login_failed",e.getMessage());
			return "login";
		}else{
			return "error";
		}
		
	}
}
