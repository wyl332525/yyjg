package com.kmzc.car.mvp.indexpage.model;

import com.kmzc.car.api.ApiManager;
import com.kmzc.car.mvp.listcar.model.CarCheckBean;
import com.kmzc.car.mvp.indexpage.contract.IndexContract;
import com.kmzc.core.net.response.MyHttpResponse;
import com.kmzc.core.net.response.RequestHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by yawei on 2017/7/3.
 */

public class IndexModel implements IndexContract.IndexModel{
    @Override
    public Flowable<MyHttpResponse<Map>> getIndexCheckCount(Map map) {
       return ApiManager.getInstence().getCarService().getCarChecksList(map);
    }
}
