package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.exception.InternalServerErrorException;
import com.imjcm.back_attend_system.exception.NotFoundException;
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
        return mapper.map(saveTeacher(mapper.map(teacherRequest, Teacher.class)), TeacherResponse.class);
    }

    public TeacherResponse getTeacherById(Long id) {
        return mapper.map(findTeacherById(id), TeacherResponse.class);
    }

    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll().stream().map(t -> mapper.map(t, TeacherResponse.class)).collect(Collectors.toList());
    }

    public TeacherResponse updateTeacherById(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = findTeacherById(id);
        teacher.setCodeTeacher(teacherRequest.getCodeTeacher());
        teacher.setName(teacherRequest.getName());
        teacher.setSurname(teacherRequest.getSurname());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setEditedAt(LocalDateTime.now());
        return mapper.map(saveTeacher(teacher), TeacherResponse.class);
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(findTeacherById(id).getId());
    }

    private Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Teacher with id = " + id));
    }

    private Teacher saveTeacher(Teacher teacher) {
        Teacher teacherSaved;
        try {
            teacherSaved = teacherRepository.save(teacher);
        }catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return teacherSaved;
    }

}
