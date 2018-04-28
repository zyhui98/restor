package com.cditie.restor.model;

import lombok.Data;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Data
public class BarrageVo {

    private String img;
    private String info;
    private String href;
    private Boolean close;
    private Integer speed;
    private Integer bottom;
    private String color;

}
/**
 *    img:'static/heisenberg.png', //图片
 info:'弹幕文字信息', //文字
 href:'http://www.yaseng.org', //链接
 close:true, //显示关闭按钮
 speed:6, //延迟,单位秒,默认6
 bottom:70, //距离底部高度,单位px,默认随机
 color:'#fff', //颜色,默认白色
 old_ie_color:'#000000', //ie低版兼容色,不能与网页背景相同,默认黑色

 **/