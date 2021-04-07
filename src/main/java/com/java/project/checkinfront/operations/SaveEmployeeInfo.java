package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmployeeClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkin.models.Employee;
import com.java.project.checkinfront.utils.ComboValuesValidation;

public class SaveEmployeeInfo {

	private static Logger logger = Logger.getLogger(SaveEmployeeInfo.class.getName());
	public final static String EMPLOYEE_SAVE_SUCCESSFULLY = "Empleado guardado con éxito!";
	public final static String NOT_EQUALS_PASSWORDS = "Contraseñas no coinciden";
	public final static String EMPLOYEE_SAVE_FAILED = "No es posible guardar Empleado en este momento";
	public final static String EMPLOYEE_SAVE_EXC = "Ocurrio una Excepcion al intentar guardar Empleado";

	public static void createNewEmployee() {
		Employee employee = new Employee();
		String passOne = new String(CheckinMainFrame.txtNewEmployeePass.getPassword());
		String passTwo = new String(CheckinMainFrame.txtNewEmployeePassConfirm.getPassword());
		if (passwordConfirmation(passOne, passTwo)) {
			try {
				employee.setIdEmployee(0);
				employee.setEmployeeName(CheckinMainFrame.txtNewEmployeeName.getText());
				employee.setRole((String) CheckinMainFrame.cmbRole.getSelectedItem());
				employee.setCellphone(CheckinMainFrame.txtNewEmployeeTel.getText());
				employee.setAddress(CheckinMainFrame.txtNewEmployeeAddress.getText());
				employee.setPassword(new String(CheckinMainFrame.txtNewEmployeePass.getPassword()));
				employee = EmployeeClient.addEmployee(employee);
				if (employee != null) {
					JOptionPane.showMessageDialog(null, EMPLOYEE_SAVE_SUCCESSFULLY);
					clearNewEmployeeSaveFields();
					ReadEmployeeInfo.fillAllEmployeeTable();
					ComboValuesValidation.initAllCombosProcess();
				} else {
					JOptionPane.showMessageDialog(null, EMPLOYEE_SAVE_FAILED);
				}
			} catch (Exception ex) {
				logger.warning("ERROR: " + ex);
				JOptionPane.showMessageDialog(null, EMPLOYEE_SAVE_EXC);
			}
		} else {
			JOptionPane.showMessageDialog(null, NOT_EQUALS_PASSWORDS);
		}
	}

	private static boolean passwordConfirmation(String passOne, String passTwo) {
		return passOne.equals(passTwo);
	}

	public static boolean validateNewEmployeeSaveFields() {
		String passOne = new String(CheckinMainFrame.txtNewEmployeePass.getPassword());
		String passTwo = new String(CheckinMainFrame.txtNewEmployeePassConfirm.getPassword());
		if (CheckinMainFrame.txtNewEmployeeName.getText().isEmpty()
				|| CheckinMainFrame.txtNewEmployeeTel.getText().isEmpty()
				|| CheckinMainFrame.txtNewEmployeeAddress.getText().isEmpty() || passOne.isEmpty()
				|| passTwo.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	private static void clearNewEmployeeSaveFields() {
		CheckinMainFrame.cmbRole.setSelectedIndex(0);
		CheckinMainFrame.txtNewEmployeeName.setText(null);
		CheckinMainFrame.txtNewEmployeeTel.setText(null);
		CheckinMainFrame.txtNewEmployeeAddress.setText(null);
		CheckinMainFrame.txtNewEmployeePass.setText(null);
		CheckinMainFrame.txtNewEmployeePassConfirm.setText(null);
	}
}
