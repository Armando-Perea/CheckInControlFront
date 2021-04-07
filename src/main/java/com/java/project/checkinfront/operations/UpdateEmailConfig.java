package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmailConfigClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkinfront.mail.EncryptSecurity;
import com.java.project.checkin.models.EmailConfig;

public class UpdateEmailConfig {

	private static Logger logger = Logger.getLogger(UpdateEmailConfig.class.getName());
	private static final String EMAIL_UPDATED = "Email Actualizado con Éxito!";
	private static final String EMAIL_NOT_FOUND = "Email no existe!";
	private static final String EMAIL_UPDATE_FAILED = "No es posible actualizar Email";
	private static final String SELECT_EMAIL = "Seleccione Un Email en la Tabla";
	private static final String VALIDATION_UPDATE_TITLE = "Validacion";

	public static void updateEmailConfigInfo() {
		int row;
		Integer emailConfigId;
		int status = 0;
		String email, password, activeService;
		EmailConfig emailConfig = new EmailConfig();
		row = CheckinMainFrame.tblEmail.getSelectedRow();
		try {
			if (row > -1) {
				emailConfigId = Integer.parseInt(CheckinMainFrame.tblEmail.getValueAt(row, 0).toString());
				emailConfig = EmailConfigClient.getEmailConfigById(emailConfigId);
				if (emailConfig != null) {
					email = (String) CheckinMainFrame.tblEmail.getValueAt(row, 1).toString();
					password = (String) CheckinMainFrame.tblEmail.getValueAt(row, 2).toString();
					activeService = (String) CheckinMainFrame.tblEmail.getValueAt(row, 3).toString();

					if (!email.isEmpty()) {
						emailConfig.setEmail(EncryptSecurity.encrypt(email));
					}
					if (!password.isEmpty()) {
						emailConfig.setPassword(EncryptSecurity.encrypt(password));
					}
					if (!activeService.isEmpty()) {
						if (activeService.equals("1") || activeService.equals("true")) {
							emailConfig.setIsActiveService(true);
						} else {
							emailConfig.setIsActiveService(false);
						}
					}
					status = EmailConfigClient.updateEmailConfig(emailConfig);
					if (status > 0 && status < 300) {
						JOptionPane.showMessageDialog(null, EMAIL_UPDATED);
						ReadEmailInfo.fillAllEmailTable();
					} else {
						JOptionPane.showMessageDialog(null, EMAIL_NOT_FOUND, VALIDATION_UPDATE_TITLE,
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, EMAIL_NOT_FOUND, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, SELECT_EMAIL, VALIDATION_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			logger.warning("ERROR AT UPDATE EMAIL: " + ex);
			JOptionPane.showMessageDialog(null, EMAIL_UPDATE_FAILED, VALIDATION_UPDATE_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
