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
                    HashMap<Integer, Integer> map = new HashMap<>();
                    for (int i = 0; i < response.body().getNumbers().size(); i++) {
                        Integer num = response.body().getNumbers().get(i).getNumber();
                        map.put(num, num);
                    }
                    Runnable setPairs = () -> SetListWithPairs(map, 0);
                    new Thread(setPairs).start();
//                    SetListWithPairs(map, 0);
                }
            }

            @Override
            public void onFailure(Call<NumbersResponse> call, Throwable t) {
                System.out.println("fetchNumbers - t: " + t.getMessage());
            }
        });
    }

    private void SetListWithPairs(HashMap map, int pairSum) {
        List<NumberItem> numberItems = new ArrayList<>();

        ArrayList<Integer> keys = new ArrayList<>(map.keySet());
        for (int i = 0; i < keys.size(); i++) {
            int currNum = keys.get(i);
            int pair = pairSum - currNum;
            if (map.containsKey(pair) && currNum != pairSum) {
                numberItems.add(new NumberItem(currNum, true));
            } else {
                numberItems.add(new NumberItem(currNum, false));
            }
        }
        Collections.sort(numberItems);
        allNumbers.postValue(numberItems);
    }

    public LiveData<List<NumberItem>> getAllNumbers() {
        return allNumbers;
    }
}
