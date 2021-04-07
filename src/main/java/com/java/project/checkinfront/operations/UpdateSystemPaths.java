package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.SystemPathsClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkin.models.SystemPaths;

public class UpdateSystemPaths {

	private static Logger logger = Logger.getLogger(UpdateSystemPaths.class.getName());
	private static final String PATHS_UPDATED = "Paths Actualizado con Éxito!";
	private static final String PATHS_NOT_FOUND = "Paths no existe!";
	private static final String PATHS_UPDATE_FAILED = "No es posible actualizar Paths";
	private static final String SELECT_PATHS = "Seleccione Un Paths en la Tabla";
	private static final String VALIDATION_UPDATE_TITLE = "Validacion";

	public static void updatePathsInfo() {
		int row;
		Integer systemPathsId;
		int status = 0;
		String checkinPdf, employeePdf;
		SystemPaths systemPaths = new SystemPaths();
		row = CheckinMainFrame.tblPaths.getSelectedRow();
		try {
			if (row > -1) {
				systemPathsId = Integer.parseInt(CheckinMainFrame.tblPaths.getValueAt(row, 0).toString());
				systemPaths = SystemPathsClient.getSystemPathById(systemPathsId);
				if (systemPaths != null) {
					checkinPdf = (String) CheckinMainFrame.tblPaths.getValueAt(row, 1).toString();
					employeePdf = (String) CheckinMainFrame.tblPaths.getValueAt(row, 2).toString();
					if (!checkinPdf.isEmpty()) {
						systemPaths.setCheckinPdf(checkinPdf);
					}
					if (!employeePdf.isEmpty()) {
						systemPaths.setEmployeePdf(employeePdf);
					}
					status = SystemPathsClient.updateSystemPath(systemPaths);
					if (status > 0 && status < 300) {
						JOptionPane.showMessageDialog(null, PATHS_UPDATED);
						ReadSystemPaths.fillAllPathsTable();
					} else {
						JOptionPane.showMessageDialog(null, PATHS_NOT_FOUND, VALIDATION_UPDATE_TITLE,
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, PATHS_NOT_FOUND, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, SELECT_PATHS, VALIDATION_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			logger.warning("ERROR AT UPDATE PATH: " + ex);
			JOptionPane.showMessageDialog(null, PATHS_UPDATE_FAILED, VALIDATION_UPDATE_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
