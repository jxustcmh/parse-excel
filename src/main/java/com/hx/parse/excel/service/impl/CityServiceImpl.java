package com.hx.parse.excel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hx.parse.excel.mapper.CityMapper;
import com.hx.parse.excel.model.City;
import com.hx.parse.excel.service.CityService;

import java.util.List;

/**
@author jxlgcmh
@date 2019-10-20 23:36
*/
@Service
public class CityServiceImpl implements CityService{

    @Resource
    private CityMapper cityMapper;

    @Override
    public int deleteByPrimaryKey(String areaid) {
        return cityMapper.deleteByPrimaryKey(areaid);
    }

    @Override
    public int insert(City record) {
        return cityMapper.insert(record);
    }

    @Override
    public int batchInset(List<City> list) {
        return cityMapper.batchInsert(list);
    }

    @Override
    public int insertSelective(City record) {
        return cityMapper.insertSelective(record);
    }

    @Override
    public City selectByPrimaryKey(String areaid) {
        return cityMapper.selectByPrimaryKey(areaid);
    }

    @Override
    public int updateByPrimaryKeySelective(City record) {
        return cityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(City record) {
        return cityMapper.updateByPrimaryKey(record);
    }

}
