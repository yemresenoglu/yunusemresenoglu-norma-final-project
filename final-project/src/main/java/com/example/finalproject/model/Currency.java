package com.example.finalproject.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Currency extends BaseModel{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 3)
    private String code;

    private String sign;


}
