package com.example.mymachine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymachine.activity.ManageActivity;

public class MainActivity extends Activity {
    private TextView main_title;
    private Button main_custom,main_admin;
    private Gallery gallery;
    Boolean root=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_title=this.findViewById(R.id.main_title);
        gallery=this.findViewById(R.id.main_gallery);
        gallery.setAdapter(new ImageAdapter(this));
        main_admin=this.findViewById(R.id.main_admin);
        main_custom=this.findViewById(R.id.main_custom);
        main_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, ManageActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("root",root);//true
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        main_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,ManageActivity.class);
                Bundle bundle=new Bundle();
                root=false;
                bundle.putBoolean("root",root);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    class ImageAdapter extends BaseAdapter {//gallery
        private Context context;
        private Integer[] images={
                R.drawable.coco,R.drawable.pepsi//从数据库中取出所有图片
        };
        public ImageAdapter(Context c) {
            context=c;
        }
        @Override
        public int getCount(){
            return images.length;
        }
        @Override
        public Object getItem(int position){
            return images[position];
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public  View getView(int position, View view, ViewGroup group){
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(images[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(800,800));
            return imageView;
        }
    }
}