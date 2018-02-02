package com.italankin.adapterdelegate.sample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.italankin.adapterdelegate.BaseAdapterDelegate;
import com.italankin.adapterdelegate.sample.R;
import com.italankin.adapterdelegate.sample.bean.Hotel;

public class HotelAdapterDelegate extends BaseAdapterDelegate<HotelViewHolder, Hotel> {
    private final Listener listener;

    public HotelAdapterDelegate(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_hotel;
    }

    @NonNull
    @Override
    protected HotelViewHolder createViewHolder(View itemView) {
        final HotelViewHolder holder = new HotelViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onHotelItemClick(pos, getAdapter().getItem(pos));
                    }
                }
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onHotelItemButtonClick(pos, getAdapter().getItem(pos));
                    }
                }
            }
        });
        return holder;
    }

    @Override
    public void onBind(HotelViewHolder holder, int position, Hotel item) {
        holder.name.setText(item.name);
        holder.description.setText(item.description);
        holder.address.setText(item.address);
    }

    @Override
    public boolean isType(int position, Object item) {
        return item instanceof Hotel;
    }

    @Override
    public long getItemId(int position, Hotel item) {
        return item.hashCode();
    }

    public interface Listener {
        void onHotelItemClick(int position, Hotel item);

        void onHotelItemButtonClick(int position, Hotel item);
    }
}

class HotelViewHolder extends RecyclerView.ViewHolder {
    final TextView name;
    final TextView description;
    final TextView address;
    final Button button;

    HotelViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        address = itemView.findViewById(R.id.address);
        button = itemView.findViewById(R.id.button);
    }
}

