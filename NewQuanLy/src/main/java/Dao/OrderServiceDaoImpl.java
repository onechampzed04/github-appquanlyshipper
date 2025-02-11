/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Customer;
import model.Order;
import model.Shipper;

/**
 *
 * @author ADMIN
 */
public class OrderServiceDaoImpl implements OrderServiceDao{
    private OrderDao orderDao = null;

    
    public OrderServiceDaoImpl() {
        orderDao = new OrderDaoImpl();
    }
    
    
    @Override
    public List<Order> getList() {
        return orderDao.getList();
    }

    @Override
    public List<Order> getOrderListForShipper(Shipper shipper,int month) {
        return orderDao.getOrderListForShipper(shipper,month);
    }

    @Override
    public int Update(Order order) {
        return orderDao.Update(order);
    }

    @Override
    public int Delete(Order order) {
        return orderDao.Delete(order);
    }

    @Override
    public int Insert(Order order) {
        return orderDao.Insert(order);
    }

    @Override
    public int getNumOfOrder() {
        return orderDao.getList().size();
    }
    
    public List<Integer> getList_ID()
    {
    	return orderDao.getListID();
    }

	public int checkPhoneNumberExists(String phoneNumber, Customer customer) {
		// TODO Auto-generated method stub
		return orderDao.checkPhoneNumberExists(phoneNumber, customer);
	}
    public void getDataFromID(Order order)
    {
    	orderDao.getDataFromID(order);
    }
    
    public List<Order> getDeletedOrders()
    {
    	return orderDao.getDeletedOrders();
    }
   
    public List<Order> getUnDeliveryOrders()
    {
    	return orderDao.getUnDeliveryOrders();

    }
	public List<Order> getCompletedOrders() 
	{
		return orderDao.getCompletedOrders();
	}


	@Override
	public List<Order> getDeliveryOrders() {
		// TODO Auto-generated method stub
		return orderDao.getDeliveryOrders();
	}

}
