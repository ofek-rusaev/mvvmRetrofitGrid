package com.ofekrus.giniappstask;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ofekrus.giniappstask.model.NumberItem;
import com.ofekrus.giniappstask.model.NumbersResponse;
import com.ofekrus.giniappstask.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
                    createPairsList(response.body().getNumbers(), 0);
                }
            }

            @Override
            public void onFailure(Call<NumbersResponse> call, Throwable t) {
                System.out.println("fetchNumbers - t: " + t.getMessage());
            }
        });
    }

    private void createPairsList(ArrayList<NumberItem> numbers, int pairSum) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        List<NumberItem> numberItems = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            int currNum = numbers.get(i).getNumber();
            int pair = pairSum - currNum;

            if (map.containsKey(pair) && currNum != pairSum) {
                map.put(currNum, true);

                // if found "pair" was marked false -> update that it is equal to zero:
                if (!map.get(pair)) {
                    map.put(pair, true);
                }

            } else {
                map.put(currNum, false);
            }
        }

        ArrayList<Integer> mapKeys = new ArrayList<>(map.keySet());
        for (Integer i: mapKeys) {
            numberItems.add(new NumberItem(i, map.get(i)));
        }

        Collections.sort(numberItems);
        allNumbers.postValue(numberItems);
    }

    public LiveData<List<NumberItem>> getAllNumbers() {
        return allNumbers;
    }
}
