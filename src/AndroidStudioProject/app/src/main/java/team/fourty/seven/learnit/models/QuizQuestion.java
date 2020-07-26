package team.fourty.seven.learnit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizQuestion implements Parcelable
{
    private QuizResult result;

    public QuizQuestion(){

    }



    public QuizQuestion(Parcel in) {
    }

    public static final Creator<QuizQuestion> CREATOR = new Creator<QuizQuestion>() {
        @Override
        public QuizQuestion createFromParcel(Parcel in) {
            return new QuizQuestion(in);
        }

        @Override
        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };

    public QuizResult getResult ()
    {
        return result;
    }

    public void setResult (QuizResult result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "\nQuizQuestion [ results = "+result+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
