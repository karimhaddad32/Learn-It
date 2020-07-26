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
import team.fourty.seven.learnit.views.adapters.EBookSilderViewHolder;

public class EBookSliderAdapter extends RecyclerView.Adapter<EBookSilderViewHolder> {

    List<EBook> eBooks;

    public EBookSliderAdapter(List<EBook> eBooks){this.eBooks = eBooks;}

    @NonNull
    @Override
    public EBookSilderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.item_image_list,parent,false);

        EBookSilderViewHolder viewHolder = new EBookSilderViewHolder(view, this,eBooks);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EBookSilderViewHolder eBookSilderViewHolder, int position) {
        EBook eBook = eBooks.get(position);

        eBookSilderViewHolder.setEBook(eBook);
    }

    @Override
    public int getItemCount() {
        return eBooks.size();
    }
}
