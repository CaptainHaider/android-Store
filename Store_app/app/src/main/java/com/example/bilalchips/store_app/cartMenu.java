package com.example.bilalchips.store_app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class cartMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        context=this;
        DatabaseHelper dataHelper=new DatabaseHelper(context);

//
        final EditText deleteid=(EditText)findViewById(R.id.delete_id_input);
        Button deletbtn=(Button)findViewById(R.id.deleteBtn);
        Button proccepayment=(Button)findViewById(R.id.procpaymentBtn);
        proccepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cartMenu.this,cardMenu.class));
            }
        });

       // startActivity(new Intent(cardMenu.this,Home.class));
        deletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=deleteid.getText().toString();
                int deletValue=Integer.parseInt(value);
                helper.deleteCartItem(deletValue);
                Toast.makeText(cartMenu.this, "Item deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(cartMenu.this,cartMenu.class));
            }
        });
        //
        TableLayout tableLayout=(TableLayout)findViewById(R.id.tablelayout);
        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        String[] headerText={"ID","Customer Id","Product name","Price"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);

        }
        tableLayout.addView(rowHeader);

        // Get data from sqlite database and add them to the table
        // Open the database for reading
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();



        try
        {
            String selectQuery = "SELECT * FROM "+DatabaseHelper.TABLE_CART+" WHERE customer_id="+loginScreen.customer_id ;//this will only display items from current user
            // String selectQuery = "SELECT * FROM "+DatabaseHelper.TABLE_CART;//this code will display all cart items from other customers too
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Read columns data
                    int outlet_id= cursor.getInt(cursor.getColumnIndex("id"));
                    int customer_id= cursor.getInt(cursor.getColumnIndex("customer_id"));
                    String product_name= cursor.getString(cursor.getColumnIndex("product_name"));
                    int price= cursor.getInt(cursor.getColumnIndex("price"));
                    // dara rows
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText={outlet_id+"",customer_id+"",product_name+"",price+""};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);
                        row.addView(tv);
                    }
                    tableLayout.addView(row);

                }

            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the home place where we need to work on.
        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(cartMenu.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_clothes:
                Intent i= new Intent(cartMenu.this,clothesMenu.class);
                startActivity(i);
                break;
            case R.id.nav_shoes:
                Intent j= new Intent(cartMenu.this,shoesMenu.class);
                startActivity(j);
                break;
            case R.id.nav_accesories:
                Intent k= new Intent(cartMenu.this,accesoriesMenu.class);
                startActivity(k);
                break;
            case R.id.nav_skateboards:
                Intent l= new Intent(cartMenu.this,skateboardsMenu.class);
                startActivity(l);
                break;
            case R.id.nav_contact_info:
                Intent m= new Intent(cartMenu.this,ContactInfo.class);
                startActivity(m);
                break;
            case R.id.nav_cart:
                Intent n= new Intent(cartMenu.this,cartMenu.class);
                startActivity(n);
                break;
            // after this lets start copying the above.
            // FOLLOW MEEEEE>>>
            //copy this now.
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
