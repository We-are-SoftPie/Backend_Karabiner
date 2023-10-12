package com.softpie.karabiner.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class ReportDto {
    private Long id;
    private String type;
    private String address;
    private String title;
    private String content;
    private String phoneNo;
    private String name;
    private String email;
    private String carNo;
}
