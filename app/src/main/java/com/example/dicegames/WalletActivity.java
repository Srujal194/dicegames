package com.example.dicegames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WalletActivity extends AppCompatActivity {

    private static int INCREMENT=5;
    private static int WIN_VALUE=6;
    private static final String KEY_BALANCE="KEY BALANCE";
    private static final String KEY_DIE_VALUE="KEY DIE VALUE";
    private static Button mDieButton;
    private static Die mDie;
    private static int mBalance;
    private static TextView mTextBalance;
    private static int DieValue=0;
    private static final String TAG ="WalletActivity";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        outState.putInt(KEY_BALANCE,mBalance);
        outState.putInt(KEY_DIE_VALUE,DieValue);
        Log.d(TAG,"Saved:balance="+mBalance+", Saved:dievalue="+mDie.value());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        mDie= new Die6();
        mDieButton=findViewById(R.id.btn_die);
        mTextBalance=findViewById(R.id.txt_balance);
        if(savedInstanceState!=null){
            mBalance=savedInstanceState.getInt(KEY_BALANCE,0);
            mDieButton.setText(Integer.toString(savedInstanceState.getInt(KEY_DIE_VALUE,0)));
            mTextBalance.setText(Integer.toString(mBalance));
        }
        mDieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDie.roll();
                Log.d(TAG,"Die roll ="+mDie.value());
                DieValue=mDie.value();
                if(mDie.value()==WIN_VALUE){
                    mBalance+=INCREMENT;
                    Log.d(TAG,"New balance ="+mBalance);
                }
                updateUI();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    private void updateUI(){
        mDieButton.setText(Integer.toString(mDie.value()));
        mTextBalance.setText(Integer.toString(mBalance));
    }
}