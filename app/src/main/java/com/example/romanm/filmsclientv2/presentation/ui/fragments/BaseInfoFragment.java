package com.example.romanm.filmsclientv2.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.romanm.filmsclientv2.R;
import com.example.romanm.filmsclientv2.presentation.mvp.model.FilmDetailPresentation;
import com.example.romanm.filmsclientv2.utils.Api;


public class BaseInfoFragment extends MvpAppCompatFragment {

    private static final String ARG_FILM = "ARG_FILM";

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
        super.onCreate(savedInstanceState);
        film = getArguments().getParcelable(ARG_FILM);

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

    public void setInfo(FilmDetailPresentation filmDetail) {
        title.setText(filmDetail.title());
        description.setText(filmDetail.overview());
        date.setText(filmDetail.releaseDate());
        Glide.with(getContext())
                .load(Api.getPathPoster(filmDetail.posterPath()))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(poster);
    }
}
