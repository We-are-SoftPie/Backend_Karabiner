package com.softpie.karabiner.service;

import com.softpie.karabiner.dto.ReportDto;
import com.softpie.karabiner.entity.ReportEntity;

import java.util.List;
import java.util.Map;

public interface ReportService {
    //C
    Long input(ReportDto reportCarDto);

    Map<String, List<ReportDto>> output();

    default ReportEntity dtoToEntity(ReportDto dto) {
        return ReportEntity.builder()
                .type(dto.getType())
                .address(dto.getAddress())
                .title(dto.getTitle())
                .content(dto.getContent())
                .phoneNo(dto.getPhoneNo())
                .name(dto.getName())
                .email(dto.getEmail())
                .carNo(dto.getCarNo())
                .build();
    }
    default ReportDto entityToDTO(ReportEntity entity) {
        return ReportDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .address(entity.getAddress())
                .title(entity.getTitle())
                .content(entity.getContent())
                .phoneNo(entity.getPhoneNo())
                .name(entity.getName())
                .email(entity.getEmail())
                .carNo(entity.getCarNo())
                .build();
    }
}
