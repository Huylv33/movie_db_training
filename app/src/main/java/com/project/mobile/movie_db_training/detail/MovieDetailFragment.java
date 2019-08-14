package com.project.mobile.movie_db_training.detail;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.data.model.Review;
import com.project.mobile.movie_db_training.data.model.Video;
import com.project.mobile.movie_db_training.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment implements MovieDetailContract.View,
        View.OnClickListener {
    @BindView(R.id.text_name)
    TextView mName;
    @BindView(R.id.text_overview)
    TextView mOverview;
    @BindView(R.id.image_backdrop)
    ImageView mBackDrop;
    @BindView(R.id.text_rating)
    TextView mRating;
    @BindView(R.id.text_release_date)
    TextView mReleaseDate;
    @BindView(R.id.rv_reviews)
    RecyclerView mReviewsRv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab_add)
    FloatingActionButton mFabAdd;
    @BindView(R.id.fab_favorite)
    FloatingActionButton mFabFavorite;
    public static final String TAG = MovieDetailFragment.class.getSimpleName();
    private ReviewsListAdapter mReviewsAdapter;
    private Unbinder mUnbinder;
    private Movie mMovie;
    private MovieDetailContract.Presenter mPresenter;
    private List<Video> mVideos;
    private List<Review> mReviews = new ArrayList<>();

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment getInstance(@NonNull Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE_KEY, movie);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        setToolbar();
        initReviewsLayout();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new MovieDetailPresenterImpl(getContext());
        mPresenter.setView(this);
        if (getArguments() != null) {
            Movie movie = getArguments().getParcelable(Constants.MOVIE_KEY);
            if (movie != null) {
                mMovie = movie;
                showDetail(mMovie);
                mPresenter.fetchVideos(mMovie.getId());
                mPresenter.fetchReviews(mMovie.getId());
            }
        } else {
            // get latest movie
            mPresenter.fetchLatestMovie();
        }
    }

    private void setToolbar() {
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(true);
            }
            mToolbar.setNavigationOnClickListener(view -> {
                getActivity().finish();
            });
        }
    }

    private void initReviewsLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mReviewsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (totalItemCount == lastVisibleItem + 1) {
                    if (mMovie != null) {
                        mPresenter.loadMoreReviews(String.valueOf(mMovie.getId()));
                    }
                }
            }
        });
        mReviewsRv.setLayoutManager(linearLayoutManager);
        mReviewsAdapter = new ReviewsListAdapter(mReviews);
        mReviewsRv.setAdapter(mReviewsAdapter);
    }

    @Override
    public void showDetail(Movie movie) {
        this.mMovie = movie;
        Picasso.get().load(Constants.IMAGE_BASE_URL + "w400/" + movie.getBackdropPath())
                .into(mBackDrop);
        mName.setText(movie.getTitle());
        mOverview.setText(movie.getOverview());
        mRating.setText(String.format(getString(R.string.rating), movie.getVoteAverage()));
        mReleaseDate.setText(movie.getReleaseDate());
    }

    @Override
    public void showTrailers(List<Video> videos) {
        mVideos = videos;
        if (getActivity() != null) {
            ViewGroup horizontalTrailers = getActivity().findViewById(R.id.horizontal_trailers);
            ViewGroup trailers = horizontalTrailers.findViewById(R.id.trailers);
            horizontalTrailers.setVisibility(View.VISIBLE);
            LayoutInflater vi = (LayoutInflater) getActivity().getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (vi != null) {
                for (int i = 0; i < videos.size(); i++) {
                    View view = vi.inflate(R.layout.video, null);
                    ImageView imageView = view.findViewById(R.id.image_thumb);
                    Picasso.get().load(Constants.IMAGE_THUMB_URL +
                            videos.get(i).getKey() + "/default.jpg").into(imageView);
                    imageView.setTag(videos.get(i).getId());
                    imageView.setOnClickListener(this);
                    trailers.addView(view);
                }
            }
        }
    }

    private void onImageThumbClick(View view) {
        String videoUrl = "https://youtube.com/watch?v=" + view.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        if (getContext() != null) getContext().startActivity(intent);
    }
    private void onFabFavoriteClick() {
        mPresenter.onFabFavoriteClick(mMovie);
    }
    @Override
    public void showReviews(List<Review> reviews) {
        mReviews.addAll(reviews);
        mReviewsRv.setVisibility(View.VISIBLE);
        mReviewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFavorite() {
        mFabFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_active_24dp));
    }

    @Override
    public void showUnFavorite() {
        mFabFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
    }
    @OnClick({R.id.fab_favorite})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_thumb:
                onImageThumbClick(view);
                break;
            case R.id.fab_favorite:
                onFabFavoriteClick();
            default:
                break;
        }
    }

    @Override
    public void loadingStart() {
        Snackbar.make(mReviewsRv, "Loading...", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void loadingFail(String message) {
        Snackbar.make(mReviewsRv, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder = null;
        mPresenter.destroy();
    }
}
