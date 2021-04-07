package com.java.project.checkinfront.operations;

import javax.swing.JOptionPane;

import java.util.logging.Logger;

import com.java.project.checkin.client.EmailConfigClient;
import com.java.project.checkinfront.gui.CheckinMainFrame;
import com.java.project.checkinfront.mail.EncryptSecurity;
import com.java.project.checkin.models.EmailConfig;

public class SaveEmailConfigInfo {

	private static Logger logger = Logger.getLogger(SaveEmailConfigInfo.class.getName());
	public final static String EMAIL_SAVE_SUCCESSFULLY = "Email guardado con éxito!";
	public final static String EMAIL_SAVE_FAILED = "No es posible guardar Email en este momento";
	public final static String EMAIL_SAVE_EXC = "Ocurrio una Excepcion al intentar guardar Email";

	public static void createNewEmailConfig() {
		EmailConfig emailConfig = new EmailConfig();
		try {
			emailConfig.setIdEmail(0);
			emailConfig.setEmail(EncryptSecurity.encrypt(CheckinMainFrame.txtNewEmail.getText()));
			emailConfig.setIsActiveService(true);
			emailConfig
					.setPassword(EncryptSecurity.encrypt(new String(CheckinMainFrame.txtNewEmailPass.getPassword())));
			emailConfig = EmailConfigClient.addEmailConfig(emailConfig);
			if (emailConfig != null) {
				JOptionPane.showMessageDialog(null, EMAIL_SAVE_SUCCESSFULLY);
				clearNewEmailConfigSaveFields();
				ReadEmailInfo.fillAllEmailTable();
			} else {
				JOptionPane.showMessageDialog(null, EMAIL_SAVE_FAILED);
			}
		} catch (Exception ex) {
			logger.warning("ERROR: " + ex);
			JOptionPane.showMessageDialog(null, EMAIL_SAVE_EXC);
		}

	}

	public static boolean validateNewEmailConfigSaveFields() {
		String emailPass = new String(CheckinMainFrame.txtNewEmailPass.getPassword());
		if (CheckinMainFrame.txtNewEmail.getText().isEmpty() || emailPass.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private static void clearNewEmailConfigSaveFields() {
		CheckinMainFrame.txtNewEmail.setText(null);
		CheckinMainFrame.txtNewEmailPass.setText(null);
	}

}
