package com.italankin.adapterdelegate;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Basic implementation of {@link AdapterDelegate}.
 *
 * @param <VH> type of {@link RecyclerView.ViewHolder} for this delegate
 * @param <T>  item type managed by this delegate
 */
public abstract class BaseAdapterDelegate<VH extends RecyclerView.ViewHolder, T> implements AdapterDelegate<VH, T> {

    private CompositeAdapter<T> adapter;

    /**
     * Get layout resource identifier for view holder's layout.
     *
     * @return resource identifier for view holder layout
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * Create {@link RecyclerView.ViewHolder}.
     *
     * @param itemView layout, inflated from {@link #getLayoutRes()}
     * @return new {@link RecyclerView.ViewHolder}
     */
    @NonNull
    protected abstract VH createViewHolder(View itemView);

    @Override
    public void onAttached(CompositeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @NonNull
    @Override
    public VH onCreate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(getLayoutRes(), parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onRecycled(VH holder) {
    }

    @Override
    public long getItemId(int position, T item) {
        return 0;
    }

    /**
     * @return adapter, which this delegate is attached to
     */
    protected CompositeAdapter<T> getAdapter() {
        return adapter;
    }
}
