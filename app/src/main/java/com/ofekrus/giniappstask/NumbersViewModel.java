package com.ofekrus.giniappstask;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ofekrus.giniappstask.model.NumberItem;

import java.util.List;

public class NumbersViewModel extends AndroidViewModel {

    private NumbersRepository repository;

    public NumbersViewModel(@NonNull Application application) {
        super(application);
        this.repository = new NumbersRepository(application);
    }

    public void fetchNumbers() {
        repository.fetchNumbers();
    }

    public LiveData<List<NumberItem>> getAllNumbers() {
        return repository.getAllNumbers();
    }
}
