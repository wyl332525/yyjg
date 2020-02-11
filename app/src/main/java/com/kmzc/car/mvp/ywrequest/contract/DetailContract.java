package com.kmzc.car.mvp.ywrequest.contract;

import android.content.Context;

import com.kmzc.car.mvp.ywrequest.activity.CarRequestDetailActivity;
import com.kmzc.core.base.CoreBaseModel;
import com.kmzc.core.base.CoreBasePresenter;
import com.kmzc.core.base.CoreBaseView;
import com.kmzc.core.net.response.RequestHttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * Created by lybin on 2017/6/27.
 */

public interface DetailContract {
    interface DetailModel extends CoreBaseModel{
        Flowable<RequestHttpResponse> commitUnlockData(RequestBody body);
        Flowable<RequestHttpResponse> commitMethod(RequestBody body);

        String getTitle(Context context, String s);
        String[] getValues(Context context, String s);
        String[] getKeys(Context context);
        String getColor(Context context, String s);
        String getInputType(Context context, String s);
        String[] getReciverValues(Context context, String s);


        //测试数据
        String getTestData(Context context, String s);
    }
    interface CarDetailActivity extends CoreBaseView{
        void initRecycle(List list);
        void updateComplete(String msg,String  isExist);
    }
    interface RequestFragment extends CoreBaseView{
        void jumpActivity(Map data,String[] keys);
    }
    abstract  class DetailPresenter extends CoreBasePresenter<DetailModel,CarRequestDetailActivity> {

    }
}
