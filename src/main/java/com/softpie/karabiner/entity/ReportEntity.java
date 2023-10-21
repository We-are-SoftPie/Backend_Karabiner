package com.softpie.karabiner.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @Builder
    public ReportEntity(String type, String address, String title, String content, String phoneNo, String name, String email, String carNo) {
        this.type = type;
        this.address = address;
        this.title = title;
        this.content = content;
        this.phoneNo = phoneNo;
        this.name = name;
        this.email = email;
        this.carNo = carNo;
    }
}
