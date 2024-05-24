package Dao;

import java.util.List;

import model.Incident;
import model.Shipper;

public interface IncidentDao {
	public List<Incident> getList();
    public int Update(Incident Incident);
    public int Delete(Incident Incident);
    public int Insert(Incident Incident);

    public List<Incident> getIncidentListForShipper(Shipper shipper, int month);
    public List<Integer> getListID();
}
