package com.example.demo.dto;

import com.example.demo.model.applicationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class applicationDTO {
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
    private List<MultipartFile> files;

    public applicationDTO(final applicationEntity entity){
        this.companyName=entity.getCompanyName();
        this.brandName=entity.getBrandName();
        this.companyNumber=entity.getCompanyNumber();
        this.promotionService=entity.getPromotionService();
        this.promotionStart=entity.getPromotionStart();
        this.promotionEnd=entity.getPromotionEnd();
        this.detailPage=entity.getDetailPage();
        this.companySize=entity.getCompanySize();
        this.promotionCategory=entity.getPromotionCategory();
        this.serviceCategory=entity.getServiceCategory();

        this.serviceUsemethod=entity.getServiceUsemethod();
        this.serviceDelivery=entity.getServiceDelivery();
        this.Distribution=entity.getDistribution();
        this.serviceOperation=entity.getServiceOperation();
        this.csOperation=entity.getCsOperation();
        this.notice=entity.getNotice();
        this.contactName=entity.getContactName();
        this.contactPhone=entity.getContactPhone();
        this.email=entity.getEmail();
    }

    public static applicationEntity toEntity(final applicationDTO dto){
        return applicationEntity.builder()
                .companyName(dto.getCompanyName())
                .brandName(dto.getBrandName())
                .companyNumber(dto.getCompanyNumber())
                .promotionService(dto.getPromotionService())
                .promotionStart(dto.getPromotionStart())
                .promotionEnd(dto.getPromotionEnd())
                .detailPage(dto.getDetailPage())
                .companySize(dto.getCompanySize())
                .promotionCategory(dto.getPromotionCategory())
                .serviceCategory(dto.getServiceCategory())
                .serviceUsemethod(dto.getServiceUsemethod())
                .serviceDelivery(dto.getServiceDelivery())
                .Distribution(dto.getDistribution())
                .serviceOperation(dto.getServiceOperation())
                .csOperation(dto.getCsOperation())
                .notice(dto.getNotice())
                .contactName(dto.getContactName())
                .contactPhone(dto.getContactPhone())
                .email(dto.getEmail())
                .build();

    }
}
