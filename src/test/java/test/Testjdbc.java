package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.netctoss.dao.AdminDAO;
import cn.tedu.netctoss.entity.Admin;
import cn.tedu.netctoss.service.LoginService;

public class Testjdbc {
	@Test
	//测试 连接池
	public void test(){
		String config = "spring-mvc.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		/*
		 * 	javax.sql.DataSource是一个接口
		 *  BasicDataSource实现了上述接口。
		 *  C3P0
		 * */
		DataSource bds = ac.getBean("d1",DataSource.class);
		try {
			System.out.println(bds.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	//测试数据访问层
	public void test2(){
		String config = "spring-mvc.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		AdminDAO dao = ac.getBean("adminDAO",AdminDAO.class);
		Admin admin = dao.findByCode("caocao");
		System.out.println(admin);
	}
	@Test
	//测试 业务层
	public void test3(){
		String config = "spring-mvc.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		LoginService ls = ac.getBean("loginService",LoginService.class);
		Admin admin =  ls.checkLogin("caocao","123");
		System.out.println(admin);
	}
}
