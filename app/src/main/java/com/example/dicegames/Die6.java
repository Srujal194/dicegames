package com.example.dicegames;
import java.util.Random;
public class Die6 implements Die{
    private static final int FACES = 6;
    private Random mRng;
    private int mValue;

    public Die6(){
        mRng = new Random();
    }

    public void roll(){
        mValue = mRng.nextInt(FACES)+1;
    }
    public int value(){
        return mValue;
    }
}
