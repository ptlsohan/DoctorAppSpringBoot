package com.java.dto;

import java.time.LocalTime;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AvailableTime {

	private LocalTime startTime;
	private LocalTime endTime;
}
