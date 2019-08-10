package com.fxj.memoryleaktest01

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG:String=MainActivity::class.java.simpleName
        var isLandscape:Boolean=false;
    }

    private var orientationEventListener:OrientationEventListener?=null
    private lateinit var btnChange:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val am=getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memorySize= am.getMemoryClass()
        Log.d(TAG,"**onCreate**memorySize=${memorySize}MB")
        setContentView(R.layout.activity_main)
        btnChange=findViewById(R.id.btn_change_orientation)
        btnChange.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                switchToLandscape()
            }
        })

        val runnable:Runnable=object :Runnable{
            override fun run() {
                Thread.sleep(1000*1000)
            }
        }

        Thread(runnable).start()
        Singleton.getInstance(this@MainActivity)
    }

    fun switchToLandscape(){
        Log.d(TAG,"**switchToLandscape**before isLandscape=${MainActivity.isLandscape}");
        requestedOrientation=if(MainActivity.isLandscape) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT else ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        MainActivity.isLandscape=!MainActivity.isLandscape
        Log.d(TAG,"**switchToLandscape**after isLandscape=${isLandscape}");
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"**onStart**")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"**onRestart**")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"**onResume**")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG,"**onConfigurationChanged**newConfig.orientation=${newConfig.orientation}")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG,"**onPause**")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"**onStop**")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"**onDestroy**")
    }
}
