package team.fourty.seven.learnit.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.EBook;

public class EBookAdapter extends RecyclerView.Adapter<EBookViewHolder> {

    private List<EBook> eBookList;

    public EBookAdapter(List<EBook> eBookList){
        this.eBookList = eBookList;
    }


    @Override
    public EBookViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.item_ebook_list,parent,false);

        EBookViewHolder viewHolder = new EBookViewHolder(view, this,eBookList);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EBookViewHolder eBookViewHolder, int position) {
        EBook eBook = eBookList.get(position);

        eBookViewHolder.setEBook(eBook);
    }

    @Override
    public int getItemCount() {
        return eBookList.size();
    }
}
