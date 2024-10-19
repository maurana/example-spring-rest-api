package com.crud.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.model.Nationality;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, String>{
    @Query(value = "SELECT * FROM nationality WHERE LOWER(n_name) = ?1", nativeQuery = true)
    Nationality findByName(String name);
}