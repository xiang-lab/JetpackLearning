package com.xiang.coolweather_mvvm.data.netWork.api;

import com.xiang.coolweather_mvvm.data.model.place.City;
import com.xiang.coolweather_mvvm.data.model.place.County;
import com.xiang.coolweather_mvvm.data.model.place.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceService {

    @GET("api/china")
    Call<List<Province>> getProvinces();

    @GET("api/china/{provinceId}")
    Call<List<City>> getCities(@Path("provinceId") int provinceId);

    @GET("api/china/{provinceId}/{cityId}")
    Call<List<County>> getCounties(@Path("provinceId") int provinceId, @Path("cityId") int cityId);
}
