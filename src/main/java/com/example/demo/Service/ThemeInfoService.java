package com.example.demo.Service;

import com.example.demo.primaryEntity.ThemeInfo;

import java.util.List;

public interface ThemeInfoService {

    /*同步数据*/
    void synchronizeThemeToXuanke(ThemeInfo themeInfo);

    /*同步删除*/
    void synchronizeDeleteThemeToXuanke(List<Long> Ids);

    /*同步修改*/
    void synchronizeUpdateThemeToXuanke(List<Long> Ids);

}
