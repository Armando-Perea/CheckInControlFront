package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmployeeClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkin.models.Employee;
import com.java.project.checkinfront.utils.ComboValuesValidation;

public class UpdateEmployeeInfo {

	private static Logger logger = Logger.getLogger(UpdateEmployeeInfo.class.getName());
	private static final String EMPLOYEE_UPDATED = "Empleado Actualizado con Éxito!";
	private static final String EMPLOYEE_NOT_FOUND = "Empleado no existe!";
	private static final String EMPLOYEE_UPDATE_FAILED = "No es posible actualizar Empleado";
	private static final String SELECT_EMPLOYEE = "Seleccione Un Empleado en la Tabla";
	private static final String VALIDATION_UPDATE_TITLE = "Validacion";

	public static void updateEmployeeInfo() {
		int row;
		Integer employeeId;
		int status = 0;
		String employeeName, role, cellPhone, address;
		Employee employee = new Employee();
		row = CheckinMainFrame.tableEmployee.getSelectedRow();
		try {
			if (row > -1) {
				employeeId = Integer.parseInt(CheckinMainFrame.tableEmployee.getValueAt(row, 0).toString());
				employee = EmployeeClient.getEmployeeById(employeeId);
				if (employee != null) {
					employeeName = (String) CheckinMainFrame.tableEmployee.getValueAt(row, 1).toString();
					role = (String) CheckinMainFrame.tableEmployee.getValueAt(row, 2).toString();
					cellPhone = (String) CheckinMainFrame.tableEmployee.getValueAt(row, 3).toString();
					address = (String) CheckinMainFrame.tableEmployee.getValueAt(row, 4).toString();

					if (!employeeName.isEmpty()) {
						employee.setEmployeeName(employeeName);
					}
					if (!role.isEmpty()) {
						employee.setRole(role);
					}
					if (!cellPhone.isEmpty()) {
						employee.setCellphone(cellPhone);
					}
					if (!address.isEmpty()) {
						employee.setAddress(address);
					}

					status = EmployeeClient.updateEmployee(employee);
					if (status > 0 && status < 300) {
						JOptionPane.showMessageDialog(null, EMPLOYEE_UPDATED);
						ReadEmployeeInfo.fillAllEmployeeTable();
						ComboValuesValidation.initAllCombosProcess();
					} else {
						JOptionPane.showMessageDialog(null, EMPLOYEE_NOT_FOUND, VALIDATION_UPDATE_TITLE,
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, EMPLOYEE_NOT_FOUND, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, SELECT_EMPLOYEE, VALIDATION_UPDATE_TITLE,
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			logger.warning("ERROR AT EMPLOYEE: " + ex);
			JOptionPane.showMessageDialog(null, EMPLOYEE_UPDATE_FAILED, VALIDATION_UPDATE_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
