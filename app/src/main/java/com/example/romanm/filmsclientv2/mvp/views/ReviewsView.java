package com.example.romanm.filmsclientv2.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.example.romanm.filmsclientv2.pojo.Reviews;

import java.util.List;

/**
 * Created by RomanM on 29.10.2017.
 */

public interface ReviewsView extends MvpView {

    void showReviews(List<Reviews> reviews);

}
