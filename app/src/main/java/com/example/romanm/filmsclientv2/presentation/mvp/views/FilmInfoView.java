package com.example.romanm.filmsclientv2.presentation.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.romanm.filmsclientv2.presentation.mvp.model.FilmDetailPresentation;

/**
 * Created by RomanM on 28.10.2017.
 */

public interface FilmInfoView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setFilmInfo(FilmDetailPresentation film);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setItemViewPager(FilmDetailPresentation film);
}
