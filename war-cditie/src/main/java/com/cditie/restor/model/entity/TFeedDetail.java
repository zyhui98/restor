package com.cditie.restor.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_feed_detail")
public class TFeedDetail {
    /**
     * id 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * feed_code
     */
    @Column(name = "feed_code")
    private String feedCode;

    /**
     * feed_id
     */
    @Column(name = "feed_id")
    private Long feedId;

    /**
     * title
     */
    private String title;

    /**
     * desc
     */
    private String desc;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * content
     */
    private String content;

    /**
     * 获取id 
     *
     * @return id - id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id 
     *
     * @param id id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取feed_code
     *
     * @return feed_code - feed_code
     */
    public String getFeedCode() {
        return feedCode;
    }

    /**
     * 设置feed_code
     *
     * @param feedCode feed_code
     */
    public void setFeedCode(String feedCode) {
        this.feedCode = feedCode;
    }

    /**
     * 获取feed_id
     *
     * @return feed_id - feed_id
     */
    public Long getFeedId() {
        return feedId;
    }

    /**
     * 设置feed_id
     *
     * @param feedId feed_id
     */
    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    /**
     * 获取title
     *
     * @return title - title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置title
     *
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取desc
     *
     * @return desc - desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置desc
     *
     * @param desc desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取更新人
     *
     * @return update_user - 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     *
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取content
     *
     * @return content - content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content
     *
     * @param content content
     */
    public void setContent(String content) {
        this.content = content;
    }
}