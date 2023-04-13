package com.tripMate.demo.repository;

import com.tripMate.demo.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer > {


//    @Query(value = "SELECT * FROM experience  WHERE experiences.title LIKE :search", nativeQuery = true)
  //  List<Experience> findByCity(@Param("search") String search);
    List<Experience> findByCategory_id(int id);
    List<Experience> findByCity_id(int id);

    List<Experience> findByTitleContaining(String title);

}

