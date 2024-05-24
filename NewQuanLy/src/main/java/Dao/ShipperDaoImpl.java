package Dao;

import database.JDBCUtil;
import model.Shipper;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShipperDaoImpl implements ShipperDao {

    @Override
    public List<Shipper> getList() {
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT TOP (1000) [SHIPPER_ID], [SHIPPER_NAME], [SHIPPER_BIRTHDAY], [SHIPPER_GENDER], [SHIPPER_PHONE], [SHIPPER_DISTINCT], [SHIPPER_PROVINCE], [SHIPPER_WARD], [SHIPPER_LICENSEPLATE], [SHIPPER_STATUS], [SHIPPER_RATING], [SHIPPER_IS_DELETED], [SHIPPER_WARNING_COUNT], [SHIPPER_UPDATED], [SHIPPER_CREATED], [SHIPPER_LAST_ASSIGNED_TIME] FROM [Shipper].[dbo].[SHIPPER]";

            List<Shipper> shippers = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Shipper shipper = new Shipper();
                shipper.setId(rs.getInt("SHIPPER_ID"));
                shipper.setName(rs.getString("SHIPPER_NAME"));
                shipper.setDob(rs.getDate("SHIPPER_BIRTHDAY"));
                shipper.setGender(rs.getString("SHIPPER_GENDER"));
                shipper.setPhoneNumber(rs.getInt("SHIPPER_PHONE"));
                shipper.setDistinct(rs.getString("SHIPPER_DISTINCT"));
                shipper.setProvince(rs.getString("SHIPPER_PROVINCE"));
                shipper.setWard(rs.getString("SHIPPER_WARD"));
                shipper.setLicensePlate(rs.getString("SHIPPER_LICENSEPLATE"));
                shipper.setStatus(rs.getString("SHIPPER_STATUS"));
                shipper.setRating(rs.getFloat("SHIPPER_RATING"));
                shipper.setIsDeleted(rs.getBoolean("SHIPPER_IS_DELETED"));
                shipper.setWarningCount(rs.getInt("SHIPPER_WARNING_COUNT"));
                shipper.setUpdated(rs.getString("SHIPPER_UPDATED"));
                shipper.setCreated(rs.getTimestamp("SHIPPER_CREATED").toLocalDateTime());
                //shipper.setLastAssignedTime(rs.getTimestamp("SHIPPER_LAST_ASSIGNED_TIME").toLocalDateTime());
                Timestamp lastAssignedTimestamp = rs.getTimestamp("SHIPPER_LAST_ASSIGNED_TIME");
                shipper.setLastAssignedTime(lastAssignedTimestamp != null ? lastAssignedTimestamp.toLocalDateTime() : null);
                shippers.add(shipper);
            }

            rs.close();
            st.close();
            JDBCUtil.closeConnection(conn);

            return shippers;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Shipper shipper) {
        int result = 0;
        try {
            Connection cons = JDBCUtil.getConnection();

            String sql = "UPDATE SHIPPER\n"
                    + "SET \n"
                    + "    SHIPPER_NAME = ?, \n"
                    + "    SHIPPER_BIRTHDAY = ?, \n"
                    + "    SHIPPER_GENDER = ?, \n"
                    + "    SHIPPER_PHONE = ?, \n"
                    + "    SHIPPER_DISTINCT = ?, \n"
                    + "    SHIPPER_PROVINCE = ?, \n"
                    + "    SHIPPER_WARD = ?, \n"
                    + "    SHIPPER_LICENSEPLATE = ?, \n"
                    + "    SHIPPER_STATUS = ?, \n"
                    + "    SHIPPER_RATING = ?, \n"
                    + "    SHIPPER_IS_DELETED = ?, \n"
                    + "    SHIPPER_WARNING_COUNT = ?, \n"
                    + "    SHIPPER_UPDATED = ?, \n"
                    + "    SHIPPER_CREATED = ?, \n"
                    + "    SHIPPER_LAST_ASSIGNED_TIME = ?\n"
                    + "WHERE \n"
                    + "    SHIPPER_ID = ?;";
            PreparedStatement ps = cons.prepareStatement(sql);

            ps.setString(1, shipper.getName());
            java.util.Date utilDate = shipper.getDob(); // Lấy thời gian từ đối tượng shipper
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Chuyển đổi thành java.sql.Date
            ps.setDate(2, sqlDate); // Sử dụng java.sql.Date trong PreparedStatement

//            ps.setDate(2, (Date) shipper.getDob());
            ps.setString(3, shipper.getGender());
            ps.setInt(4, shipper.getPhoneNumber());
            ps.setString(5, shipper.getDistinct());
            ps.setString(6, shipper.getProvince());
            ps.setString(7, shipper.getWard());
            ps.setString(8, shipper.getLicensePlate());
            ps.setString(9, shipper.getStatus());
            ps.setFloat(10, (float) shipper.getRating());
            ps.setBoolean(11, shipper.isIsDeleted());
            ps.setInt(12, shipper.getWarningCount());
            ps.setString(13, shipper.getUpdated());
            ps.setTimestamp(14, Timestamp.valueOf(shipper.getCreated()));
            ps.setTimestamp(15, Timestamp.valueOf(shipper.getLastAssignedTime()));
            ps.setInt(16, shipper.getId());

            result = ps.executeUpdate();

            ps.close();
            JDBCUtil.closeConnection(cons);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Shipper shipper) {
        int result = 0;
        try {
            Connection cons = JDBCUtil.getConnection();

            String sql = "DELETE FROM SHIPPER\n"
                    + "WHERE SHIPPER_ID = ?;";
            PreparedStatement ps = cons.prepareStatement(sql);

            ps.setInt(1, shipper.getId());

            result = ps.executeUpdate();
            ps.close();
            JDBCUtil.closeConnection(cons);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Shipper shipper) {
        int result = 0;
        try {
            Connection cons = JDBCUtil.getConnection();

            String sql = "INSERT INTO SHIPPER (SHIPPER_NAME, SHIPPER_BIRTHDAY, SHIPPER_GENDER, SHIPPER_PHONE, SHIPPER_DISTINCT, SHIPPER_PROVINCE, SHIPPER_WARD, SHIPPER_LICENSEPLATE, SHIPPER_STATUS, SHIPPER_RATING, SHIPPER_IS_DELETED, SHIPPER_WARNING_COUNT, SHIPPER_UPDATED, SHIPPER_CREATED, SHIPPER_LAST_ASSIGNED_TIME)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, shipper.getName());
            java.util.Date utilDate = shipper.getDob(); // Lấy thời gian từ đối tượng shipper
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Chuyển đổi thành java.sql.Date
            ps.setDate(2, sqlDate); // Sử dụng java.sql.Date trong PreparedStatement

            //ps.setDate(2, (Date) shipper.getDob());
            ps.setString(3, shipper.getGender());
            ps.setInt(4, shipper.getPhoneNumber());
            ps.setString(5, shipper.getDistinct());
            ps.setString(6, shipper.getProvince());
            ps.setString(7, shipper.getWard());
            ps.setString(8, shipper.getLicensePlate());
            ps.setString(9, shipper.getStatus());
            ps.setFloat(10, (float) shipper.getRating());
            ps.setBoolean(11, shipper.isIsDeleted());
            ps.setInt(12, shipper.getWarningCount());
            ps.setString(13, shipper.getUpdated());
            ps.setTimestamp(14, Timestamp.valueOf(shipper.getCreated()));
//            ps.setTimestamp(15, Timestamp.valueOf(shipper.getLastAssignedTime()));
            ps.setTimestamp(15, shipper.getLastAssignedTime() != null ? Timestamp.valueOf(shipper.getLastAssignedTime()) : null);

            result = ps.executeUpdate();
            ps.close();
            JDBCUtil.closeConnection(cons);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> getListId() {
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT SHIPPER_ID FROM SHIPPER;";

            List<Integer> shippers = new ArrayList<>();

            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int shipperId = rs.getInt("SHIPPER_ID");
                shippers.add(shipperId);
            }

            st.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
            return shippers;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
