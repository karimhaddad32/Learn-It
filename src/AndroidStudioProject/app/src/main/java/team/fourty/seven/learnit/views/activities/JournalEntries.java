package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.controllers.JournalEntryController;
import team.fourty.seven.learnit.models.JournalEntry;
import team.fourty.seven.learnit.views.adapters.JournalAdapter;

public class JournalEntries extends MenuActivity implements View.OnClickListener
{

    private final String critical_thinking = "critical thinking";
    private final String law = "law";
    private final String coding = "coding";
    private final String technology = "technology";
    private final String painting = "painting";
    private final String sculpting = "sculpting";
    private final String music = "music";
    private final String self_defense = "self defense";
    private TextView skill_notes_title;

    private final int REQUEST_CODE = 1;
    private ImageButton btnAddEntry;

    private RecyclerView recyclerView;
    private JournalAdapter jAdapter;
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<JournalEntry> testEntries = new ArrayList<>();

    private JournalEntryController journalEntryController;

    private String indicator;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);

        indicator = getIntent().getStringExtra("category");



        btnAddEntry = (ImageButton) findViewById(R.id.btnAddEntry);
        recyclerView = (RecyclerView) findViewById(R.id.recvJournalEntries);
        skill_notes_title = (TextView) findViewById(R.id.journal_title);


        btnAddEntry.setOnClickListener(this);
        registerForContextMenu(recyclerView);



        journalEntryController = new JournalEntryController(this);



        journalEntries = journalEntryController.getEntries(indicator);

        jAdapter = new JournalAdapter(journalEntries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jAdapter);

        skill_notes_title.setText(indicator + " Notes");



        Log.i("journal entries",  jAdapter.getItemCount()+" entries in the journal");
        Toast.makeText(this,jAdapter.getItemCount()+" entries in the journal", Toast.LENGTH_LONG).show();

    }



    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        final int position = item.getGroupId();
        if(item.getItemId() == R.id.itemEntryDelete)
        {

            deleteEntry(position);
            jAdapter.deleteEntry(position);

        }
        else if(item.getItemId() == R.id.itemEntryEdit)
        {

            String title = journalEntries.get(position).getTitle();



            String description = journalEntries.get(position).getDescription();

            intent = new Intent(this, PersonalJournalEntryEdit.class);
            intent.putExtra("category", indicator);
            intent.putExtra("title", title);
            intent.putExtra("description", description);



            startActivityForResult(intent, REQUEST_CODE);

        }
        return super.onContextItemSelected(item);
    }



    public void deleteEntry(int position)
    {
        JournalEntry journalEntry = journalEntries.get(position);

        journalEntryController.removeEntry(journalEntry);

        Log.i("context menu position","entry deleted at position" + position);
        Toast.makeText(this,"Deleted entry at position " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v)
    {

        intent = new Intent(this, PersonalJournalEntryAdd.class);
        intent.putExtra("category", indicator);

        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(resultCode == 3)
        {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String category = data.getStringExtra("category");

            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setTitle(title);
            journalEntry.setDescription(description);
            journalEntry.setCategory(category);

            journalEntryController.createEntry(journalEntry);

            journalEntries = journalEntryController.getEntries(category);

            jAdapter = new JournalAdapter(journalEntries);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(jAdapter);

            Log.i("journal entries",  jAdapter.getItemCount()+" entries in the journal");
            Toast.makeText(this,jAdapter.getItemCount()+" entries in the journal", Toast.LENGTH_LONG).show();
        }
        else if(resultCode == 2)
        {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String category = data.getStringExtra("category");

            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setTitle(title);
            journalEntry.setDescription(description);
            journalEntry.setCategory(category);

            journalEntryController.editEntry(journalEntry);

            journalEntries = journalEntryController.getEntries(category);

            jAdapter = new JournalAdapter(journalEntries);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(jAdapter);

            Log.i("journal entries",  jAdapter.getItemCount()+" entries in the journal");
            Toast.makeText(this,jAdapter.getItemCount()+" entries in the journal", Toast.LENGTH_LONG).show();
        }
    }
}
