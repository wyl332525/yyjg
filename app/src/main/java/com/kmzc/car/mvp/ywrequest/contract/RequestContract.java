package com.kmzc.car.mvp.ywrequest.contract;

import com.kmzc.core.base.CoreBaseModel;
import com.kmzc.core.base.CoreBasePresenter;
import com.kmzc.core.base.CoreBaseView;
import com.kmzc.core.net.response.RequestHttpResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by lybin on 2017/6/27.
 */

public interface RequestContract {
    interface RequestModel extends CoreBaseModel{
        Flowable<RequestHttpResponse<Map>> requestCarRequestDetail(Map map);
    }
    interface RequestFragment extends CoreBaseView{
         void jumpActivity(Map data);
    }


    abstract  class RequestPresenter extends CoreBasePresenter<RequestModel,RequestFragment> {

    }
}
