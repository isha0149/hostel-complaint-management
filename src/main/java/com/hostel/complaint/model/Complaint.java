// package src.main.java.com.hostel.complain.model;

// public class Complaint {
    
// }


package com.hostel.complaint.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    private String id;
    
    @Column(name = "student_name", nullable = false)
    private String studentName;
    
    @Column(name = "roll_number", nullable = false)
    private String rollNumber;
    
    @Column(name = "hostel_type", nullable = false)
    private String hostelType;
    
    @Column(name = "room_number", nullable = false)
    private String roomNumber;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "contact_number")
    private String contactNumber;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    
    @Column(name = "date_submitted")
    private LocalDateTime dateSubmitted = LocalDateTime.now();
    
    @Column(name = "date_resolved")
    private LocalDateTime dateResolved;
    
    @Column(name = "assigned_to")
    private String assignedTo;
    
    @Column(name = "resolution_notes")
    private String resolutionNotes;

    public Complaint() {}
    
    public Complaint(String studentName, String rollNumber, String hostelType, 
                     String roomNumber, Category category, Priority priority, 
                     String description, String contactNumber) {
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.hostelType = hostelType;
        this.roomNumber = roomNumber;
        this.category = category;
        this.priority = priority;
        this.description = description;
        this.contactNumber = contactNumber;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    
    public String getHostelType() { return hostelType; }
    public void setHostelType(String hostelType) { this.hostelType = hostelType; }
    
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public LocalDateTime getDateSubmitted() { return dateSubmitted; }
    public void setDateSubmitted(LocalDateTime dateSubmitted) { this.dateSubmitted = dateSubmitted; }
    
    public LocalDateTime getDateResolved() { return dateResolved; }
    public void setDateResolved(LocalDateTime dateResolved) { this.dateResolved = dateResolved; }
    
    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    
    public String getResolutionNotes() { return resolutionNotes; }
    public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }

    public enum Category {
        PLUMBING, ELECTRIC, CARPENTRY, WIFI, CLEANING
    }

    public enum Priority {
        LOW, MEDIUM, HIGH, URGENT
    }

    public enum Status {
        PENDING, IN_PROGRESS, RESOLVED, CLOSED
    }
}