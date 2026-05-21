package com.example.mymachine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymachine.JavaBean.ProductBean;
import com.example.mymachine.MainActivity;
import com.example.mymachine.R;
import com.example.mymachine.adapter.CommodityAdapter;
import com.example.mymachine.adapter.ProductAdapter;
import com.example.mymachine.dao.ProductsDao;
import com.example.mymachine.util.DBHelper;

import java.util.List;

public class ManageActivity extends AppCompatActivity {
    //继承自activity
    public TextView title,money;
    public ListView plist;
    List<ProductBean> products;
    public ImageButton add,back;
    DBHelper dbHelper;
    double moneyvalue=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        dbHelper=new DBHelper(ManageActivity.this);
        dbHelper.db=dbHelper.getWritableDatabase();//给db赋值
        products = ProductsDao.getAllProducts();//获取全部产品数据集
        title=this.findViewById(R.id.main_title);
        money=this.findViewById(R.id.main_money);//for循环取出商品的销售额，求和
        for(int i=0;i<products.size();i++){
            ProductBean product=products.get(i);
            moneyvalue += Double.parseDouble(product.getPrice()) * Double.parseDouble(product.getSnum());}
        money.setText(String.valueOf(moneyvalue));
        this.findViewById(R.id.main_money1);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        add=this.findViewById(R.id.main_add);
        back=this.findViewById(R.id.main_back);
        plist=this.findViewById(R.id.plist);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        if(bundle.getBoolean("root")){
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ManageActivity.this, InfoActivity.class);
                    Bundle bundle=new Bundle();
                    String pid=null;
                    bundle.putString("pid",pid);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            ProductAdapter adapter=new ProductAdapter(ManageActivity.this,products);//将查询到的数据集放入adapter(自定义adapter继承自ArrayAdapter<>)
            plist.setAdapter(adapter);//绑定适配器，plist获取adapter的值

        }
        else{
            add.setVisibility(View.INVISIBLE);
            CommodityAdapter adapter=new CommodityAdapter(ManageActivity.this,products);//将查询到的数据集放入adapter(自定义adapter继承自ArrayAdapter<>)
            plist.setAdapter(adapter);//绑定适配器，plist获取adapter的值
        }




    }
}