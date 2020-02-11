package com.kmzc.car.mvp.ywrequest.model;

import com.kmzc.car.api.ApiManager;
import com.kmzc.car.mvp.ywrequest.contract.RequestContract;
import com.kmzc.core.net.response.RequestHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by yawei on 2017/7/3.
 */

public class RequestModel implements RequestContract.RequestModel{
    @Override
    public Flowable<RequestHttpResponse<Map>> requestCarRequestDetail(Map map) {
        return  ApiManager.getInstence().getCarService().getCarRequestDetail(map);
    }
}
