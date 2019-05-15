package com.xiang.coolweather_mvvm.data.db;

import android.widget.ListView;

import com.xiang.coolweather_mvvm.data.model.place.City;
import com.xiang.coolweather_mvvm.data.model.place.County;
import com.xiang.coolweather_mvvm.data.model.place.Province;

import org.litepal.LitePal;

import java.util.List;

public class PlaceDao {
    public List<Province> getProvinceList() {
        return LitePal.findAll(Province.class);
    }

    public List<City> getCityList(int provinceId) {
        return LitePal.where("provinceId = ?", String.valueOf(provinceId)).find(City.class);
    }

    public List<County> getCountyList(int cityId) {
        return LitePal.where("cityId = ?", String.valueOf(cityId)).find(County.class);
    }

    public void saveProvinceList(List<Province> provinceList) {
        if (provinceList != null && !provinceList.isEmpty()) {
            LitePal.saveAll(provinceList);
        }
    }

    public void saveCityList(List<City> cityList) {
        if (cityList != null && !cityList.isEmpty()) {
            LitePal.saveAll(cityList);
        }
    }

    public void saveCountyList(List<County> countyList) {
        if (countyList != null && !countyList.isEmpty()) {
            LitePal.saveAll(countyList);
        }
    }
}