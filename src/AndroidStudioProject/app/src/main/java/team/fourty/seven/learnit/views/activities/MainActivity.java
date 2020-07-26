package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team.fourty.seven.learnit.R;


public class MainActivity extends MenuActivity implements View.OnClickListener {

    private Button buttonSkillJournal;
    private Button buttonFindStudyPlace;
    private Button buttonLearnSkill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSkillJournal = (Button) findViewById(R.id.btnSkillJournal);
        buttonFindStudyPlace = (Button) findViewById(R.id.btnStudyPlace);
        buttonLearnSkill = (Button) findViewById(R.id.btnLearnSkill);

        buttonSkillJournal.setOnClickListener(this);
        buttonFindStudyPlace.setOnClickListener(this);
        buttonLearnSkill.setOnClickListener(this);


    }


    public void onClick(View v) {

        if (v.getId() == R.id.btnLearnSkill) {

            Intent intent = new Intent(this, TwoSidesOfBrain.class);

            startActivity(intent);

        } else if (v.getId() == R.id.btnStudyPlace) {
            //         Insert finding study place Activity here
             Intent intent = new Intent(this, StudyMapsActivity.class);

               startActivity(intent);

        } else if (v.getId() == R.id.btnSkillJournal) {
            //         Insert   Skill journal Activity here

            Intent intent = new Intent(this, PersonalJournal.class);

            startActivity(intent);

        }


    }


}
