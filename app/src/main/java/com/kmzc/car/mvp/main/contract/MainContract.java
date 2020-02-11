package com.kmzc.car.mvp.main.contract;

import android.app.Activity;
import android.content.Context;

import com.kmzc.car.mvp.main.model.ConfigBean;
import com.kmzc.core.base.CoreBaseModel;
import com.kmzc.core.base.CoreBasePresenter;
import com.kmzc.core.base.CoreBaseView;
import com.kmzc.core.net.response.MyHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by lybin on 2017/6/27.
 */

public interface MainContract {
    interface MainModel extends CoreBaseModel{
        String[] getTabs(Context context);
        Flowable<MyHttpResponse<ConfigBean>> getConfig(Map map);
    }
    interface MainView extends CoreBaseView{
        void showTabList(String[] mTabs);
        Activity getParentActivity();
    }
    abstract  class MainPresenter extends CoreBasePresenter<MainModel,MainView>{
        public abstract void getTabList();
    }
}
