package com.cditie.restor.crawler.model;

import lombok.Data;

/**
 * @author zhuyunhui
 * @since 4/28/2018
 */
@Data
public class FeedDetail {
    private Integer id;
    private Integer feedId;
    private String feedCode;
    private String content;
    private String title;
    private String desc;

}
