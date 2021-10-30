package com.ofekrus.giniappstask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private NumbersViewModel numbersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(MainActivity.this.getApplication()))
                .get(NumbersViewModel.class);

        NumbersAdapter numbersAdapter = new NumbersAdapter(MainActivity.this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_numbers);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(numbersAdapter);

        numbersViewModel.fetchNumbers();

        numbersViewModel.getAllNumbers().observe(MainActivity.this, numberItems -> {
            numbersAdapter.submitList(numberItems);
            numbersAdapter.notifyDataSetChanged();
        });
    }
}