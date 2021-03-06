package com.kmzc.car.mvp.ywrequest.ItemFactory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kmzc.car.R;
import com.kmzc.car.mvp.searchcar.gotoview.ItemFactory.SprinnerAdapter;
import com.kmzc.car.mvp.ywrequest.model.SprinnerValueBean;
import com.kmzc.car.mvp.ywrequest.presenter.DetailPresenter;
import com.kmzc.core.assemblyadapter.AssemblyRecyclerItem;
import com.kmzc.core.assemblyadapter.AssemblyRecyclerItemFactory;

/**
 * Created by yawei on 2017/8/25.
 */

public class SprinnerItemFactory extends AssemblyRecyclerItemFactory<SprinnerItemFactory.CarItem> {
    @Override
    public boolean isTarget(Object data) {
        return data instanceof SprinnerValueBean;
    }

    @Override
    public CarItem createAssemblyItem(ViewGroup parent) {
        return new CarItem(R.layout.item_list_layout,parent);
    }

    public class CarItem extends AssemblyRecyclerItem<SprinnerValueBean> {
        TextView name,tvFlag;
        Spinner spValue;
        //        XRadioGroup group;
        Context context;


        public CarItem(int itemLayoutId, ViewGroup parent) {
            super(itemLayoutId, parent);
        }

        @Override
        protected void onFindViews() {
            name = (TextView) findViewById(R.id.name);
            tvFlag = (TextView) findViewById(R.id.flag);
            spValue = (Spinner) findViewById(R.id.sp_value);
//            group = (XRadioGroup) findViewById(R.id.group);
        }

        @Override
        protected void onConfigViews(Context context) {
            this.context = context;
            if (getData() != null) {
                inflaterSprinner(getData());
            }
        }

        @Override
        protected void onSetData(int position, SprinnerValueBean bean) {
            name.setText(bean.getName());
            if("true".equals(bean.getFlag())){
                tvFlag.setVisibility(View.VISIBLE);
            }else {
                tvFlag.setVisibility(View.GONE);
            }
            inflaterSprinner(bean);


        }

        private void inflaterSprinner(SprinnerValueBean bean) {
//                        group.setOnCheckedChangeListener(l);
            if (bean != null) {
                String[] selectes = bean.getSelects();
                String[] codes = bean.getCode();
                String value = bean.getValue();
                if (selectes != null && selectes.length > 0) {
                    SprinnerAdapter adapter = new SprinnerAdapter(context, selectes);
//                    ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, selectes);
                    spValue.setAdapter(adapter);
                    for (int i = 0; i < selectes.length; i++) {
                        if (value!=null&&value.equals(selectes[i])) {
                            spValue.setSelection(i, true);
                        }
                        //添加联网获取数据
                       DetailPresenter.setParams(getData().getKey(), bean.getNameCode());
                    }
                    spValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            spValue.setSelection(i, true);
                            String value = selectes[i];
                            String code = codes[i];
                            DetailPresenter.setParams(getData().getKey(), code);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

        }
    }
}
