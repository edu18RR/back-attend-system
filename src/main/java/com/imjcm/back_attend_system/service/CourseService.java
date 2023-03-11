package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.enums.AttendanceStatus;
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
        return mapper.map(
                courseRepository.save(mapper.map(courseRequest, Course.class)),
                CourseResponse.class);
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(c -> mapper.map(c, CourseResponse.class))
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        return mapper.map(courseRepository.findById(id).orElseThrow(() -> new RuntimeException("error find id course")), CourseResponse.class);
    }

    public CourseResponse updateCourseById(CourseRequest courseRequest, Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("error update id"));
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setCourseModality(courseRequest.getCourseModality());
        course.setEditedAt(LocalDate.now());
        return mapper.map(courseRepository.save(course), CourseResponse.class);
    }

    public CourseResponse assignTeacherToSubject(Long idCourse, Long idTeacher) {
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("error id course"));
        Teacher teacher = teacherRepository.findById(idTeacher).orElseThrow(() -> new RuntimeException("error id teacher "));
        course.setTeacher(teacher);
        return mapper.map(courseRepository.save(course), CourseResponse.class);
    }

    public CourseResponse enrolledStudent(Long idCourse, Long idStudent) {
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("This course not exist."));
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new RuntimeException("This student not exist."));
        course.getStudents().add(student);
        return mapper.map(courseRepository.save(course), CourseResponse.class);
    }

    public void deleteCourseById(Long id) {
        courseRepository.findById(id).orElseThrow(() -> new RuntimeException("This course not exist."));
        courseRepository.deleteById(id);
    }

    public CourseResponse createAttendance(Long idCourse) {
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("This course not exist."));
        course.getAttendances().add(attendanceRepository.save(buildDefaultAttendance(course)));
        return mapper.map(courseRepository.save(course), CourseResponse.class);
    }

    private Attendance buildDefaultAttendance(Course course) {
        return Attendance.builder()
                .id(null)
                .status(AttendanceStatus.ACTIVE)
                .createAt(LocalDate.now())
                .course(course)
                .students(null)
                .build();
    }

    public CourseResponse recordStudentToAttendance(Long idCourse, Long idAttendance, Long idStudent) {
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("This course does not exist."));
        Attendance attendance = attendanceRepository.findById(idAttendance).orElseThrow(() -> new RuntimeException("this attendance does not exist."));
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new RuntimeException("This student does not exist."));
        attendance.getStudents().add(student);
        return mapper.map(courseRepository.save(course), CourseResponse.class);
    }
}
