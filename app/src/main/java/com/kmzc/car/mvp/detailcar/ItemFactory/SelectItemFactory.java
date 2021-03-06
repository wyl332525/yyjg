package com.kmzc.car.mvp.detailcar.ItemFactory;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kmzc.car.R;
import com.kmzc.car.mvp.detailcar.model.SelectValueBean;
import com.kmzc.car.mvp.detailcar.presenter.DetailPresenter;
import com.kmzc.core.assemblyadapter.AssemblyRecyclerItem;
import com.kmzc.core.assemblyadapter.AssemblyRecyclerItemFactory;
import com.kmzc.core.widget.XRadioGroup;

/**
 * Created by yawei on 2017/8/25.
 */

public class SelectItemFactory extends AssemblyRecyclerItemFactory<SelectItemFactory.CarItem> {
    @Override
    public boolean isTarget(Object data) {
        return data instanceof SelectValueBean;
    }

    @Override
    public CarItem createAssemblyItem(ViewGroup parent) {
        return new CarItem(R.layout.item_select_layout, parent);
    }

    public class CarItem extends AssemblyRecyclerItem<SelectValueBean> {
        TextView name, divider,Tvflag;
        XRadioGroup group;
        Context context;
        int Index = 0;
        private XRadioGroup.OnCheckedChangeListener l = new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                String value = rb.getText().toString();
                getData().setValue(value);
                if (!TextUtils.isEmpty(value) && value.equals("通过")) {
                    value = "1";
                } else if (!TextUtils.isEmpty(value) && value.equals("不通过")) {
                    value = "2";
                } else if (!TextUtils.isEmpty(value) && value.equals("是")) {
                    value = "1";
                } else if (!TextUtils.isEmpty(value) && value.equals("否")) {
                    value = "2";
                }
                DetailPresenter.setParams(getData().getKey(), value);

            }
        };

        public CarItem(int itemLayoutId, ViewGroup parent) {
            super(itemLayoutId, parent);
        }

        @Override
        protected void onFindViews() {
            name = (TextView) findViewById(R.id.name);
            Tvflag = (TextView) findViewById(R.id.flag);
            divider = (TextView) findViewById(R.id.divider);
            group = (XRadioGroup) findViewById(R.id.group);
        }

        @Override
        protected void onConfigViews(Context context) {
            this.context = context;
            if (getData() != null) {
                inflaterRadioButton(getData());
            }
        }

        @Override
        protected void onSetData(int position, SelectValueBean bean) {
            name.setText(bean.getName());
            String value = bean.getValue();
            getData().setValue(value);
            inflaterRadioButton(bean);
            if (!TextUtils.isEmpty(value) && value.equals("通过")) {
                value = "1";
            } else if (!TextUtils.isEmpty(value) && value.equals("不通过")) {
                value = "2";
            } else if (!TextUtils.isEmpty(value) && value.equals("是")) {
                value = "1";
            } else if (!TextUtils.isEmpty(value) && value.equals("否")) {
                value = "2";
            }
            if("true".equals(bean.getFlag())){
                Tvflag.setVisibility(View.VISIBLE);
            }else {
                Tvflag.setVisibility(View.GONE);
            }
            DetailPresenter.setParams(getData().getKey(), value);
//            divider = (TextView) findViewById(R.id.divider);
//            group = (XRadioGroup) findViewById(R.id.group);
//            name.setText(bean.getName());

//            if(group.getTag()!=null){
//                LinearLayout layout = (LinearLayout) group.getTag();
//                group.removeView(layout);
//                group.addView(layout);
//            }else{
//                inflaterRadioButton(bean);
//            }
        }

        private void inflaterRadioButton(SelectValueBean bean) {
            group.setOnCheckedChangeListener(l);
            String[] selectes = bean.getSelects();
            LinearLayout parentLayout = new LinearLayout(context);
            parentLayout.setOrientation(LinearLayout.VERTICAL);
            parentLayout.setGravity(Gravity.CENTER_VERTICAL);
            XRadioGroup.LayoutParams params = new XRadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            parentLayout.setLayoutParams(params);
            LinearLayout lineLayout = new LinearLayout(context);
            lineLayout.setOrientation(LinearLayout.HORIZONTAL);
            XRadioGroup.LayoutParams lineParams = new XRadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            for (int i = 0; i < selectes.length; i++) {
                RadioButton rb = new RadioButton(context);
                rb.setButtonDrawable(R.drawable.radio_bg);
                rb.setText(selectes[i]);
                if (getData().getValue() != null && selectes[i].equals(getData().getValue())) {
                    group.setTag(rb);
                }
                LinearLayout.LayoutParams _Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                _Params.weight = 1;
//                LinearLayout.LayoutParams _Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                        DisplayUtil.dip2px(context,30));
                if (i % 3 == 0 && i > 0) { //控制RadioButton换行
                    parentLayout.addView(lineLayout, lineParams);
                    lineLayout = new LinearLayout(context);
                    lineLayout.setOrientation(LinearLayout.HORIZONTAL);
                    lineLayout.setGravity(Gravity.CENTER_VERTICAL);
                }
                lineLayout.addView(rb, _Params);
            }
            parentLayout.addView(lineLayout, lineParams);
            if (group.getChildCount() >= 1) {
                group.removeAllViews();
            }
            group.addView(parentLayout, params);
            if (group.getTag() != null) {
                ((RadioButton) group.getTag()).setChecked(true);
            }
        }
    }
}
