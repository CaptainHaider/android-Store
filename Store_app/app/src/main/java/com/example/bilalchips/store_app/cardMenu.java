package com.example.bilalchips.store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class cardMenu extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_menu);

        CardForm cardForm=(CardForm) findViewById(R.id.cardfrm);
        //this could be used to show payment to be made by customer
        TextView payment_amount=(TextView)findViewById(R.id.payment_amount);
        //
        Button btnpay=(Button)findViewById(R.id.btn_pay);
        payment_amount.setText("$"+Integer.toString(helper.findTotal()));
        btnpay.setText(String.format("payer %s",payment_amount.getText()));
        //new On..
        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {


                String namestr=card.getName();
                String cardNostr=card.getNumber();
                String expirestr=(card.getExpMonth().toString())+(card.getExpYear());
                String CCV=card.getCVC();

                cardDetail c=new cardDetail();
                c.setName(namestr);
                c.setCardNo(cardNostr);
                c.setExpiry(expirestr);
                c.setCVV(CCV);
                helper.insertCard(c);

                Toast.makeText(cardMenu.this, "Name"+card.getName()+" | Last 4 digits :"+card.getLast4(), Toast.LENGTH_SHORT).show();

                startActivity(new Intent(cardMenu.this,Home.class));



            }
        });
    }
}
