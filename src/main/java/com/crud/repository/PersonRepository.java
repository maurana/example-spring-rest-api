package com.crud.repository;

import com.crud.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String>{
    @Query(value = "SELECT * FROM person WHERE LOWER(p_name) = ?1", nativeQuery = true)
    Person findByName(String name);

    @Query(value = "SELECT p.* FROM person p " +
        "JOIN nationality n ON p.p_n_id = n.n_id WHERE LOWER(p.p_name) LIKE ?1 " +
        "AND n.n_id LIKE ?2 AND p.p_status = true ORDER BY p.p_created ASC", nativeQuery = true)
    Page<Person> findPerPage(String name, String nationalityId, Pageable pageable);
}