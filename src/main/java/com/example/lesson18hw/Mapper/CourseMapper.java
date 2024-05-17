package com.example.lesson18hw.Mapper;

import com.example.lesson18hw.dto.CourseDTO;
import com.example.lesson18hw.dto.StudentDTO;
import com.example.lesson18hw.entity.CourseEntity;

import java.time.LocalDate;

public class CourseMapper {
    public static CourseDTO toCourseDto(CourseEntity entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public static CourseEntity toCourseEntity(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
}
