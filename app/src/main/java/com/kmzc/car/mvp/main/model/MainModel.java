package com.kmzc.car.mvp.main.model;

import android.content.Context;

import com.kmzc.car.R;
import com.kmzc.car.api.ApiManager;
import com.kmzc.car.mvp.main.contract.MainContract;
import com.kmzc.core.net.response.MyHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by lybin on 2017/6/27.
 */

public class MainModel implements MainContract.MainModel {
    @Override
    public String[] getTabs(Context context) {
        return context.getResources().getStringArray(R.array.tabs);
    }

    @Override
    public Flowable<MyHttpResponse<ConfigBean>> getConfig(Map map) {
        return ApiManager.getInstence().getCarService().getConfig( map);
    }


}
