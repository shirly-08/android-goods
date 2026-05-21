package com.example.mymachine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymachine.JavaBean.ProductBean;
import com.example.mymachine.MainActivity;
import com.example.mymachine.R;
import com.example.mymachine.dao.ProductsDao;

public class MessageActivity extends AppCompatActivity {
    Boolean root=true;
    public TextView value;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String pid=bundle.getString("pid");
        String condition=bundle.getString("condition");
        ProductBean product= ProductsDao.getProductById(pid);

        switch (condition){
            case "购买":
                ProductsDao.buyProduct(product);
                root=false;
                break;
            case "修改":
                //ProductsDao.updateProduct(product);
                break;
            case "删除":
                ProductsDao.delProduct(product);
                break;
            case "添加":
                //ProductsDao.addProduct(product);
        }
        String message=bundle.getString("message");
        findViewById(R.id.message_title);
        value=findViewById(R.id.message_value);
        value.setText(message);
        button=findViewById(R.id.message_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MessageActivity.this, ManageActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("root",root);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}