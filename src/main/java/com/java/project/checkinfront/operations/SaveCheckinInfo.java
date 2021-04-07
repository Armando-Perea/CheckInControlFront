package com.java.project.checkinfront.operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.CheckInClient;
import com.java.project.checkin.client.EmployeeClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkin.models.CheckIn;
import com.java.project.checkin.models.Employee;
import com.java.project.checkinfront.utils.ComboValuesValidation;

public class SaveCheckinInfo {

	private static Logger logger = Logger.getLogger(SaveCheckinInfo.class.getName());
	private final static String CHECKIN_SAVE_SUCCESSFULLY = "Excelente día ";
	private final static String CHECKIN_SAVE_FAILED = "No es posible guardar Checkin en este momento";
	private final static String INVALID_PASSWORD = "Contraseña Incorrecta";


	public static void createNewCheckin() {
		CheckIn checkIn = new CheckIn();
		Employee expectedEmployee = new Employee();
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("es", "ES"));
		String formattedDate = myDateObj.format(myFormatObj);
		String checkPassword = new String(CheckinMainFrame.txtCheckinPass.getPassword());
		expectedEmployee = EmployeeClient.getEmployeeById(ComboValuesValidation
				.getEmployeeIdFromComboInfo((String) CheckinMainFrame.cmbCheckinEmployee.getSelectedItem()));
		if(validateEmployeePassword(checkPassword,expectedEmployee.getPassword())) {
			try {
				checkIn.setIdCheckin(0);
				checkIn.setConcept((String) CheckinMainFrame.cmbCheckinConcept.getSelectedItem());
				checkIn.setEmployeename(ComboValuesValidation
						.getEmployeeNameFromComboInfo((String) CheckinMainFrame.cmbCheckinEmployee.getSelectedItem()));
				checkIn.setRole(ComboValuesValidation
						.getRoleFromComboInfo((String) CheckinMainFrame.cmbCheckinEmployee.getSelectedItem()));
				checkIn.setArrivalDate(formattedDate);
				checkIn.setArrivalHour(getHour() + ":" + getMinutes()+ ":"+getSeconds()+" "+getAMPM());
				checkIn = CheckInClient.addCheckIn(checkIn);
				if (checkIn != null) {
					JOptionPane.showMessageDialog(null, CHECKIN_SAVE_SUCCESSFULLY+checkIn.getEmployeename());
					clearCheckinInfo();
					ReadCheckinInfo.fillAllCheckinTable();
				} else {
					JOptionPane.showMessageDialog(null, CHECKIN_SAVE_FAILED);
				}
			} catch (Exception ex) {
				logger.warning("ERROR: " + ex);
				JOptionPane.showMessageDialog(null, CHECKIN_SAVE_FAILED);
			}
		}else {
			JOptionPane.showMessageDialog(null, INVALID_PASSWORD);
			CheckinMainFrame.txtCheckinPass.setText(null);
		}
	}

	private static void clearCheckinInfo() {
		CheckinMainFrame.txtCheckinPass.setText(null);
		CheckinMainFrame.cmbCheckinConcept.setSelectedIndex(0);
		CheckinMainFrame.cmbCheckinEmployee.setSelectedIndex(0);
	}
	
	public static boolean validateSaveCheckin() {
		String pwd = new String(CheckinMainFrame.txtCheckinPass.getPassword());
		if(pwd.isEmpty() || pwd==null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateEmployeePassword(String password,String originalPwd) {
		return originalPwd.equals(password);
	}
	
	public static String getHour() {
		Integer hour = LocalDateTime.now().getHour();
		if(hour<10) {
			return "0"+hour;
		}
		return hour.toString();
	}
	
	public static String getMinutes() {
		Integer minute = LocalDateTime.now().getMinute();
		if(minute<10) {
			return "0"+minute;
		}
		return minute.toString();
	}
	
	public static String getSeconds() {
		Integer second = LocalDateTime.now().getSecond();
		if(second<10) {
			return "0"+second;
		}
		return second.toString();
	}
	
	public static String getAMPM() {
		Integer hour = LocalDateTime.now().getHour();
		if(hour<12) {
			return "AM";
		}
		return "PM";
	}

}
