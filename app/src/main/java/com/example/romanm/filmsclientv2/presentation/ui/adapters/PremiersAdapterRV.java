package com.example.romanm.filmsclientv2.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.romanm.filmsclientv2.R;
import com.example.romanm.filmsclientv2.presentation.mvp.model.FilmPresentation;
import com.example.romanm.filmsclientv2.utils.Api;

import java.util.ArrayList;
import java.util.List;


public class PremiersAdapterRV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SEARCH = 0;
    public static final int PREMIERS = 1;

    private final static int TYPE_ITEM = 0;
    private final static int TYPE_FOOTER = 1;

    private PremiersAdapterListener listener;
    private Context context;

    private List<FilmPresentation> list = new ArrayList<>();
    private int state;


    public PremiersAdapterRV(Context context, int state, List<FilmPresentation> movies, PremiersAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.state = state;
    }

    @Override
    public int getItemViewType(int position) {
        if (state == PREMIERS) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        } else {
            return TYPE_ITEM;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == TYPE_ITEM) {
            view = inflater.inflate(R.layout.item_film_card_max, parent, false);
            return new ViewHolderMainScreen(view);
        } else {
            view = inflater.inflate(R.layout.item_footer, parent, false);
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderMainScreen) {
            ViewHolderMainScreen holderMainScreen = (ViewHolderMainScreen) holder;
            holderMainScreen.bindTo(list.get(position), holderMainScreen);
        } else if (position == getItemCount() - 1 && getItemCount() > 1) {
            listener.loadMore();
        }

    }

    @Override
    public int getItemCount() {
        int itemCount = list.size();
        if (state == PREMIERS) {
            //footer
            itemCount++;
            return itemCount;
        } else {
            return itemCount;
        }
    }

    public void setMovies(List<FilmPresentation> movies) {

        list.addAll(movies);
        notifyItemRangeChanged(list.size() - 20, list.size());
    }

    public void setSearchMovies(List<FilmPresentation> movies) {
        list.clear();
        list.addAll(movies);
        notifyDataSetChanged();
    }

    public interface PremiersAdapterListener {
        void onItemClick(int idFilm);

        void loadMore();
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderMainScreen extends RecyclerView.ViewHolder {
        public ImageView poster;
        public TextView tittle;
        public TextView description;
        TextView date;
        TextView rating;

        public ViewHolderMainScreen(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.recycler_film_card_poster);
            tittle = (TextView) itemView.findViewById(R.id.recycler_film_card_tittle);
            description = (TextView) itemView.findViewById(R.id.recycler_film_card_description);
            rating = (TextView) itemView.findViewById(R.id.rating);
            date = (TextView) itemView.findViewById(R.id.date_card_film);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(list.get(getAdapterPosition()).getId());
                }
            });
        }

        public void bindTo(FilmPresentation item, ViewHolderMainScreen holder) {
            tittle.setText(item.getTitle());
            if (item.getOverview().length() > 74) {
                description.setText(item.getOverview().subSequence(0, 75) + "...");
            } else {
                description.setText(item.getOverview());
            }
            rating.setText(String.valueOf(item.getVoteAverage()));
            date.setText(item.getReleaseDate());
            Glide.with(context)
                    .load(Api.getPathPoster(item.getPosterPath()))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(new BitmapImageViewTarget(holder.poster));

            Log.v("itemMovie", item.getTitle());

        }
    }
}
