package com.example.lesson18hw.repository;

import com.example.lesson18hw.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Integer>, PagingAndSortingRepository<CourseEntity, Integer> {

    @Query("from CourseEntity as s where s.name = ?1")
    List<CourseEntity> findAllByName(String name);

    @Query("from CourseEntity as s where s.price = ?1")
    List<CourseEntity> findAllByPrice(Integer price);

    @Query("from CourseEntity as s where s.duration = ?1")
    List<CourseEntity> findAllByDuration(Integer duration);

    @Query("from CourseEntity as s where s.price between ?1 and ?2")
    List<CourseEntity> findAllByPriceBetween(Integer from, Integer to);

    @Query("from CourseEntity as s where s.createdDate between ?1 and ?2")
    List<CourseEntity> findAllByCreatedDateBetween(LocalDate from, LocalDate to);

    Page<CourseEntity> findAllBy(Pageable pageable);

    Page<CourseEntity> findByPrice(Integer price, Pageable pageable);

    Page<CourseEntity> findByPriceBetween(Integer from, Integer to, Pageable pageable);

}
