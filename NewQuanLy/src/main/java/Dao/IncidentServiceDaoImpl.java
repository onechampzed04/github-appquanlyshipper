package Dao;

import java.util.List;

import model.Incident;
import model.Shipper;

public class IncidentServiceDaoImpl implements IncidentServiceDao
{
    private IncidentDao incidentDao = null;

	@Override
	public List<Incident> getList() {
		// TODO Auto-generated method stub
		return incidentDao.getList();
	}

	@Override
	public int Update(Incident Incident) {
		// TODO Auto-generated method stub
		return incidentDao.Update(Incident);
	}

	@Override
	public int Delete(Incident Incident) {
		// TODO Auto-generated method stub
		return incidentDao.Delete(Incident);
	}

	@Override
	public int Insert(Incident Incident) {
		// TODO Auto-generated method stub
		return incidentDao.Insert(Incident);
	}

	@Override
	public List<Incident> getIncidentListForShipper(Shipper shipper, int month) {
		// TODO Auto-generated method stub
		return incidentDao.getIncidentListForShipper(shipper, month);
	}

	@Override
	public List<Integer> getListID() {
		// TODO Auto-generated method stub
		return incidentDao.getListID();
	}
	
}
