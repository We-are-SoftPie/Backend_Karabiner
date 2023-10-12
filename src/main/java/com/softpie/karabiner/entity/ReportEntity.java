package com.softpie.karabiner.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column
    private String address;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String phoneNo;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String carNo;
}
