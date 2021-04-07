package com.java.project.checkinfront;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.project.checkinfront.gui.CheckinMainFrame;

@SpringBootApplication
public class CheckinFrontApp {
	
    public static void main( String[] args ) {
    	    initializeMainFrame();
    }
    
	private static void initializeMainFrame() {
			CheckinMainFrame checkin = new CheckinMainFrame();
	    	checkin.setDefaultCloseOperation(CheckinMainFrame.EXIT_ON_CLOSE);
	    	checkin.setVisible(true);
	}
}
