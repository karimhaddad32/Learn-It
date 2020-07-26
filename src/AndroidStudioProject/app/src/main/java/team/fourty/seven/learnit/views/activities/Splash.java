package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import team.fourty.seven.learnit.R;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(Splash.this, MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        },SPLASH_TIME);

    }
}
