package com.example.romanm.filmsclientv2.di.component;

import com.example.romanm.filmsclientv2.di.modules.SearchModule;
import com.example.romanm.filmsclientv2.di.scopes.SearchScope;
import com.example.romanm.filmsclientv2.presentation.mvp.presenters.SearchPresenter;

import dagger.Subcomponent;

/**
 * Created by RomanM on 27.11.2017.
 */
@SearchScope
@Subcomponent(modules = SearchModule.class)
public interface SearchComponent {

    SearchPresenter getPresenter();
}
