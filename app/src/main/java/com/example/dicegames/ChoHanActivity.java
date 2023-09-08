package com.example.dicegames;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class ChoHanActivity extends AppCompatActivity {
    Random mrng = new Random();
    private static String TAG="ChoHanActivity";
    public static final int WAGERLIMIT = 10;
    public static final String KEY_BALANCE_RETURN="KEY_BALANCE_RETURN";
    public static TextView waged,die1,die2,chohanbalance_txt,parity_txt;
    public static ToggleButton toggleparity;
    public void launchWallet(View view){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_BALANCE_RETURN,Integer.parseInt((String) chohanbalance_txt.getText()));
        setResult(RESULT_OK,resultIntent);
        finish();
    }
    public void minusbtn(View view){

        int wager = Integer.parseInt((String)waged.getText());
        if(wager>0){ wager-=1;}
        waged.setText(Integer.toString(wager));
    }
    public void plusbtn(View view){
        int wager = Integer.parseInt((String)waged.getText());
        if(wager<WAGERLIMIT){ wager+=1;}
        waged.setText(Integer.toString(wager));
    }
    public void rollbtn(View view){
        int die1value=mrng.nextInt(6)+1;
        int die2value=mrng.nextInt(6)+1;
        die1.setText(Integer.toString(die1value));
        die2.setText(Integer.toString(die2value));
        int balance = Integer.parseInt((String)chohanbalance_txt.getText());
        int wager = Integer.parseInt((String)waged.getText());
        if(((die1value+die2value)%2==0 && toggleparity.isChecked()) || ((die1value+die2value)%2!=0 && !toggleparity.isChecked())){
            balance+=wager;
        }
        else{
            balance-=wager;
        }
        chohanbalance_txt.setText(Integer.toString(balance));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chohan);

        int balance = getIntent().getIntExtra(WalletActivity.KEY_BALANCE,0);
        chohanbalance_txt=findViewById(R.id.txt_cho_han_balance);
        die1 = findViewById(R.id.txt_cho_han_die1);
        die2 = findViewById(R.id.txt_cho_han_die2);
        waged=findViewById(R.id.txt_wager_balance);
        toggleparity=(ToggleButton) findViewById(R.id.tglbtn_parity_button);
        parity_txt=findViewById(R.id.txt_guess_label);

        chohanbalance_txt.setText(Integer.toString(balance));
    }


}