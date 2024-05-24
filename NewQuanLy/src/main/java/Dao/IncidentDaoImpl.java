package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Incident;
import model.Shipper;

public class IncidentDaoImpl implements IncidentDao {

    @Override
    public List<Incident> getList() {
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT INC_ID, INT_TIME, INT_DESCRIPTION, INT_RESOLVED, INT_RESOLUTIONDETAILS FROM [INCIDENTS];";

            List<Incident> incidents = new ArrayList<>();

            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Incident incident = new Incident();
                incident.setId(rs.getInt("INC_ID"));
                LocalDateTime time = rs.getTimestamp("INT_TIME").toLocalDateTime();
                incident.setTime(time);
                incident.setDescription(rs.getString("INT_DESCRIPTION"));
                incident.setResolved(rs.getBoolean("INT_RESOLVED"));
                incident.setResolutionDetails(rs.getString("INT_RESOLUTIONDETAILS"));
                incidents.add(incident);
            }

            st.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
            return incidents;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int Update(Incident incident) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "UPDATE [INCIDENTS]\n"
                    + "SET \n"
                    + "    INT_TIME = ?,\n"
                    + "    INT_DESCRIPTION = ?,\n"
                    + "    INT_RESOLVED = ?,\n"
                    + "    INT_RESOLUTIONDETAILS = ?\n"
                    + "WHERE \n"
                    + "    INC_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(incident.getTime()));
            ps.setString(2, incident.getDescription());
            ps.setBoolean(3, incident.isResolved());
            ps.setString(4, incident.getResolutionDetails());
            ps.setInt(5, incident.getId());

            result = ps.executeUpdate();

            JDBCUtil.closeConnection(conn);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(Incident incident) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "DELETE FROM [INCIDENTS]\n"
                    + "WHERE INC_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, incident.getId());

            result = ps.executeUpdate();

            JDBCUtil.closeConnection(conn);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Insert(Incident incident) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "INSERT INTO [INCIDENTS] (INT_TIME, INT_DESCRIPTION, INT_RESOLVED, INT_RESOLUTIONDETAILS)\n"
                    + "VALUES (?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(incident.getTime()));
            ps.setString(2, incident.getDescription());
            ps.setBoolean(3, incident.isResolved());
            ps.setString(4, incident.getResolutionDetails());

            result = ps.executeUpdate();

            JDBCUtil.closeConnection(conn);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Incident> getIncidentListForShipper(Shipper shipper, int month) {
    	try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT * FROM [INCIDENTS] \n"
                    + "WHERE SHIPPER_ID = ? \n"
                    + "AND MONTH(ORD_DELIVERY_TIME) = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, shipper.getId());
            ps.setInt(2, month);

            List<Incident> incidents = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Incident incident = new Incident();
                

                incidents.add(incident);
            }

            JDBCUtil.closeConnection(conn);
            return incidents;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> getListID() {
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT INC_ID FROM [INCIDENTS];";

            List<Integer> incidentIds = new ArrayList<>();

            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int incidentId = rs.getInt("INC_ID");
                incidentIds.add(incidentId);
            }

            st.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
            return incidentIds;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
