package com.softpie.karabiner.controller;

import com.softpie.karabiner.dto.ReportDto;
import com.softpie.karabiner.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@RequestMapping("/v1/kr/public/as/safety-report/car-traffic-violation")
@RequestMapping("/test")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;


    @PostMapping("/input")
    public Map<String, String> input(@RequestBody ReportDto dto){
        Long id = service.input(dto);
        String reportId = "SPP-0000-";

        for (int i=1; i<7; i++){
            if(id.toString().length()==i) {
                for (int j=i; j<8-i; j++){
                    reportId = reportId+"0";
                }
            }
        }
        reportId=reportId+id.toString();
        Map<String, String> map = new HashMap<>();
        map.put("status" , "200");
        map.put("reportId", reportId);

        return map;
    }
    @GetMapping("/output")
    public Map<String , List<ReportDto>> output(){
        return service.output();
    }


}
