package cn.tedu.netctoss.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import cn.tedu.netctoss.entity.Cost;
//@Repository("costDAO")
public class CostDAOJdbcImp1 implements CostDAO {
	@Resource(name="d1")
	private DataSource ds;
	public Cost findById(int id) {
		
		return null;
	}

	public List<Cost> findAll() {
		List<Cost> list = new ArrayList<Cost>();
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from ghb_cost order by cost_id";
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				Cost c = new Cost();
				creatCost(rs, c);
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	public void addCost(Cost c) {
		

	}

	public void modi(Cost c) {
		

	}

	public void deleteCost(int id) {

	}
	private void creatCost(ResultSet rs, Cost c) throws SQLException {
		c.setCostId(rs.getInt("cost_id"));
		c.setName(rs.getString("name"));
		c.setBaseDuration(rs.getInt("base_duration"));
		c.setBaseCost(rs.getDouble("base_cost"));
		c.setUnitCost(rs.getDouble("unit_cost"));
		c.setStatus(rs.getString("status"));
		c.setDescr(rs.getString("descr"));
		c.setCreatime(rs.getTimestamp("creatime"));
		c.setStartime(rs.getTimestamp("startime"));
		c.setCostType(rs.getString("cost_type"));
	}

}
