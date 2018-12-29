package com.java.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.java.service.EmailService;

@Component
public class NotificationListener {
	@Autowired EmailService emailService;
	
	
	@JmsListener(destination = "appointment.queue")
	public void receiveMessage(Message message) throws JMSException {
		 if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("Received: " + textMessage.getText());
            JSONObject object = new JSONObject(textMessage.getText());
     		String content = "Appointment start time: " + object.getJSONObject("timeOfAppointment") + "\n" 
     				 
     				 
     				+ "Clinic address: "+ object.getString("clinicAddress") + "\n" 
     				+ "Doctor name: " + object.getString("doctorName") + "\n" 
     				+ "Speciality: "+ object.getString("speciality");

     		emailService.sendEmail(object.getString("patientEmailId"),"Appointment " + object.getString("status"),content);
         } else {
             System.out.println("Invalid");
         }
	}


	
}
