package com.fxj.memoryleaktest01;

import android.content.Context;

public class Singleton {
    private static Singleton sInstance;
    Context mContext;

    public static Singleton getInstance(Context context){
        if(sInstance==null){
            synchronized (Singleton.class){
                if(sInstance==null){
                    sInstance=new Singleton(context);
                }
            }
        }
        return sInstance;
    }
    private Singleton(Context mContext) {
        this.mContext = mContext;
    }
}
