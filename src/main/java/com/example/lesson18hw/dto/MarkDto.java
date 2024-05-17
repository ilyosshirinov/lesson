package com.example.lesson18hw.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarkDto {
    private Integer id;
    private Integer studentId;
    private StudentDTO student;
    private Integer courseId;
    private CourseDTO course;
    private Integer mark;
    private LocalDate createdDate;

    // todo: pasdagi o'zgaruvchilarni vaqti kelganda qo'llang

    // todo: bu o'zgaruvchini StudentCourseMarkdagi 16...17 topshiriq uchun qo'shganman
    //  siz ham shu joyga kelgan vaqtingiz tushinib olasiz ungacha hayolingizni buzman
    private Double average;

    // todo: bu o'zgaruvchini StudentCourseMarkdagi 18 (21 ga ham moslab olamiz) topshiriq uchun qo'shganman
    //  siz ham shu joyga kelgan vaqtingiz tushinib olasiz ungacha hayolingizni buzman
      private Double count;

    // todo: bu o'zgaruvchini StudentCourseMarkdagi 19 topshiriq uchun qo'shganman
    //  siz ham shu joyga kelgan vaqtingiz tushinib olasiz ungacha hayolingizni buzman
      private Integer higher;
}
