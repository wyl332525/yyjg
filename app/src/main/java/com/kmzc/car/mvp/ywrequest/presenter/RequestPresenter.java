package com.kmzc.car.mvp.ywrequest.presenter;

import com.kmzc.car.app.ConsTants;
import com.kmzc.car.mvp.ywrequest.contract.RequestContract;
import com.kmzc.core.net.response.RequestHttpResponse;
import com.kmzc.core.rxjava.CommonSubscriber;
import com.kmzc.core.rxjava.RxUtil;

import java.util.HashMap;
import java.util.Map;


public class RequestPresenter extends RequestContract.RequestPresenter{
    @Override
    public void onStart() {

    }

    public void getCarInfo(Map dataMap){
        HashMap map=new HashMap<>();
        map.put("carCardNumber",dataMap.get("cphm"));
        map.put("carCardColor",dataMap.get("hpzl"));
        map.put("VIN",dataMap.get("vin"));
        addSubscribe(mModel.requestCarRequestDetail(map)
                .compose(RxUtil.<RequestHttpResponse<Map>>rxSchedulerHelper())
                .compose(RxUtil.<Map>handleRequestResult())
                .subscribeWith(
                        new CommonSubscriber<Map>(mView) {
                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                            @Override
                            public void onNext(Map  map) {
                                String color = (String) map.get("carCardColor");
                                String colorName = ConsTants.getCarColorMap().get(color);
                                map.put("carCardColor",colorName);
                                mView.jumpActivity(map);
//                                initRecycleBean(map);
                            }}
                ));
    }

}
