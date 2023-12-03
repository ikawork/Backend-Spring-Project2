package com.irakliM.demo.repositories;

import com.irakliM.demo.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    @Query(value = "select * from student " +
            "where active = :active and " +
            "(:searchText is null or concat(first_name, concat(' ', last_name)) like :searchText)",
            countQuery = "select count(*) from student " +
                    "where active = :active and " +
                    "(:searchText is null or concat(first_name, concat(' ', last_name)) like :searchText)",
            nativeQuery = true )
    Slice<Student> search(@Param("active") Integer active, @Param("searchText") String searchText, Pageable pageable);





}
