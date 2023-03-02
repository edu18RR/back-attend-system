package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.mapper.DataMapper;
import com.imjcm.back_attend_system.model.Teacher;
import com.imjcm.back_attend_system.repository.TeacherRepository;
import com.imjcm.back_attend_system.request.TeacherRequest;
import com.imjcm.back_attend_system.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        return DataMapper.buildTeacherDto(teacherRepository.save(DataMapper.buildTeacher(teacherRequest)));
    }

    public TeacherResponse getTeacherById(Long id) {
        return DataMapper.buildTeacherDto(teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist")));
    }

    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll().stream().map(DataMapper::buildTeacherDto).collect(Collectors.toList());
    }

    public TeacherResponse updateTeacherById(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist"));
        teacher.setCodeTeacher(teacherRequest.getCodeTeacher());
        teacher.setName(teacherRequest.getName());
        teacher.setSurname(teacherRequest.getSurname());
        teacher.setEmail(teacherRequest.getEmail());
        return DataMapper.buildTeacherDto(teacherRepository.save(teacher));
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist"));
        teacherRepository.deleteById(id);
    }
}
