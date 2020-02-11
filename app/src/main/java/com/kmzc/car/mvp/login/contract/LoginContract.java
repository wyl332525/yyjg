package com.kmzc.car.mvp.login.contract;

import com.kmzc.car.mvp.login.model.LoginBean;
import com.kmzc.core.base.CoreBaseModel;
import com.kmzc.core.base.CoreBasePresenter;
import com.kmzc.core.base.CoreBaseView;
import com.kmzc.core.net.response.MyHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by lybin on 2017/6/27.
 */

public interface LoginContract {
    interface LoginModel extends CoreBaseModel {
        Flowable<LoginBean> getUserBean(Map map);

        Flowable<MyHttpResponse<Map>> getList();
    }

    interface LoginActivity extends CoreBaseView {
        void initLoginStation(Map map, boolean isRemember, boolean isAuto);

        void startLogin();

        void stopLogin();

        void startActivity();

        void jumpActivity();
    }

    abstract class LoginPresenter extends CoreBasePresenter<LoginModel, LoginActivity> {

    }
}
