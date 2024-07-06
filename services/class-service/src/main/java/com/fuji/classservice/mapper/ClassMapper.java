package com.fuji.classservice.mapper;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.entities.Class;

public interface ClassMapper {
    Class mapToClass(ClassRequest request);
    ClassResponse mapToClassResponse(Class _class);
}
