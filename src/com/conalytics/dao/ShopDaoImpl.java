package com.conalytics.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



import com.conalytics.domain.Shop;
import com.conalytics.jdbc.ShopRowMapper;

public class ShopDaoImpl implements ShopDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(Shop shop) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO SHOP(SHOP_ID, CITY_ID, TOWN_MUNI, SHOP_NAME, SHOP_DESC, PHONE_NUMBER, WEBSITE, EMAIL, ADDRESS, STD_HRLY_LABOR_RATE) "
				+ "VALUES  (?, ?, ?, ?, ?, ? ,?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						shop.getShopId(),
						shop.getCityId(),
						shop.getTownMuni(),
						shop.getShopName(),
						shop.getShopDesc(),
						shop.getPhoneNumber(),
						shop.getWebsite(),
						shop.getEmail(),
						shop.getAddress(),
						shop.getStdHrlyLaborRate()
							});
	}

	@Override
	public void updateData(Shop shop) {
		// TODO Auto-generated method stub
		

		String sql = "UPDATE SHOP set CITY_ID=?, TOWN_MUNI=?, SHOP_NAME=?, SHOP_DESC=?, PHONE_NUMBER=?, WEBSITE=?, EMAIL=?, ADDRESS=?, STD_HRLY_LABOR_RATE=?  where SHOP_ID = ?";
		
		System.out.println(shop.toString());
		System.out.println(sql);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						shop.getCityId(),
						shop.getTownMuni(),
						shop.getShopName(),
						shop.getShopDesc(),
						shop.getPhoneNumber(),
						shop.getWebsite(),
						shop.getEmail(),
						shop.getAddress(),
						shop.getStdHrlyLaborRate(),
						shop.getShopId()
							});
    
	}

	@Override
	public void deleteData(Double id) {
		// TODO Auto-generated method stub
		String sql = "delete from SHOP where shop_id=" + id.doubleValue();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	@Override
	public Shop getShopbyId(Double id) {
		// TODO Auto-generated method stub
		List<Shop> shopList = new ArrayList<Shop>();
		String sql = "select * from SHOP where SHOP_ID=" + id ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		shopList = jdbcTemplate.query(sql, new ShopRowMapper());
		return shopList.get(0);
	}
	
	public List<Shop> getShopList() {
		List<Shop> shopList = new ArrayList<Shop>();
		String sql = "select * from SHOP" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		shopList = jdbcTemplate.query(sql, new ShopRowMapper());
		return shopList;	
	}


}