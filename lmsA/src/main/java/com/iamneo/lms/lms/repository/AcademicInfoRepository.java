package com.iamneo.lms.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamneo.lms.lms.model.AcademicInfo;

@Repository
public interface AcademicInfoRepository extends JpaRepository<AcademicInfo, Long> {

}
