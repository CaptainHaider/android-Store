package com.example.bilalchips.store_app;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bilalchips on 11/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    Cursor shoescursor=null;
    Cursor clothescursor=null;
    Cursor skateboardcursor=null;
    Cursor accesoriescursor=null;
    private static final int DATABASE_VERSION=10;
    private static final String DATABASE_NAME="shop.db";
    private static final String TABLE_SHOES="shoes";
    private static final String TABLE_CONTACTS="contacts";
    private static final String TABLE_CLOTHES="clothes";
    private static final String TABLE_SKATEBOARDS="skateboards";
    private static final String TABLE_ACCESORIES="accesories";
    private static final String TABLE_CARDS="cards";
    public static final String TABLE_CART="cart";

    private static final String TABLE_ACCESORIES_CREATE="create table accesories(id integer primary key not null,Brandname text" +
            ",size integer,price integer,imgurl text);";

    private static final String TABLE_SKATEBOARDS_CREATE="create table skateboards(id integer primary key not null,Brandname text" +
            ",size integer,price integer,imgurl text);";

    private static final String TABLE_CLOTHES_CREATE="create table clothes(id integer primary key not null,Brandname text" +
            ",size integer,price integer,imgurl text);";

    private static final String TABLE_SHOES_CREATE="create table shoes(id integer primary key not null,Brandname text" +
            ",size integer,price integer,imgurl text);";

    private static final String TABLE_CREATE_CARDS="create table cards(id integer primary key not null,contact_id integer,name text,cardNo text,CVV text,Expiry text);";


    private static final String TABLE_CART_CREATE="create table cart(id integer primary key not null,customer_id integer not null,product_name text not null,price integer);";

    private static final String TABLE_CREATE_CONTACTS="create table contacts(id integer primary key not null ," +
            "name text not null ,email text not null,uname text not null,pass text not null);";

    SQLiteDatabase db;
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_SHOES_CREATE);
        db.execSQL( TABLE_CART_CREATE);
        db.execSQL(TABLE_CREATE_CONTACTS);
        db.execSQL(TABLE_CLOTHES_CREATE);
        db.execSQL(TABLE_SKATEBOARDS_CREATE);
        db.execSQL(TABLE_ACCESORIES_CREATE);
        db.execSQL(TABLE_CREATE_CARDS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SHOES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLOTHES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKATEBOARDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCESORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        onCreate(db);

    }


    public void insertCart(cart c){

        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //this is for id increment
        String query="select * from cart";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        //
        values.put("id",count);
        values.put("customer_id",c.getCustomer_id());
        values.put("product_name",c.getProduct_name());
        values.put("price",c.getPrice());


        db.insert(TABLE_CART,null,values);
        db.close();
    }

    public int findTotal(){
        db=this.getWritableDatabase();
        String query="select SUM(price) From "+TABLE_CART+" where customer_id= "+loginScreen.customer_id;
       // Cursor cursor=db.rawQuery(query,null);
        int i=0;

        try{
            Cursor cursor=db.rawQuery(query,null);
            cursor.moveToFirst();
             i=cursor.getInt(0);


        }
        catch (Exception ex){

        }
        finally {
            db.close();
        }
        return i;
    }
    public void insertCard(cardDetail c){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from cards";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        //
        values.put("id",count);
        values.put("contact_id", loginScreen.customer_id);
        values.put("name",count);
        values.put("cardNo",count);
        values.put("CVV",count);
        values.put("Expiry",count);

        db.insert(TABLE_CARDS,null,values);
        db.close();
    }
    public int currentuserid(String uname){
        db=this.getReadableDatabase();
        String query="select id,uname from "+TABLE_CONTACTS;

        Cursor cursor=db.rawQuery(query,null);
        String a="",b="not found";
        if(cursor.moveToFirst()){
            do{

                b=cursor.getString(1);

                if(b.equals(uname)){
                    a=cursor.getString(0);
                    break;
                }
            }
            while (cursor.moveToNext());

        }
        return Integer.parseInt(a);
    }
    public void insertContact(contact c){

        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //this is for id increment
        String query="select * from contacts";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        //
        values.put("id",count);
        values.put("name",c.getName());
        values.put("email",c.getEmail());
        values.put("uname",c.getUname());
        values.put("pass",c.getPass());

        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }
    public void deleteCartItem(int id){
        db=this.getWritableDatabase();
String deleteQuery="delete from "+TABLE_CART+" where id = "+id;
try{
db.execSQL(deleteQuery);

}
catch (Exception ex){

}
finally {
    db.close();
}
    }

    public String searchPass(String uname){
        db=this.getReadableDatabase();
        String query="select uname ,pass from "+TABLE_CONTACTS;
        Cursor cursor=db.rawQuery(query,null);
        String a,b="not found";
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                b=cursor.getString(1);

                if(a.equals(uname)){
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());

        }
        return b;
    }

    public void insertaccesories(accesories ch){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from accesories";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put("id",count);
        values.put("Brandname",ch.getBrandname());
        values.put("size",ch.getSize());
        values.put("price",ch.getPrice());
        values.put("imgurl",ch.getImgurl());

        db.insert(TABLE_ACCESORIES,null,values);
        db.close();
    }
    public void insertSkateBoards(skateboards ch){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from skateboards";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put("id",count);
        values.put("Brandname",ch.getBrandname());
        values.put("size",ch.getSize());
        values.put("price",ch.getPrice());
        values.put("imgurl",ch.getImgurl());

        db.insert(TABLE_SKATEBOARDS,null,values);
        db.close();
    }
    public void insertClothes(clothes ch){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from clothes";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put("id",count);
        values.put("Brandname",ch.getBrandname());
        values.put("size",ch.getSize());
        values.put("price",ch.getPrice());
        values.put("imgurl",ch.getImgurl());

        db.insert(TABLE_CLOTHES,null,values);
        db.close();
    }
    public void insertShoes(shoes sh){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from shoes";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put("id",count);
        values.put("Brandname",sh.getBrandname());
        values.put("size",sh.getSize());
        values.put("price",sh.getPrice());
        values.put("imgurl",sh.getImgurl());

        db.insert(TABLE_SHOES,null,values);
        db.close();
    }
    public void cursoraccesories(){
        db=this.getReadableDatabase();
        String query="select id,Brandname,size,price,imgurl from "+TABLE_ACCESORIES;

        accesoriescursor=db.rawQuery(query,null);
    }
    public void cursorSkateBoards(){
        db=this.getReadableDatabase();
        String query="select id,Brandname,size,price,imgurl from "+TABLE_SKATEBOARDS;

        skateboardcursor=db.rawQuery(query,null);
    }
    public void cursorShoes(){
        db=this.getReadableDatabase();
        String query="select id,Brandname,size,price,imgurl from "+TABLE_SHOES;

        shoescursor=db.rawQuery(query,null);
    }
    public void cursorClothes(){
        db=this.getReadableDatabase();
        String query="select id,Brandname,size,price,imgurl from "+TABLE_CLOTHES;

        clothescursor=db.rawQuery(query,null);
    }
    public String[] imgaccesories(boolean f){



        //111  this is for button next
        String a="",b="not found";
        String id="",Brandname="",size="",price="",imgurl="";

        // shoescursor.moveToNext();
        if(f==true) {
            accesoriescursor.moveToNext();
            if (accesoriescursor.isAfterLast()) {
                accesoriescursor.moveToFirst();
            }
        }
////111
        ///222 this is for button back
        if(f==false) {
            accesoriescursor.moveToPrevious();
            if (accesoriescursor.isBeforeFirst()) {
                accesoriescursor.moveToLast();
            }
        }
        ///222
        id = accesoriescursor.getString(0);
        Brandname=accesoriescursor.getString(1);
        size=accesoriescursor.getString(2);
        price=accesoriescursor.getString(3);
        imgurl=accesoriescursor.getString(4);
        // shoescursor.moveToNext();







        return new String[]{id,Brandname,size,price,imgurl};
        //111
    }
    public String[] imgskateboards(boolean f){



        //111  this is for button next
        String a="",b="not found";
        String id="",Brandname="",size="",price="",imgurl="";

        // shoescursor.moveToNext();
        if(f==true) {
            skateboardcursor.moveToNext();
            if (skateboardcursor.isAfterLast()) {
                skateboardcursor.moveToFirst();
            }
        }
////111
        ///222 this is for button back
        if(f==false) {
            skateboardcursor.moveToPrevious();
            if (skateboardcursor.isBeforeFirst()) {
                skateboardcursor.moveToLast();
            }
        }
        ///222
        id = skateboardcursor.getString(0);
        Brandname=skateboardcursor.getString(1);
        size=skateboardcursor.getString(2);
        price=skateboardcursor.getString(3);
        imgurl=skateboardcursor.getString(4);
        // shoescursor.moveToNext();







        return new String[]{id,Brandname,size,price,imgurl};
        //111
    }
    public String[] imgclothes(boolean f){



        //111  this is for button next
        String a="",b="not found";
        String id="",Brandname="",size="",price="",imgurl="";

        // shoescursor.moveToNext();
        if(f==true) {
            clothescursor.moveToNext();
            if (clothescursor.isAfterLast()) {
                clothescursor.moveToFirst();
            }
        }
////111
        ///222 this is for button back
        if(f==false) {
            clothescursor.moveToPrevious();
            if (clothescursor.isBeforeFirst()) {
                clothescursor.moveToLast();
            }
        }
        ///222
        id = clothescursor.getString(0);
        Brandname=clothescursor.getString(1);
        size=clothescursor.getString(2);
        price=clothescursor.getString(3);
        imgurl=clothescursor.getString(4);
        // shoescursor.moveToNext();







        return new String[]{id,Brandname,size,price,imgurl};
        //111
    }
    //this function will bring shoes img
    public String[] imgshoes(boolean f){


        //below code is to view one image data
       /*
        db=this.getReadableDatabase();
       String query="select id,Brandname,size,price,imgurl from "+TABLE_SHOES;

        Cursor cursor=db.rawQuery(query,null);
        String a="",b="not found";
        String id,Brandname,size,price,imgurl;

        if(cursor.moveToFirst()) {
            id = cursor.getString(0);
            Brandname=cursor.getString(1);
            size=cursor.getString(2);
            price=cursor.getString(3);
            imgurl=cursor.getString(4);
        }
        else
        {
            cursor.moveToNext();
            id = cursor.getString(0);
            Brandname=cursor.getString(1);
            size=cursor.getString(2);
            price=cursor.getString(3);
            imgurl=cursor.getString(4);
        }
        return new String[]{id,Brandname,size,price,imgurl};


 */
        //111  this is for button next
        String a="",b="not found";
        String id="",Brandname="",size="",price="",imgurl="";

        // shoescursor.moveToNext();
        if(f==true) {
            shoescursor.moveToNext();
            if (shoescursor.isAfterLast()) {
                shoescursor.moveToFirst();
            }
        }
////111
        ///222 this is for button back
        if(f==false) {
            shoescursor.moveToPrevious();
            if (shoescursor.isBeforeFirst()) {
                shoescursor.moveToLast();
            }
        }
        ///222
        id = shoescursor.getString(0);
        Brandname=shoescursor.getString(1);
        size=shoescursor.getString(2);
        price=shoescursor.getString(3);
        imgurl=shoescursor.getString(4);
        // shoescursor.moveToNext();







        return new String[]{id,Brandname,size,price,imgurl};
        //111
    }
}
