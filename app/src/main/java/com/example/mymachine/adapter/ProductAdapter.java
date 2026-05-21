package com.example.mymachine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mymachine.JavaBean.ProductBean;
import com.example.mymachine.R;
import com.example.mymachine.activity.DetailActivity;
import com.example.mymachine.activity.InfoActivity;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<ProductBean> {
    //ArrayAdapter<>:将List<>与ListView视图绑定
    List<ProductBean> list;
    public ProductAdapter(Context context, List<ProductBean> list) {
        super(context, R.layout.plist,list);//重写构造函数，确定绑定的对象。将context以plist（模板）的格式存储进list里
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {//显示adapter
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.plist,parent,false);
        }
        //注册页面视图
        TextView v_pid=convertView.findViewById(R.id.list_pid);
        convertView.findViewById(R.id.list_pid1);
        TextView v_pname=convertView.findViewById(R.id.list_pname);
        TextView v_pnum=convertView.findViewById(R.id.list_pnum);
        convertView.findViewById(R.id.list_pnum1);
        TextView v_price=convertView.findViewById(R.id.list_price);
        convertView.findViewById(R.id.list_price1);
        ImageView v_image=convertView.findViewById(R.id.list_image);
        Button v_button=convertView.findViewById(R.id.list_button);
        TextView v_snum=convertView.findViewById(R.id.list_snum);
        convertView.findViewById(R.id.list_snum1);
        TextView v_money=convertView.findViewById(R.id.list_money);
        convertView.findViewById(R.id.list_money1);
        //赋值,从product类中取值存入显示的view
        ProductBean product=list.get(position);
        double money;
        money =Double.parseDouble(product.getSnum())*Double.parseDouble(product.getPrice());
        v_pname.setText(product.getPname());
        v_pid.setText(product.getPid());
        v_price.setText(product.getPrice());
        v_pnum.setText(product.getPnum());
        v_snum.setText(product.getSnum());
        v_money.setText(String.valueOf(money));
        v_image.setImageResource(Integer.parseInt(product.getImage()));
        v_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), InfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("pid",product.getPid());
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }

}
