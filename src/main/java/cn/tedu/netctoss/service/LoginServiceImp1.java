package cn.tedu.netctoss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.netctoss.dao.AdminDAO;
import cn.tedu.netctoss.entity.Admin;

@Service("loginService")
public class LoginServiceImp1 implements LoginService {
	@Resource(name="adminDAO")
	private AdminDAO dao;
	public Admin checkLogin(String code, String pwd) {
		Admin admin = null;
		admin = dao.findByCode(code);
		System.out.println(admin);
		if(admin == null){
			//帐号不存在,抛出应用异常。
			//(了解)
			//应用异常指的是因为用户不正确的操作引起的异常（比如输入了错误的帐号密码）
			//对于应用异常，需要提示用户采取正确的操作。
			throw new AppException("帐号错误");
			
		}
		if(!admin.getPassword().equals(pwd)){
			//密码错误;
			throw new AppException("密码错误");
		}
		//验证通过
		return admin;
	}

}
