package com.iamneo.lms.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamneo.lms.lms.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
