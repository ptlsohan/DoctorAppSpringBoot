package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.Leave;
import com.java.dto.LeaveStatus;

@Repository
@Transactional
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

	@Modifying
	@Query("update Leave set status= :status where leaveId =: leaveId")
	public void updateLeave_Status(LeaveStatus status,int leaveId);
	
	
	
}
