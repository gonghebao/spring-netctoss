package cn.tedu.netctoss.service;

import cn.tedu.netctoss.entity.Admin;

/**
 * 业务层接口
 * @author soft01
 *
 */
public interface LoginService {
	public Admin checkLogin(String code,String pwd);
}
