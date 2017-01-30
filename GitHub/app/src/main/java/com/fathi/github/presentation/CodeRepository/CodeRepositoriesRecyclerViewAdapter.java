package com.fathi.github.presentation.CodeRepository;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fathi.github.R;
import com.fathi.github.data.model.CodeRepository;

import java.util.List;

public class CodeRepositoriesRecyclerViewAdapter extends RecyclerView.Adapter<CodeRepositoriesRecyclerViewAdapter.ViewHolder> {

    private List<CodeRepository> items;
    private CodeRepositoriesViewFragment.CodeRepositoriesItemListener mItemsClickListener;

    public CodeRepositoriesRecyclerViewAdapter(List<CodeRepository> items, CodeRepositoriesViewFragment.CodeRepositoriesItemListener listener) {
        this.items = items;
        this.mItemsClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_fork, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_no_fork, parent, false);
                break;
        }

        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        // return 0 or 1 depending on position
        return position % 2;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = items.get(position);
        holder.nameView.setText(items.get(position).getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemsClickListener) {
                    mItemsClickListener.onRepositoryClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void replaceData(List<CodeRepository> codeRepositories) {
        this.items = codeRepositories;
        notifyDataSetChanged();
    }

    public void addData(List<CodeRepository> codeRepositories) {

        for(CodeRepository entry : codeRepositories) {
            this.items.add(entry);
        }

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView nameView;
        public CodeRepository mItem;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            nameView = (TextView) view.findViewById(R.id.name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameView.getText() + "'";
        }
    }
}
