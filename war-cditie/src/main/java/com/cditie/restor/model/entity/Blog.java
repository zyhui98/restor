package com.cditie.restor.model.entity;

import java.util.Date;
import javax.persistence.*;

public class Blog {
    /**
     * id 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * title
     */
    private String title;

    /**
     * content
     */
    private String content;

    /**
     * image
     */
    private String image;

    /**
     * 提交时间
     */
    @Column(name = "submit_time")
    private Date submitTime;

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

    /**
     * 获取image
     *
     * @return image - image
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置image
     *
     * @param image image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取提交时间
     *
     * @return submit_time - 提交时间
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置提交时间
     *
     * @param submitTime 提交时间
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
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
}