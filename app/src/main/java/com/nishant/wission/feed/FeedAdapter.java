package com.nishant.wission.feed;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nishant.wission.R;
import com.nishant.wission.preferences.Preferences;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<Feed> mFeedsList;
    private SparseBooleanArray mVisibilityArray;

    public FeedAdapter(Context mContext, ArrayList<Feed> mFeedsList) {
        this.mContext = mContext;
        this.mFeedsList = mFeedsList;
        mVisibilityArray = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ProfileViewHolder(LayoutInflater.from(mContext).inflate(R.layout.profile, parent, false));
        } else {
            return new FeedViewHolder(LayoutInflater.from(mContext).inflate(R.layout.feed_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


        if (holder.getItemViewType() == 0) {
            ProfileViewHolder profileViewHolder = (ProfileViewHolder) holder;
            if (Preferences.getProfilePicLang(mContext) != null) {
                Glide.with(mContext).load(Preferences.getProfilePicLang(mContext)).into(profileViewHolder.mProfileIv);
            }
            profileViewHolder.mGenderTv.setText(Preferences.getGenderLang(mContext));
            profileViewHolder.mAgeTv.setText(Preferences.getAgeLang(mContext));
            profileViewHolder.mNameTv.setText(Preferences.getNameLang(mContext));
            profileViewHolder.mEmailTv.setText(Preferences.getEmailLang(mContext));

        } else {
            final FeedViewHolder feedHolder = (FeedViewHolder) holder;
            if (mVisibilityArray.get(position)) {
                feedHolder.mCommentLayout.setVisibility(View.VISIBLE);
                feedHolder.mCommentEt.setText("");
            } else {
                feedHolder.mCommentLayout.setVisibility(View.GONE);
            }
            feedHolder.mCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (feedHolder.mCommentLayout.getVisibility() == View.GONE) {
                        mVisibilityArray.put(position, true);
                        feedHolder.mCommentLayout.setVisibility(View.VISIBLE);
                        feedHolder.mCommentEt.setText("");
                    } else {
                        mVisibilityArray.delete(position);
                        feedHolder.mCommentLayout.setVisibility(View.GONE);
                    }
                }
            });
            feedHolder.mAddCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (feedHolder.mCommentEt.getText().toString().trim().length() > 0) {
                        Snackbar.make(view, "Comment added", Snackbar.LENGTH_SHORT).show();
                        feedHolder.mCommentLayout.setVisibility(View.GONE);
                    } else {
                        Snackbar.make(view, "Add comment ..", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
            feedHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + mFeedsList.get(position).getUrlKey()));
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + mFeedsList.get(position).getUrlKey()));
                    try {
                        mContext.startActivity(appIntent);
                    } catch (ActivityNotFoundException ex) {
                        mContext.startActivity(webIntent);
                    }
                }
            });
            Glide.with(mContext).load(mFeedsList.get(position).getThumbnail()).into(feedHolder.mThumbnailIv);
            feedHolder.mLikesTv.setText(mFeedsList.get(position).getLikes());
            feedHolder.mTitleTv.setText(mFeedsList.get(position).getTitle());
        }


    }

    @Override
    public int getItemCount() {
        return mFeedsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {

        private ImageView mThumbnailIv;
        private TextView mTitleTv;
        private TextView mLikesTv;
        private Button mLikeBtn;
        private Button mCommentBtn;
        private EditText mCommentEt;
        private Button mAddCommentBtn;
        private LinearLayout mCommentLayout;

        FeedViewHolder(View itemView) {
            super(itemView);

            mThumbnailIv = itemView.findViewById(R.id.thumbnail_iv);
            mTitleTv = itemView.findViewById(R.id.title_tv);
            mLikeBtn = itemView.findViewById(R.id.like_btn);
            mLikesTv = itemView.findViewById(R.id.likes_tv);
            mCommentBtn = itemView.findViewById(R.id.comment_btn);
            mCommentEt = itemView.findViewById(R.id.comment_et);
            mAddCommentBtn = itemView.findViewById(R.id.add_comment_btn);
            mCommentLayout = itemView.findViewById(R.id.comment_layout);
        }
    }

    private class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView mEmailTv;
        private TextView mNameTv;
        private TextView mAgeTv;
        private TextView mGenderTv;
        private ImageView mProfileIv;


        public ProfileViewHolder(View itemView) {
            super(itemView);
            mEmailTv = itemView.findViewById(R.id.email_tv);
            mNameTv = itemView.findViewById(R.id.name_tv);
            mAgeTv = itemView.findViewById(R.id.age_tv);
            mGenderTv = itemView.findViewById(R.id.gender_tv);
            mProfileIv = itemView.findViewById(R.id.profile_iv);
        }

    }
}
