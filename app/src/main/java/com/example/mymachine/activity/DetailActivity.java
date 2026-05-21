package com.example.mymachine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymachine.JavaBean.ProductBean;
import com.example.mymachine.R;
import com.example.mymachine.dao.ProductsDao;


public class DetailActivity extends AppCompatActivity {

    private TextView detail_pid,detail_pname,detail_pnum,detail_price,detail_snum,detail_money;
    private ImageView detail_image;
    private Button detail_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String pid=bundle.getString("pid");
        ProductBean product= ProductsDao.getProductById(pid);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detail_pname=this.findViewById(R.id.detail_pname);
        detail_pname.setText(product.getPname());
        detail_image=this.findViewById(R.id.detail_image);
        detail_image.setImageResource(Integer.parseInt(product.getImage()));
        detail_pid=this.findViewById(R.id.detail_pid);
        detail_pid.setText(product.getPid());
        this.findViewById(R.id.detail_pid1);
        this.findViewById(R.id.detail_title);
        detail_pnum=this.findViewById(R.id.detail_pnum);
        detail_pnum.setText(product.getPnum());
        this.findViewById(R.id.detail_pnum1);
        detail_money=this.findViewById(R.id.main_money);
        this.findViewById(R.id.main_money1);
        double money=Double.parseDouble(product.getPrice())*Double.parseDouble(product.getSnum());
        //detail_money.setText(String.valueOf(money));
        detail_snum=this.findViewById(R.id.detail_snum);
        this.findViewById(R.id.detail_snum1);
        detail_snum.setText(product.getSnum());
        detail_price=this.findViewById(R.id.detail_price);
        detail_price.setText(product.getPrice());
        this.findViewById(R.id.detail_price1);
        detail_button=this.findViewById(R.id.detail_button);
        detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this,MessageActivity.class);
                String message="您已成功购买"+product.getPname()+"，售价："+product.getPrice()+"元";
                String condition="购买";
                bundle.putString("condition",condition);
                bundle.putString("message",message);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}