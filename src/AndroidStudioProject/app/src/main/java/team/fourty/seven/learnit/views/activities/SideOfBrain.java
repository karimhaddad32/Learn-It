package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.controllers.SkillController;

public class SideOfBrain extends MenuActivity implements View.OnClickListener {

    private int idSide;

    private ImageView imageViewOne;
    private ImageView imageViewTwo;
    private ImageView imageViewThree;
    private ImageView imageViewFour;

    private TextView textViewOne;
    private TextView textViewTwo;
    private TextView textViewThree;
    private TextView textViewFour;

    private CardView cardViewOne;
    private CardView cardViewTwo;
    private CardView cardViewThree;
    private CardView cardViewFour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_of_brain);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        idSide = getIntent().getIntExtra("sideId", 0);

        findViews();

        if (idSide == R.id.btnLeftBrain) {

            loadLeftBrain();



        } else if (idSide == R.id.btnRightBrain) {

            loadRightBrain();


        }

        cardViewOne.setOnClickListener(this);
        cardViewTwo.setOnClickListener(this);
        cardViewThree.setOnClickListener(this);
        cardViewFour.setOnClickListener(this);

    }

    private void findViews() {

        imageViewOne = (ImageView) findViewById(R.id.imageViewOne);
        imageViewTwo = (ImageView) findViewById(R.id.imageViewTwo);
        imageViewThree = (ImageView) findViewById(R.id.imageViewThree);
        imageViewFour = (ImageView) findViewById(R.id.imageViewFour);

        textViewOne = (TextView) findViewById(R.id.textViewOne);
        textViewTwo = (TextView) findViewById(R.id.textViewTwo);
        textViewThree = (TextView) findViewById(R.id.textViewThree);
        textViewFour = (TextView) findViewById(R.id.textViewFour);

        cardViewOne = (CardView) findViewById(R.id.cardViewOne);
        cardViewTwo = (CardView) findViewById(R.id.cardViewTwo);
        cardViewThree = (CardView) findViewById(R.id.cardViewThree);
        cardViewFour = (CardView) findViewById(R.id.cardViewFour);
    }

    private void loadRightBrain() {

        imageViewOne.setImageDrawable(getDrawable(R.drawable.painting));
        imageViewTwo.setImageDrawable(getDrawable(R.drawable.sculpting));
        imageViewThree.setImageDrawable(getDrawable(R.drawable.music));
        imageViewFour.setImageDrawable(getDrawable(R.drawable.self_defense));

        textViewOne.setText(R.string.txtPainting);
        textViewTwo.setText(R.string.txtSculpting);
        textViewThree.setText(R.string.txtMusic);
        textViewFour.setText(R.string.txtSelfDefense);

    }

    private void loadLeftBrain() {

        imageViewOne.setImageDrawable(getDrawable(R.drawable.critical_thinking1));
        imageViewTwo.setImageDrawable(getDrawable(R.drawable.law));
        imageViewThree.setImageDrawable(getDrawable(R.drawable.coding));
        imageViewFour.setImageDrawable(getDrawable(R.drawable.technology));

        textViewOne.setText(R.string.txtCriticalThinking);
        textViewTwo.setText(R.string.txtLaw);
        textViewThree.setText(R.string.txtCoding);
        textViewFour.setText(R.string.txtTechnology);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SkillActivity.class);
        SkillController controller = new SkillController(this);
        int videoId = v.getId();
        intent.putExtra("category", controller.getCategory(idSide, videoId));
        startActivity(intent);
    }

}
