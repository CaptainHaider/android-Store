package com.example.bilalchips.store_app;

import android.os.Bundle;
import android.content.Intent;
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

public class clothesMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper helper=new DatabaseHelper(this);
    static String result[];
    ImageView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


 ////////////////////////////////////////////////////////
        v1=(ImageView)findViewById(R.id.imageView1Clothes);
        final TextView t1=(TextView)findViewById(R.id.textViewClothes);
        final TextView txtprice=(TextView)findViewById(R.id.pricetxtClothes);
        clothes s=new clothes();


        s.setBrandname("Blue");
        s.setSize(50);
        s.setPrice(59);
        s.setImgurl("http://lp.hm.com/hmprod?set=key[source],value[/model/2017/E00%200495629%20001%2029%202638.jpg]&set=key[rotate],value[]&set=key[width],value[]&set=key[height],value[]&set=key[x],value[]&set=key[y],value[]&set=key[type],value[STILL_LIFE_FRONT]&set=key[hmver],value[3]&set=key[quality],value[80]&set=key[size],value[346x405]&call=url[file:/mobile/v2/product]");

        helper.insertClothes(s);

        clothes s1=new clothes();
        s1.setBrandname("Vine short Sleeves");
        s1.setSize(30);
        s1.setPrice(90);
        s1.setImgurl("http://lp.hm.com/hmprod?set=key[source],value[/model/2017/E00%200453308%20011%2058%202445.jpg]&set=key[rotate],value[]&set=key[width],value[]&set=key[height],value[]&set=key[x],value[]&set=key[y],value[]&set=key[type],value[STILL_LIFE_FRONT]&set=key[hmver],value[1]&set=key[quality],value[80]&set=key[size],value[346x405]&call=url[file:/mobile/v2/product]");
        helper.insertClothes(s1);

        ///button next
        helper.cursorClothes();


        //to make it apper at start
        result=helper.imgclothes(true);
        Picasso.with(getApplicationContext()).load(result[4]) .into(v1);
        t1.setText(result[1]);
        txtprice.setText("$"+result[3]);
        //

        Button b1=(Button)findViewById(R.id.btnNextClothes);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=helper.imgclothes(true);
                Picasso.with(getApplicationContext()).load(result[4]) .into(v1);
                t1.setText(result[1]);
                txtprice.setText("$"+result[3]);

            }
        });

        //button back

        Button b2=(Button)findViewById(R.id.btnBackClothes);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=helper.imgclothes(false);
                Picasso.with(getApplicationContext()).load(result[4]) .into(v1);
                t1.setText(result[1]);
                txtprice.setText("$"+result[3]);
            }
        });

        ///size

        Spinner size=(Spinner)findViewById(R.id.spinnerClothes);
        String []sizes={"8","9","10"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sizes);
        size.setAdapter(adapter);

        Button b3=(Button)findViewById(R.id.buybtnClothes);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart ct=new cart();

                ct.setCustomer_id(loginScreen.customer_id);
                ct.setProduct_name(result[1]);
                ct.setPrice(Integer.parseInt(result[3]));

                helper.insertCart(ct);
                Toast.makeText(clothesMenu.this, "Added to your cart", Toast.LENGTH_SHORT).show();
            }
        });
        //12313

/////////////////////////////////////////////////////////////////////////////////////////////

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
                Intent h= new Intent(clothesMenu.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_clothes:
                Intent i= new Intent(clothesMenu.this,clothesMenu.class);
                startActivity(i);
                break;
            case R.id.nav_shoes:
                Intent j= new Intent(clothesMenu.this,shoesMenu.class);
                startActivity(j);
                break;
            case R.id.nav_accesories:
                Intent k= new Intent(clothesMenu.this,accesoriesMenu.class);
                startActivity(k);
                break;
            case R.id.nav_skateboards:
                Intent l= new Intent(clothesMenu.this,skateboardsMenu.class);
                startActivity(l);
                break;
            case R.id.nav_contact_info:
                Intent m= new Intent(clothesMenu.this,ContactInfo.class);
                startActivity(m);
                break;
            case R.id.nav_cart:
                Intent n= new Intent(clothesMenu.this,cartMenu.class);
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
