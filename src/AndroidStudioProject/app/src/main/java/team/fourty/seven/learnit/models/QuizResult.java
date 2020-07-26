package team.fourty.seven.learnit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizResult implements Parcelable
{
    private String difficulty;

    private String question;

    private String correct_answer;

    private String[] incorrect_answers;

    private String category;

    private String type;

    public QuizResult(){

    }

    public QuizResult(Parcel in) {
        difficulty = in.readString();
        question = in.readString();
        correct_answer = in.readString();
        incorrect_answers = in.createStringArray();
        category = in.readString();
        type = in.readString();
    }

    public static final Creator<QuizResult> CREATOR = new Creator<QuizResult>() {
        @Override
        public QuizResult createFromParcel(Parcel in) {
            return new QuizResult(in);
        }

        @Override
        public QuizResult[] newArray(int size) {
            return new QuizResult[size];
        }
    };

    public String getDifficulty ()
    {
        return difficulty;
    }

    public void setDifficulty (String difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getQuestion ()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    public String getCorrect_answer ()
    {
        return correct_answer;
    }

    public void setCorrect_answer (String correct_answer)
    {
        this.correct_answer = correct_answer;
    }

    public String[] getIncorrect_answers ()
    {
        return incorrect_answers;
    }

    public void setIncorrect_answers (String[] incorrect_answers)
    {
        this.incorrect_answers = incorrect_answers;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "QuizResults [difficulty = "+difficulty+", question = "+question+", correct_answer = "+correct_answer+", incorrect_answers = "+incorrect_answers+", category = "+category+", type = "+type+"]";
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(difficulty);
        dest.writeString(question);
        dest.writeString(correct_answer);
        dest.writeStringArray(incorrect_answers);
        dest.writeString(category);
        dest.writeString(type);
    }
}
