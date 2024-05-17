package com.example.lesson18hw.service;


import com.example.lesson18hw.dto.CourseDTO;
import com.example.lesson18hw.dto.MarkDto;
import com.example.lesson18hw.dto.StudentDTO;
import com.example.lesson18hw.entity.CourseEntity;
import com.example.lesson18hw.entity.MarkEntity;
import com.example.lesson18hw.entity.StudentEntity;
import com.example.lesson18hw.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarkService {
    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    public MarkDto createMarkService(MarkDto markDto) {
        MarkEntity entity = toMarkEntity(markDto);
        markRepository.save(entity);
        markDto.setId(entity.getId());
        markDto.setCreatedDate(entity.getCreatedDate());
        return markDto;
    }

    public MarkDto updateByIdService(Integer id, MarkDto markDto) {
        MarkEntity entity = get(id);
        entity.setStudent(studentService.get(markDto.getStudentId()));
        entity.setCourse(courseService.get(markDto.getCourseId()));
        entity.setMark(markDto.getMark());
        entity.setCreatedDate(LocalDate.now());
        return toMarkDto(markRepository.save(entity));
    }

    public MarkDto byIdMarkService(Integer id) {
        MarkEntity entity = get(id);

        return toMarkDto(entity);
    }

    public MarkDto byIdDetailService(Integer id) {
        MarkEntity entity = get(id);

        MarkDto markDto = new MarkDto();
        markDto.setId(entity.getId());
        markDto.setCreatedDate(entity.getCreatedDate());
        markDto.setMark(entity.getMark());

        // todo student

        StudentEntity studentEntity = studentService.get(entity.getStudent().getId());
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        markDto.setStudent(studentDTO);

        // todo course
        CourseEntity courseEntity = courseService.get(entity.getCourse().getId());
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(courseEntity.getId());
        courseDTO.setName(courseEntity.getName());
        markDto.setCourse(courseDTO);

        return markDto;
    }

    public Boolean deleteByIdService(Integer id) {
        markRepository.delete(get(id));
        return true;
    }

    public List<MarkDto> allMarkService() {
        Iterable<MarkEntity> entity = markRepository.findAll();
        List<MarkDto> list = new ArrayList<>();

        for (MarkEntity markEntity : entity) {
            list.add(toMarkDto(markEntity));
        }
        return list;
    }

    public List<MarkDto> byIdDayMarkStudentService(Integer student_id, LocalDate day) {
        studentService.get(student_id);
        return markRepository.findAllByIdAndCreatedDate(student_id, day)
                .stream()
                .map(this::toMarkDto)
                .collect(Collectors.toList());
    }

    public List<MarkDto> byIdTwoMarkStudentService(Integer student_id, LocalDate day, LocalDate to) {
        studentService.get(student_id);
        return markRepository.findAllByIdAndCreatedDateBetween(student_id, day, to)
                .stream()
                .map(this::toMarkDto)
                .collect(Collectors.toList());
    }

    public List<MarkDto> allStudentMarkService(Integer student_id) {
        studentService.get(student_id);
        return markRepository.findAllById(student_id)
                .stream()
                .map(this::toMarkDto)
                .collect(Collectors.toList());
    }

    public List<MarkDto> allCourseMarkService(Integer student_id, Integer course_id) {
        studentService.get(student_id);
        courseService.get(course_id);
        List<MarkEntity> entity = markRepository.findAllByIdAndCourse(student_id, course_id);
        List<MarkDto> list = new ArrayList<>();
        for (MarkEntity markEntity : entity) {
            MarkDto markDto = new MarkDto();
            markDto.setMark(markEntity.getMark());
            markDto.setCreatedDate(markEntity.getCreatedDate());
            list.add(markDto);
        }
        return list;
    }

    public MarkDto studentLastMarkService(Integer student_id) {
        Optional<MarkEntity> optional = markRepository.getLastMarkByStudentIdFetch(student_id);

        if (optional.isEmpty()) {
            return new MarkDto();
        }
        MarkEntity entity = optional.get();
        MarkDto dto = new MarkDto();

        dto.setMark(entity.getMark());


        CourseEntity course = entity.getCourse();
        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        dto.setCourse(courseDTO);

        return dto;
    }

    public List<MarkDto> studentBigThreeService(Integer student_id) {
        StudentEntity studentEntity = studentService.get(student_id);
        List<MarkEntity> entity = markRepository.getThreeMarkByStudentId(student_id);
        List<MarkDto> list = new ArrayList<>();

        for (MarkEntity markEntity : entity) {
            MarkDto markDto = new MarkDto();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(studentEntity.getName());
            markDto.setMark(markEntity.getMark());
            markDto.setStudent(studentDTO);

            list.add(markDto);
        }
        return list;
    }

    public List<MarkDto> studentFirstMarkService(Integer student_id) {
        StudentEntity studentEntity = studentService.get(student_id);
        List<MarkEntity> entity = markRepository.getFirstByMarkByStudentId(student_id);
        List<MarkDto> list = new ArrayList<>();

        for (MarkEntity markEntity : entity) {
            MarkDto markDto = new MarkDto();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(studentEntity.getName());
            studentDTO.setSurname(studentEntity.getSurname());
            markDto.setMark(markEntity.getMark());
            markDto.setCreatedDate(markEntity.getCreatedDate());
            markDto.setStudent(studentDTO);

            list.add(markDto);
        }
        return list;
    }

    public List<MarkDto> studentCourseMarkService(Integer student_id, Integer course_id) {
        CourseEntity courseEntity = courseService.get(course_id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(courseEntity.getName());

        StudentEntity studentEntity = studentService.get(student_id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());

        List<MarkEntity> entity = markRepository.findAllByStudentIdAndCourseIdOrderByCreatedDate(student_id, course_id);
        List<MarkDto> list = new ArrayList<>();
        for (MarkEntity markEntity : entity) {
            MarkDto markDto = new MarkDto();
            markDto.setMark(markEntity.getMark());
            markDto.setCreatedDate(markEntity.getCreatedDate());
            markDto.setStudent(studentDTO);
            markDto.setCourse(courseDTO);

            list.add(markDto);
        }
        return list;
    }

    public List<MarkDto> studentCourseHighestGradeMarkService(Integer student_id, Integer course_id) {
        // todo student
        StudentEntity studentEntity = studentService.get(student_id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        // todo course
        CourseEntity courseEntity = courseService.get(course_id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(courseEntity.getName());
        // todo mark
        List<MarkEntity> entity = markRepository.getByStudentIdAndCourseIdMarkHighest(student_id, course_id);
        List<MarkDto> list = new ArrayList<>();

        for (MarkEntity markEntity : entity) {
            MarkDto markDto = new MarkDto();
            markDto.setMark(markEntity.getMark());
            markDto.setCreatedDate(markEntity.getCreatedDate());
            markDto.setStudent(studentDTO);
            markDto.setCourse(courseDTO);

            list.add(markDto);
        }
        return list;
    }

    public MarkDto studentAverageMarkService(Integer student_id) {
        // todo student
        StudentEntity studentEntity = studentService.get(student_id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        Double aDouble = markRepository.getByStudentAverageMark(student_id);
        MarkDto markDto = new MarkDto();
        markDto.setStudent(studentDTO);
        markDto.setAverage(aDouble);

        return markDto;
    }

    public MarkDto studentCourseAverageMarkService(Integer student_id, Integer course_id) {
        // todo student
        StudentEntity studentEntity = studentService.get(student_id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        // todo course
        CourseEntity courseEntity = courseService.get(course_id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(courseEntity.getName());
        // todo mark
        Double aDouble = markRepository.getByStudentCourseAverageMark(student_id, course_id);
        MarkDto markDto = new MarkDto();
        markDto.setStudent(studentDTO);
        markDto.setAverage(aDouble);
        markDto.setCourse(courseDTO);

        return markDto;
    }

    //TODO                    METHOD
    public MarkEntity get(Integer id) {
        return markRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Mark not found"));
    }

    public MarkEntity toMarkEntity(MarkDto markDto) {
        MarkEntity entity = new MarkEntity();

        entity.setStudent(studentService.get(markDto.getStudentId()));
        entity.setCourse(courseService.get(markDto.getCourseId()));

        entity.setMark(markDto.getMark());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }

    public MarkDto toMarkDto(MarkEntity entity) {
        MarkDto markDto = new MarkDto();
        markDto.setId(entity.getId());

        markDto.setStudentId(entity.getStudent().getId());
        markDto.setCourseId(entity.getCourse().getId());

        markDto.setMark(entity.getMark());
        markDto.setCreatedDate(entity.getCreatedDate());
        return markDto;
    }



}
