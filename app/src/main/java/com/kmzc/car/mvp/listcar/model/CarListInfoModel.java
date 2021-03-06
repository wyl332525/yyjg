package com.kmzc.car.mvp.listcar.model;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.kmzc.car.R;
import com.kmzc.car.api.ApiManager;
import com.kmzc.car.mvp.listcar.contract.CarListInfoContract;
import com.kmzc.core.net.response.CarHttpResponse;
import com.kmzc.core.utils.XmlHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by yawei on 2017/7/3.
 */

public class CarListInfoModel implements CarListInfoContract.CarListInfoModel,Serializable{
    private Map titleMap ;
    private Map valuesMap ;
    private Map colorMap;
    private Map inputTypeMap;
    private HashMap dataMap;
    public List<CarCheckBean.DataBean> data;
    @Override
    public Flowable<CarCheckBean> getCarInfoList(Map map) {
        return ApiManager.getInstence().getCarService().getCarCheckList(map);
    }

    @Override
    public Flowable<CarCheckBean> getAddCarDetail(Map map) {
        return ApiManager.getInstence().getCarService().getAddCarData(map);
    }
    //修改检测方法列表数据
    @Override
    public Flowable<CarCheckBean> getReviseCarDetail(Map map) {
        return  ApiManager.getInstence().getCarService().getReviseCarData(map);
    }
      //查询修改车辆信息
    @Override
    public Flowable<CarCheckBean> getQueryReviseCarDetail(Map map) {
         return  ApiManager.getInstence().getCarService().getQueryReviseCarData(map);
    }
   //查询跨站解锁
    @Override
    public Flowable<CarCheckBean> getQueryLockCarDetail(Map map) {
        return ApiManager.getInstence().getCarService().getQueryLockCarData(map);
    }
   //查询修改检测方法
    @Override
    public Flowable<CarCheckBean> getQueryCheckCarDetail(Map map) {
        return ApiManager.getInstence().getCarService().getQueryCheckCarData(map);
    }

    @Override
    public Flowable<CarHttpResponse<Map>> getChangeMethodDetail(Map map) {
      return  ApiManager.getInstence().getCarService().getChangeMethodDetail(map);
    }



    @Override
    public List<CarCheckBean.DataBean> getCarListData() {
        return data;
    }

    @Override
    public void setCarListData(List<CarCheckBean.DataBean> data) {

    }


//    @Override
//    public Flowable<MyHttpResponse<Map>> getAddCarDetail(Map map) {
//        return ApiManager.getInstence().getCarService().getAddCarData(map);
//    }
//
//    @Override
//    public Flowable<CarTypeBean> getList() {
//        return ApiManager.getInstence().getCarService().getCarTypeList();
//    }
//
//
//    @Override
//    public List getCarListData() {
//        return CarListData;
//    }
//    @Override
//    public void setCarListData(List carListData) {
//        CarListData = carListData;
//    }

    @Override
    public String getTitle(Context context, String s) {
        if(titleMap==null)
            initConfig(context);
        return (String) titleMap.get(s);
    }

    @Override
    public String[] getValues(Context context, String s) {
        if(valuesMap==null)
            initConfig(context);
        return (String[]) valuesMap.get(s);
//        return new String[0];
    }

    @Override
    public String[] getKeys(Context context) {
        return context.getResources().getStringArray(R.array.data);
    }

    @Override
    public String getColor(Context context, String s) {
        if(colorMap==null)
            initConfig(context);
        return (String) colorMap.get(s);
    }

    @Override
    public String getInputType(Context context, String s) {
        if(inputTypeMap==null)
            initConfig(context);
        return (String) inputTypeMap.get(s);
    }

    @Override
    public String getTestData(Context context, String s) {
        Log.e("llll",s);
        if(dataMap==null)
            initConfig(context);
        return (String) dataMap.get(s);
    }

    private void initConfig(Context context){
        titleMap = new HashMap();
        valuesMap = new HashMap();
        colorMap = new HashMap();
        inputTypeMap = new HashMap();
        //测试数据
        dataMap = new HashMap();
        EditText et = new EditText(context);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        try {
            InputStream is = context.getResources().getAssets().open("car_config.xml");
            valuesMap = XmlHelper.getArrayValueByName("VALUE",is);
            InputStream ins = context.getResources().getAssets().open("car_config.xml");
            colorMap = XmlHelper.getListStyleByName("TEXT_COLOR",ins);
            InputStream input = context.getResources().getAssets().open("car_config.xml");
            titleMap = XmlHelper.getListStyleByName("KEY_NAME",input);
            InputStream type = context.getResources().getAssets().open("car_config.xml");
            inputTypeMap = XmlHelper.getListStyleByName("INPUT_MAX",type);
            //测试数据
            InputStream istest = context.getResources().getAssets().open("car_config.xml");
            dataMap   = XmlHelper.getListStyleByName("KEY_VALUE",istest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
