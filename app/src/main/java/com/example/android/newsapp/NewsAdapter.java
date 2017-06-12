package com.example.android.newsapp;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hp on 08/06/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private static final String DATE_SEPARATOR = "T";
    private static OnItemClickListener Listener;
    List<News> mNews;
    NewsActivity mContext;

    // Construct a new NewsAdapter
    public NewsAdapter(NewsActivity context, List<News> news, OnItemClickListener listener) {
        this.mNews = news;
        this.mContext = context;
        this.Listener = listener;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(listItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int position) {
        final News currentNews = mNews.get(position);

        // Populate the ViewHolder
        viewHolder.category.setText(currentNews.getCategory());
        int categoryColor = getCategoryColor(currentNews.getCategory());
        viewHolder.category.setBackgroundColor(categoryColor);

        viewHolder.title.setText(currentNews.getTitle());

        // Remove time information from the date String
        String formattedDate;
        String unformattedDate = currentNews.getPublicationDate();
        if (unformattedDate.contains(DATE_SEPARATOR)) {
            String[] parts = unformattedDate.split(DATE_SEPARATOR);
            formattedDate = parts[0];
            viewHolder.publicationDate.setText(formattedDate);
        } else {
            viewHolder.publicationDate.setText(currentNews.getPublicationDate());
        }

        viewHolder.bind(mNews.get(position), Listener);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    // Set colors for categories
    private int getCategoryColor(String category) {
        int categoryColorResourceId;
        switch (category) {
            case "Sport":
                categoryColorResourceId = R.color.sport;
                break;
            case "Politics":
                categoryColorResourceId = R.color.politics;
                break;
            case "Film":
                categoryColorResourceId = R.color.film;
                break;
            case "Economics":
                categoryColorResourceId = R.color.economics;
                break;
            case "Books":
                categoryColorResourceId = R.color.books;
                break;
            case "Recipes":
                categoryColorResourceId = R.color.recipes;
                break;
            case "Music":
                categoryColorResourceId = R.color.music;
                break;
            case "Health":
                categoryColorResourceId = R.color.health;
                break;
            case "Tech":
                categoryColorResourceId = R.color.tech;
                break;
            default:
                categoryColorResourceId = R.color.science;
                break;
        }
        return ContextCompat.getColor(this.mContext, categoryColorResourceId);
    }

    void clear() {
        mNews.clear();
        notifyDataSetChanged();
    }

    void addAll(List<News> news) {
        mNews.addAll(news);
        notifyDataSetChanged();
    }

    // Create an onItemClickListener for the RecyclerView
    interface OnItemClickListener {
        void onItemClick(News news);
    }

    // Create the custom ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        TextView title;
        TextView publicationDate;

        public ViewHolder(View listItemView) {
            super(listItemView);
            category = (TextView) listItemView.findViewById(R.id.category);
            title = (TextView) listItemView.findViewById(R.id.title_text_view);
            publicationDate = (TextView) listItemView.findViewById(R.id.date);
        }

        // Bind the onItemClickListener to the RecyclerView
        void bind(final News news, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(news);
                }
            });
        }
    }
}
