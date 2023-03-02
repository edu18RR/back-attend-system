package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.mapper.DataMapper;
import com.imjcm.back_attend_system.model.Teacher;
import com.imjcm.back_attend_system.repository.TeacherRepository;
import com.imjcm.back_attend_system.request.TeacherRequest;
import com.imjcm.back_attend_system.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = DataMapper.buildTeacher(teacherRequest);
        teacherRepository.save(teacher);
        log.info("Teacher saved {}", teacher);
        return DataMapper.buildTeacherDto(teacher);
    }
}
