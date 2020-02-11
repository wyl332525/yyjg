package com.kmzc.car.mvp.searchcar.gotoview.contract;

import android.content.Context;

import com.kmzc.core.base.CoreBaseModel;
import com.kmzc.core.base.CoreBasePresenter;
import com.kmzc.core.base.CoreBaseView;
import com.kmzc.core.net.response.MyHttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * Created by lybin on 2017/6/27.
 */

public interface CarListReciverContract {
    interface CarListReciverModel extends CoreBaseModel{

        Flowable<MyHttpResponse> commitReciverDetail(RequestBody body);


        String getTitle(Context context, String s);
        String[] getValues(Context context, String s);
        String[] getKeys(Context context);
        String getColor(Context context, String s);
        String getInputType(Context context, String s);
        String[] getReciverValues(Context context, String s);


        //测试数据
        String getTestData(Context context, String s);
    }
    interface CarListReciverActivity extends CoreBaseView{
        void initRecycle(List list);
        void updateComplete(String msg);
        void jumpActivity(Map map);
    }
    abstract  class CarListReciverPresenter extends CoreBasePresenter<CarListReciverModel,CarListReciverActivity> {

    }
}
