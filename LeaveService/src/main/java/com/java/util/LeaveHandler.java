package com.java.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.java.dto.Leave;
import com.java.dto.LeaveStatus;



@Component
@RepositoryEventHandler(Leave.class)
public class LeaveHandler {


	@Autowired
	JmsTemplate template;

	
	@HandleAfterDelete
	public void handleAppointmentAfterDelete(Leave leave) {
		System.out.println("invoked");
		GenericMessage<Leave> message = new GenericMessage<Leave>(leave);
		leave.setStatus(LeaveStatus.REJECTED);
		template.convertAndSend("appointment-queue", message);
	}
	
	@HandleAfterCreate
	public void handleAppointmentAfterCreate(Leave leave) {
		Gson gson = new Gson();

		template.send("leave.queue", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(gson.toJson(leave));
			}
		});
	}
	
	@HandleAfterSave
	public void handleAppointmentAfterUpdate(Leave leave) {
		Gson gson = new Gson();

		template.send("leave.queue", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(gson.toJson(leave));
			}
		});
	}

}


