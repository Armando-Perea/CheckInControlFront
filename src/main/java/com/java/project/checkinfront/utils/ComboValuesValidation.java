package com.java.project.checkinfront.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmployeeClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkin.models.Employee;

public class ComboValuesValidation {

	public static Logger logger = Logger.getLogger(ComboValuesValidation.class.getName());
	public static List<Employee> employeeList = new ArrayList<>();

	public static void initAllCombosProcess() {
		CheckinMainFrame.cmbCheckinEmployee.removeAllItems();
		employeeList = null;
		employeeList = Arrays.asList(EmployeeClient.getAllEmployees());
		for (Employee empl : employeeList) {
			CheckinMainFrame.cmbCheckinEmployee
					.addItem(empl.getIdEmployee().toString() + " - " + empl.getEmployeeName() + " - " + empl.getRole());
		}
	}

	public static Integer getEmployeeIdFromComboInfo(String employee) {
		String[] infoList;
		String description;
		try {
			infoList = employee.split("\\-");
			description = infoList[0];
			description = description.trim();
			return Integer.parseInt(description);
		}catch(Exception ex) {
			return 0;
		}
	}

	public static String getEmployeeNameFromComboInfo(String employee) {
		String[] infoList;
		String description;
		try {
			infoList = employee.split("\\-");
			description = infoList[1];
			description = description.trim();
			return description;
		}catch(Exception ex) {
			return "N/A";
		}
	}

	public static String getRoleFromComboInfo(String employee) {
		String[] infoList;
		String description;
		try {
			infoList = employee.split("\\-");
			description = infoList[2];
			description = description.trim();
			return description;
		}catch(Exception ex) {
			return "N/A";
		}
	}

}
