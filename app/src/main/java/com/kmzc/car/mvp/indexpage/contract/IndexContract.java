package com.kmzc.car.mvp.indexpage.contract;

import com.kmzc.car.mvp.listcar.model.CarCheckBean;
import com.kmzc.core.base.CoreBaseModel;
import com.kmzc.core.base.CoreBasePresenter;
import com.kmzc.core.base.CoreBaseView;
import com.kmzc.core.net.response.MyHttpResponse;
import com.kmzc.core.net.response.RequestHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;


public interface IndexContract {
    interface IndexModel extends CoreBaseModel {
        Flowable<MyHttpResponse<Map>> getIndexCheckCount(Map map);
    }

    interface IndexFragment extends CoreBaseView {
        void jumpActivity(Map data, String[] keys);
    }


    abstract class IndexPresenter extends CoreBasePresenter<IndexModel, IndexFragment> {

    }
}
