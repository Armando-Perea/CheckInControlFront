package com.java.project.checkinfront;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.java.project.checkin.client.CheckInClient;
import com.java.project.checkin.models.CheckIn;



public class CheckInClientTest {

	public void createDummyCheckIn() {
		CheckIn checkIn = new CheckIn();  
		LocalDateTime myDateObj = LocalDateTime.now();  
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("es","ES"));
        String formattedDate = myDateObj.format(myFormatObj);  
		checkIn.setIdCheckin(0);
		checkIn.setArrivalDate(formattedDate);
		checkIn.setArrivalHour(LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond());
		checkIn.setEmployeename("Isabella");
		checkIn.setRole("Recepcionista");
		checkIn.setConcept("ENTRADA");
		checkIn = CheckInClient.addCheckIn(checkIn);
		System.out.println("CHECK IN CREATED: "+checkIn.toString());
	}
	
	public void updateDummyCheckIn() {
		CheckIn checkIn = new CheckIn();  
		LocalDateTime myDateObj = LocalDateTime.now();  
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("es","ES"));
        String formattedDate = myDateObj.format(myFormatObj);  
		checkIn.setIdCheckin(2);
		checkIn.setArrivalDate(formattedDate);
		checkIn.setArrivalHour(LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond());
		checkIn.setEmployeename("Isabella");
		checkIn.setRole("Recepcionista");
		checkIn.setConcept("ENTRADA");
		int response = CheckInClient.updateCheckIn(checkIn);
		System.out.println("UPDATE RESPONSE: "+response);
	}
	
	public void getAllCheckIn() {
		List<CheckIn> admin = Arrays.asList(CheckInClient.getAllCheckIn());
		admin.forEach(System.out::println);
	}
	
	public void getCheckInById(Integer id) {
		CheckIn checkIn = CheckInClient.getCheckInById(id);
		System.out.println("CHECK IN ID: "+checkIn.toString());
	}
	
	public void getCheckInByName(String name) {
		List<CheckIn> admin = Arrays.asList(CheckInClient.getCheckInByName(name));
		admin.forEach(System.out::println);
	}
	
	public void deleteCheckIn(Integer id) {
		Integer admin = CheckInClient.deleteCheckIn(id);
		System.out.println("CHECK IN REMOVED: "+admin);
	}

	public static void main(String[] args) {
		CheckInClientTest checkInClientTest = new CheckInClientTest();
		checkInClientTest.createDummyCheckIn();
		//checkInClientTest.updateDummyCheckIn();
		//checkInClientTest.getCheckInById(1);
		//checkInClientTest.getCheckInByName("EMPLOYEE ");
		//checkInClientTest.getAllCheckIn();
		//checkInClientTest.deleteCheckIn(2);
		//CheckInClient.truncateCheckin();
		// EVERYTHING WORKING OK !!!
	}
	
}
