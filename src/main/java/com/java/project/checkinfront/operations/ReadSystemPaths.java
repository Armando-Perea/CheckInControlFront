package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.SystemPathsClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkin.models.SystemPaths;

public class ReadSystemPaths {

	private static Logger logger = Logger.getLogger(ReadEmailInfo.class.getName());
	private static final String CHECKIN_EXCEPTION = "Excepcion al consultar Email Info";

	public static void fillAllPathsTable() {
		try {
			SystemPaths[] systemPathsList;
			systemPathsList = SystemPathsClient.getAllSystemPaths();
			if (systemPathsList.length > 0) {
				CheckinMainFrame.tableModelPaths.setRowCount(0);
				for (SystemPaths system : systemPathsList) {
					Object[] systemPathsItems = { system.getIdPath(),system.getCheckinPdf(),system.getEmployeePdf() };
					CheckinMainFrame.tableModelPaths.addRow(systemPathsItems);
				}
				CheckinMainFrame.scrollPathsPane.setViewportView(CheckinMainFrame.tblPaths);
			} else {
				CheckinMainFrame.tableModelPaths.setRowCount(0);
			}
		} catch (Exception ex) {
			logger.warning("EXCEPCION AL CONSULTAR EMPLOYEES: " + ex);
			JOptionPane.showMessageDialog(null, CHECKIN_EXCEPTION);
		}

	}
	
}
