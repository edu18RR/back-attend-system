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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseResponse createCourse(CourseRequest courseRequest, Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("BAD ID"));
        Course toSave = DataMapper.buildCourse(courseRequest);
        toSave.setTeacher(teacher);
        Course course = courseRepository.save(toSave);
        return DataMapper.buildCourseDto(course);
    }

    public CourseResponse enrolledStudent(Long idCourse, Long idStudent) {
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("This course not exist."));
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new RuntimeException("This student not exist."));
        course.getStudents().add(student);
        return DataMapper.buildCourseDto(courseRepository.save(course));
    }
}
