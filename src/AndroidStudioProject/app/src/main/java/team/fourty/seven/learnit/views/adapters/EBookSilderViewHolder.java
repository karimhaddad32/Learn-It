package team.fourty.seven.learnit.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.EBook;

public class EBookSilderViewHolder extends RecyclerView.ViewHolder {

    private EBookSliderAdapter eBookSliderAdapter;

    private ImageView imageViewEbook;
    private TextView txtTitle;

    private List<EBook> eBooks;
    private EBook eBook;

    public EBookSilderViewHolder(View itemView, EBookSliderAdapter adapter, List<EBook> eBookList) {
        super(itemView);

        eBooks = eBookList;
        eBookSliderAdapter = adapter;

        imageViewEbook = itemView.findViewById(R.id.imageViewSlideImage);
        txtTitle = itemView.findViewById(R.id.txtTitleSlide);

    }

    public void setEBook(EBook eBook){
        this.eBook = eBook;

        imageViewEbook.setImageBitmap(eBook.getPhoto());
        txtTitle.setText(eBook.getTitle());

    }




}
