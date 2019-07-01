package com.example.orb;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

        setTitle("Xchange Buddies");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewReviews);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Reviews");

        new FirebaseDatabaseHelper().readReviews(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Review> reviews, List<String> keys) {
                new RecyclerViewConfig().setConfig(mRecyclerView, ReviewPage.this,
                        reviews, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reviewlist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post_review:
                startActivity(new Intent(this, NewReviewActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}