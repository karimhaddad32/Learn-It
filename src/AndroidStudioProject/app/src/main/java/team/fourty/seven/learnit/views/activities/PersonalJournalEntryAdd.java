package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team.fourty.seven.learnit.R;

public class PersonalJournalEntryAdd extends MenuActivity implements View.OnClickListener
{
    private final String critical_thinking = "critical thinking";
    private final String law = "law";
    private final String coding = "coding";
    private final String technology = "technology";
    private final String painting = "painting";
    private final String sculpting = "sculpting";
    private final String music = "music";
    private final String self_defense = "self defense";

    private final int RESULT_CODE = 3;
    Button btnAdd;
    Button btnCancel;

    EditText editTxtJournalTitle;
    EditText editTxtJournalDescription;

    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_journal_entry_add);

        intent = getIntent();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        editTxtJournalTitle = (EditText) findViewById(R.id.editTxtJournalTitle);
        editTxtJournalDescription = (EditText) findViewById(R.id.editTxtJournalDescription);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId() == R.id.btnAdd)
        {

            if(!editTxtJournalTitle.getText().toString().equals(""))
            {

                String title = editTxtJournalTitle.getText().toString();
                String description = editTxtJournalDescription.getText().toString();
                String category = intent.getStringExtra("category");

                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("category", category);

                setResult(RESULT_CODE, intent);

                finish();

            }
            else
            {
                Toast.makeText(this, "Title cannot be blank!", Toast.LENGTH_LONG).show();
            }
        }
        else if (v.getId() == R.id.btnCancel)
        {
            Intent intentpj = new Intent(this, PersonalJournal.class);
            String category = intent.getStringExtra("category");

            if(category.equals(law))
            {
                intentpj.putExtra("tab", 1);
            }
            else if(category.equals(coding))
            {
                intentpj.putExtra("tab", 2);
            }
            else if(category.equals(technology))
            {
                intentpj.putExtra("tab", 3);
            }
            else if(category.equals(painting))
            {
                intentpj.putExtra("tab", 4);
            }
            else if(category.equals(sculpting))
            {
                intentpj.putExtra("tab", 5);
            }
            else if(category.equals(music))
            {
                intentpj.putExtra("tab", 6);
            }
            else if(category.equals(self_defense))
            {
                intentpj.putExtra("tab", 7);
            }
            startActivity(intentpj);
        }
    }
}
