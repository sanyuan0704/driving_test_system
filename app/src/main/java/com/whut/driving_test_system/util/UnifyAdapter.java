package com.whut.driving_test_system.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 通用 Adapter
 * 适用列表开发场景
 * @param <T>
 */
public abstract class UnifyAdapter<T> extends RecyclerView.Adapter<UnifyAdapter.ViewHolder> {
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private List<T> list;
    private int layoutId;
    private int variableId;

    public UnifyAdapter(int layoutId, int variableId, List<T> list) {
        this.list = list;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UnifyAdapter.ViewHolder holder, int position) {
        convert(holder, list.get(position));
    }

    public abstract void convert(ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }


        public ViewHolder setBinding(int variableId , Object object){
            binding.setVariable(variableId , object);
            binding.executePendingBindings();
            return this;
        }
    }

}
