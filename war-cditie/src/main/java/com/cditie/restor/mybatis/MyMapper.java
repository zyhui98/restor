package com.cditie.restor.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author zhuyunhui
 * @since 4/28/2018
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
