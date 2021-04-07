package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmployeeClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkinfront.utils.ComboValuesValidation;

public class DeleteEmployeeInfo {

	private static Logger logger = Logger.getLogger(DeleteEmployeeInfo.class.getName());
	private static final String EMPLOYEE_DELETED = "Empleado Borrado con Éxito!";
	private static final String EMPLOYEE_DELETED_FAILED = "No es posible borrar Empleado";
	private static final String SELECT_EMPLOYEE = "Seleccione Empleado en la Tabla";
	private static final String VALIDATION_UPDATE_TITLE = "Validacion";

	public static void deleteEmployee() {
		int row;
		int employeeId;
		int status = 0;
		try {
			row = CheckinMainFrame.tableEmployee.getSelectedRow();
			if (row > -1) {
				employeeId = Integer.parseInt(CheckinMainFrame.tableEmployee.getValueAt(row, 0).toString());
				status = EmployeeClient.deleteEmployee(employeeId);
				if (status > 0 && status < 300) {
					JOptionPane.showMessageDialog(null, EMPLOYEE_DELETED, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
					ReadEmployeeInfo.fillAllEmployeeTable();
					ComboValuesValidation.initAllCombosProcess();
				} else {
					JOptionPane.showMessageDialog(null, EMPLOYEE_DELETED_FAILED, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, SELECT_EMPLOYEE, VALIDATION_UPDATE_TITLE,
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			logger.warning("ERROR AT DELETE EMPLOYEE: " + ex);
			JOptionPane.showMessageDialog(null, EMPLOYEE_DELETED_FAILED+ " Exception Detected!", VALIDATION_UPDATE_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
