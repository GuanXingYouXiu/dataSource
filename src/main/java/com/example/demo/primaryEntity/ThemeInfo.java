package com.example.demo.primaryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName: ThemeInfo
 * @Description: 话题（实体类）
 * @author: LZA
 * @date: 2018-08-07 13:35:00
 */
@Data
@Builder
@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class ThemeInfo {
    //话题id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //话题标题
    private String title;

    //话题内容
    @Column(columnDefinition = "text")
    private String content;

    //阅读量(browseNum)
    @ColumnDefault("0")
    private Integer browseNum;

    //发布日期(issueTime)
    private LocalDateTime publishTime;

    //发布人(issuer)
    private String userName;

    //类型(type)（0--普通文本、1--投票单选、2--投票多选）
    private Integer type;

    //是否热门(1--为热门)
    private Integer isHot;

    //上下架（1--上架，3--下架）
    private Integer status;

//    //投票内容list
//    @OneToMany(mappedBy = "themeInfo",fetch = FetchType.LAZY,cascade ={CascadeType.PERSIST,CascadeType.MERGE})
//    private List<Vote> votes;


}
