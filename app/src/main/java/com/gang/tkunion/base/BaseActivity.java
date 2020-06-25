package com.gang.tkunion.base;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void initViews();
    public abstract void initEvents();
    public abstract void initBroadcasts();
    public abstract void release();

}
