package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmployeeClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkin.models.Employee;

public class ReadEmployeeInfo {

	private static Logger logger = Logger.getLogger(ReadCheckinInfo.class.getName());
	private static final String CHECKIN_EXCEPTION = "Excepcion al consultar Empleados Info";

	public static void fillAllEmployeeTable() {
		try {
			Employee[] employeeList;
			employeeList = EmployeeClient.getAllEmployees();
			if (employeeList.length > 0) {
				CheckinMainFrame.tableModelEmployee.setRowCount(0);
				for (Employee employee : employeeList) {
					Object[] employeeItems = { employee.getIdEmployee(), employee.getEmployeeName(), employee.getRole(),
							employee.getCellphone(), employee.getAddress() };
					CheckinMainFrame.tableModelEmployee.addRow(employeeItems);
				}
				CheckinMainFrame.scrollPaneEmployee.setViewportView(CheckinMainFrame.tableEmployee);
			} else {
				CheckinMainFrame.tableModelEmployee.setRowCount(0);
			}
		} catch (Exception ex) {
			logger.warning("EXCEPCION AL CONSULTAR EMPLOYEES: " + ex);
			JOptionPane.showMessageDialog(null, CHECKIN_EXCEPTION);
		}

	}
}
