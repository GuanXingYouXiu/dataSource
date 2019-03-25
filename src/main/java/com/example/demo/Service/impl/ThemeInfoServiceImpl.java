package com.example.demo.Service.impl;

import com.example.demo.Service.ThemeInfoService;
import com.example.demo.bean.GambitBean;
import com.example.demo.primaryEntity.ThemeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class ThemeInfoServiceImpl implements ThemeInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${synchronize.SynchronUrl}")
    private String SynchronUrl;

    /**
     * 同步添加数据
     */
    @Transactional
    public void synchronizeThemeToXuanke(ThemeInfo themeInfo) {
            GambitBean gambitBean= GambitBean.builder()
                    .id(themeInfo.getId())
                    .content(themeInfo.getContent())
                    .isHot(themeInfo.getIsHot())
                    .publishTime(themeInfo.getPublishTime())
                    .status(themeInfo.getStatus())
                    .title(themeInfo.getTitle())
                    .totalReadNum(themeInfo.getBrowseNum())
                    .type(themeInfo.getType())
                    .userName(themeInfo.getUserName())
                    .build();
        ResponseEntity responseEntity = restTemplate.postForEntity(URI.create(SynchronUrl + "/gambit/addGambit"), gambitBean, Boolean.class);
        if (responseEntity.getStatusCode().value() != 200) {
            log.info("数据同步错误>>>>>" + themeInfo.toString() + ">>>>>" + System.currentTimeMillis());
        }
    }

    /**
     * 同步删除数据
     */
    @Transactional
    public void synchronizeDeleteThemeToXuanke(List<Long> Ids) {
        ResponseEntity responseEntity = restTemplate.postForEntity(URI.create(SynchronUrl + "/gambit/deleteGambits"), Ids, Object.class);
        if (responseEntity.getStatusCode().value() != 200) {
            log.info("数据同步错误>>>>>" + System.currentTimeMillis());
        }
    }

    /*同步修改*/
    public void synchronizeUpdateThemeToXuanke(List<Long> Ids) {
        String toUrl = SynchronUrl + "/gambit/updateGambits";
        String requestBody = Ids.toString();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity responseEntity = new RestTemplate().postForEntity(toUrl, httpEntity, String.class);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            log.info("数据同步错误>>>>>" + System.currentTimeMillis());
        }
    }
}
