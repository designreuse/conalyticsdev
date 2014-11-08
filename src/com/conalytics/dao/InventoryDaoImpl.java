package com.conalytics.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;









import com.conalytics.domain.Inventory;
import com.conalytics.domain.Shop;
import com.conalytics.jdbc.InventoryRowMapper;


public class InventoryDaoImpl implements InventoryDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(Inventory invent) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO SHOP_PARTS_INFO (SHOP_PART_ID, SHOP_ID, PART_ID, QTY_AVAILABLE, PRICE_PER_UNIT, RTL_PRICE_PER_UNIT, STD_HRLY_PART_LABOR_RATE) "
				+ "VALUES  (?, ?, ?, ?, ?, ? ,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						invent.getSHOP_PART_ID(),
						invent.getSHOP_ID(),
						invent.getPART_ID(),
						invent.getQTY_AVAILABLE(),
						invent.getPRICE_PER_UNIT(),
						invent.getRTL_PRICE_PER_UNIT(),
						invent.getSTD_HRLY_PART_LABOR_RATE()
							});
	}

	@Override
	public void updateData(Inventory invent) {
		// TODO Auto-generated method stub
		

		String sql = "UPDATE SHOP_PARTS_INFO set SHOP_ID=?, PART_ID=?, QTY_AVAILABLE=?, PRICE_PER_UNIT=?, RTL_PRICE_PER_UNIT=?, STD_HRLY_PART_LABOR_RATE=? where SHOP_PART_ID = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						invent.getSHOP_ID(),
						invent.getPART_ID(),
						invent.getQTY_AVAILABLE(),
						invent.getPRICE_PER_UNIT(),
						invent.getRTL_PRICE_PER_UNIT(),
						invent.getSTD_HRLY_PART_LABOR_RATE(),
						invent.getSHOP_PART_ID()
							});
    
	}

	@Override
	public void deleteData(Double id) {
		// TODO Auto-generated method stub
		String sql = "delete from SHOP_PARTS_INFO where SHOP_PART_ID=" + id.doubleValue();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	@Override
	public List<Inventory> getInventorybyPartId(Double partId) {
		// TODO Auto-generated method stub
		List<Inventory> invList = new ArrayList<Inventory>();
		String sql = "select * , null from SHOP_PARTS_INFO where PART_ID= " + partId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		invList = jdbcTemplate.query(sql, new InventoryRowMapper());
		return invList;
		
		}

	
	@Override
	public List<Inventory> getInventorybyShopList(List<Shop> shopl,Double partId) {
		// TODO Auto-generated method stub
		String inClause="";
		for (int i = 0; i < shopl.size(); i++) {
			Shop s=shopl.get(i);
			if(i ==0)
			{
				inClause=s.getShopId().toString();
			}
			else
			{
			inClause = inClause +","+s.getShopId();
		    }
			
		}
			inClause =" IN ("+inClause+")";
			
			String sql ="select * from SHOP_PARTS_INFO where PART_ID= " + partId +" and SHOP_ID "+inClause;
            System.out.println(sql);
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Inventory> invList = new ArrayList<Inventory>();
			invList = jdbcTemplate.query(sql, new InventoryRowMapper());
			return invList;
	       }
	

	
}	