package com.kmzc.car.mvp.lookcar.presenter;

import android.util.Log;

import com.kmzc.car.R;
import com.kmzc.car.app.ConsTants;
import com.kmzc.car.mvp.detailcar.presenter.DetailPresenter;
import com.kmzc.car.mvp.lookcar.contract.LookCarContract;
import com.kmzc.core.net.response.MyHttpResponse;
import com.kmzc.core.rxjava.CommonSubscriber;
import com.kmzc.core.rxjava.RxUtil;

import java.util.Map;


public class LookCarPresenter extends LookCarContract.MapPresenter{
    @Override
    public void onStart() {

    }
    //查验
    public void requestData(Map map){
        map.put("userId", ConsTants.UserId);
        map.put("regionCode",ConsTants.regionCode);
        addSubscribe( mModel.getCarDetail(map)
                .compose(RxUtil.<MyHttpResponse<Map>>rxSchedulerHelper())
                .compose(RxUtil.<Map>handleResult())
                .subscribeWith(
                        new CommonSubscriber<Map>(mView) {
                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                            @Override
                            public void onNext(Map map) {
                                map.put("type", DetailPresenter.CHECK_TYPE);
                                String[] keys = mView.getContext().getResources().getStringArray(R.array.carcheckout);
                                mView.jumpActivity(map,keys);
                            }}
                ));
    }
    //补录
    public void requestAddData(Map map){
//        map.put("userId",ConsTants.UserId);
//        map.put("regionCode",ConsTants.regionCode);
//        addSubscribe( mModel.getAddCarDetail(map)
//                .compose(RxUtil.<MyHttpResponse<Map>>rxSchedulerHelper())
//                .compose(RxUtil.<Map>handleResult())
//                .subscribeWith(
//                        new CommonSubscriber<Map>(mView) {
//                            @Override
//                            public void onError(Throwable e) {
//                                super.onError(e);
//                            }
//                            @Override
//                            public void onNext(Map map) {
                                String[] keys = mView.getContext().getResources().getStringArray(R.array.keys);
                                map.put("type", DetailPresenter.ADD_TYPE);
                                for(int i=0;i< keys.length;i++){
                                    Log.i("1231231",keys[i].toString());
                                }
                                for(Object key : map.keySet()){
                                    Log.i("1231231",key.toString()+"--"+map.get(key));
                                }
                                mView.jumpActivity(map,keys);
//                            }}
//                ));
    }



}
