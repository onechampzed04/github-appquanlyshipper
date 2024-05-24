package Dao;

import java.util.List;

import model.Delivery;
import model.Shipper;

public interface DeliveriesDao {
	public List<Delivery> getList();
    public int Update(Delivery delivery);
    public int Delete(Delivery delivery);
    public int Insert(Delivery delivery);

    public List<Delivery> getDeliveryListForShipper(Shipper shipper, int month);
    public List<Integer> getListID();
}
