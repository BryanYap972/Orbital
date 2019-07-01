package com.example.orb;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class NewReviewActivity extends AppCompatActivity {

    private Spinner mUni_spinner;
    private EditText mDesc_editTxt;
    private Button mPost_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);
        mUni_spinner = (Spinner) findViewById(R.id.spinnerUni);
        mDesc_editTxt = (EditText) findViewById(R.id.editTextDesc);
        mPost_btn = (Button) findViewById(R.id.buttonPost);

        mPost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Review review = new Review();
                review.setReviewUni(mUni_spinner.getSelectedItem().toString());
                review.setReviewDesc(mDesc_editTxt.getText().toString());
                new FirebaseDatabaseHelper().addReview(review, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Review> reviews, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewReviewActivity.this,
                                "Review has been posted", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });


    }
}
