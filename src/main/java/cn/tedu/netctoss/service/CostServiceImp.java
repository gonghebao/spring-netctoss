package cn.tedu.netctoss.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.netctoss.dao.AdminDAO;
import cn.tedu.netctoss.dao.CostDAO;
import cn.tedu.netctoss.entity.Admin;
import cn.tedu.netctoss.entity.Cost;

@Service("costService")
public class CostServiceImp implements CostService {
	@Resource(name="costDAO")
	private CostDAO dao;
	public List<Cost> checkData() {
		List<Cost> list = null;
		list = dao.findAll();
//		if(list == null){
//			throw new AppException("没有数据");
//		}
		
		return list;
	}
}
