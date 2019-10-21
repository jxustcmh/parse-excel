package com.hx.parse.excel.service;

import com.hx.parse.excel.model.City;

import java.util.List;

/**
@author jxlgcmh
@date 2019-10-20 23:36
*/
public interface CityService{


    int deleteByPrimaryKey(String areaid);

    int insert(City record);

    int batchInset(List<City> list);

    int insertSelective(City record);

    City selectByPrimaryKey(String areaid);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

}
