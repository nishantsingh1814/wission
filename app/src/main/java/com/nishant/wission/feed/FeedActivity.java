package com.nishant.wission.feed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nishant.wission.R;

import java.util.ArrayList;
import java.util.Random;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView mFeedRv;
    private FeedAdapter mFeedAdapter;
    private ArrayList<Feed> mFeedList;
    private ArrayList<Feed> mDummyList;
    private Random random;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FloatingActionButton mTopFab;
    private RecyclerView.SmoothScroller smoothScroller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        initialize();
        smoothScroller = new LinearSmoothScroller(FeedActivity.this) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        mTopFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smoothScroller.setTargetPosition(0);

                mFeedRv.getLayoutManager().startSmoothScroll(smoothScroller);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                addItemsToTop();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mFeedRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    addItems();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0)
                    mTopFab.hide();
                else if (dy > 0)
                    mTopFab.show();
            }
        });
    }

    private void addItemsToTop() {
        for (int i = 0; i < 10; i++) {
            mFeedList.add(0, mDummyList.get(random.nextInt(20)));
        }
        mFeedAdapter.notifyDataSetChanged();
    }

    private void initialize() {
        mTopFab = findViewById(R.id.move_o_top_fab);
        mTopFab.hide();
        mFeedRv = findViewById(R.id.feeds_rv);
        mFeedList = new ArrayList<>();
        mFeedAdapter = new FeedAdapter(this, mFeedList);
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);

        mFeedRv.setLayoutManager(new LinearLayoutManager(this));
        mFeedRv.setAdapter(mFeedAdapter);

        random = new Random();
        mDummyList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        fillDummyList();
        addItems();
    }

    private void addItems() {
        for (int i = 0; i < 10; i++) {
            mFeedList.add(mDummyList.get(random.nextInt(20)));
        }
        mFeedAdapter.notifyDataSetChanged();
    }

    private void fillDummyList() {
        mDummyList.add(new Feed("https://i.ytimg.com/vi/lLVQrRaN-KY/hqdefault.jpg", "1234", "CHOTU KI TEEN PATTI","lLVQrRaN-KY"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/Nw0ArNPuLYM/hqdefault.jpg", "24354", "Must Watch Funny\uD83D\uDE02 \uD83D\uDE02Comedy Videos 2018","Nw0ArNPuLYM"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/YaNc70hTmAI/hqdefault.jpg", "24354", "Bin bulaye baraati comedy | Rajpal yadav comedy | vijay raaz | sanjay mishra","YaNc70hTmAI"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/w6S48rHydtM/hqdefault.jpg", "24354", "Best of Akshay Kumar's Comedy Scenes !!!","w6S48rHydtM"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/Bei-7Qtg7co/hqdefault.jpg", "24354", "CHOTU Ki Comedy | 2018 New Hindi Comedy | Khandesh Comedy","Bei-7Qtg7co"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/e0L7CmN0uTY/hqdefault.jpg", "24354", "CHOTU SANJU | Sanju Movie Spoof || Khandesh Comedy Video 2018","e0L7CmN0uTY"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/atiJR1LLJzg/hqdefault.jpg", "24354", "CHOTU ka Rajnikant Style","atiJR1LLJzg"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/iNs_ZktmPsI/hqdefault.jpg", "24354", "Most watch Funny\uD83D\uDE02 \uD83D\uDE02comedy videos 2018 - Episode 12","iNs_ZktmPsI"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/4oZaTi8y_WY/hqdefault.jpg", "24354", "Chota baccha samajha kar panga mat lena comedy video","4oZaTi8y_WY"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/j04NTYNNYUg/hqdefault.jpg", "432536", "Funny Videos NEW 2017 \uD83D\uDE02 Top Funny Chinese Comedy P2","j04NTYNNYUg"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/Yp0kth7-zsM/hqdefault.jpg", "13231", "Best of \"FRIENDS\" - All seasons","Yp0kth7-zsM"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/8mP5xOg7ijs/hqdefault.jpg", "12121", "Top 15 Funniest Friends Moments","8mP5xOg7ijs"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/dhN1loL1cvU/hqdefault.jpg", "2344", "Friends   Funny Moments All Seasons ✔","dhN1loL1cvU"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/4aGRV1lMdeU/hqdefault.jpg", "24", "Friends Top 10 Funniest Scenes","4aGRV1lMdeU"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/Y18Kw2YiTNE/hqdefault.jpg", "24", "Friends Movie Comedy Scenes","Y18Kw2YiTNE"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/T2I_O6OIzRw/hqdefault.jpg", "2343", "Friends   Best of All Seasons Hilarious Moments","T2I_O6OIzRw"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/Km0Kxhvsn_o/hqdefault.jpg", "133", "friends Joey comedy scenes season5","Km0Kxhvsn_o"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/5HoapBYe7cE/hqdefault.jpg", "32435", "Best of Friends - All Seasons","5HoapBYe7cE"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/hT-8Yz1JsdQ/hqdefault.jpg", "132", "Friends - Funniest Moments","hT-8Yz1JsdQ"));
        mDummyList.add(new Feed("https://i.ytimg.com/vi/I6ES6opeHYI/hqdefault.jpg", "121", "Ladke Dost | Stand-up Comedy by Abijit Ganguly","I6ES6opeHYI"));

    }


}
