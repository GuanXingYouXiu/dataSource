package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GambitBean {
    @Id
    private Long id;

    //话题标题
    private String title;

    //话题内容
    @Column(columnDefinition = "text")
    private String content;

    //阅读量(browseNum)
    private Integer totalReadNum;

    //发布日期(issueTime)
    private LocalDateTime publishTime;

    //发布人(issuer)
    private String userName;

    //类型(type)（0--普通文本、1--投票单选、F2--投票多选）
    private Integer type;

    //显示状态
    private Integer status;

    //是否热门
    private Integer isHot;
}
