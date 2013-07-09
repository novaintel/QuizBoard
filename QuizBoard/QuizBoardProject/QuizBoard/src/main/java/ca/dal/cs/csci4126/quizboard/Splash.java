package ca.dal.cs.csci4126.quizboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by James on 6/30/13.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent openStartingPoint =
                            new Intent("ca.dal.cs.csci4126.quizboard.STARTINGPOINT");
                            //new Intent("ca.dal.cs.csci4126.quizboard.SETTINGS");
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
