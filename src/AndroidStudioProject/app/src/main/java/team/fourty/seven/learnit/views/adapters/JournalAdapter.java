package team.fourty.seven.learnit.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.JournalEntry;

public class JournalAdapter extends RecyclerView.Adapter<JournalViewHolder>
{

    private List<JournalEntry> journalEntries;
    private int viewPosition;




    public JournalAdapter(List<JournalEntry> journalEntries)
    {

        this.journalEntries = journalEntries;

    }

    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context context = parent.getContext();

        View v = LayoutInflater.from(context).inflate(R.layout.journal_entry_item, parent, false);

        JournalViewHolder journalViewHolder = new JournalViewHolder(v, this, journalEntries);

        return journalViewHolder;

    }

    @Override
    public void onBindViewHolder(final JournalViewHolder holder, int position)
    {

        JournalEntry journalEntry = journalEntries.get(position);

        holder.setEntry(journalEntry);

    }

    @Override
    public int getItemCount()
    {
        return journalEntries.size();
    }



    public void deleteEntry(int position)
    {
        this.journalEntries.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position,this.journalEntries.size());
    }

    public JournalEntry getEntry(int position)
    {
        return this.journalEntries.get(position);
    }



    public int getViewPosition()
    {

        return viewPosition;

    }

    public void setViewPosition(int viewPosition)
    {

        this.viewPosition = viewPosition;

    }

}
