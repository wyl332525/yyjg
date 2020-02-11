package com.kmzc.car.mvp.login.model;

import com.kmzc.car.api.ApiManager;
import com.kmzc.car.mvp.login.contract.LoginContract;
import com.kmzc.core.net.response.MyHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by yawei on 2017/7/3.
 */

public class LoginModel implements LoginContract.LoginModel {
    @Override
    public Flowable<LoginBean> getUserBean(Map map) {
        return ApiManager.getInstence().getCarService().login(map);
    }


    @Override
    public Flowable<MyHttpResponse<Map>> getList() {
        return ApiManager.getInstence().getCarService().getCarModeList();
    }
}
