package com.example.majdh.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DonateActivity extends AppCompatActivity
{

    TextView donations_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        donations_rec = (TextView)findViewById(R.id.donations_rec_TV);

        updateDonations_rec();
    }

    public void donateBtn_handle(View view)
    {
        TextView message = (TextView)findViewById(R.id.donateMsg);
        EditText money = (EditText)findViewById(R.id.money_value_ET);
        if(money.getText().toString().isEmpty())
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("Please insert money value to donate.");
            return;
        }
        Donations donate = new Donations(Double.valueOf(money.getText().toString()));
        donate.insertDonateToDB(this);
        updateDonations_rec();
        message.setTextColor(getResources().getColor(R.color.green));
        message.setText("Thank you for donating.");
    }

    private void updateDonations_rec()
    {
        Donations d = new Donations();
        donations_rec.setText(String.format("%.2f", d.getDonations_recFromDB(this)));
    }
}
