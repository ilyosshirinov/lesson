package com.example.lesson18hw.service;

import com.example.lesson18hw.Mapper.CourseMapper;
import com.example.lesson18hw.dto.CourseDTO;
import com.example.lesson18hw.entity.CourseEntity;
import com.example.lesson18hw.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO createCourseService(CourseDTO dto) {
        CourseEntity entity = CourseMapper.toCourseEntity(dto);
        courseRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }

    public CourseEntity byIdCourseService(Integer id) {
        return get(id);
    }

    public List<CourseDTO> allCourseService() {
        Iterable<CourseEntity> entity = courseRepository.findAll();
        List<CourseDTO> list = new ArrayList<>();
        for (CourseEntity courseEntity : entity) {
            list.add(CourseMapper.toCourseDto(courseEntity));
        }
        return list;
    }

    public CourseDTO updateCourseService(Integer id, CourseDTO dto) {
        CourseEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        return CourseMapper.toCourseDto(courseRepository.save(entity));
    }

    public Boolean deleteCourseService(Integer id) {
        CourseEntity entity = get(id);
        courseRepository.delete(entity);
        return true;

    }

    public List<CourseDTO> byNameCourseService(String name) {
        List<CourseEntity> entity = courseRepository.findAllByName(name);
        return mapWithStreamCourse(entity);
    }

    public List<CourseDTO> byPriceCourseService(Integer price) {
        List<CourseEntity> entity = courseRepository.findAllByPrice(price);
        return mapWithStreamCourse(entity);
    }

    public List<CourseDTO> byDurationCourseService(Integer duration) {
        List<CourseEntity> entity = courseRepository.findAllByDuration(duration);
        return mapWithStreamCourse(entity);
    }

    public List<CourseDTO> pricesBetweenService(Integer from, Integer to) {
        List<CourseEntity> entity = courseRepository.findAllByPriceBetween(from, to);
        return mapWithStreamCourse(entity);
    }

    public List<CourseDTO> createdDatesBetweenService(LocalDate from, LocalDate to) {
        List<CourseEntity> entity = courseRepository.findAllByCreatedDateBetween(from, to);
        return mapWithStreamCourse(entity);
    }


    //               TODO  Pagination

    public Page<CourseDTO> coursePaginationService(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseEntity> entityPage = courseRepository.findAll(pageable);
        return responsePageCourse(entityPage, pageable);
    }

    public Page<CourseDTO> createdDateService(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CourseEntity> entityPage = courseRepository.findAllBy(pageable);
        return responsePageCourse(entityPage, pageable);
    }

    public Page<CourseDTO> priceCreatedDateService(Integer page, Integer size, Integer price) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CourseEntity> entityPage = courseRepository.findByPrice(price, pageable);
        return responsePageCourse(entityPage, pageable);
    }

    public Page<CourseDTO> priceBetweenPaginationService(Integer page, Integer size, Integer from, Integer to) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CourseEntity> entityPage = courseRepository.findByPriceBetween(from, to, pageable);
        return responsePageCourse(entityPage, pageable);
    }


    //  TODO Method
    public CourseEntity get(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    public List<CourseDTO> mapWithStreamCourse(List<CourseEntity> entityList) {
        return entityList.stream().map(CourseMapper::toCourseDto).collect(Collectors.toList());
    }

    // TODO Pagination Method
    public Page<CourseDTO> responsePageCourse(Page<CourseEntity> entityPage, Pageable pageable) {
        List<CourseDTO> list = new ArrayList<>();
        for (CourseEntity entity : entityPage) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        long totalElements = entityPage.getTotalElements();
        return new PageImpl<>(list, pageable, totalElements);
    }


}
