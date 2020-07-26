package team.fourty.seven.learnit.views.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.EBook;
import team.fourty.seven.learnit.views.adapters.EBookAdapter;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.views.adapters.EBookViewHolder;

public class EBooksActivity extends MenuActivity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter<EBookViewHolder> adapter;

    private RecyclerView.LayoutManager layoutManager;

    private String category;

    SkillController skillController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebooks);

        this.recyclerView = (RecyclerView) findViewById(R.id.eBookRecyclerView);

        this.layoutManager = new LinearLayoutManager(this);

        skillController = new SkillController(this);

        List<EBook> eBookList = null;
        try {
            eBookList = getEbooks();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.adapter = new EBookAdapter(eBookList);

        this.recyclerView.setLayoutManager(layoutManager);

        this.recyclerView.setAdapter(adapter);

    }

    private List<EBook> getEbooks() throws MalformedURLException {

        List<EBook> eBookList = new ArrayList<>();

        category = getIntent().getStringExtra("category");

        eBookList = skillController.getSkillEBooks(category);

        return eBookList;

    }
}
