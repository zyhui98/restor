package com.cditie.restor.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_feed_detail_comment")
public class TFeedDetailComment {
    /**
     * id 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * feed_code
     */
    @Column(name = "feed_detail_id")
    private Long feedDetailId;

    /**
     * user_id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 设备
     */
    private String device;

    /**
     * 系统
     */
    private String os;

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
     * comments
     */
    private String comments;

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
     * @return feed_detail_id - feed_code
     */
    public Long getFeedDetailId() {
        return feedDetailId;
    }

    /**
     * 设置feed_code
     *
     * @param feedDetailId feed_code
     */
    public void setFeedDetailId(Long feedDetailId) {
        this.feedDetailId = feedDetailId;
    }

    /**
     * 获取user_id
     *
     * @return user_id - user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置user_id
     *
     * @param userId user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取设备
     *
     * @return device - 设备
     */
    public String getDevice() {
        return device;
    }

    /**
     * 设置设备
     *
     * @param device 设备
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * 获取系统
     *
     * @return os - 系统
     */
    public String getOs() {
        return os;
    }

    /**
     * 设置系统
     *
     * @param os 系统
     */
    public void setOs(String os) {
        this.os = os;
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
     * 获取comments
     *
     * @return comments - comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置comments
     *
     * @param comments comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}