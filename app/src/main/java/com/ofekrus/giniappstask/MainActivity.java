package com.ofekrus.giniappstask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.ofekrus.giniappstask.model.NumberItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NumbersViewModel numbersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(MainActivity.this.getApplication()))
                .get(NumbersViewModel.class);

        numbersViewModel.fetchNumbers();

        numbersViewModel.getAllNumbers().observe(MainActivity.this, (Observer<List<NumberItem>>) numberItems -> {
            NumbersAdapter numbersAdapter = new NumbersAdapter(MainActivity.this, numberItems);
            GridView numberGrid = findViewById(R.id.grid_view_numbers);
            numberGrid.setAdapter((ListAdapter) numbersAdapter);
        });

    }
}