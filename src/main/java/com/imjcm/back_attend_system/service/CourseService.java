package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.enums.AttendanceStatus;
import com.imjcm.back_attend_system.exception.InternalServerErrorException;
import com.imjcm.back_attend_system.exception.NotFoundException;
import com.imjcm.back_attend_system.model.Attendance;
import com.imjcm.back_attend_system.model.Course;
import com.imjcm.back_attend_system.model.Student;
import com.imjcm.back_attend_system.model.Teacher;
import com.imjcm.back_attend_system.repository.AttendanceRepository;
import com.imjcm.back_attend_system.repository.CourseRepository;
import com.imjcm.back_attend_system.repository.StudentRepository;
import com.imjcm.back_attend_system.repository.TeacherRepository;
import com.imjcm.back_attend_system.request.CourseRequest;
import com.imjcm.back_attend_system.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final ModelMapper mapper;

    public CourseResponse createCourse(CourseRequest courseRequest) {
        return mapper.map(saveCourse(mapper.map(courseRequest, Course.class)), CourseResponse.class);
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream().map(c -> mapper.map(c, CourseResponse.class)).collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        return mapper.map(findCourseById(id), CourseResponse.class);
    }

    public CourseResponse updateCourseById(CourseRequest courseRequest, Long id) {
        Course course = findCourseById(id);
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setCourseModality(courseRequest.getCourseModality());
        course.setEditedAt(LocalDate.now());
        return mapper.map(saveCourse(course), CourseResponse.class);
    }

    public CourseResponse assignTeacherToSubject(Long idCourse, Long idTeacher) {
        Course course = findCourseById(idCourse);
        Teacher teacher = findTeacherById(idTeacher);
        course.setTeacher(teacher);
        return mapper.map(saveCourse(course), CourseResponse.class);
    }

    public CourseResponse enrolledStudent(Long idCourse, Long idStudent) {
        Course course = findCourseById(idCourse);
        Student student = findStudentById(idStudent);
        course.getStudents().add(student);
        return mapper.map(saveCourse(course), CourseResponse.class);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(findCourseById(id).getId());
    }

    public CourseResponse createAttendance(Long id) {
        Course course = findCourseById(id);
        course.getAttendances().add(attendanceRepository.save(buildDefaultAttendance(course)));
        return mapper.map(saveCourse(course), CourseResponse.class);
    }

    private Attendance buildDefaultAttendance(Course course) {
        return Attendance.builder().id(null).status(AttendanceStatus.ACTIVE).createAt(LocalDate.now()).course(course).students(null).build();
    }

    private Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Course with id = " + id));
    }

    private Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Teacher with id = " + id));
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Student with id = " + id));
    }

    private Course saveCourse(Course course) {
        Course courseSaved;
        try {
            courseSaved = courseRepository.save(course);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return courseSaved;
    }


}
