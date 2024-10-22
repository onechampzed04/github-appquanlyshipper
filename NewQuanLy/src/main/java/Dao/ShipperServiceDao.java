/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.List;

import model.Shipper;

/**
 *
 * @author ADMIN
 */
public interface ShipperServiceDao {
    public List<Shipper> getList();
    public int update(Shipper shipper);
    public int delete(Shipper shipper);
    public int insert(Shipper shipper);
    
    public int getNumOfShipper();
    public List<Integer> getListId();
}
