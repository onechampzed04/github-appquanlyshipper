package model;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;

import Dao.OrderServiceDao;
import Dao.OrderServiceDaoImpl;
import controller.OrderController;
import view.UpdateOrDeleteOrder;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ClassTableModel {
	public DefaultTableModel setTableCustomer(List<Customer> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;

        int rows = listItem.size();
        if (rows > 0) {
            for (Customer customer : listItem) {
                obj = new Object[columns];
                obj[0] = customer.getId();
                obj[1] = customer.getName();
                obj[2] = customer.getPhoneNumber();
                obj[3] = customer.getEmail();
                obj[4] = customer.getGender();
                obj[5] = customer.getDistinct();
                obj[6] = customer.getProvince();
                obj[7] = customer.getWard();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
	public DefaultTableModel setTableShipper(List<Shipper> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dtm.setColumnIdentifiers(listColumn);
		int columns = listColumn.length;
		Object[] obj = null;

		int rows = listItem.size();
		if (rows > 0) {
			for (Shipper shipper : listItem) {
				obj = new Object[columns];
				obj[0] = shipper.getId();
				obj[1] = shipper.getName();
				obj[2] = shipper.getDob();
				obj[3] = shipper.getGender();
				obj[4] = shipper.getPhoneNumber();
				obj[5] = shipper.getDistinct();
				obj[6] = shipper.getProvince();
				obj[7] = shipper.getWard();
				obj[8] = shipper.getLicensePlate();
				obj[9] = shipper.getStatus();
				obj[10] = shipper.getRating();
				obj[11] = shipper.isIsDeleted();
				obj[12] = shipper.getWarningCount();
				obj[13] = shipper.getUpdated();
				obj[14] = shipper.getCreated();
				obj[15] = shipper.getLastAssignedTime();

				dtm.addRow(obj);
			}
		}
		return dtm;
	}

	public DefaultTableModel setTableOrder(List<Order> listOrders, String[] listColumn,JTable table ,String type) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return (column == 13 || column == 11); // Chỉ có cột chứa nút mới có thể chỉnh sửa
			}
		};

		dtm.setColumnIdentifiers(listColumn);
		int columnCount = listColumn.length;

		int columns = listColumn.length;
		Object[] obj = null;

		int rows = listOrders.size();
		if (rows > 0) {
			// Danh sach pending
			if (type == "Pending") {
				for (Order order : listOrders) {
					obj = new Object[columns];
					obj[0] = order.getId();
					obj[1] = order.getName();
					obj[2] = order.getWeight();
					obj[3] = order.getPrice();
					obj[4] = order.getWard();
					obj[5] = order.getOrderDate();
					obj[6] = order.getShipperId();
					obj[7] = order.getCusId();
					obj[8] = order.isDeleted();
					obj[9] = order.getServiceId();
					if (order.getShipFee() != 0)
					{
						obj[10]= order.getShipFee();
					}
					else obj[10] = null;
					obj[11] = "Chỉnh sửa";
					dtm.addRow(obj);
					
				}
				ButtonRenderer buttonRenderer = new ButtonRenderer();
				ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());
				
				// Áp dụng renderer và editor vào cột cuối cùng của table
				table.setModel(dtm);
				table.getColumnModel().getColumn(11).setCellRenderer(buttonRenderer);
				table.getColumnModel().getColumn(11).setCellEditor(buttonEditor);

			}
			
			// danh sach da xoa
			else if (type == "Deleted") {

				for (Order order : listOrders) {
					obj = new Object[columns];
					obj[0] = order.getId();
					obj[1] = order.getName();
					obj[2] = order.getPrice();
					obj[3] = order.getOrderDate();
					obj[4] = order.getShipperId();
					obj[5] = order.getCusId();
					obj[6] = order.isRespond();
					obj[7] = order.getShipTime();
					obj[8] = order.getShipCount();
					obj[9] = order.getCompletedTime();
					obj[10] = order.getServiceId();

					dtm.addRow(obj);
				}
			}
			
			else if (type == "Processing")
			{
				for (Order order : listOrders) {
					Vector<Object> rowData = new Vector<>();
					rowData.add(order.getId()); // 0
					rowData.add(order.getName());
					rowData.add(order.getShipFee());
					rowData.add(order.getPrice());
					rowData.add(order.getWard());
					rowData.add(order.getDistance());
					rowData.add(order.getOrderDate());
					rowData.add(order.getShipperId());
					rowData.add(order.getCusId());
					rowData.add(order.isRespond());
					System.out.println(order.getShipTime()+"xcxcx");
					rowData.add(order.getShipTime());
					rowData.add(order.getShipCount());
					rowData.add(order.getServiceId());
					rowData.add("Xóa"); // 13

					dtm.addRow(rowData);
				}
				ButtonRenderer buttonRenderer = new ButtonRenderer();
				ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());

				// Áp dụng renderer và editor vào cột cuối cùng của table
				table.setModel(dtm);
				table.getColumnModel().getColumn(13).setCellRenderer(buttonRenderer);
				table.getColumnModel().getColumn(13).setCellEditor(buttonEditor);
			}
				
			else if (type == "Completed")
			{
				for (Order order : listOrders) {
					obj = new Object[columns];
					obj[0] = order.getId();
					obj[1] = order.getName();
					obj[2] = order.getPrice();
					obj[3] = order.getOrderDate();
					obj[4] = order.getShipperId();
					obj[5] = order.getCusId();
					obj[6] = order.isRespond();
					obj[7] = order.getShipCount();
					obj[8] = order.getCompletedTime();
					obj[9] = order.getServiceId();
					obj[10] = order.getShipFee();
					dtm.addRow(obj);
					table.setModel(dtm);
				}
			}
			return dtm;
		} else
			return null;
	}


	// Lớp xử lý việc hiển thị nút
	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);

		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor(new Color(200, 200, 200)));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	// Lớp xử lý sự kiện của nút
	class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

		protected JButton button;
		private String label;
		private boolean isPushed;
		private JTable table;

		public ButtonEditor(JCheckBox checkBox) {
			super();
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(this);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}

			label = (value == null) ? "" : value.toString();
			button.setText(label);
			isPushed = true;
			this.table = table;
			System.out.println("isSelected3");
			return button;
		}

		public Object getCellEditorValue() {
			if (isPushed) {
				System.out.println("itorValu");

				// TODO: Thực thi hành động khi nút được nhấn
				// Lấy thông tin shipper từ hàng được chọn
				
				OrderServiceDao orderServiceDao = new OrderServiceDaoImpl();
				int selectedRowIndex = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Order order = new Order();
				order.setId((int) model.getValueAt(selectedRowIndex, 0));
				orderServiceDao.getDataFromID(order);
				
				if (label.equals("Xóa")) 
				{
					OrderController orderController = new OrderController();
					orderController.DeleteOrder(order);
				}
				else if (label.equals("Chỉnh sửa"))
				{
					UpdateOrDeleteOrder updateOrDeleteOrder = new UpdateOrDeleteOrder(order);
					updateOrDeleteOrder.setVisible(true);
					updateOrDeleteOrder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				orderServiceDao.Update(order); 
				// Truy cập đến đối tượng Shipper tương ứng ở đây thông qua row index và thực
				// hiện hành động chỉnh sửa shipper
			}
			isPushed = false;
			return new String(label);
		}

		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}

		public void actionPerformed(ActionEvent e) {
			fireEditingStopped();
		}

	}
	
	
	
	
	
//    // Tạo và thiết lập Checkbox Renderer và Editor
//    CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
//    CheckBoxEditor checkBoxEditor = new CheckBoxEditor(new JCheckBox());
//
//    // Áp dụng renderer và editor vào cột cuối cùng của table
//    table.setModel(dtm);
//    table.getColumnModel().getColumn(14).setCellRenderer(checkBoxRenderer);
//    table.getColumnModel().getColumn(14).setCellEditor(checkBoxEditor);
//	class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
//		CheckBoxRenderer() {
//			setHorizontalAlignment(JCheckBox.CENTER);
//		}
//
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//				int row, int column) {
//			if (value instanceof Boolean) {
//				setSelected((Boolean) value);
//			}
//			return this;
//		}
//	}
//
//	// Lớp CheckBoxEditor dùng để chỉnh sửa giá trị của ô chứa checkbox trong bảng
//	class CheckBoxEditor extends AbstractCellEditor implements TableCellEditor {
//		private JCheckBox checkBox;
//
//		CheckBoxEditor(JCheckBox checkBox) {
//			this.checkBox = checkBox;
//			this.checkBox.setHorizontalAlignment(JCheckBox.CENTER);
//		}
//
//		@Override
//		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
//				int column) {
//			if (value instanceof Boolean) {
//				checkBox.setSelected((Boolean) value);
//			}
//			return checkBox;
//		}
//
//		@Override
//		public Object getCellEditorValue() {
//			return checkBox.isSelected();
//		}
//	}
//	public DefaultTableModel setTableOrderProcessing(List<Order> listOrders, String[] listColumn, JTable table) {
//	DefaultTableModel dtm = new DefaultTableModel() {
//		@Override
//		public boolean isCellEditable(int row, int column) {
//			return column == 14; // Chỉ có cột chứa nút mới có thể chỉnh sửa
//		}
//	};
//
//	dtm.setColumnIdentifiers(listColumn);
//
//	if (listOrders != null && !listOrders.isEmpty()) {
//		for (Order order : listOrders) {
//			Vector<Object> rowData = new Vector<>();
//			rowData.add(order.getId());
//			rowData.add(order.getName());
//			rowData.add(order.getShipFee());
//			rowData.add(order.getPrice());
//			rowData.add(order.getWard());
//			rowData.add(order.getDistance());
//			rowData.add(order.getOrderDate());
//			rowData.add(order.getShipperId());
//			rowData.add(order.getCusId());
//			rowData.add(order.isRespond());
//			rowData.add(order.getShipTime());
//			rowData.add(order.getShipCount());
//			rowData.add(order.getCompletedTime());
//			rowData.add(order.getServiceId());
//			rowData.add("click"); // Sử dụng checkbox
//
//			dtm.addRow(rowData);
//		}
//	}
//
//	// Tạo và thiết lập Button Renderer và Editor
//	ButtonRenderer buttonRenderer = new ButtonRenderer();
//	ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());
//
//	// Áp dụng renderer và editor vào cột cuối cùng của table
//	table.setModel(dtm);
//	table.getColumnModel().getColumn(14).setCellRenderer(buttonRenderer);
//	table.getColumnModel().getColumn(14).setCellEditor(buttonEditor);
//
//	return dtm;
//}

}
