package Dao;

import java.util.List;

import model.Delivery;
import model.Shipper;

public class DeliveriesServiceDaoImpl implements DeliveriesServiceDao
{
	private DeliveriesDao deliveriesDao = null;
	
	@Override
	public List<Delivery> getList() {
		// TODO Auto-generated method stub
		return deliveriesDao.getList();
	}

	@Override
	public int Update(Delivery delivery) {
		// TODO Auto-generated method stub
		return deliveriesDao.Update(delivery);
	}

	@Override
	public int Delete(Delivery delivery) {
		// TODO Auto-generated method stub
		return deliveriesDao.Delete(delivery);
	}

	@Override
	public int Insert(Delivery delivery) {
		// TODO Auto-generated method stub
		return deliveriesDao.Insert(delivery);
	}

	@Override
	public List<Delivery> getDeliveryListForShipper(Shipper shipper, int month) {
		// TODO Auto-generated method stub
		return deliveriesDao.getDeliveryListForShipper(shipper, month);
	}

	@Override
	public List<Integer> getListID() {
		// TODO Auto-generated method stub
		return deliveriesDao.getListID();
	}

}
