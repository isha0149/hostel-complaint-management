// package src.main.java.com.hostel.complain.repository;

// public class ComplaintRepository {
    
// }


package com.hostel.complaint.repository;

import com.hostel.complaint.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, String> {
    
    List<Complaint> findByStatus(Complaint.Status status);
    
    List<Complaint> findByCategory(Complaint.Category category);
    
    List<Complaint> findByHostelType(String hostelType);
    
    List<Complaint> findByRollNumber(String rollNumber);
    
    @Query("SELECT c FROM Complaint c WHERE c.dateSubmitted BETWEEN :startDate AND :endDate")
    List<Complaint> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(c) FROM Complaint c WHERE c.status = :status")
    Long countByStatus(@Param("status") Complaint.Status status);
    
    @Query("SELECT c.category, COUNT(c) FROM Complaint c GROUP BY c.category")
    List<Object[]> getComplaintCountByCategory();
    
    @Query("SELECT AVG(DATEDIFF(c.dateResolved, c.dateSubmitted)) FROM Complaint c WHERE c.dateResolved IS NOT NULL")
    Double getAverageResolutionTime();
}