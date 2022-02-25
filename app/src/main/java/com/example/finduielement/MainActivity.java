package com.example.finduielement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    View view;
    RelativeLayout relativeLayout;
    float x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.image);
        view=findViewById(R.id.view);
        relativeLayout=findViewById(R.id.relativeLayout);
        /*DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int pxWidth = displayMetrics.widthPixels;
        int pxHeight = displayMetrics.heightPixels;
        DisplayMetrics dms = getResources().getDisplayMetrics();
        float densityxdpi = dms.xdpi;
        float densityydpi = dms.ydpi;
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetricss = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;*/

        DisplayMetrics dm = new DisplayMetrics();

        this.getWindowManager().getDefaultDisplay().getMetrics(dm);


        int topOffset = dm.heightPixels - view.getMeasuredHeight();
        int horoffset=dm.widthPixels-view.getMeasuredWidth();
        System.out.println("topset:"+topOffset);
        System.out.println("horoffset:"+horoffset);
        int location[] = new int[2];

   SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstTime", false);
        editor.commit();
        //editor.putBoolean("firstTime", false);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
        // mark first time has runned.
            editor.putBoolean("firstTime", true);
            editor.commit();
            relativeLayout.setVisibility(View.VISIBLE);
        }
        else{
            relativeLayout.setVisibility(View.GONE);
        }


        imageView.post(new Runnable() {
            @Override
            public void run() {


                int[] location = new int[2];

                imageView.getLocationInWindow(location);

                x = location[0];
                y = location[1]+dpToPx(60);


                System.out.println("x:"+x+" y:"+y);
            }
        });
        relativeLayout.post(new Runnable() {
            @Override
            public void run() {
                relativeLayout.setX(x);
                relativeLayout.setY(y);
            }
        });

    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}