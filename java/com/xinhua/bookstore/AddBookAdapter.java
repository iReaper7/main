package com.xinhua.bookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xinhua.bookstore.Table.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookAdapter extends RecyclerView.Adapter<AddBookAdapter.ViewHolder> {

    private List<Category> categoryList = new ArrayList<>();
    //用来记录所有checkbox的状态
    private Map<String, Boolean> checkStatus = new HashMap<>();

    public Map<String, Boolean> getCheckStatus() {
        return checkStatus;
    }

    public AddBookAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_book_category_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryTextView.setText(category.getCategory());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkStatus.put(category.getCategory(), isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View categoryView;
        TextView categoryTextView;
        CheckBox checkBox;
        public ViewHolder(@NonNull View view) {
            super(view);
            categoryView = view;
            categoryTextView = view.findViewById(R.id.add_book_category);
            checkBox = view.findViewById(R.id.add_book_checkbox);
        }
    }
}
