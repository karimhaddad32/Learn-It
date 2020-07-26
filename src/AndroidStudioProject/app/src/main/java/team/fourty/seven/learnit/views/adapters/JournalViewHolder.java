package team.fourty.seven.learnit.views.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.JournalEntry;

public class JournalViewHolder extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener
{

    JournalAdapter journalAdapter;

    private TextView title;
    private TextView description;
    private List<JournalEntry> journalEntries;
    private JournalEntry entry;



    public JournalViewHolder(View view, JournalAdapter journalAdapter, List<JournalEntry> journalEntryList)
    {

        super(view);

        this.journalAdapter = journalAdapter;
        this.journalEntries = journalEntryList;

        this.title = (TextView) view.findViewById(R.id.txtvJournalTitle);
        this.description = (TextView) view.findViewById(R.id.txtvJournalDescription);


        LinearLayout layoutEntry = (LinearLayout) itemView.findViewById(R.id.layoutEntry);

        layoutEntry.setOnCreateContextMenuListener(this);

    }



    public void setEntry(JournalEntry journalEntry)
    {

        this.entry = journalEntry;
        this.title.setText(journalEntry.getTitle());
        this.description.setText(journalEntry.getDescription());

    }



    public JournalEntry getEntry()
    {

        return entry;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {

        menu.add(this.getAdapterPosition(), R.id.itemEntryEdit, 0, "Edit");
        menu.add(this.getAdapterPosition(), R.id.itemEntryDelete, 1, "Delete");

    }
    
}
