package com.italankin.adapterdelegate.sample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.italankin.adapterdelegate.BaseAdapterDelegate;
import com.italankin.adapterdelegate.sample.R;
import com.italankin.adapterdelegate.sample.bean.FitnessCenter;

public class FitnessCenterAdapterDelegate extends BaseAdapterDelegate<FitnessCenterViewHolder, FitnessCenter> {
    private final Listener listener;

    public FitnessCenterAdapterDelegate(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_fitness_center;
    }

    @NonNull
    @Override
    protected FitnessCenterViewHolder createViewHolder(View itemView) {
        final FitnessCenterViewHolder holder = new FitnessCenterViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onFitnessCenterItemClick(pos, getAdapter().getItem(pos));
                    }
                }
            }
        });
        return holder;
    }

    @Override
    public void onBind(FitnessCenterViewHolder holder, int position, FitnessCenter item) {
        holder.name.setText(item.name);
        holder.description.setText(item.description);
        holder.price.setText(item.price);
    }

    @Override
    public boolean isType(int position, Object item) {
        return item instanceof FitnessCenter;
    }

    @Override
    public long getItemId(int position, FitnessCenter item) {
        return item.hashCode();
    }

    public interface Listener {
        void onFitnessCenterItemClick(int position, FitnessCenter item);
    }
}

class FitnessCenterViewHolder extends RecyclerView.ViewHolder {
    final TextView name;
    final TextView description;
    final TextView price;

    FitnessCenterViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        price = itemView.findViewById(R.id.price);
    }
}


