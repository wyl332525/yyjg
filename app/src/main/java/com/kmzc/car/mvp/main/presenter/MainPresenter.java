package com.kmzc.car.mvp.main.presenter;

import android.Manifest;

import com.kmzc.car.app.ConsTants;
import com.kmzc.car.mvp.main.contract.MainContract;
import com.kmzc.car.mvp.main.model.ConfigBean;
import com.kmzc.core.net.response.MyHttpResponse;
import com.kmzc.core.rxjava.CommonSubscriber;
import com.kmzc.core.rxjava.RxUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lybin on 2017/6/27.
 */

public class MainPresenter extends MainContract.MainPresenter {
    RxPermissions rxPermissions;
    @Override
    public void getTabList() {
        mView.showTabList(mModel.getTabs(mView.getContext()));
    }

    @Override
    public void onStart() {
//        initConfig();
        getTabList();
    }

    public void initConfig(){
        Map map = new HashMap();
        map.put("userId",ConsTants.UserId);
        map.put("regionCode",ConsTants.regionCode);
        addSubscribe( mModel.getConfig(map)
                .compose(RxUtil.<MyHttpResponse<ConfigBean>>rxSchedulerHelper())
                .compose(RxUtil.<ConfigBean>handleResult())
                .subscribeWith(
                        new CommonSubscriber<ConfigBean>(mView) {
                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                            @Override
                            public void onNext(ConfigBean bean) {
                                ConsTants.CarTypeList = bean.getHplb();
                            }}
                ));
    }
    public void requestPermission(){
        if(rxPermissions==null)
            rxPermissions = new RxPermissions(mView.getParentActivity());
        rxPermissions
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                .subscribe(granted -> {
                    if (granted) {

                    } else {

                    }
                });
    }
}
