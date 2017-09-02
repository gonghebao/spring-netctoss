package cn.tedu.netctoss.dao;

import java.sql.ResultSet;
import java.util.List;

import cn.tedu.netctoss.entity.Cost;

public interface CostDAO {
	public Cost findById(int id);
	public List<Cost> findAll();
	public void addCost(Cost c);
	public void modi(Cost c);
	public void deleteCost(int id);
}
