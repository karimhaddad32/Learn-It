package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.models.QuizQuestion;
import team.fourty.seven.learnit.models.QuizQuestionsList;
import team.fourty.seven.learnit.views.adapters.QuizAdapter;
import team.fourty.seven.learnit.views.adapters.QuizViewHolder;

public class QuizzesActivity extends MenuActivity
{

    public final String TAG = getClass().getSimpleName();

    private String category;
    private List<QuizQuestionsList> quizQuestionsLists;
    private List<QuizQuestion> quizQuestionList;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter<QuizViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;


    SkillController skillController;
    TextView txtSkillTest;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        findViews();
        setViews();

    }

    private class QuizAsynkTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
           String response = "";
           StringBuilder builder = new StringBuilder();

           if(strings[1].equals(null) && strings[2].equals(null) && strings[3].equals(null)){
               for(int i = 1 ; i < strings.length;i++){
                   strings[i] = "";
               }

           }

            try {

                builder.append("[" + skillController.makeApiForQuizzes(strings[0],skillController.EASY_DIFFICULTY,strings[1]));
                builder.append("," + skillController.makeApiForQuizzes(strings[0],skillController.MEDIUM_DIFFICULTY,strings[2]));
                builder.append("," + skillController.makeApiForQuizzes(strings[0],skillController.HARD_DIFFICULTY,strings[3]) + "]");

                Log.i(TAG,"Checking the result of the final Builder: " + builder.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return builder.toString();


        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            try {
                parseJSONResponse(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            HashMap<String,List<QuizQuestion>> mapOfListsOfQuestions = skillController.getHashMapOfLists(quizQuestionList);

            quizQuestionsLists = skillController.divideQuestionsToGroupOfTens(mapOfListsOfQuestions);

            makeChangesToActivity();

        }
    }

    private void makeChangesToActivity() {

        this.adapter = new QuizAdapter(quizQuestionsLists);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);

    }

    private void parseJSONResponse(String response) throws JSONException {

        quizQuestionList =skillController.parseQuestions(response);

    }

    private void setViews() {

        category = getIntent().getStringExtra("category");
        skillController = new SkillController(this);
        QuizAsynkTask asynkTask = new QuizAsynkTask();
        layoutManager = new LinearLayoutManager(this);

        //CRITICAL THINKING
        if (category.equals(skillController.CRITICAL_THINKING_CAT)) {
            //Quizz not found
            asynkTask.execute(skillController.CRITICAL_THINKING_CAT_API_ID,"30","30","30");
            txtSkillTest.setText(R.string.critical_thinking_quiz_title);
        }
        //LAW
        else if (category.equals(skillController.LAW_CAT)) {
            //Quizz not found
            asynkTask.execute(skillController.LAW_CAT_API_ID,"30","30","30");
            txtSkillTest.setText(R.string.law_quiz_title);
        }
        //CODING
        else if (category.equals(skillController.CODING_CAT)) {
            txtSkillTest.setText(R.string.coding_quiz_title);
            //the numbers are codes for how many quizzes available to each difficulty starting easy to hard: 29,49,22
            asynkTask.execute(skillController.CODING_CAT_API_ID,"29","49","22");

        }
        //TECHNOLOGY
        else if (category.equals(skillController.TECHNOLOGY_CAT)) {
            asynkTask.execute(skillController.TECHNOLOGY_CAT_API_ID,"7","6","4");
            txtSkillTest.setText(R.string.technology_quiz_title);
        }

        //PAINTING
        if (category.equals(skillController.PAINTING_CAT)) {
            asynkTask.execute(skillController.PAINTING_CAT_API_ID,"4","7","7");
            txtSkillTest.setText(R.string.painting_quiz_title);
        }
        //SCULPTING
        else if (category.equals(skillController.SCULPTING_CAT)) {
            //Quizz not found
            asynkTask.execute(skillController.SCULPTING_CAT_API_ID,"30","30","30");
            txtSkillTest.setText(R.string.sculpting_quiz_title);
        }
        //MUSIC
        else if (category.equals(skillController.MUSIC_CAT)) {
            asynkTask.execute(skillController.MUSIC_CAT_API_ID,"200","200","49");
            txtSkillTest.setText(R.string.music_quiz_title);
        }
        //SELF DEFENSE
        else if (category.equals(skillController.SELF_DEFENSE_CAT)) {
            //Quizz not found
            asynkTask.execute(skillController.SELF_DEFENSE_CAT_API_ID,"30","30","30");
            txtSkillTest.setText(R.string.self_defense_quiz_title);
        }

    }



    private void findViews() {

        txtSkillTest = findViewById(R.id.txtSkillTest);
        recyclerView = findViewById(R.id.recyclerViewQuizzes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = data;


        if(intent != null){
            String testName = intent.getStringExtra("name");
            String result = intent.getStringExtra("result");
            String category = intent.getStringExtra("category");
            String difficulty = intent.getStringExtra("difficulty");

            Log.i(TAG,"result is here : " + result);
            SharedPreferences preferences = getSharedPreferences("Quizzes",this.MODE_PRIVATE);

            SharedPreferences.Editor  editor=preferences.edit();
            editor.putString(category+difficulty+testName,result);
            editor.commit();



            adapter.notifyDataSetChanged();
        }



    }
}


//website for painting quizes: www.funtrivia.com/quizzes/humanities/art/art_techniques.html