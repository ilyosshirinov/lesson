package com.example.lesson18hw.controller;

import com.example.lesson18hw.dto.MarkDto;
import com.example.lesson18hw.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MarkDto> studentAverageMark(@PathVariable("student_id") Integer student_id) {
        // todo 16. Studentni o'rtacha olgan baxolari.
        return ResponseEntity.ok(markService.studentAverageMarkService(student_id));
    }

    @GetMapping("/studentCourseAverage/mark/{student_id}/{course_id}")
    public ResponseEntity<MarkDto> studentCourseAverageMark(@PathVariable("student_id") Integer student_id,
                                                            @PathVariable("course_id") Integer course_id) {
        // todo 17. Studentni berilgan curse dan olgan o'rtacha baxolari.
        return ResponseEntity.ok(markService.studentCourseAverageMarkService(student_id, course_id));
    }

    @GetMapping("/studentHigher/mark/{student_id}/{student_mark}")
    public ResponseEntity<?> studentHigherMark(@PathVariable("student_id") Integer student_id,
                                               @PathVariable("student_mark") Integer student_mark) {
        // todo 18. Studentni berilgan baxodan katta bo'lgan baxolari soni.  //add va shu baholarni kimlar olgan
        return ResponseEntity.ok(markService.studentHigherMarkService(student_id, student_mark));
    }

    @GetMapping("/courseHigher/mark/{course_id}")
    public ResponseEntity<?> courseHigherMark(@PathVariable("course_id") Integer course_id) {
        // todo 19. Berilgan Cursdan eng baland baxo. //add  va shu bahoni kim olgan
        return ResponseEntity.ok(markService.courseHigherMarkService(course_id));
    }

    @GetMapping("/courseAverage/mark/{course_id}")
    public ResponseEntity<?> courseAverageMark(@PathVariable("course_id") Integer course_id) {
        // todo 20. Berilgan Cursdan o'lingan o'rtacha baxo.
        return ResponseEntity.ok(markService.courseAverageMarkService(course_id));
    }

    @GetMapping("/courseGradesCount/mark/{course_id}")
    public ResponseEntity<?> courseGradesCountMark(@PathVariable("course_id") Integer course_id) {
        // todo 21. Berilgan Course dan olingna baxolar soni.
        return ResponseEntity.ok(markService.courseGradesCountService(course_id));
    }

    @GetMapping("/pagination/mark")
    public ResponseEntity<Page<MarkDto>> paginationMark(@RequestParam("page") Integer page,
                                                        @RequestParam("size") Integer size) {
        // todo 22. StudentCourseMark pagination.
        return ResponseEntity.ok(markService.paginationMarkService(page, size));
    }

    @GetMapping("/byStudentId/pagination/mark")
    public ResponseEntity<Page<MarkDto>> byStudentIdPaginationMark(@RequestParam("student_id") Integer student_id,
                                                                   @RequestParam("page") Integer page,
                                                                   @RequestParam("size") Integer size) {
        // todo 23. StudentCourseMark pagination by given studentId. List should be sorted by createdDate.
        return ResponseEntity.ok(markService.byStudentIdPaginationMarkService(student_id, page - 1, size));
    }

    @GetMapping("/byCourseId/pagination/mark")
    public ResponseEntity<Page<MarkDto>> byCourseIdPaginationMark(@RequestParam("course_id") Integer course_id,
                                                                   @RequestParam("page") Integer page,
                                                                   @RequestParam("size") Integer size) {
        // todo 23. StudentCourseMark pagination by given studentId. List should be sorted by createdDate.
        return ResponseEntity.ok(markService.byCourseIdPaginationMarkService(course_id, page - 1, size));
    }

    @GetMapping("/byStudentIdJoinFetchCourse/mark")
    public ResponseEntity<List<MarkDto>> byStudentIdJoinFetchCourseMark(@RequestParam("student_id") Integer student_id){
        // todo 25. Berilgan studentId-si orqali u olgan baxolari ro'yhatini return qiling.
        //        Service quyidagi ma'lumotlar return qilinishi kerak.
        //      studentCourse{id,mark,createdDate,
        //                    course{id,name,title}
        //        }
        return ResponseEntity.ok(markService.byStudentIdJoinFetchCourseMarkService(student_id));
    }

    @GetMapping("/byCourseIdJoinFetchCourse/mark")
    public ResponseEntity<List<MarkDto>> byCourseIdJoinFetchCourseMark(@RequestParam("course_id") Integer course_id){
        // todo 26. Berilgan Cours-ning id-si orqali undan olingna baxolarni return qiling.
        //        Service quyidagi ma'lumotlar return qilinishi kerak.
        //        studentCourse{id,mark,createdDate,
        //                            student{id,name,surname}
        //                }
        return ResponseEntity.ok(markService.byCourseIdJoinFetchCourseMarkService(course_id));
    }

    @GetMapping("/allStudentCourse/mark")
    public ResponseEntity<List<MarkDto>> allStudentCourseMark(@RequestParam("student_id") Integer student_id){
        // todo 25. 27. Xar bir olingan baxo, kurs ma'lumot, student ma'lumotini retunr qiladigna method yozing.
        //	    Service quyidagi ma'lumotlar return qilinishi kerak.
        //	    studentCourse{
        //	        id,mark,createdDate,
        //	        student{id,name,surname},
        //	        course{id,title}
        //	        }
        return ResponseEntity.ok(markService.allStudentCourseMarkService(student_id));
    }
}
