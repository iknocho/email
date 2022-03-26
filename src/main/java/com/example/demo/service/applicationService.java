package com.example.demo.service;

import com.example.demo.model.applicationEntity;
import com.example.demo.persistence.applicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Service
public class applicationService {
    @Autowired
    private applicationRepository repository;
    @Autowired
    private JavaMailSender javaMailSender;

    public void upload(final applicationEntity entity){
        repository.save(entity);
    }

    public void sendCustomer(final applicationEntity entity) throws MessagingException, IOException {
        //감사합니다 형식상의 인사
        String to=entity.getEmail();


        String from = "dlrsh741@gmail.com";//관리자 이미엘
        String subject = "SALELARY 환영합니다";

        StringBuilder body = new StringBuilder();
        body.append("<img src=\"/Users/ikno/salelaryemail/src/main/java/com/example/demo/image/salelary.png\">");
        body.append("<html> <body><h1>Hello </h1>");
        body.append("dddd"+subject+"");
        //body.append("<div>테스트 입니다2. <img src=\"cid:flower.jpg\"> </div> </body></html>");


        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true,"UTF-8");

        mimeMessageHelper.setFrom(from,"dlrsh");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body.toString(), true);

        javaMailSender.send(message);

    }

    public void sendManager(final applicationEntity entity, List<MultipartFile> files) throws MessagingException, IOException {
        String to = "dlrsh741@gmail.com";//관리자 이메일

        String from = "dlrsh741@gmail.com";//관리자 이미엘

        String subject=entity.getCompanyName()+" 지원서";

        StringBuilder body = new StringBuilder();
        body.append("회사명 : "+entity.getCompanyName()+"\n");
        body.append("브랜드명 : "+entity.getBrandName()+"\n");
        body.append("사업자등록번호 : "+entity.getCompanyNumber()+"\n");
        body.append("프로모션 대표 서빗 : "+entity.getPromotionService()+"\n");
        body.append("프로모션 게시 희망 기간 : "+entity.getPromotionStart()+" ~ "+entity.getPromotionEnd());
        body.append("서비스 상세페이지 유무 : "+entity.getDetailPage()+"\n");
        body.append("프로모션 제공 희망 기업 : "+entity.getCompanySize()+"\n");
        body.append("프로모션 유형 : "+entity.getPromotionCategory()+"\n");
        body.append("서비스 유형 : "+entity.getServiceCategory()+"\n");
        body.append("서비스 사용 방식 : "+entity.getServiceUsemethod()+"\n");
        body.append("상품 서비스 전달방식 : "+entity.getServiceDelivery()+"\n");
        body.append("유통 형태 : "+entity.getDistribution()+"\n");
        body.append("자체 물류 서비스 운영 : "+entity.getServiceOperation()+"\n");
        body.append("cs 운영: "+entity.getCsOperation()+"\n");
        body.append("기타 전달 사항 : "+entity.getNotice()+"\n");
        body.append("담당자명: "+entity.getContactName()+"\n");
        body.append("담당자 연락처: "+entity.getCompanyNumber()+"\n");
        body.append("이메일: "+entity.getEmail()+"\n");


        //bdy.append("<div>테스트 입니다2. <img src=\"cid:flower.jpg\"> </div> </body></html>");



        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true,"UTF-8");

        mimeMessageHelper.setFrom(from,"dlrsh");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body.toString(), true);


        for (MultipartFile file:files){
            String originalfileName=file.getOriginalFilename();
            File dest = new File("/Users/ikno/testspring/"+file.getOriginalFilename());
            file.transferTo(dest);
//            File convFile=new File(file.getOriginalFilename());
//            convFile.createNewFile();
//            FileOutputStream fos=new FileOutputStream(convFile);
//            fos.write(file.getBytes());
//            fos.close();
            FileSystemResource fileSystemResource = new FileSystemResource(dest);
            mimeMessageHelper.addAttachment(file.getOriginalFilename(), fileSystemResource);
        }

        //FileSystemResource file = new FileSystemResource(new File("C:/Users/HOME/Desktop/flower.jpg"));
        //mimeMessageHelper.addInline("flower.jpg", file);

        javaMailSender.send(message);


        //mimeMessageHelper.addAttachment("또르르.txt", fileSystemResource);

        //FileSystemResource file = new FileSystemResource(new File("C:/Users/HOME/Desktop/flower.jpg"));

    }
}
