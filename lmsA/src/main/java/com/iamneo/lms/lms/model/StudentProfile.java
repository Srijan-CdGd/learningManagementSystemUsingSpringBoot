package com.iamneo.lms.lms.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;
    
    @OneToOne
    private User user;

    private String name;

    private byte[] image;
    private Date dateOfBirth;
    private Date dateOfJoining;

    @OneToOne
    private Contact contact;

    @OneToOne
    private AcademicInfo academicInfos;

    private boolean isComplete;
}
