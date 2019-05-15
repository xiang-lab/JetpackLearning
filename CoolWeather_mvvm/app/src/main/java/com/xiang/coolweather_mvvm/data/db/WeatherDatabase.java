package com.xiang.coolweather_mvvm.data.db;

import com.xiang.coolweather_mvvm.data.model.weather.Weather;

public class WeatherDatabase {
    private static WeatherDao sWeatherDao;
    private static PlaceDao sPlaceDao;

    private WeatherDatabase() {}

    public static PlaceDao getsPlaceDao() {
        if (sPlaceDao == null)
            sPlaceDao = new PlaceDao();
        return sPlaceDao;
    }

    public static WeatherDao getsWeatherDao() {
        if (sWeatherDao == null)
            sWeatherDao = new WeatherDao();
        return sWeatherDao;
    }
}
