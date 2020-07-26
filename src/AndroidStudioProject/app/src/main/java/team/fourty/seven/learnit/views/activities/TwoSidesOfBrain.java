package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import team.fourty.seven.learnit.R;

public class TwoSidesOfBrain extends MenuActivity implements View.OnClickListener {

    private ImageButton btnLeftBrain;
    private ImageButton btnRightBrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_sides_of_brain);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnLeftBrain = (ImageButton) findViewById(R.id.btnLeftBrain);
        btnRightBrain = (ImageButton) findViewById(R.id.btnRightBrain);

        btnLeftBrain.setOnClickListener(this);
        btnRightBrain.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, SideOfBrain.class);
        int sideId = v.getId();
        intent.putExtra("sideId", sideId);
        startActivity(intent);

    }

}
