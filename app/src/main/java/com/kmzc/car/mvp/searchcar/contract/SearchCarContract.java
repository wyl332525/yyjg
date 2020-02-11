package com.kmzc.car.mvp.searchcar.contract;

import android.content.Context;

import com.kmzc.car.mvp.searchcar.gotoview.model.CarBean;
import com.kmzc.car.mvp.searchcar.model.CarSelectBean;
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

public interface SearchCarContract {
    interface SearchCarModel extends CoreBaseModel {
        Flowable<CarBean> getCarList(Map map);
        Flowable<CarSelectBean> getCarSelect();
        Flowable<MyHttpResponse<Map>> getAddCarDetail(Map map);

        Flowable<MyHttpResponse> commitDetail(RequestBody body);

        //             Flowable<MyHttpResponse> commitDetail(Map map);
        List getCarListData();

        void setCarListData(List carListData);

        String getTitle(Context context, String s);

        String[] getValues(Context context, String s);

        String[] getKeys(Context context);

        String getColor(Context context, String s);

        String getInputType(Context context, String s);

        String getTestData(Context context, String s);

        String[] getCodeValues(Context context, String s);
    }

    interface SearchCarFragment extends CoreBaseView {
        void startCarListActivity(CarBean carBean,CarSelectBean carSelectBean);

        void initRecycle(List list);

        void updateComplete(String msg);
    }

    abstract class SearchCarPresenter extends CoreBasePresenter<SearchCarModel, SearchCarFragment> {

    }
}
