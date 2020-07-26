package team.fourty.seven.learnit.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.controllers.helpers.DBAccessController;
import team.fourty.seven.learnit.models.EBook;
import team.fourty.seven.learnit.models.QuizQuestion;
import team.fourty.seven.learnit.models.QuizQuestionsList;
import team.fourty.seven.learnit.models.QuizResult;
import team.fourty.seven.learnit.models.YoutubeVideo;


public class SkillController {

    private static final String TAG = SkillController.class.getSimpleName();

    private final String DB_EBOOKS_TABLE = "EBOOKS";
    private final String DB_VIDEOS_TABLE = "VIDEOS";

    //columns in eBooks table

    private final String AUTHOR = "author";
    private final String PHOTO = "photo";
    private final String IS_READ = "is_read";
    private final String IS_FAVOURED = "is_favoured";


    //similar column names

    private final String TITLE = "title";
    private final String DESCRIPTION = "description";
    private final String URL = "url";
    private final String CATEGORY = "skill_category";

    //columns in videos table:

    private final String THUMBNAIL = "thumbnail";
    private final String CHANNEL = "channel";
    private final String VIDEO_ID = "video_id";

    //category (keys)
    public final String CRITICAL_THINKING_CAT = "critical thinking";
    public final String CODING_CAT = "coding";
    public final String LAW_CAT = "law";
    public final String TECHNOLOGY_CAT = "technology";
    public final String PAINTING_CAT = "painting";
    public final String MUSIC_CAT = "music";
    public final String SELF_DEFENSE_CAT = "self defense";
    public final String SCULPTING_CAT = "sculpting";

    //API related variables

    public final String CRITICAL_THINKING_CAT_API_ID = "";
    public final String CODING_CAT_API_ID = "18";
    public final String LAW_CAT_API_ID = "";
    public final String TECHNOLOGY_CAT_API_ID = "30";
    public final String PAINTING_CAT_API_ID = "25";
    public final String MUSIC_CAT_API_ID = "12";
    public final String SELF_DEFENSE_CAT_API_ID = "";
    public final String SCULPTING_CAT_API_ID = "";

    public final String EASY_DIFFICULTY = "easy";
    public final String MEDIUM_DIFFICULTY = "medium";
    public final String HARD_DIFFICULTY = "hard";



    private DBAccessController databaseAccessHelper;
    private SQLiteDatabase sqLiteDatabase;


    public SkillController(Context context) {

        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    public List<EBook> getSkillEBooks(String category) {

        List<EBook> eBooks = new ArrayList<>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s = '%s'", DB_EBOOKS_TABLE, CATEGORY, category);
        Log.i(TAG, query);

        try {

            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                EBook ebook = new EBook();
                ebook.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                ebook.setAuthor(cursor.getString(cursor.getColumnIndex(AUTHOR)));
                ebook.setUri(cursor.getString(cursor.getColumnIndex(URL)));
                ebook.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                ebook.setPhoto(toBitmap(cursor.getBlob(cursor.getColumnIndex(PHOTO))));
                ebook.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
                ebook.setFavourited(intToBoolean(cursor.getInt(cursor.getColumnIndex(IS_FAVOURED))));
                ebook.setRead(intToBoolean(cursor.getInt(cursor.getColumnIndex(IS_READ))));
                eBooks.add(ebook);
                cursor.moveToNext();
                Log.i(TAG, ebook.toString());

            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return eBooks;
    }

    private Bitmap toBitmap(byte[] blob) {
        return BitmapFactory.decodeByteArray(blob, 0, blob.length);
    }

    public String getCategory(int idSide, int idSkill) {
        String category = "";

        if (idSide == R.id.btnLeftBrain) {
            //CRITICAL THINKING
            if (idSkill == R.id.cardViewOne) {
                return CRITICAL_THINKING_CAT;
            }
            //LAW
            else if (idSkill == R.id.cardViewTwo) {
                return LAW_CAT;
            }
            //CODING
            else if (idSkill == R.id.cardViewThree) {
                return CODING_CAT;
            }
            //TECHNOLOGY
            //Youtube Link: https://www.youtube.com/watch?v=jG4cxSFgQUo
            else if (idSkill == R.id.cardViewFour) {
                return TECHNOLOGY_CAT;
            }
        }
        //RIGHT SIDE OF THE BRAIN
        else if (idSide == R.id.btnRightBrain) {

            //PAINTING
            if (idSkill == R.id.cardViewOne) {
                return PAINTING_CAT;
            }
            //SCULPTING
            else if (idSkill == R.id.cardViewTwo) {
                return SCULPTING_CAT;
            }
            //MUSIC
            else if (idSkill == R.id.cardViewThree) {
                return MUSIC_CAT;
            }
            //SELF DEFENSE
            else if (idSkill == R.id.cardViewFour) {
                return SELF_DEFENSE_CAT;
            }

        }

        return category;
    }

    public List<YoutubeVideo> getSkillVideos(String category) {

        List<YoutubeVideo> videos = new ArrayList<>();
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s = '%s'",
                                      DB_VIDEOS_TABLE, CATEGORY, category);
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                YoutubeVideo youtubeVideo = new YoutubeVideo();
                youtubeVideo.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                youtubeVideo.setChannel(cursor.getString(cursor.getColumnIndex(CHANNEL)));
                youtubeVideo.setUri(cursor.getString(cursor.getColumnIndex(URL)));
                youtubeVideo.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                youtubeVideo.setThumbnail(toBitmap(cursor.getBlob(cursor.getColumnIndex(THUMBNAIL))));
                youtubeVideo.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
                youtubeVideo.setVideoId(cursor.getString(cursor.getColumnIndex(VIDEO_ID)));
                videos.add(youtubeVideo);
                cursor.moveToNext();
                Log.i(TAG, youtubeVideo.toString());

            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return videos;
    }

    private boolean intToBoolean(int num) {
        if (num == 0) {
            return false;
        } else
            return true;
    }

    private int booleanToInt(boolean b) {
        if (b) {
            return 1;
        } else
            return 0;
    }

    public void updateEBooks(EBook eBook) {


        Log.i(TAG, eBook.getTitle());


        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put(IS_FAVOURED, booleanToInt(eBook.isFavourited()));
            values.put(IS_READ, booleanToInt(eBook.isRead()));
            sqLiteDatabase.update(DB_EBOOKS_TABLE, values, TITLE + " = '" + eBook.getTitle() + "'", null);

        } catch (Exception e) {

            Log.wtf(TAG, e.fillInStackTrace());

        } finally {

            databaseAccessHelper.closeDatabase();

        }
    }

    public String makeApiForQuizzes(String categoryApiId, String difficulty, String  questionsAmount) throws IOException, JSONException {

        Log.i(TAG, "calling API ");

        StringBuilder builder = new StringBuilder();


        String endPoint = "https://opentdb.com/api.php?amount=" + questionsAmount + "&category=" + categoryApiId + "&difficulty=" + difficulty + "&type=multiple";

        Log.i(TAG, "URL: " + endPoint);

        URL url = new URL(endPoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.connect();

        InputStream stream = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        JSONObject jsonObjectResponse = new JSONObject(builder.toString());

        Log.i(TAG, "response code: " + jsonObjectResponse.getInt("response_code"));



        Log.i(TAG, "questions amount: " + String.valueOf(questionsAmount));


        Log.i(TAG, "JSON FILE: " + builder.toString());

        return builder.toString();



    }


    public List<QuizQuestion> parseQuestions(String response) throws JSONException {

        final String RESULTS = "results";
        final String CATEGORY = "category";
        final String TYPE = "type";
        final String DIFFICULTY = "difficulty";
        final String QUESTION = "question";
        final String CORRECT_ANSWER = "correct_answer";
        final String INCORRECT_ANSWER = "incorrect_answers";

        List<QuizQuestion> questionsList = new ArrayList<>();

        JSONArray rootObjectArray = new JSONArray(response);


        for (int i = 0; i < rootObjectArray.length(); i++) {

            JSONObject rootObject = rootObjectArray.getJSONObject(i);
            JSONArray resultsArray = rootObject.getJSONArray(RESULTS);

            for (int j = 0; j < resultsArray.length(); j++) {
                QuizResult result = new QuizResult();
                JSONObject jsonQuestion = resultsArray.getJSONObject(j);
                QuizQuestion question = new QuizQuestion();

                result.setQuestion((String) jsonQuestion.get(QUESTION));
                result.setCategory(jsonQuestion.getString(CATEGORY));
                result.setCorrect_answer(jsonQuestion.getString(CORRECT_ANSWER));
                result.setDifficulty(jsonQuestion.getString(DIFFICULTY));
                result.setType(jsonQuestion.getString(TYPE));

                JSONArray jsonWrongAnswers = jsonQuestion.getJSONArray(INCORRECT_ANSWER);
                String[] incorrectAnswers = new String[jsonWrongAnswers.length()];

                for (int k = 0; k < jsonWrongAnswers.length(); k++) {
                    incorrectAnswers[k] = jsonWrongAnswers.getString(k);
                }
                result.setIncorrect_answers(incorrectAnswers);
                question.setResult(result);

                questionsList.add(question);
            }
        }

        return questionsList;
    }

    public HashMap<String, List<QuizQuestion>> getHashMapOfLists(List<QuizQuestion> questionList) {

        HashMap<String, List<QuizQuestion>> listMap = new HashMap<>();
        List<QuizQuestion> easyList = new ArrayList<>();
        List<QuizQuestion> mediumList = new ArrayList<>();
        List<QuizQuestion> hardList = new ArrayList<>();




        for (int i = 0; i < questionList.size(); i++) {

            QuizResult result = questionList.get(i).getResult();

            if (result.getDifficulty().equals(EASY_DIFFICULTY)) {
                easyList.add(questionList.get(i));
            } else if (result.getDifficulty().equals(MEDIUM_DIFFICULTY)) {
                mediumList.add(questionList.get(i));
            } else if (result.getDifficulty().equals(HARD_DIFFICULTY)) {
                hardList.add(questionList.get(i));
            }
        }

        listMap.put(EASY_DIFFICULTY, easyList);
        listMap.put(MEDIUM_DIFFICULTY, mediumList);
        listMap.put(HARD_DIFFICULTY, hardList);

        Log.i(TAG,easyList.toString());
        Log.i(TAG,mediumList.toString());
        Log.i(TAG,hardList.toString());
        Log.i(TAG,questionList.toString());


        return listMap;

    }

    public List<QuizQuestionsList> divideQuestionsToGroupOfTens(HashMap<String, List<QuizQuestion>> mapOfListsOfQuestions) {

        List<QuizQuestionsList> questionsListList = new ArrayList<>();

        List<QuizQuestion> easyList = mapOfListsOfQuestions.get(EASY_DIFFICULTY);
        List<QuizQuestion> medium = mapOfListsOfQuestions.get(MEDIUM_DIFFICULTY);
        List<QuizQuestion> hardList = mapOfListsOfQuestions.get(HARD_DIFFICULTY);

        questionsListList.addAll(divideListsOfQuestions(easyList));
        questionsListList.addAll(divideListsOfQuestions(medium));
        questionsListList.addAll(divideListsOfQuestions(hardList));

        for(int i = 0 ;i < questionsListList.size(); i++){
            Log.i("List of Lists","Name: "  +questionsListList.get(i).getTestName() + "Difficulty "  + questionsListList.get(i).getDifficulty() + "Number of Questions "  + questionsListList.get(i).getNumberOfQuestions());
        }

        return questionsListList;
    }

    private List<QuizQuestionsList> divideListsOfQuestions(List<QuizQuestion> questionList){

        List<QuizQuestionsList> questionsListList = new ArrayList<>();
        QuizQuestionsList list = new QuizQuestionsList();

        for(int i = 0, counter = 1, quizCounter = 1; i < questionList.size(); i++, counter++){

            list.getQuestionList().add(questionList.get(i));

            if(counter == 10){
                counter = 1;

                list.setTestName("Quiz " + quizCounter);
                quizCounter++;
                list.setDifficulty(questionList.get(i).getResult().getDifficulty());
                Log.i(TAG,"counter reached 10:" +  list.toString());

                questionsListList.add(list);

                list = new QuizQuestionsList();

            }else if (counter < 10 && i == questionList.size() - 1){

                if(questionsListList.isEmpty()){
                    list.setTestName("Quiz "+ quizCounter);
                    list.setDifficulty(questionList.get(i).getResult().getDifficulty());
                    questionsListList.add(list);
                }
                else{
                    questionsListList.get(quizCounter - 2).getQuestionList().addAll(list.getQuestionList());
                }
            }
        }
        return questionsListList;
    }
}
