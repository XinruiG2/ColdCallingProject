package com.example.coldcalling;

import java.util.Calendar;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class TimerView extends TextView {

    private Handler mHandler = new Handler();
    private int mHour, mMinute, mSecond;// variables holding the hour and minute
    private Runnable mUpdate = new Runnable() {

        @Override
        public void run() {
            mSecond += 1;
            // just some checks to keep everything in order
            if (mSecond >= 60) {
                mSecond = 0;
                mMinute+=1;

            }
            if (mMinute >= 60) {
                mMinute = 0;
                mHour += 1;
            }
            if (mHour >= 24) {
                mHour = 0;
            }
            // or call your method
            setText(mHour + ":" + mMinute+":"+mSecond);
            mHandler.postDelayed(this, 1000);
        }
    };



    public TimerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mHandler.removeCallbacks(mUpdate);
        Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSecond = c.get(Calendar.SECOND);
        setText(mHour + ":" + mMinute+":"+mSecond);
        mHandler.postDelayed(mUpdate, 1000); // 60000 a minute


    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimerView(Context context) {
        super(context);
        init(context);
    }

}