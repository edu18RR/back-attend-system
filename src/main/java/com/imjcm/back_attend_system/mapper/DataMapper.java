package com.imjcm.back_attend_system.mapper;

import com.imjcm.back_attend_system.model.Course;
import com.imjcm.back_attend_system.model.Student;
import com.imjcm.back_attend_system.model.Teacher;
import com.imjcm.back_attend_system.request.CourseRequest;
import com.imjcm.back_attend_system.request.StudentRequest;
import com.imjcm.back_attend_system.request.TeacherRequest;
import com.imjcm.back_attend_system.response.CourseResponse;
import com.imjcm.back_attend_system.response.StudentResponse;
import com.imjcm.back_attend_system.response.TeacherResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DataMapper {
    public StudentResponse buildStudentDto(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .email(student.getEmail())
                .studentCode(student.getStudentCode())
                .createdAt(student.getCreatedAt())
                .build();
    }

    public Student buildStudent(StudentRequest studentRequest) {
        LocalDateTime now = LocalDateTime.now();
        return Student.builder()
                .id(null)
                .name(studentRequest.getName())
                .surname(studentRequest.getSurname())
                .email(studentRequest.getEmail())
                .studentCode(studentRequest.getStudentCode())
                .createdAt(now)
                .editedAt(now)
                .build();
    }

    public TeacherResponse buildTeacherDto(Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .email(teacher.getEmail())
                .codeTeacher(teacher.getCodeTeacher())
                .createdAt(teacher.getCreatedAt())
                .build();
    }


    public Teacher buildTeacher(TeacherRequest teacherRequest) {
        LocalDateTime now = LocalDateTime.now();
        return Teacher.builder()
                .id(null)
                .name(teacherRequest.getName())
                .surname(teacherRequest.getSurname())
                .email(teacherRequest.getEmail())
                .codeTeacher(teacherRequest.getCodeTeacher())
                .createdAt(now)
                .editedAt(now)
                .build();
    }


    public Course buildCourse(CourseRequest courseRequest) {
        LocalDate now = LocalDate.now();
        return Course.builder()
                .id(null)
                .title(courseRequest.getTitle())
                .description(courseRequest.getDescription())
                .createdAt(now)
                .editedAt(now)
                .courseModality(courseRequest.getCourseModality())
                .teacher(null)
                .students(null)
                .build();
    }

    public CourseResponse buildCourseDto(Course course) {
        List<StudentResponse> list = null;
        TeacherResponse teacher = null;
        if (course.getStudents() != null) {
            list = course.getStudents().stream().map(DataMapper::buildStudentDto).collect(Collectors.toList());
        }
        if (course.getTeacher() != null) {
            teacher = buildTeacherDto(course.getTeacher());
        }

        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .createdAt(course.getCreatedAt())
                .courseModality(course.getCourseModality())
                .teacher(teacher)
                .students(list)
                .build();
    }
}
