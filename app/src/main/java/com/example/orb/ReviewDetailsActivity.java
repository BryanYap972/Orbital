package com.example.orb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ReviewDetailsActivity extends AppCompatActivity {
    private Spinner mUni_spinner;
    private EditText mDesc_editTxt;
    private Button mUpdate_btn;
    private Button mDelete_btn;

    private String key;
    private String reviewUni;
    private String reviewDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        key = getIntent().getStringExtra("key");
        reviewUni = getIntent().getStringExtra("reviewUni");
        reviewDesc = getIntent().getStringExtra("reviewDesc");

        mUni_spinner = (Spinner) findViewById(R.id.spinnerUni);
        mDesc_editTxt = (EditText) findViewById(R.id.editTextDesc);
        mDesc_editTxt.setText(reviewDesc);

        mUni_spinner.setSelection(getIndex_SpinnerItem(mUni_spinner, reviewUni));

        mUpdate_btn = (Button) findViewById(R.id.buttonUpdate);
        mDelete_btn = (Button) findViewById(R.id.buttonDelete);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Review review = new Review();
                review.setReviewDesc(mDesc_editTxt.getText().toString());
                review.setReviewUni(mUni_spinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().updateReview(key, review, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Review> reviews, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(ReviewDetailsActivity.this,
                                "Review has been updated", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteReview(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Review> reviews, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(ReviewDetailsActivity.this,
                                "Review has been deleted", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });


    }

    private int getIndex_SpinnerItem(Spinner spinner, String item) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
