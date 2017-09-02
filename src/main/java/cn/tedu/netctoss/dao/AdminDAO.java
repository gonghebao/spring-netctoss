package cn.tedu.netctoss.dao;


import org.springframework.stereotype.Repository;

import cn.tedu.netctoss.entity.Admin;

/**
 * 数据访问层接口
 * @author soft01
 *
 */
@Repository("adminDAO")
public interface AdminDAO {
	public Admin findByCode(String code);
}
