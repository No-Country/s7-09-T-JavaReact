package com.tripMate.demo.repository;

import com.tripMate.demo.entity.City;
import com.tripMate.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer > {

}
