package com.ujm.sinsahelper.crawling.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long itemId;

    // private String itemName;
    private String itemUrl;
    
    // KCH : MainCate와 SubCate embedded로 바꾸는 거 고려하기
    private String mainCategory;
    private String subCategory;

    private String priceToday;
    private String priceYesterday;
    private String photo;
    private String review;

    // KCH : django에서 DB에 넣는 부분
    private Long deliveryScore;
    private Long sizeScore;
    private Long qualityScore;

    // preference * score 한 값들을 다 더한 것
    private Long totalScore;

}