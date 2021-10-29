package com.ofekrus.giniappstask;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ofekrus.giniappstask.model.NumberItem;
import com.ofekrus.giniappstask.model.NumbersResponse;
import com.ofekrus.giniappstask.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NumbersRepository {

    private MutableLiveData<List<NumberItem>> allNumbers = new MutableLiveData<>();
    private Application application;

    public NumbersRepository(Application application) {
        this.application = application;
    }

    public void fetchNumbers() {
        Call<NumbersResponse> call = RetrofitClient.getInstance().getRetrofitInterface().executeGetNumbers();
        call.enqueue(new Callback<NumbersResponse>() {
            @Override
            public void onResponse(Call<NumbersResponse> call, Response<NumbersResponse> response) {
                if (response.isSuccessful()) {
                    // TODO: check if isPairEqualToZero is true

                    List<NumberItem> list = new ArrayList<>(response.body().getNumbers());

                    Collections.sort(list);

                    hasArrayTwoCandidatesEqualToZero(list);

                    allNumbers.postValue(list);
                }
            }

            @Override
            public void onFailure(Call<NumbersResponse> call, Throwable t) {
                System.out.println("fetchNumbers - t: " + t.getMessage());
            }
        });
    }

    private boolean hasArrayTwoCandidatesEqualToZero(List<NumberItem> list) {
        int l, r;
        l = 0;
        r = list.size() - 1;

        while (l < r) {
            if (list.get(l).getNumber() + list.get(r).getNumber() == 0) {
                list.get(l).setPairEqualToZero(true);
                list.get(r).setPairEqualToZero(true);
                return true;
            } else if (list.get(l).getNumber() + list.get(r).getNumber() < 0) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    public LiveData<List<NumberItem>> getAllNumbers() {
        return allNumbers;
    }
}
