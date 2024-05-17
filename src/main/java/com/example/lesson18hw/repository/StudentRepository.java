package com.example.lesson18hw.repository;

import com.example.lesson18hw.entity.StudentEntity;
import com.example.lesson18hw.enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {
    @Query("from StudentEntity as s where s.name = ?1")
    List<StudentEntity> findAllByName(String name);

    @Query("from StudentEntity as s where s.surname = ?1")
    List<StudentEntity> findAllBySurname(String surname);

    @Query("from StudentEntity as s where s.level = ?1")
    List<StudentEntity> findAllByLevel(Integer level);

    @Query("from StudentEntity as s where s.age = ?1")
    List<StudentEntity> findAllByAge(Integer age);

    @Query("from StudentEntity as s where s.gender = ?1")
    List<StudentEntity> findAllByGender(Gender gender);

    @Query("from StudentEntity as s where s.createdDate = ?1")
    List<StudentEntity> findAllByCreatedDate(LocalDate from);


    @Query(value = "select * from student18hv as s where s.created_date  between ?1 and ?2",nativeQuery = true)  // native
    List<StudentEntity> findAllByCreatedDateBetween(LocalDate from, LocalDate to);

    // TODO Page
    Page<StudentEntity> findAllByLevel(Integer level, Pageable pageable);

    Page<StudentEntity> findAllByGender(Gender gender, Pageable pageable);
}
