package com.softpie.karabiner.service;

import com.softpie.karabiner.dto.ReportDto;
import com.softpie.karabiner.entity.ReportEntity;
import com.softpie.karabiner.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository repository;

    @Override
    public Long input(ReportDto reportDto) {
        ReportEntity reportEntity = dtoToEntity(reportDto);
        repository.save(reportEntity);
        return reportEntity.getId();
    }

    @Override
    public Map<String, List<ReportDto>> output() {
        List<ReportEntity> reportEntities = repository.findAll();
        List<ReportDto> reportDtos = reportEntities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());

        //역순으로 데이터 반환
        Collections.reverse(reportDtos);

        Map<String, List<ReportDto>> resultMap = new HashMap<>();
        resultMap.put("data", reportDtos);

        return resultMap;
    }
}
