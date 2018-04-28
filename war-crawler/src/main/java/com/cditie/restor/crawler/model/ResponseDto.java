package com.cditie.restor.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private Boolean success;

    private String msg;

    private Object data;

    public ResponseDto(Boolean success){
        this.success = success;
    }




}
