package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import team.fourty.seven.learnit.R;

public class MenuActivity extends AppCompatActivity {

    ActionMenuView amvMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!this.getClass().equals(MainActivity.class)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.main_menu:

                if(!this.getClass().equals(MainActivity.class) ){
                startActivity(new Intent(this, MainActivity.class));
            }
                return true;
            case R.id.btnLearnSkill:

                if(!this.getClass().equals(TwoSidesOfBrain.class) ){
                    startActivity(new Intent(this,TwoSidesOfBrain.class) );
                }

                return true;

            case R.id.btnSkillJournal:
                if(!this.getClass().equals(PersonalJournal.class) ){
                    startActivity(new Intent(this,PersonalJournal.class) );
                }

                return true;
            case R.id.btnStudyPlace:
                if(!this.getClass().equals(StudyMapsActivity.class) ){
                    startActivity(new Intent(this,StudyMapsActivity.class) );
                }

                return true;
                case android.R.id.home:
                    this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
