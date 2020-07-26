package team.fourty.seven.learnit.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.EBook;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.views.adapters.EBookAdapter;

import static team.fourty.seven.learnit.R.string.*;

public class EBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final String TAG = this.getClass().getSimpleName();

    private EBookAdapter eBookAdapter;

    private ImageView bookImage;
    private TextView txtBookTitle;
    private TextView txtBookAuthor;
    private TextView txtBookDescription;
    private Button btnRead;
    private CheckBox checkBoxFavorites;
    private CheckBox checkBoxMarkAsRead;
    private List<EBook> eBookList;

    private EBook eBook;
    SkillController controller;

    public EBookViewHolder(View itemView, EBookAdapter adapter, List<EBook> eBookList) {
        super(itemView);


        this.eBookList = eBookList;
        this.eBookAdapter = adapter;
        //Views
        this.bookImage = itemView.findViewById(R.id.imageEBook);
        this.txtBookTitle = itemView.findViewById(R.id.eBookTitle);
        this.txtBookAuthor = itemView.findViewById(R.id.eBookAuthor);
        this.txtBookDescription = itemView.findViewById(R.id.eBookDescription);
        this.btnRead = itemView.findViewById(R.id.btnRead);
        this.checkBoxFavorites = itemView.findViewById(R.id.checkboxFavorites);
        this.checkBoxMarkAsRead = itemView.findViewById(R.id.checkboxMarkAsRead);



        btnRead.setOnClickListener(this);
        checkBoxFavorites.setOnClickListener(this);
        checkBoxMarkAsRead.setOnClickListener(this);


    }

    public void setEBook(EBook eBook){

        this.eBook = eBook;
        this.bookImage.setImageBitmap(eBook.getPhoto());
        this.txtBookTitle.setText(eBook.getTitle());
        this.txtBookAuthor.setText(itemView.getResources().getString(author) + "  " + eBook.getAuthor());
        this.txtBookDescription.setText(eBook.getDescription());
        this.checkBoxFavorites.setChecked(eBook.isFavourited());
        this.checkBoxMarkAsRead.setChecked(eBook.isRead());

        Log.i(TAG, eBook.getTitle() + "\nFavorites: " + eBook.isFavourited() + " is Read: "+ eBook.isRead());

    }

    @Override
    public void onClick(View view) {
        //Launch ebook.getURL();
        Context context = view.getContext();
        int position = getAdapterPosition();
        controller = new SkillController(view.getContext());


        switch (view.getId()){
            case R.id.btnRead:

                Uri uri = eBookList.get(position).getUri();

                if(uri.equals(Uri.parse("Unknown"))){
                    Toast.makeText(context,eBook.getTitle() + " is not available!" + position,Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    context.startActivity(intent);
                }
                break;
            case R.id.checkboxFavorites:
                if(!checkBoxFavorites.isChecked()){
                    checkBoxFavorites.setSelected(true);
                }else
                    checkBoxFavorites.setSelected(false);
                eBook.setFavourited(checkBoxFavorites.isChecked());
                controller.updateEBooks(eBook);
                break;
            case R.id.checkboxMarkAsRead:
                if(!checkBoxMarkAsRead.isChecked()){
                    checkBoxMarkAsRead.setSelected(true);
                }else
                    checkBoxMarkAsRead.setSelected(false);
                eBook.setRead(checkBoxMarkAsRead.isChecked());
                controller.updateEBooks(eBook);
                break;
                default:
                    break;
        }


    }




}
