package com.example.lesson18hw.controller;

import com.example.lesson18hw.dto.StudentDTO;
import com.example.lesson18hw.enums.Gender;
import com.example.lesson18hw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create/student")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        // todo 1. Create student name,surname,level,age,Gender,
        return ResponseEntity.ok(studentService.createStudentService(studentDTO));
    }

    @GetMapping("/all/student")
    public ResponseEntity<List<StudentDTO>> allStudent() {
        // todo 2. Get Student List. return all.
        return ResponseEntity.ok(studentService.allStudentService());
    }

    @GetMapping("/byId/student/{id}")
    public ResponseEntity<StudentDTO> byIdStudent(@PathVariable("id") Integer id) {
        // todo 3. Get student by id.
        return ResponseEntity.ok(studentService.byIdStudentService(id));
    }

    @PostMapping("/update/student/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) {
        // todo 4. Update student.
        return ResponseEntity.ok(studentService.updateStudentService(id, dto));
    }


    @DeleteMapping("/delete/student/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") Integer id) {
        // todo 5. Delete Student by id.
        return ResponseEntity.ok(studentService.deleteStudentService(id));
    }


    // todo 6. Get method by each field. (getByName, getBySurname, getByLevel, getByAge, getByGender)
    @GetMapping("/byName/Student/{name}")
    public ResponseEntity<List<StudentDTO>> byNameStudent(@PathVariable("name") String name) {
        // todo 6.1
        return ResponseEntity.ok(studentService.byNameStudentService(name));
    }

    @GetMapping("/bySurname/Student/{surname}")
    public ResponseEntity<List<StudentDTO>> bySurnameStudent(@PathVariable("surname") String surname) {
        // todo 6.2
        return ResponseEntity.ok(studentService.bySurnameStudentService(surname));
    }


    @GetMapping("/byLevel/Student/{level}")
    public ResponseEntity<List<StudentDTO>> byLevelStudent(@PathVariable("level") Integer level) {
        // todo 6.3
        return ResponseEntity.ok(studentService.byLevelStudentService(level));
    }

    @GetMapping("/byAge/Student/{age}")
    public ResponseEntity<List<StudentDTO>> byAgeStudent(@PathVariable("age") Integer age) {
        // todo 6.4
        return ResponseEntity.ok(studentService.byAgeStudentService(age));
    }

    @GetMapping("/byGender/Student/{gender}")
    public ResponseEntity<List<StudentDTO>> byGenderStudent(@PathVariable("gender") Gender gender) {
        // todo 6.5
        return ResponseEntity.ok(studentService.byGenderStudentService(gender));
    }

    @GetMapping("/byListDate/student/{date}")
    public ResponseEntity<List<StudentDTO>> byListDate(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(studentService.byListDateService(date));
    }

    @GetMapping("/byListBetweenDate/student")
    public ResponseEntity<List<StudentDTO>> byListBetweenDate(@RequestParam(name = "from") LocalDate from,
                                                              @RequestParam(name = "to") LocalDate to) {
        return ResponseEntity.ok(studentService.byListBetweenDateService(from, to));
    }


    //                       TODO  Pagination
    @PostMapping("/pagination/Student")
    public ResponseEntity<Page<StudentDTO>> paginationStudent(@RequestParam("page") Integer page,
                                                              @RequestParam("size") Integer size) {
        //todo 9. Student Pagination;
        return ResponseEntity.ok(studentService.paginationStudentService(page, size));
    }

    @PostMapping("/paginationByLevel/Student")
    public ResponseEntity<Page<StudentDTO>> paginationByLevel(@RequestParam("level") Integer level,
                                                              @RequestParam("page") Integer page,
                                                              @RequestParam("size") Integer size) {
        // todo 10. Student Pagination by given Level. List should be sorted by id.
        return ResponseEntity.ok(studentService.paginationByLevelService(level, page - 1, size));
    }

    @PostMapping("/paginationByGender/Student")
    public ResponseEntity<Page<StudentDTO>> paginationByGender(@RequestParam("gender") Gender gender,
                                                               @RequestParam("page") Integer page,
                                                               @RequestParam("size") Integer size) {
        return ResponseEntity.ok(studentService.paginationByGenderService(gender, page - 1, size));
    }


}
