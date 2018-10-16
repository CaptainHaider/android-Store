package com.example.bilalchips.store_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class accesoriesMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper helper=new DatabaseHelper(this);
    static String result[];
    ImageView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //12312
        v1=(ImageView)findViewById(R.id.imageView1accesories);
        final TextView t1=(TextView)findViewById(R.id.textViewaccesories);
        final TextView txtprice=(TextView)findViewById(R.id.pricetxtaccesories);

/*
        Picasso.with(getApplicationContext()).load(url)
                .into(v1);

        Button b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load(url)
                        .into(v1);
            }
        });
    */
        accesories s=new accesories();

        ///shoes 1
        s.setBrandname("Trap House Hat");
        s.setSize(10);
        s.setPrice(10);
        s.setImgurl("http://scene7.zumiez.com/is/image/zumiez/pdp_hero/Artist-Collective-Trap-House-Khaki-Baseball-Hat-_277004-front-US.jpg");

        helper.insertaccesories(s);

        accesories s1=new accesories();
        s1.setBrandname("Tan leather Watch");
        s1.setSize(10);
        s1.setPrice(20);
        s1.setImgurl("https://cdn.shopify.com/s/files/1/0377/2037/products/WhiteTanLeather.Front_1024x.jpg?v=1510683461");
        helper.insertaccesories(s1);

        ///button next
        helper.cursoraccesories();


        //to make it apper at start
        result=helper.imgaccesories(true);
        Picasso.with(getApplicationContext()).load(result[4]) .into(v1);
        t1.setText(result[1]);
        txtprice.setText("$"+result[3]);
        //

        Button b1=(Button)findViewById(R.id.btnNextaccesories);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=helper.imgaccesories(true);
                Picasso.with(getApplicationContext()).load(result[4]) .into(v1);
                t1.setText(result[1]);
                txtprice.setText("$"+result[3]);


            }
        });

        //button back

        Button b2=(Button)findViewById(R.id.btnBackaccesories);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=helper.imgaccesories(false);
                Picasso.with(getApplicationContext()).load(result[4]) .into(v1);
                t1.setText(result[1]);
                txtprice.setText("$"+result[3]);
            }
        });

        ///size

        Spinner size=(Spinner)findViewById(R.id.spinneraccesories);
        String []sizes={"8","9","10"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sizes);
        size.setAdapter(adapter);

        Button b3=(Button)findViewById(R.id.buybtnaccesories);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart ct=new cart();

                ct.setCustomer_id(loginScreen.customer_id);
                ct.setProduct_name(result[1]);
                ct.setPrice(Integer.parseInt(result[3]));

                helper.insertCart(ct);
                Toast.makeText(accesoriesMenu.this, "Added to your cart", Toast.LENGTH_SHORT).show();
            }
        });
        //12313
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
                Intent h= new Intent(accesoriesMenu.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_clothes:
                Intent i= new Intent(accesoriesMenu.this,clothesMenu.class);
                startActivity(i);
                break;
            case R.id.nav_shoes:
                Intent j= new Intent(accesoriesMenu.this,shoesMenu.class);
                startActivity(j);
                break;
            case R.id.nav_accesories:
                Intent k= new Intent(accesoriesMenu.this,accesoriesMenu.class);
                startActivity(k);
                break;
            case R.id.nav_skateboards:
                Intent l= new Intent(accesoriesMenu.this,skateboardsMenu.class);
                startActivity(l);
                break;
            case R.id.nav_contact_info:
                Intent m= new Intent(accesoriesMenu.this,ContactInfo.class);
                startActivity(m);
                break;
            case R.id.nav_cart:
                Intent n= new Intent(accesoriesMenu.this,cartMenu.class);
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
