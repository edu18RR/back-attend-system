package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.mapper.DataMapper;
import com.imjcm.back_attend_system.model.Course;
import com.imjcm.back_attend_system.model.Student;
import com.imjcm.back_attend_system.model.Teacher;
import com.imjcm.back_attend_system.repository.CourseRepository;
import com.imjcm.back_attend_system.repository.StudentRepository;
import com.imjcm.back_attend_system.repository.TeacherRepository;
import com.imjcm.back_attend_system.request.CourseRequest;
import com.imjcm.back_attend_system.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseResponse createCourse(CourseRequest courseRequest) {
        return DataMapper.buildCourseDto(courseRepository.save(DataMapper.buildCourse(courseRequest)));
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(DataMapper::buildCourseDto)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        return DataMapper.buildCourseDto(courseRepository.findById(id).orElseThrow(() -> new RuntimeException("error find id course")));
    }

    public CourseResponse updateCourseById(CourseRequest courseRequest, Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("error update id"));
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setCourseModality(courseRequest.getCourseModality());
        return DataMapper.buildCourseDto(courseRepository.save(course));
    }

    public CourseResponse assignTeacherToSubject(Long idCourse, Long idTeacher) {
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("error id course"));
        Teacher teacher = teacherRepository.findById(idTeacher).orElseThrow(() -> new RuntimeException("error id teacher "));
        course.setTeacher(teacher);
        return DataMapper.buildCourseDto(courseRepository.save(course));
    }


    public CourseResponse enrolledStudent(Long idCourse, Long idStudent) {
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("This course not exist."));
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new RuntimeException("This student not exist."));
        course.getStudents().add(student);
        return DataMapper.buildCourseDto(courseRepository.save(course));
    }


    public void deleteCourseById(Long id) {
        courseRepository.findById(id).orElseThrow(() -> new RuntimeException("This course not exist."));
        courseRepository.deleteById(id);
    }
}
