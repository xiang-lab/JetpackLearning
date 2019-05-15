package com.xiang.coolweather_mvvm.data;

import com.xiang.coolweather_mvvm.data.db.PlaceDao;
import com.xiang.coolweather_mvvm.data.model.place.Province;
import com.xiang.coolweather_mvvm.data.model.weather.Weather;
import com.xiang.coolweather_mvvm.data.netWork.WeatherNetwork;
import com.xiang.coolweather_mvvm.util.CoolWeatherExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceRepository {

    private static PlaceRepository sPlaceRepository;
    private PlaceDao mPlaceDao;
    private WeatherNetwork mWeatherNetwork;

    public PlaceRepository(PlaceDao dao, WeatherNetwork network) {
        this.mPlaceDao = dao;
        this.mWeatherNetwork = network;
    }

    public static PlaceRepository getInstance(PlaceDao placeDao, WeatherNetwork weatherNetwork) {
        if (sPlaceRepository == null) {
            synchronized (PlaceRepository.class) {
                if (sPlaceRepository == null) {
                    sPlaceRepository = new PlaceRepository(placeDao, weatherNetwork);
                }
            }
        }
        return sPlaceRepository;
    }

    public LiveData<Resource<List<Province>>> getProvinceList() {
        MutableLiveData<Resource<List<Province>>> liveData = new MutableLiveData<>();
        liveData.setValue(null);
        CoolWeatherExecutors.diskIO.execute(() -> {
            List<Province> provinceList = mPlaceDao.getProvinceList();
            if (provinceList.isEmpty()) {
                //mWeatherNetwork
                mWeatherNetwork.fetchProvinceList(new Callback<List<Province>>() {
                    @Override
                    public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                        // TODO: 09/03/2019 save in disk
                        List<Province> provinces = response.body();
                        mPlaceDao.saveProvinceList(provinces);
                        liveData.postValue(new Resource().success(provinces));
                    }

                    @Override
                    public void onFailure(Call<List<Province>> call, Throwable t) {
                        t.printStackTrace();
                        liveData.postValue(new Resource().error(null, "load failed"));
                    }
                });
            } else {
                liveData.postValue(new Resource<List<Province>>().success(provinceList));
            }
        });
        return liveData;
    }





}

