package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.CheckInClient;
import com.java.project.checkin.models.CheckIn;
import com.java.project.checkinfront.gui.CheckinMainFrame;

public class ReadCheckinInfo {

	private static Logger logger = Logger.getLogger(ReadCheckinInfo.class.getName());
   	private static final String CHECKIN_EXCEPTION = "Excepcion al consultar Checkin Info";
	
	public static void fillAllCheckinTable() {
		try {
			CheckIn[] checkInList ;
			checkInList = CheckInClient.getAllCheckIn();
			if(checkInList.length>0) {
				CheckinMainFrame.tableModelCheckin.setRowCount(0);
				for (CheckIn check : checkInList) {
					Object[] checkinItems = {check.getIdCheckin(),check.getConcept(),check.getEmployeename(),check.getRole(),check.getArrivalDate(),check.getArrivalHour()};
					CheckinMainFrame.tableModelCheckin.addRow(checkinItems);
				}
				CheckinMainFrame.scrollPaneCheckIn.setViewportView(CheckinMainFrame.tblCheckIn);
			}else {
				CheckinMainFrame.tableModelCheckin.setRowCount(0);
				}
		}catch(Exception ex) {
			logger.warning("EXCEPCION AL CONSULTAR CHECKIN: "+ex);
			JOptionPane.showMessageDialog(null, CHECKIN_EXCEPTION);
		}
		
	}
}
