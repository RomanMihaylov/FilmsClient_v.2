package com.example.romanm.filmsclientv2.presentation.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.romanm.filmsclientv2.App;
import com.example.romanm.filmsclientv2.R;
import com.example.romanm.filmsclientv2.presentation.mvp.model.FilmDetailPresentation;
import com.example.romanm.filmsclientv2.presentation.mvp.presenters.filmDetail.baseInfo.BaseInfoPresenterImpl;
import com.example.romanm.filmsclientv2.presentation.mvp.views.BaseInfoView;
import com.example.romanm.filmsclientv2.data.source.remote.models.filmDetail.FilmDetail;
import com.example.romanm.filmsclientv2.utils.Api;

import javax.inject.Inject;


public class BaseInfoFragment extends MvpAppCompatFragment implements BaseInfoView {


    @Inject
    @InjectPresenter
    BaseInfoPresenterImpl presenter;

    @ProvidePresenter
    BaseInfoPresenterImpl providePresenter() {
        return presenter;
    }

    private static final String ARG_FILM = "ARG_FILM";

    private BaseInfoFragmentListener listener;

    private ImageView poster;

    private TextView title;

    private TextView description;

    private TextView date;


    private FilmDetailPresentation film;

    public BaseInfoFragment() {
        // Required empty public constructor
    }


    public static BaseInfoFragment newInstance(FilmDetailPresentation filmDetailPresentation) {
        BaseInfoFragment fragment = new BaseInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FILM, filmDetailPresentation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.plusBaseInfoComponent().inject(this);
        super.onCreate(savedInstanceState);
            film = getArguments().getParcelable(ARG_FILM);
        Log.d("testsfafad", "onCreate() called with: savedInstanceState = [" + film.getTitle() + "]");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_info_content, container, false);
        initFields(view);
        setInfo(film);
        return view;
    }



    private void initFields(View view) {
        poster = (ImageView) view.findViewById(R.id.image_poster_base_info);
        title = (TextView) view.findViewById(R.id.text_title_base_info);
        date = (TextView) view.findViewById(R.id.text_date_base_info);
        description = (TextView) view.findViewById(R.id.text_description_base_info);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseInfoFragmentListener) {
            listener = (BaseInfoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseInfoFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void setInfo(FilmDetailPresentation filmDetail) {
        title.setText(filmDetail.getTitle());
        description.setText(filmDetail.getOverview());
        date.setText(filmDetail.getReleaseDate());
        Glide.with(getContext())
                .load(Api.getPathPoster(filmDetail.getPosterPath()))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(poster);


    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        Log.d("dssdfsdfsfsdfsd", "dssdfsdfsfsdfsdonDestroy() called");
        super.onDestroy();
    }

    public interface BaseInfoFragmentListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}