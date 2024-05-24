

package controller;

import AddressAPI.AddressController;
import Dao.ShipperServiceDao;
import Dao.ShipperServiceDaoImpl;
import model.Shipper;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShipperController {

    private final String PHONE_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    private final Pattern pattern_phone = Pattern.compile(PHONE_PATTERN);
    private final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern_email = Pattern.compile(EMAIL_PATTERN);

    private JButton btnSubmit;
    private JButton btnDelete;
    private JComboBox jcbDistinct;
    private JComboBox jcbProvince;
    private JComboBox jcbWard;
    private JDateChooser jdcDob;
    private JLabel jlbID;
    private JLabel jlbMsg;
    private JTextArea jtaUpdate;
    private JRadioButton jrbBusy;
    private JRadioButton jrbFalse;
    private JRadioButton jrbIdle;
    private JRadioButton jrbNam;
    private JRadioButton jrbNu;
    private JRadioButton jrbTrue;
    private JTextField jtfLiscensePlate;
    private JTextField jtfName;
    private JTextField jtfPhone;

    private Shipper shipper = null;
    private ShipperServiceDao shipperServiceDao = null;

    // JFrame insert
    public ShipperController(JButton btnSubmit, JComboBox jcbDistinct,
    		JComboBox jcbProvince, JComboBox jcbWard,
    		JDateChooser jdcDob, JLabel jlbMsg, 
    		JRadioButton jrbBusy, JRadioButton jrbIdle, JRadioButton jrbNam, JRadioButton jrbNu, JTextField jtfLiscensePlate, JTextField jtfName, JTextField jtfPhone) {
        this.btnSubmit = btnSubmit;
        //this.btnDelete = btnDelete;
        this.jcbDistinct = jcbDistinct;
        this.jcbProvince = jcbProvince;
        this.jcbWard = jcbWard;
        this.jdcDob = jdcDob;
        this.jlbMsg = jlbMsg;
        this.jrbBusy = jrbBusy;
        this.jrbIdle = jrbIdle;
        this.jrbNam = jrbNam;
        this.jrbNu = jrbNu;
        this.jtfLiscensePlate = jtfLiscensePlate;
        this.jtfName = jtfName;
        this.jtfPhone = jtfPhone;

        this.shipperServiceDao = new ShipperServiceDaoImpl();
        this.shipper = new Shipper();
    }

    // JFrame update and delete
    public ShipperController(JButton btnSubmit, JButton btnDelete, JComboBox jcbDistinct, JComboBox jcbProvince, JComboBox jcbWard, JDateChooser jdcDob, JLabel jlbID, JLabel jlbMsg, JTextArea jtaUpdate, JRadioButton jrbBusy, JRadioButton jrbFalse, JRadioButton jrbIdle, JRadioButton jrbNam, JRadioButton jrbNu, JRadioButton jrbTrue, JTextField jtfLiscensePlate, JTextField jtfName, JTextField jtfPhone) {
        this.btnSubmit = btnSubmit;
        this.btnDelete = btnDelete;
        this.jcbDistinct = jcbDistinct;
        this.jcbProvince = jcbProvince;
        this.jcbWard = jcbWard;
        this.jdcDob = jdcDob;
        this.jlbID = jlbID;
        this.jlbMsg = jlbMsg;
        this.jtaUpdate = jtaUpdate;
        this.jrbBusy = jrbBusy;
        this.jrbFalse = jrbFalse;
        this.jrbIdle = jrbIdle;
        this.jrbNam = jrbNam;
        this.jrbNu = jrbNu;
        this.jrbTrue = jrbTrue;
        this.jtfLiscensePlate = jtfLiscensePlate;
        this.jtfName = jtfName;
        this.jtfPhone = jtfPhone;

        this.shipperServiceDao = new ShipperServiceDaoImpl();
    }

    public void setView(Shipper shipper) {
        this.shipper = shipper;
        jlbID.setText("" + shipper.getId());
        jtfName.setText(shipper.getName());
        jdcDob.setDate(shipper.getDob());
        if (shipper.getGender() == "Female") {
            jrbNam.setSelected(false);
            jrbNu.setSelected(true);

        } else if (shipper.getGender() == "Male") {
            jrbNam.setSelected(true);
            jrbNu.setSelected(false);
        } else {
            jrbNam.setSelected(true);
            jrbNu.setSelected(false);
        }
        if (shipper.getStatus() == "Active") {
            jrbBusy.setSelected(false);
            jrbIdle.setSelected(true);

        } else if (shipper.getStatus() == "Busy") {
            jrbBusy.setSelected(true);
            jrbIdle.setSelected(false);
        } else {
            jrbIdle.setSelected(true);
            jrbBusy.setSelected(false);
        }
        jtaUpdate.setText(shipper.getUpdated());
        jtfPhone.setText(shipper.getPhoneNumber() + "");
        jtfLiscensePlate.setText(shipper.getLicensePlate());
        //AddressController addressController = new AddressController(jcbDistinct, jcbProvince, jcbWard);
        jcbProvince.setSelectedItem(shipper.getProvince());
        jcbDistinct.setSelectedItem(shipper.getDistinct());
        jcbWard.setSelectedItem(shipper.getWard());
        if (shipper.isIsDeleted()) {
            jrbFalse.setSelected(false);
            jrbTrue.setSelected(true);

        } else if (!shipper.isIsDeleted()) {
            jrbFalse.setSelected(true);
            jrbTrue.setSelected(false);
        }
    }

    public void setEvent(String s) {

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfName.getText().isEmpty() || jtfPhone.getText().isEmpty()
                        || jdcDob.getDate() == null
                        || (!jrbNam.isSelected() && !jrbNu.isSelected())
                        || jcbDistinct.getSelectedItem() == null
                        || jcbProvince.getSelectedItem() == null || jcbWard.getSelectedItem() == null
                        || jtfLiscensePlate.getText().isEmpty()) {
                    jlbMsg.setText("Vui lòng điền đầy đủ thông tin!");
                } else {

                    shipper.setName(jtfName.getText());
                    shipper.setDob(jdcDob.getDate());
                    shipper.setGender(jrbNam.isSelected() ? "Male" : "Female");
                    shipper.setStatus(jrbIdle.isSelected() ? "Active" : "Busy");
                    shipper.setPhoneNumber(Integer.parseInt(jtfPhone.getText()));
                    //LocalDateTime now = LocalDateTime.now();
                    //shipper.setUpdated(jtaUpdate.getText());
                    shipper.setLicensePlate(jtfLiscensePlate.getText());
                    //AddressController addressController = new AddressController(jcbDistinct, jcbProvince, jcbWard);
                    shipper.setProvince((String) jcbProvince.getSelectedItem());
                    shipper.setDistinct((String) jcbDistinct.getSelectedItem());
                    shipper.setWard((String) jcbWard.getSelectedItem());
                    if (s.equalsIgnoreCase("UpdateOrDelete") && showDialog("cập nhật")) {
                        shipper.setUpdated(jtaUpdate.getText());
                        shipper.setIsDeleted(jrbTrue.isSelected());
                        int result = shipperServiceDao.update(shipper);
                        if (result > 0) {
                            jlbMsg.setText("Cập nhật dữ liệu thành công!");
                        } else {
                            jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                        }
                    } else if (s.equalsIgnoreCase("Insert") && showDialog("thêm")) {
                        shipper.setRating(5);
                        shipper.setIsDeleted(false);
                        shipper.setWarningCount(0);
                        //LocalDateTime now = LocalDateTime.now();
                        shipper.setUpdated("create");
                        LocalDateTime now = LocalDateTime.now();
                        shipper.setCreated(now);

                        shipper.setLastAssignedTime(null);
                        int result = shipperServiceDao.insert(shipper);
                        if (result > 0) {
                            jlbMsg.setText("Thêm dữ liệu thành công!");
                        } else {
                            jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                        }
                    }

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 83));
            }

        }
        );
        if (s.equalsIgnoreCase("UpdateOrDelete")) {
            btnDelete.addMouseListener(new MouseAdapter() {
                //int result = shipperServiceDao.delete(shipper);
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (showDialog("xóa")) {
                        int result = shipperServiceDao.delete(shipper);
                        if (result > 0) {
                            jlbMsg.setText("Xóa dữ liệu thành công!");
                        } else {
                            jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e
                ) {
                    btnDelete.setBackground(new Color(205, 0, 0));
                }

                @Override
                public void mouseExited(MouseEvent e
                ) {
                    btnDelete.setBackground(new Color(255, 0, 0));
                }

            }
            );
        }
    }

    public boolean showDialog(String msg) {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn " + msg + " dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    public boolean validate_phone(String phoneNumber) {
        Matcher matcher = pattern_phone.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean validate_email(String email) {
        Matcher matcher = pattern_email.matcher(email);
        return matcher.matches();
    }
}
