package com.xiang.coolweather_mvvm.data.netWork;

import com.xiang.coolweather_mvvm.data.model.place.City;
import com.xiang.coolweather_mvvm.data.model.place.County;
import com.xiang.coolweather_mvvm.data.model.place.Province;
import com.xiang.coolweather_mvvm.data.model.weather.HeWeather;
import com.xiang.coolweather_mvvm.data.model.weather.Weather;
import com.xiang.coolweather_mvvm.data.netWork.api.PlaceService;
import com.xiang.coolweather_mvvm.data.netWork.api.WeatherService;

import java.util.List;

import retrofit2.Callback;

public class WeatherNetwork {
    private static WeatherNetwork sWeatherNetwork;
    private ServiceCreator serviceCreator = new ServiceCreator();
    private PlaceService placeService = serviceCreator.create(PlaceService.class);
    private WeatherService weatherService = serviceCreator.create(WeatherService.class);

    private WeatherNetwork() {
    }

    public static WeatherNetwork getInstance() {
        if (sWeatherNetwork == null) {
            synchronized (WeatherNetwork.class) {
                if (sWeatherNetwork == null) {
                    sWeatherNetwork = new WeatherNetwork();
                }
            }
        }
        return sWeatherNetwork;
    }

    public void fetchBingPic(Callback<String> callback) {
        weatherService.getBingPck().enqueue(callback);
    }

    public void fetchWeather(String weatherId, String key, Callback<HeWeather> callback) {
        weatherService.getWeather(weatherId, key).enqueue(callback);
    }

    public void fetchProvinceList(Callback<List<Province>> callback) {
        placeService.getProvinces().enqueue(callback);
    }

    public void fetchCityList(int provinceId, Callback<List<City>> callback) {
        placeService.getCities(provinceId).enqueue(callback);
    }

    public void fetchCountyList(int provinceId, int cityId, Callback<List<County>> callback) {
        placeService.getCounties(provinceId, cityId).enqueue(callback);
    }
}
