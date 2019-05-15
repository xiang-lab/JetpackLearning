package com.xiang.coolweather_mvvm.data.db;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.xiang.coolweather_mvvm.data.model.weather.Weather;

public class WeatherDao {

    // 图片未处理
    public void cacheBingPic(String bingPic) {
        if (TextUtils.isEmpty(bingPic))
            return;

    }
    public String getCachedBingPic() {
        return null;
    }

    public void cacheWeatherInfo(Weather weather) {

    }

    public Weather getCacheWeatherInfo() {
        return null;
    }
}
