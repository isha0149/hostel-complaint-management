// package src.main.java.com.hostel.complain.controller;

// public class ComplaintController {
    
// }


package com.hostel.complaint.controller;

import com.hostel.complaint.model.Complaint;
import com.hostel.complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {
    
    @Autowired
    private ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) {
        try {
            Complaint savedComplaint = complaintService.createComplaint(complaint);
            return new ResponseEntity<>(savedComplaint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints = complaintService.getAllComplaints();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable String id) {
        Optional<Complaint> complaint = complaintService.getComplaintById(id);
        return complaint.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Complaint>> getComplaintsByStatus(@PathVariable String status) {
        try {
            Complaint.Status complaintStatus = Complaint.Status.valueOf(status.toUpperCase());
            List<Complaint> complaints = complaintService.getComplaintsByStatus(complaintStatus);
            return new ResponseEntity<>(complaints, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Complaint>> getComplaintsByCategory(@PathVariable String category) {
        try {
            Complaint.Category complaintCategory = Complaint.Category.valueOf(category.toUpperCase());
            List<Complaint> complaints = complaintService.getComplaintsByCategory(complaintCategory);
            return new ResponseEntity<>(complaints, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/hostel/{hostelType}")
    public ResponseEntity<List<Complaint>> getComplaintsByHostel(@PathVariable String hostelType) {
        List<Complaint> complaints = complaintService.getComplaintsByHostel(hostelType);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping("/student/{rollNumber}")
    public ResponseEntity<List<Complaint>> getComplaintsByStudent(@PathVariable String rollNumber) {
        List<Complaint> complaints = complaintService.getComplaintsByStudent(rollNumber);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Complaint> updateComplaintStatus(@PathVariable String id, 
                                                            @RequestParam String status) {
        try {
            Complaint.Status complaintStatus = Complaint.Status.valueOf(status.toUpperCase());
            Complaint updatedComplaint = complaintService.updateComplaintStatus(id, complaintStatus);
            
            if (updatedComplaint != null) {
                return new ResponseEntity<>(updatedComplaint, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<Complaint> assignComplaint(@PathVariable String id, 
                                                     @RequestParam String assignedTo) {
        Complaint updatedComplaint = complaintService.assignComplaint(id, assignedTo);
        
        if (updatedComplaint != null) {
            return new ResponseEntity<>(updatedComplaint, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = complaintService.getDashboardStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable String id) {
        boolean deleted = complaintService.deleteComplaint(id);
        
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}