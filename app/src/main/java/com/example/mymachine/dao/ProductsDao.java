package com.example.mymachine.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mymachine.JavaBean.ProductBean;
import com.example.mymachine.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    /**
     * 增删改查
     */
    public static SQLiteDatabase db= DBHelper.db;//数据库赋值
    public static List<ProductBean> getAllProducts(){//list
        List<ProductBean> list=new ArrayList<>();
        Cursor c=db.rawQuery("select * from products",null);
        while(c.moveToNext()){
            ProductBean product=new ProductBean(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
            list.add(product);
        }
        return list;
    }
    public static ProductBean getProductById(String pid){//查询1个
        ProductBean product = null;
        Cursor c=db.rawQuery("select * from products where pid=?", new String[]{pid});
        while(c.moveToNext()){
            product=new ProductBean(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
        }
        return product;
    }
    public static void addProduct(ProductBean product){//增加
        String pid,pname,pnum,snum,price,image;
        pid=product.getPid();
        pname=product.getPname();
        image=product.getImage();
        pnum=product.getPnum();
        snum=product.getSnum();
        price=product.getPrice();
        db.execSQL("INSERT INTO products VALUES(?,?,?,?,?,?)",new String[]{pid,pname,image,pnum,snum,price});
    }
    public static void delProduct(ProductBean product){//删除
        String pid=product.getPid();
        db.execSQL("delete from products where pid=?",new String[]{pid});
    }
    public static void updateProduct(ProductBean product){//修改
        String pid,pname,pnum,snum,price,image;
        pid=product.getPid();
        pname=product.getPname();
        image=product.getImage();
        pnum=product.getPnum();
        snum=product.getSnum();
        price=product.getPrice();
        db.execSQL("UPDATE products SET pname=?,image=?,pnum=?,snum=?,price=? where pid=?",new String[]{pname,image,pnum,snum,price,pid});
    }
    public static String buyProduct(ProductBean product){//购买
        String pid,pnum,snum,price;
        pid=product.getPid();
        price=product.getPrice();
        pnum= String.valueOf(Integer.parseInt(product.getPnum())-1);
        snum=String.valueOf(Integer.parseInt(product.getSnum())+1);
        db.execSQL("UPDATE products SET pnum=?,snum=? where pid=?",new String[]{pnum,snum,pid});
        return price;
    }
}
