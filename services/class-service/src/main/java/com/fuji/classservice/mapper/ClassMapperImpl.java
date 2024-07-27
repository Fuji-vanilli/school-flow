package com.fuji.classservice.mapper;


import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.entities.Class;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassMapperImpl implements ClassMapper{
    @Override
    public Class mapToClass(ClassRequest request) {
        return Class.builder()
                .level(request.level())
                .section(request.section())
                .ecolage(request.ecolage())
                .maximumCapacity(request.maximumCapacity())
                .build();
    }

    @Override
    public ClassResponse mapToClassResponse(Class _class) {
        return new ClassResponse(
                _class.getId(),
                _class.getLevel(),
                _class.getSection(),
                _class.getMaximumCapacity(),
                _class.getNumberOfStudents(),
                _class.getEcolage(),
                _class.getStudents(),
                _class.getProfessors(),
                _class.getCourses(),
                _class.getCreatedDate(),
                _class.getLastModifiedDate()
        );
    }
}
