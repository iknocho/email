package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="appication")
public class applicationEntity {
    @Id//프라이머리키
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String companyName;
    private String brandName;
    private String companyNumber;
    private String promotionService;
    private String promotionStart;//추후 타입변경 고려
    private String promotionEnd;//추후 타입변경 고려 int or date    json은 date x
    private String detailPage;
    private String companySize;
    private String promotionCategory;
    private String serviceCategory;
    private String serviceUsemethod;
    private String serviceDelivery; //상품 서비스 전달 방식
    private String Distribution; //유통형태
    private String serviceOperation;
    private String csOperation;
    private String notice;
    private String contactName;
    private String contactPhone;
    private String email;

}
