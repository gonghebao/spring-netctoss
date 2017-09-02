package cn.tedu.netctoss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import cn.tedu.netctoss.entity.Admin;
/**
 * 数据访问层实现类
 * 持久
 * @author soft01
 *
 */
//@Repository("adminDAO")
public class AdminDAOJdbcImp1 implements AdminDAO {
	@Resource(name="d1")
	private DataSource ds;
	public Admin findByCode(String code) {
		Admin admin = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from ghb_admin_info where ADMIN_CODE=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				admin = new Admin();
				admin.setAdminCode(rs.getString("admin_code"));
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setEmail(rs.getString("email"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
				admin.setTelephone(rs.getString("telephone"));
				admin.setEnrolldate(rs.getDate("enrolldate"));
			}
		}catch (SQLException e) {
			//step1.记日志(保留现场)
			e.printStackTrace();
			/*
			 * step2.看异常能否恢复，
			 * 如果不能够恢复(比如，数据库服务停止,一般称这样的异常为系统异常),
			 * 则提示用户稍后重试
			 * 如果能够恢复，则立即恢复
			 */
			throw new RuntimeException(e);
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return admin;
	}

}
