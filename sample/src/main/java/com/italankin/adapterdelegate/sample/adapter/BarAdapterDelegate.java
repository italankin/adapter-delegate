package com.italankin.adapterdelegate.sample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.italankin.adapterdelegate.BaseAdapterDelegate;
import com.italankin.adapterdelegate.sample.R;
import com.italankin.adapterdelegate.sample.bean.Bar;

public class BarAdapterDelegate extends BaseAdapterDelegate<BarViewHolder, Bar> {
    private final Listener listener;

    public BarAdapterDelegate(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_bar;
    }

    @NonNull
    @Override
    protected BarViewHolder createViewHolder(View itemView) {
        final BarViewHolder holder = new BarViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onBarItemClick(pos, getAdapter().getItem(pos));
                    }
                }
            }
        });
        return holder;
    }

    @Override
    public void onBind(BarViewHolder holder, int position, Bar item) {
        holder.name.setText(item.name);
        holder.description.setText(item.description);
        holder.address.setText(item.address);
        holder.price.setText(item.price);
    }

    @Override
    public boolean isType(int position, Object item) {
        return item instanceof Bar;
    }

    @Override
    public long getItemId(int position, Bar item) {
        return item.hashCode();
    }

    public interface Listener {
        void onBarItemClick(int position, Bar item);
    }
}

class BarViewHolder extends RecyclerView.ViewHolder {
    final TextView name;
    final TextView description;
    final TextView address;
    final TextView price;

    BarViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        address = itemView.findViewById(R.id.address);
        price = itemView.findViewById(R.id.price);
    }
}
