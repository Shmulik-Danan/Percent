package com.example.elixi.percent;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , FragmentPercent.OnFragmentInteractionListener
        ,show.OnFragmentInteractionListener ,View.OnClickListener,FragmentPercent2.OnFragmentInteractionListener{
    private static String TAG;
    SQL  myDb;
    static ArrayList<DB > arr;
    static TextView textView;
    static FloatingActionButton fab;
    static FloatingActionButton fab1;
    static FloatingActionButton fab2;
    static FloatingActionButton fab3;
    static FloatingActionButton fab4;
    static Animation fabOpen;
    static Animation fabCloce;
    static Animation rotateForward;
    static Animation rotatebackward;
    static Animation textClose;
    static Animation textOpen;
    static Animation  fab11;
    static boolean isOpen=false;
    static boolean isFirst=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String TAG="";
        arr=new ArrayList<DB>();
        myDb = new SQL(this);
        myDb.setarr();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        textView=(TextView)findViewById(R.id.textView);

        fab11=AnimationUtils.loadAnimation(this,R.anim.fab1);
        textOpen=AnimationUtils.loadAnimation(this,R.anim.text_open);
        textClose=AnimationUtils.loadAnimation(this,R.anim.text_close);
        fabOpen= AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabCloce= AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotateForward= AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotatebackward= AnimationUtils.loadAnimation(this,R.anim.rotate_backforward);



        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
       // textView.startAnimation(textOpen);

        onClick(fab1);

    }
    static void animatefab(){

        if(isOpen){

            fab.startAnimation(rotateForward);
            fab1.startAnimation(fabCloce);
            fab2.startAnimation(fabCloce);
            fab3.startAnimation(fabCloce);
            fab4.startAnimation(fabCloce);


            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);

            isOpen=false;
        }
        else{

            fab.startAnimation(rotatebackward);
            fab1.startAnimation(fabOpen);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);
            fab4.startAnimation(fabOpen);



            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);

            isOpen=true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.fab):
                animatefab();

                break;
            case(R.id.fab1):

                FragmentPercent fragmentPercent=new FragmentPercent();
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main,fragmentPercent,fragmentPercent.getTag()).commit();
                animatefab();




            break;
            case(R.id.fab2):
                FragmentPercent2 fragmentPercent2=new FragmentPercent2();
                FragmentManager manager3=getSupportFragmentManager();
                manager3.beginTransaction().replace(R.id.content_main,fragmentPercent2,fragmentPercent2.getTag()).commit();

                animatefab();
                break;
            case(R.id.fab3):
                if(!arr.isEmpty()){
                show show=new show();
                FragmentManager manager2=getSupportFragmentManager();
                manager2.beginTransaction().replace(R.id.content_main,show,show.getTag()).commit();

                animatefab();
                }


                else{
                    animatefab();
                    fab.setEnabled(false);
                    Snackbar.make(fab1, "History is empty", 1000)
                            .setAction("Action", null).show();

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {

                                Thread.sleep(1100);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                           MainActivity.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    fab.setEnabled(true);

                                }
                            });
                        }
                    }).start();

                    // Toast.makeText(this, "Hstory is empty", Toast.LENGTH_SHORT).show();
                }

                break;
            case(R.id.fab4):

                animatefab();
                break;
        }
    }

}
