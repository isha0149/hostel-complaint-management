// package src.main.java.com.hostel.complain.service;

// public class ComplaintService {
    
// }


package com.hostel.complaint.service;



import com.hostel.complaint.model.Complaint;
import com.hostel.complaint.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComplaintService {
    
    @Autowired
    private ComplaintRepository complaintRepository;
    
    private int complaintCounter = 1;

    public Complaint createComplaint(Complaint complaint) {
        String id = "CP" + String.format("%03d", complaintCounter++);
        complaint.setId(id);
        complaint.setDateSubmitted(LocalDateTime.now());
        complaint.setStatus(Complaint.Status.PENDING);
        
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Optional<Complaint> getComplaintById(String id) {
        return complaintRepository.findById(id);
    }

    public List<Complaint> getComplaintsByStatus(Complaint.Status status) {
        return complaintRepository.findByStatus(status);
    }

    public List<Complaint> getComplaintsByCategory(Complaint.Category category) {
        return complaintRepository.findByCategory(category);
    }

    public List<Complaint> getComplaintsByHostel(String hostelType) {
        return complaintRepository.findByHostelType(hostelType);
    }

    public List<Complaint> getComplaintsByStudent(String rollNumber) {
        return complaintRepository.findByRollNumber(rollNumber);
    }

    public Complaint updateComplaintStatus(String id, Complaint.Status status) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(id);
        if (complaintOpt.isPresent()) {
            Complaint complaint = complaintOpt.get();
            complaint.setStatus(status);
            
            if (status == Complaint.Status.RESOLVED) {
                complaint.setDateResolved(LocalDateTime.now());
            }
            
            return complaintRepository.save(complaint);
        }
        return null;
    }

    public Complaint assignComplaint(String id, String assignedTo) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(id);
        if (complaintOpt.isPresent()) {
            Complaint complaint = complaintOpt.get();
            complaint.setAssignedTo(assignedTo);
            complaint.setStatus(Complaint.Status.IN_PROGRESS);
            return complaintRepository.save(complaint);
        }
        return null;
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        Long totalComplaints = complaintRepository.count();
        Long pendingComplaints = complaintRepository.countByStatus(Complaint.Status.PENDING);
        Long resolvedComplaints = complaintRepository.countByStatus(Complaint.Status.RESOLVED);
        Double avgResolutionTime = complaintRepository.getAverageResolutionTime();
        
        stats.put("totalComplaints", totalComplaints);
        stats.put("pendingComplaints", pendingComplaints);
        stats.put("resolvedComplaints", resolvedComplaints);
        stats.put("avgResolutionTime", avgResolutionTime != null ? avgResolutionTime : 0.0);
        
        List<Object[]> categoryStats = complaintRepository.getComplaintCountByCategory();
        Map<String, Long> categoryBreakdown = new HashMap<>();
        for (Object[] row : categoryStats) {
            categoryBreakdown.put(row[0].toString(), (Long) row[1]);
        }
        stats.put("categoryBreakdown", categoryBreakdown);
        
        return stats;
    }

    public boolean deleteComplaint(String id) {
        if (complaintRepository.existsById(id)) {
            complaintRepository.deleteById(id);
            return true;
        }
        return false;
    }
}