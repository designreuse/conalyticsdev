package com.conalytics.services;

import java.util.List;

import com.conalytics.domain.Inventory;
import com.conalytics.domain.Shop;

public interface InventoryService {
	
	public void insertData(Inventory invent);

	public void updateData(Inventory invent);

	public void deleteData(Double id);
	
	public List<Inventory> getInventorybyPartId(Double partId);
	
    public List<Inventory> getInventorybyShopList(List<Shop> shopl, Double partid);

	public Inventory getInventorybyShopandPartId(Double shopid, Double partid);
	
	public byte[] getImagebyInvId(Double invId);
	
	public List<Inventory> getShopPartsdata();
	
	public List<Inventory> getInventorybyShopListandPartDesc(List<Shop> shopl,String pdesc);

}
