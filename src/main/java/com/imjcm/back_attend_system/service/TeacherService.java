package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.model.Teacher;
import com.imjcm.back_attend_system.repository.TeacherRepository;
import com.imjcm.back_attend_system.request.TeacherRequest;
import com.imjcm.back_attend_system.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        return mapper.map(teacherRepository.save(mapper.map(teacherRequest, Teacher.class)), TeacherResponse.class);
    }

    public TeacherResponse getTeacherById(Long id) {
        return mapper.map(teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist")), TeacherResponse.class);
    }

    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll().stream().map(t -> mapper.map(t, TeacherResponse.class)).collect(Collectors.toList());
    }

    public TeacherResponse updateTeacherById(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist"));
        teacher.setCodeTeacher(teacherRequest.getCodeTeacher());
        teacher.setName(teacherRequest.getName());
        teacher.setSurname(teacherRequest.getSurname());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setEditedAt(LocalDateTime.now());
        return mapper.map(teacherRepository.save(teacher), TeacherResponse.class);
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist"));
        teacherRepository.deleteById(id);
    }
}
