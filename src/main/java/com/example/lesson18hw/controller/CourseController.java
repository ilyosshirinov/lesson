package com.example.lesson18hw.controller;

import com.example.lesson18hw.dto.CourseDTO;
import com.example.lesson18hw.entity.CourseEntity;
import com.example.lesson18hw.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create/course")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO dto) {
        // todo 1. Create Course
        return ResponseEntity.ok(courseService.createCourseService(dto));
    }

    @GetMapping("/byId/course/{id}")
    public ResponseEntity<CourseEntity> byIdCourse(@PathVariable("id") Integer id) {
        // todo 2. Get Course by id.
        return ResponseEntity.ok(courseService.byIdCourseService(id));
    }

    @GetMapping("/all/course")
    public ResponseEntity<List<CourseDTO>> allCourse() {
        // todo 3. Get Course list. return all.
        return ResponseEntity.ok(courseService.allCourseService());
    }

    @PostMapping("update/course")
    public ResponseEntity<CourseDTO> updateCourse(@RequestParam("id") Integer id, @RequestBody CourseDTO dto) {
        // todo 4. Update Course.
        return ResponseEntity.ok(courseService.updateCourseService(id, dto));
    }

    @DeleteMapping("/delete/course")
    public ResponseEntity<Boolean> deleteCourse(@RequestParam("id") Integer id) {
        // todo 5. Delete Course
        return ResponseEntity.ok(courseService.deleteCourseService(id));
    }


    // todo 6. Get method by each field. (getByName, getByPrice,getByDuration)
    @GetMapping("/byName/course")
    public ResponseEntity<List<CourseDTO>> byNameCourse(@RequestParam("name") String name) {
        // todo 6.1
        return ResponseEntity.ok(courseService.byNameCourseService(name));
    }

    @GetMapping("/byPrice/course")
    public ResponseEntity<List<CourseDTO>> byPriceCourse(@RequestParam("price") Integer price) {
        // todo 6.2
        return ResponseEntity.ok(courseService.byPriceCourseService(price));
    }

    @GetMapping("/byDuration/course")
    public ResponseEntity<List<CourseDTO>> byDurationCourse(@RequestParam("duration") Integer duration) {
        // todo 6.3
        return ResponseEntity.ok(courseService.byDurationCourseService(duration));
    }

    @GetMapping("/pricesBetween/course")
    public ResponseEntity<List<CourseDTO>> pricesBetween(@RequestParam("from") Integer from,
                                                         @RequestParam("to") Integer to) {
        // todo 7. Get Course list between given 2 prices.
        return ResponseEntity.ok(courseService.pricesBetweenService(from, to));
    }

    @GetMapping("/createdDatesBetween/course")
    public ResponseEntity<List<CourseDTO>> createdDatesBetween(@RequestParam("from") LocalDate from,
                                                               @RequestParam("to") LocalDate to) {
        // todo 8. Get Course list between 2 createdDates
        return ResponseEntity.ok(courseService.createdDatesBetweenService(from, to));
    }


    //                       TODO  Pagination
    @GetMapping("/course/pagination")
    public ResponseEntity<Page<CourseDTO>> coursePagination(@RequestParam("page") Integer page,
                                                            @RequestParam("size") Integer size) {
        // todo 9. Course Pagination.
        return ResponseEntity.ok(courseService.coursePaginationService(page, size));
    }

    @GetMapping("/createdDate/pagination")
    public ResponseEntity<Page<CourseDTO>> createdDate(@RequestParam("page") Integer page,
                                                       @RequestParam("size") Integer size) {
        // todo 10. Course Pagination. List should be sorted by createdDate.
        return ResponseEntity.ok(courseService.createdDateService(page - 1, size));
    }

    @GetMapping("/priceCreatedDate/pagination")
    public ResponseEntity<Page<CourseDTO>> priceCreatedDate(@RequestParam("page") Integer page,
                                                            @RequestParam("size") Integer size,
                                                            @RequestParam("price") Integer price) {
        // todo 11. Course Pagination by price. List should be sorted by createdDate.
        return ResponseEntity.ok(courseService.priceCreatedDateService(page - 1, size, price));
    }

    @GetMapping("/priceBetween/pagination")
    public ResponseEntity<Page<CourseDTO>> priceBetweenPagination(@RequestParam("page") Integer page,
                                                                  @RequestParam("size") Integer size,
                                                                  @RequestParam("from") Integer from,
                                                                  @RequestParam("to") Integer to) {
        // todo 12. Course Pagination by price between. List should be sorted by createdDate.
        return ResponseEntity.ok(courseService.priceBetweenPaginationService(page - 1, size, from, to));
    }
}
