package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;

import team.fourty.seven.learnit.R;

public class YoutubeMenuActivity extends YouTubeBaseActivity {

        ActionMenuView amvMenu;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

//            if(!this.getClass().equals(MainActivity.class)) {
//                getActionBar().setDisplayHomeAsUpEnabled(true);
//                getActionBar().setDisplayShowHomeEnabled(true);
//
//            }
//            getActionBar().setDisplayShowTitleEnabled(false);

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

                    return true;
                case R.id.btnStudyPlace:

                    return true;
                case android.R.id.home:
                    Toast.makeText(this, "Action clicked", Toast.LENGTH_LONG).show();
                    this.finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }


}
