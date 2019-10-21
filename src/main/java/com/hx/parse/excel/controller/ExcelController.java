package com.hx.parse.excel.controller;

import com.hx.parse.excel.model.City;
import com.hx.parse.excel.service.CityService;
import com.hx.parse.excel.utils.Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jxlgcmh
 * @date 2019-10-20 23:38
 */
@Controller
public class ExcelController {

    @Autowired
    private CityService cityService;

    @ResponseBody
    @RequestMapping("/start")
    public String excelToMySql() {
        List<City> cities = Utils.excelToMysql("D:\\Chrome\\weather_areaid_data.xlsx");
        System.out.println("开始批量导入城市数据.....");
        cityService.batchInset(cities);
        System.out.println("导入城市数据完成！");
        return "success";
    }
}
