package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Delivery;
import model.Shipper;

public class DeliveriesDaoImpl implements DeliveriesDao {

    @Override
    public List<Delivery> getList() {
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT DELIVE_ID, DELIVE_PICKUP_TIME, DELIVE_DESCRIPTION, DELIVE_STARTLOCATION, DELIVE_ENDLOCATION FROM [DELIVERIES];";

            List<Delivery> deliveries = new ArrayList<>();

            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Delivery delivery = new Delivery();
                delivery.setId(rs.getInt("DELIVE_ID"));
                LocalDateTime pickupTime = rs.getTimestamp("DELIVE_PICKUP_TIME").toLocalDateTime();
                delivery.setPickupTime(pickupTime);
                delivery.setDescription(rs.getString("DELIVE_DESCRIPTION"));
                delivery.setStartLocation(rs.getString("DELIVE_STARTLOCATION"));
                delivery.setEndLocation(rs.getString("DELIVE_ENDLOCATION"));
                deliveries.add(delivery);
            }

            st.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
            return deliveries;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int Update(Delivery delivery) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "UPDATE [DELIVERIES]\n"
                    + "SET \n"
                    + "    DELIVE_PICKUP_TIME = ?,\n"
                    + "    DELIVE_DESCRIPTION = ?,\n"
                    + "    DELIVE_STARTLOCATION = ?,\n"
                    + "    DELIVE_ENDLOCATION = ?\n"
                    + "WHERE \n"
                    + "    DELIVE_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(delivery.getPickupTime()));
            ps.setString(2, delivery.getDescription());
            ps.setString(3, delivery.getStartLocation());
            ps.setString(4, delivery.getEndLocation());
            ps.setInt(5, delivery.getId());

            result = ps.executeUpdate();

            JDBCUtil.closeConnection(conn);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(Delivery delivery) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "DELETE FROM [DELIVERIES]\n"
                    + "WHERE DELIVE_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, delivery.getId());

            result = ps.executeUpdate();

            JDBCUtil.closeConnection(conn);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Insert(Delivery delivery) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "INSERT INTO [DELIVERIES] (DELIVE_PICKUP_TIME, DELIVE_DESCRIPTION, DELIVE_STARTLOCATION, DELIVE_ENDLOCATION)\n"
                    + "VALUES (?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(delivery.getPickupTime()));
            ps.setString(2, delivery.getDescription());
            ps.setString(3, delivery.getStartLocation());
            ps.setString(4, delivery.getEndLocation());

            result = ps.executeUpdate();

            JDBCUtil.closeConnection(conn);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Delivery> getDeliveryListForShipper(Shipper shipper, int month) {
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT * FROM [DELIVERIES] \n"
                    + "WHERE SHIPPER_ID = ? \n"
                    + "AND MONTH(DELIVE_PICKUP_TIME) = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, shipper.getId());
            ps.setInt(2, month);

            List<Delivery> deliveries = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Delivery delivery = new Delivery();
                // Set properties for delivery from result set
                deliveries.add(delivery);
            }

            JDBCUtil.closeConnection(conn);
            return deliveries;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> getListID() {
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT DELIVE_ID FROM [DELIVERIES];";

            List<Integer> deliveryIds = new ArrayList<>();

            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int deliveryId = rs.getInt("DELIVE_ID");
                deliveryIds.add(deliveryId);
            }

            st.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
            return deliveryIds;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
