package com.example.lesson18hw.controller;

import com.example.lesson18hw.dto.MarkDto;
import com.example.lesson18hw.service.MarkService;
import jakarta.persistence.MapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MarkController {
    @Autowired
    private MarkService markService;


    @PostMapping("/create/mark")
    public ResponseEntity<MarkDto> createMark(@RequestBody MarkDto markDto) {
        // todo 1. Create StudentCourseMark
        return ResponseEntity.ok(markService.createMarkService(markDto));
    }

    @PostMapping("/updateById/mark")
    public ResponseEntity<MarkDto> updateById(@RequestParam("id") Integer id,
                                              @RequestBody MarkDto markDto) {
        //todo 2. Update StudentCourseMark
        return ResponseEntity.ok(markService.updateByIdService(id, markDto));
    }

    @GetMapping("/byId/mark")
    public ResponseEntity<MarkDto> byIdMark(@RequestParam("id") Integer id) {
        // todo 3. Get StudentCourseMark by id. response (id,studentId,courseId,mark,createdDate,)
        return ResponseEntity.ok(markService.byIdMarkService(id));
    }

    @GetMapping("/byIdDetail/mark")
    public ResponseEntity<MarkDto> byIdDetail(@RequestParam("id") Integer id) {
        // todo 4. Get StudentCourseMark by id with detail. response (id,student(id,name,surname),Course(id,name),mark,createdDate,)
        return ResponseEntity.ok(markService.byIdDetailService(id));
    }

    @DeleteMapping("/deleteById/mark")
    public ResponseEntity<Boolean> deleteById(@RequestParam("id") Integer id) {
        // todo 5. Delete StudentCourseMark by id.
        return ResponseEntity.ok(markService.deleteByIdService(id));
    }

    @GetMapping("/all/mark")
    public ResponseEntity<List<MarkDto>> allMark() {
        // todo 6. Get List of StudentCourseMark. Return All
        return ResponseEntity.ok(markService.allMarkService());
    }

    @GetMapping("/byStudentIdDayMarkStudent/mark")
    public ResponseEntity<?> byIdDayMarkStudent(@RequestParam("student_id") Integer student_id,
                                                @RequestParam("day") LocalDate day) {
        // todo 7. Studentni berilgan kundagi olgan baxosi
        return ResponseEntity.ok(markService.byIdDayMarkStudentService(student_id, day));
    }

    @GetMapping("/byStudentIdTwoMarkStudent/mark")
    public ResponseEntity<?> byIdTwoMarkStudent(@RequestParam("student_id") Integer student_id,
                                                @RequestParam("from") LocalDate day,
                                                @RequestParam("to") LocalDate to) {
        // todo 8. Studentni berilgan 2kun oralig'idagi olgan baxosi.
        return ResponseEntity.ok(markService.byIdTwoMarkStudentService(student_id, day, to));
    }

    @GetMapping("/allStudent/mark")
    public ResponseEntity<?> allStudentMark(@RequestParam("student_id") Integer student_id) {
        // todo 9. Studentni olgan barcha baxolari vaqt boyicha kamayish tartibida sord qiling.
        return ResponseEntity.ok(markService.allStudentMarkService(student_id));
    }

    @GetMapping("allCourse/mark")
    public ResponseEntity<List<MarkDto>> allCourseMark(@RequestParam("student_id") Integer student_id,
                                                       @RequestParam("course_id") Integer course_id) {
        // todo 10. Studentni berilgan curse dan olgan baxolari vaqt boyicha kamayish tartibida sord qiling.
        return ResponseEntity.ok(markService.allCourseMarkService(student_id, course_id));
    }

    @GetMapping("/studentLast/mark")
    public ResponseEntity<MarkDto> studentLastMark(@RequestParam("student_id") Integer student_id) {
        // todo 11. Studentni eng oxirda olgan baxosi, va curse nomi.
        return ResponseEntity.ok(markService.studentLastMarkService(student_id));
    }

    @GetMapping("/studentBigThree/mark")
    public ResponseEntity<List<MarkDto>> studentBigThree(@RequestParam("student_id") Integer student_id) {
        // todo 12. Studentni olgan eng katta 3ta baxosi.
        return ResponseEntity.ok(markService.studentBigThreeService(student_id));
    }

    @GetMapping("/studentFirst/mark")
    public ResponseEntity<List<MarkDto>> studentFirstMark(@RequestParam("student_id") Integer student_id) {
        // todo 13. Studentni eng birinchi olgan baxosi.
        return ResponseEntity.ok(markService.studentFirstMarkService(student_id));
    }

    @GetMapping("/studentCourse/mark/{student_id}/{course_id}")
    public ResponseEntity<List<MarkDto>> studentCourseMark(@PathVariable("student_id") Integer student_id,
                                                           @PathVariable("course_id") Integer course_id) {
        // todo 14. Studenti eng berilgan course dan olgan birinchi baxosi.
        return ResponseEntity.ok(markService.studentCourseMarkService(student_id, course_id));
    }

    @GetMapping("/studentCourseHighestGrade/mark/{student_id}/{course_id}")
    public ResponseEntity<List<MarkDto>> studentCourseHighestGradeMark(@PathVariable("student_id") Integer student_id,
                                                                       @PathVariable("course_id") Integer course_id) {
        // todo 15. Studentni berilgan cuorse dan olgan eng baland baxosi.
        return ResponseEntity.ok(markService.studentCourseHighestGradeMarkService(student_id, course_id));
    }

    @GetMapping("/studentAverage/mark/{student_id}")
    public ResponseEntity<MarkDto> studentAverageMark(@PathVariable("student_id") Integer student_id){
        // todo 16. Studentni o'rtacha olgan baxolari.
        return ResponseEntity.ok(markService.studentAverageMarkService(student_id));
    }

    @GetMapping("/studentCourseAverage/mark/{student_id}/{course_id}")
    public ResponseEntity<MarkDto> studentCourseAverageMark(@PathVariable("student_id") Integer student_id,
                                                            @PathVariable("course_id") Integer course_id) {
        // todo 17. Studentni berilgan curse dan olgan o'rtacha baxolari.
        return ResponseEntity.ok(markService.studentCourseAverageMarkService(student_id, course_id));
    }



}