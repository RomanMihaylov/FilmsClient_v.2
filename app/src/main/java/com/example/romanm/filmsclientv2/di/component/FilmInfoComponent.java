package com.example.romanm.filmsclientv2.di.component;

import com.example.romanm.filmsclientv2.di.scopes.FilmInfoScope;
import com.example.romanm.filmsclientv2.presentation.ui.fragments.FilmDetailFragment;

import dagger.Subcomponent;

/**
 * Created by RomanM on 15.11.2017.
 */

@FilmInfoScope
@Subcomponent
public interface FilmInfoComponent {

    void inject(FilmDetailFragment filmDetailFragment);

    BaseInfoComponent plusBaseInfoComponent();


}
