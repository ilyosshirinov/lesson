package com.example.lesson18hw.Mapper;

import com.example.lesson18hw.dto.StudentDTO;
import com.example.lesson18hw.entity.StudentEntity;

import java.time.LocalDate;

public class StudentMapper {

    public static StudentDTO toStudentDto(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setGender(entity.getGender());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public static StudentEntity toStudentEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
}
