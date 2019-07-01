package com.example.orb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewConfig {
    private Context mContext;
    private ReviewsAdapter mReviewsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Review> reviews, List<String> keys) {
        mContext = context;
        mReviewsAdapter = new ReviewsAdapter(reviews, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mReviewsAdapter);
    }

    class ReviewItemView extends RecyclerView.ViewHolder {
        private TextView mUni;
        private TextView mDesc;

        private String key;

        public ReviewItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.review_list_item, parent, false));

            mUni = (TextView) itemView.findViewById(R.id.textViewUni);
            mDesc = (TextView) itemView.findViewById(R.id.textViewDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ReviewDetailsActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("reviewUni", mUni.getText().toString());
                    intent.putExtra("reviewDesc", mDesc.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Review review, String key) {
            mUni.setText(review.getReviewUni());
            mDesc.setText(review.getReviewDesc());
            this.key = key;
        }
    }

    class ReviewsAdapter extends RecyclerView.Adapter<ReviewItemView> {
        private List<Review> mReviewList;
        private List<String> mKeys;

        public ReviewsAdapter(List<Review> mReviewList, List<String> mKeys) {
            this.mReviewList = mReviewList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ReviewItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReviewItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReviewItemView holder, int position) {
            holder.bind(mReviewList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mReviewList.size();
        }
    }
}
