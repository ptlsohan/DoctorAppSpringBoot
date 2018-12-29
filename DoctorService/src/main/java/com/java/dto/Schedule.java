package com.java.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
@Embeddable
public class Schedule {
// 11/9/2018: leave
//	private LocalDate date;
//	
//	@Embedded
//	private AvailableTime availTimes;
//	@ElementCollection
//	Map<AvailableTime,List<Days>> availability = new HashMap<>();
	
//	@Embedded
//	@Enumerated(EnumType.STRING)
//	private Map<Days,AvailableTime> availability = new HashMap<>();
	@Enumerated(EnumType.STRING)
	private Days day;
	@Embedded
	private AvailableTime availTime;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (day != other.day)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		return result;
	}
	
}
