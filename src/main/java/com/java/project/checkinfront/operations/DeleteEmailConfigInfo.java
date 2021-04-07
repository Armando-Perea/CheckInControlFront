package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmailConfigClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;

public class DeleteEmailConfigInfo {

	private static Logger logger = Logger.getLogger(DeleteEmailConfigInfo.class.getName());
	private static final String EMAIL_DELETED = "Email Borrado con Éxito!";
	private static final String EMAIL_DELETED_FAILED = "No es posible borrar Email";
	private static final String SELECT_EMAIL = "Seleccione Email en la Tabla";
	private static final String VALIDATION_UPDATE_TITLE = "Validacion";

	public static void deleteEmail() {
		int row;
		int emailConfigId;
		int status = 0;
		try {
			row = CheckinMainFrame.tblEmail.getSelectedRow();
			if (row > -1) {
				emailConfigId = Integer.parseInt(CheckinMainFrame.tblEmail.getValueAt(row, 0).toString());
				status = EmailConfigClient.deleteEmailConfig(emailConfigId);
				if (status > 0 && status < 300) {
					JOptionPane.showMessageDialog(null, EMAIL_DELETED, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
					ReadEmailInfo.fillAllEmailTable();
				} else {
					JOptionPane.showMessageDialog(null, EMAIL_DELETED_FAILED, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, SELECT_EMAIL, VALIDATION_UPDATE_TITLE,
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			logger.warning("ERROR AT DELETE EMAIL: " + ex);
			JOptionPane.showMessageDialog(null, EMAIL_DELETED_FAILED+ " Exception Detected!", VALIDATION_UPDATE_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
