package team.fourty.seven.learnit.views.activities;

import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import team.fourty.seven.learnit.R;

public class PersonalJournal extends TabActivity implements TabHost.OnTabChangeListener
{

    private final String critical_thinking = "Critical Thinking";
    private final String law = "Law";
    private final String coding = "Coding";
    private final String technology = "Technology";
    private final String painting = "Painting";
    private final String sculpting = "Sculpting";
    private final String music = "Music";
    private final String self_defense = "Self Defense";

    Button criticalThinkingTab;
    Button lawTab;
    Button codingTab;
    Button technologyTab;
    Button paintingTab;
    Button sculptingTab;
    Button musicTab;
    Button selfDefenseTab;

    TabHost tabHost;
    public static String Tab="";
    int tab;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_journal);



//        RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.vertical_text);
//        rotateAnimation.setFillAfter(true);

//        criticalThinkingTab.setAnimation(rotateAnimation);
//        lawTab.setAnimation(rotateAnimation);
//        codingTab.setAnimation(rotateAnimation);
//        technologyTab.setAnimation(rotateAnimation);
//        paintingTab.setAnimation(rotateAnimation);
//        sculptingTab.setAnimation(rotateAnimation);
//        musicTab.setAnimation(rotateAnimation);
//        selfDefenseTab.setAnimation(rotateAnimation);



        tabHost = getTabHost();



        criticalThinkingTab = (Button) findViewById(R.id.criticalThinkingTab);
        lawTab = (Button) findViewById(R.id.lawTab);
        codingTab = (Button) findViewById(R.id.codingTab);
        technologyTab = (Button) findViewById(R.id.technologyTab);
        paintingTab = (Button) findViewById(R.id.paintingTab);
        sculptingTab = (Button) findViewById(R.id.sculptingTab);
        musicTab = (Button) findViewById(R.id.musicTab);
        selfDefenseTab = (Button) findViewById(R.id.selfDefenseTab);



        addSpec("criticalThinkingTab", critical_thinking);
        addSpec("lawTab", law);
        addSpec("codingTab", coding);
        addSpec("technologyTab", technology);
        addSpec("paintingTab", painting);
        addSpec("sculptingTab", sculpting);
        addSpec("musicTab", music);
        addSpec("selfDefenseTab", self_defense);



        //returns to the right tab after exiting add or edit actions
        if(getIntent().hasExtra("tab"))
        {
            tab = getIntent().getIntExtra("tab", 0);
            tabHost.setCurrentTab(tab);

            if(tab == 0)
            {
                criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
            else if(tab == 1)
            {
                lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
            else if(tab == 2)
            {
                codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
            else if(tab == 3)
            {
                technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
            else if(tab == 4)
            {
                paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
            else if(tab == 5)
            {
                sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
            else if(tab == 6)
            {
                musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
            else if(tab == 7)
            {
                selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
            }
        }
        else
        {
            criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));
        }
    }



    public void addSpec(String indicator, String category)
    {
        TabSpec spec = tabHost.newTabSpec(indicator);
        spec.setIndicator(indicator);
        Intent intent = new Intent(this, JournalEntries.class);
        intent.putExtra("category", category);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }



    public void tabHandler(View target)
    {
        if (target.getId() == R.id.criticalThinkingTab)
        {
            Tab="this is Tab 1";
            tabHost.setCurrentTab(0);
            criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
        else if (target.getId() == R.id.lawTab)
        {
            Tab="this is Tab 2";
            tabHost.setCurrentTab(1);
            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
        else if (target.getId() == R.id.codingTab)
        {
            Tab="this is Tab 3";
            tabHost.setCurrentTab(2);
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
        else if (target.getId() == R.id.technologyTab)
        {
            Tab="this is Tab 4";
            tabHost.setCurrentTab(3);
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
        else if (target.getId() == R.id.paintingTab)
        {
            Tab="this is Tab 5";
            tabHost.setCurrentTab(4);
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
        else if (target.getId() == R.id.sculptingTab)
        {
            Tab="this is Tab 6";
            tabHost.setCurrentTab(5);
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            criticalThinkingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
        else if (target.getId() == R.id.musicTab)
        {
            Tab="this is Tab 7";
            tabHost.setCurrentTab(6);
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
        else if (target.getId() == R.id.selfDefenseTab)
        {
            Tab="this is Tab 8";
            tabHost.setCurrentTab(7);
            selfDefenseTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left_open_right));

            lawTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            codingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            technologyTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            paintingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            sculptingTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
            musicTab.setBackground(getDrawable(R.drawable.rounded_half_corners_left));
        }
    }

    @Override
    public void onTabChanged(String tabId)
    {

    }
}
