package com.example.lesson18hw.service;

import com.example.lesson18hw.Mapper.StudentMapper;
import com.example.lesson18hw.dto.StudentDTO;
import com.example.lesson18hw.entity.StudentEntity;
import com.example.lesson18hw.enums.Gender;
import com.example.lesson18hw.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO createStudentService(StudentDTO dto) {
        StudentEntity entity = StudentMapper.toStudentEntity(dto);
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> allStudentService() {
        Iterable<StudentEntity> iterator = studentRepository.findAll();
        List<StudentDTO> list = new ArrayList<>();
        for (StudentEntity studentEntity : iterator) {
            list.add(StudentMapper.toStudentDto(studentEntity));
        }
        return list;
    }

    public StudentDTO byIdStudentService(Integer id) {
        StudentEntity entity = get(id);
        return StudentMapper.toStudentDto(entity);
    }

    public StudentDTO updateStudentService(Integer id, StudentDTO dto) {
        StudentEntity entity = get(id);// check if student exists
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(LocalDate.now());
        studentRepository.save(entity);
        return StudentMapper.toStudentDto(entity);
    }

    public Boolean deleteStudentService(Integer id) {
        StudentEntity entity = get(id);
        studentRepository.delete(entity);
        return true;
    }


    public List<StudentDTO> byNameStudentService(String name) {
        List<StudentEntity> entity = studentRepository.findAllByName(name);
        return mapWithStream(entity);
    }

//    public List<StudentEntity> byNameStudentService(String name) {
////        List<StudentEntity> entity = studentRepository.findAllByName(name);
//        return studentRepository.findAllByName(name);
//    }

    public List<StudentDTO> bySurnameStudentService(String surname) {
        List<StudentEntity> entity = studentRepository.findAllBySurname(surname);
        return mapWithStream(entity);
    }

    public List<StudentDTO> byLevelStudentService(Integer level) {
        List<StudentEntity> entity = studentRepository.findAllByLevel(level);
        return mapWithStream(entity);
    }

    public List<StudentDTO> byAgeStudentService(Integer age) {
        List<StudentEntity> entity = studentRepository.findAllByAge(age);
        return mapWithStream(entity);
    }

    public List<StudentDTO> byGenderStudentService(Gender gender) {
        List<StudentEntity> entity = studentRepository.findAllByGender(gender);
        return mapWithStream(entity);
    }

    public List<StudentDTO> byListDateService(LocalDate date) {
        List<StudentEntity> entity = studentRepository.findAllByCreatedDate(date);
        return mapWithStream(entity);
    }

    public List<StudentDTO> byListBetweenDateService(LocalDate from, LocalDate to) {
        List<StudentEntity> entity = studentRepository.findAllByCreatedDateBetween(from, to);
        return mapWithStream(entity);
    }


    //                 TODO   Pagination
    public Page<StudentDTO> paginationStudentService(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> entityPage = studentRepository.findAll(pageable);
        return responsePage(entityPage, pageable);
    }

    public Page<StudentDTO> paginationByLevelService(Integer level, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<StudentEntity> entityPage = studentRepository.findAllByLevel(level, pageable);
        return responsePage(entityPage, pageable);
    }

    public Page<StudentDTO> paginationByGenderService(Gender gender, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<StudentEntity> entityPage = studentRepository.findAllByGender(gender, pageable);
        return responsePage(entityPage, pageable);

    }


    // TODO METHOD
    public StudentEntity get(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public List<StudentDTO> mapWithStream(List<StudentEntity> entityList) {
        return entityList.stream().map(StudentMapper::toStudentDto).collect(Collectors.toList());
    }

    // TODO METHOD Page
    public Page<StudentDTO> responsePage(Page<StudentEntity> entityPage, Pageable pageable) {
        List<StudentDTO> list = new ArrayList<>();
        for (StudentEntity entity : entityPage.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        Long totalElements = entityPage.getTotalElements();
        return new PageImpl<>(list, pageable, totalElements);
    }


}
