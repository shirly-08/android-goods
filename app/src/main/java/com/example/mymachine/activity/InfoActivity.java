package com.example.mymachine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymachine.JavaBean.ProductBean;
import com.example.mymachine.R;
import com.example.mymachine.dao.ProductsDao;

public class InfoActivity extends AppCompatActivity {

    private EditText info_pid,info_pname,info_pnum,info_price;
    private ImageView info_image;
    private Button info_del,info_update;
    private RadioButton info_coco,info_peisi;
    ProductBean product=null;
    String image= String.valueOf(R.drawable.ic_launcher_foreground);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        info_pname=this.findViewById(R.id.info_pname);
        info_image=this.findViewById(R.id.info_image);
        info_pid=this.findViewById(R.id.info_pid);
        info_pnum=this.findViewById(R.id.info_pnum);
        this.findViewById(R.id.info_pid1);
        this.findViewById(R.id.info_title);
        this.findViewById(R.id.info_pnum1);
        info_price=this.findViewById(R.id.info_price);
        this.findViewById(R.id.info_price1);
        info_del=this.findViewById(R.id.info_del);
        info_update=this.findViewById(R.id.info_update);
        this.findViewById(R.id.info_radiodroup);
        info_coco=this.findViewById(R.id.info_coco);
        info_peisi=this.findViewById(R.id.info_peisi);

        String pid=bundle.getString("pid");
        if(pid==null){
            product=new ProductBean("null","null",image,"null","null","null");
        }
        else {
            product= ProductsDao.getProductById(pid);
            info_pname.setText(product.getPname());
            info_image.setImageResource(Integer.parseInt(product.getImage()));
            info_pid.setText(product.getPid());
            info_pnum.setText(product.getPnum());
            info_price.setText(product.getPrice());
        }

        info_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoActivity.this,MessageActivity.class);
                String message="您已成功删除"+product.getPname()+"商品。";
                String condition="删除";
                bundle.putString("condition",condition);
                bundle.putString("message",message);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        info_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String condition="添加";
                if(pid==null){
                    condition="添加";
                    String pid=info_pid.getText().toString();
                    bundle.putString("pid",pid);
                }
                else{
                    condition="修改";
                }
                product.setPid(info_pid.getText().toString());
                product.setPname(info_pname.getText().toString());
                if(info_coco.isChecked()){
                    image= String.valueOf(R.drawable.coco);
                }
                else if(info_peisi.isChecked()){
                    image= String.valueOf(R.drawable.pepsi);
                }
                product.setImage(image);
                product.setPnum(info_pnum.getText().toString());
                product.setSnum("0");
                product.setPrice(info_price.getText().toString());
                if (condition=="添加"){
                    ProductsDao.addProduct(product);
                }
                else{
                    ProductsDao.updateProduct(product);
                }
                Intent intent=new Intent(InfoActivity.this,MessageActivity.class);
                String message="您已成功"+condition+product.getPname()+"商品。";
                bundle.putString("condition",condition);
                bundle.putString("message",message);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}