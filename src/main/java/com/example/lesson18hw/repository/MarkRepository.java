package com.example.lesson18hw.repository;

import com.example.lesson18hw.entity.MarkEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends CrudRepository<MarkEntity, Integer>, PagingAndSortingRepository<MarkEntity, Integer> {

    @Query("from MarkEntity as s where s.student.id = ?1 and s.createdDate = ?2")
    List<MarkEntity> findAllByIdAndCreatedDate(Integer id, LocalDate day);

    @Query("from MarkEntity as s where s.student.id = ?1 and s.createdDate between ?2 and ?3")
    List<MarkEntity> findAllByIdAndCreatedDateBetween(Integer id, LocalDate from, LocalDate to);

    @Query("from MarkEntity as s where s.student.id = ?1  ORDER BY s.createdDate DESC")
    List<MarkEntity> findAllById(Integer student_id);

    @Query("from MarkEntity as s where s.student.id = ?1 and s.course.id = ?2 ORDER BY s.createdDate DESC")
    List<MarkEntity> findAllByIdAndCourse(Integer student_id, Integer course_id);

    @Query("select cm from MarkEntity as cm " +
            " inner join fetch cm.course " +
            " inner join fetch cm.student s" +
            " where s.id = ?1  order by cm.createdDate desc LIMIT 1")
    Optional<MarkEntity> getLastMarkByStudentIdFetch(Integer student_id);

    @Query("from MarkEntity as s where s.student.id = ?1 order by s.mark desc limit 3")
    List<MarkEntity> getThreeMarkByStudentId(Integer student_id);

    @Query("from MarkEntity as s where s.student.id = ?1 order by s.createdDate asc limit 1")
    List<MarkEntity> getFirstByMarkByStudentId(Integer student_id);

    @Query("from MarkEntity as s where s.student.id = ?1 and s.course.id = ?2 order by s.createdDate asc limit 1")
    List<MarkEntity> findAllByStudentIdAndCourseIdOrderByCreatedDate(Integer student_id, Integer course_id);

    @Query("from MarkEntity as s where s.student.id = ?1 and s.course.id = ?2 order by s.mark desc limit 1")
    List<MarkEntity> getByStudentIdAndCourseIdMarkHighest(Integer student_id, Integer course_id);

    @Query("SELECT AVG(s.mark) FROM MarkEntity as s WHERE s.student.id = ?1")
    Double getByStudentAverageMark(Integer student_id);

    @Query("SELECT AVG(s.mark) FROM MarkEntity as s WHERE s.student.id = ?1 and s.course.id = ?2")
    Double getByStudentCourseAverageMark(Integer student_id, Integer course_id);

    @Query("SELECT COUNT(*) FROM MarkEntity as s WHERE s.student.id = ?1 and s.mark > ?2")
    Double getByStudentHigherMark(Integer student_id, Integer student_mark);


    @Query("select MAX(s.mark) FROM MarkEntity as s WHERE s.course.id = ?1")
    Integer getByCourseHigherMark(Integer course_id);

    @Query("SELECT AVG(s.mark) FROM MarkEntity as s WHERE s.course.id = ?1")
    Double getByCourseAverageMark(Integer course_id);

    @Query("SELECT count(*) FROM MarkEntity as s WHERE s.course.id = ?1")
    Double getByCourseGradesCountMark(Integer course_id);


}
