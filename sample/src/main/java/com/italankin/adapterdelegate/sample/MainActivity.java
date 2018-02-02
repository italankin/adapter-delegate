package com.italankin.adapterdelegate.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.italankin.adapterdelegate.CompositeAdapter;
import com.italankin.adapterdelegate.sample.adapter.BarAdapterDelegate;
import com.italankin.adapterdelegate.sample.adapter.FitnessCenterAdapterDelegate;
import com.italankin.adapterdelegate.sample.adapter.HotelAdapterDelegate;
import com.italankin.adapterdelegate.sample.bean.Bar;
import com.italankin.adapterdelegate.sample.bean.FitnessCenter;
import com.italankin.adapterdelegate.sample.bean.Hotel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BarAdapterDelegate.Listener, HotelAdapterDelegate.Listener, FitnessCenterAdapterDelegate.Listener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView list = findViewById(R.id.list);
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        new CompositeAdapter.Builder<>(this)
                .add(new BarAdapterDelegate(this))
                .add(new HotelAdapterDelegate(this))
                .add(new FitnessCenterAdapterDelegate(this))
                .dataset(generateList())
                .recyclerView(list)
                .setHasStableIds(true)
                .create();
    }

    private static List<Object> generateList() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            switch (i % 3) {
                case 0:
                    list.add(new Bar());
                    break;
                case 1:
                    list.add(new FitnessCenter());
                    break;
                case 2:
                    list.add(new Hotel());
                    break;
            }
        }
        return list;
    }

    @Override
    public void onBarItemClick(int position, Bar item) {
        Toast.makeText(this, "Bar item clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHotelItemClick(int position, Hotel item) {
        Toast.makeText(this, "Hotel item clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHotelItemButtonClick(int position, Hotel item) {
        Toast.makeText(this, "Hotel item button clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFitnessCenterItemClick(int position, FitnessCenter item) {
        Toast.makeText(this, "FitnessCenter item click: " + position, Toast.LENGTH_SHORT).show();
    }
}
