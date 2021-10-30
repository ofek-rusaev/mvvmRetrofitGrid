package com.ofekrus.giniappstask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ofekrus.giniappstask.model.NumberItem;

import java.util.List;

public class NumbersAdapter extends RecyclerView.Adapter {

    private static final int TYPE_EQUAL_TO_ZERO = 0;
    private static final int TYPE_NOT_EQUAL_TO_ZERO = 1;

    private Context context;
    private List<NumberItem> numbers;
    private LayoutInflater inflater;

    public NumbersAdapter(Context context) {
        this.context = context;
    }

    public void submitList(List<NumberItem> mNumbers) {
        if (numbers != null) {
            numbers.clear();
        }
        numbers = mNumbers;
    }

    private class NumberEqualToZeroHolder extends RecyclerView.ViewHolder {
        TextView numberTextView;

        public NumberEqualToZeroHolder(@NonNull View itemView) {
            super(itemView);
            numberTextView = itemView.findViewById(R.id.red_number_text_view);
        }
    }

    private class NumberNotEqualToZeroHolder extends RecyclerView.ViewHolder {
        TextView numberTextView;

        public NumberNotEqualToZeroHolder(@NonNull View itemView) {
            super(itemView);
            numberTextView = itemView.findViewById(R.id.orange_number_text_view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        NumberItem numberItem = numbers.get(position);

        if (numberItem.isPairEqualToZero()) {
            return TYPE_EQUAL_TO_ZERO;
        } else {
            return TYPE_NOT_EQUAL_TO_ZERO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        switch (viewType) {
            case TYPE_EQUAL_TO_ZERO:
                view = inflater.inflate(R.layout.number_item_red, parent, false);
                return new NumberEqualToZeroHolder(view);
            case TYPE_NOT_EQUAL_TO_ZERO:
                view = inflater.inflate(R.layout.number_item_orange, parent, false);
                return new NumberNotEqualToZeroHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NumberItem currentNumber = numbers.get(position);
        if (currentNumber.isPairEqualToZero()) {
            NumberEqualToZeroHolder numberHolder = (NumberEqualToZeroHolder) holder;
            numberHolder.numberTextView.setText(String.valueOf(currentNumber.getNumber()));
        } else {
            NumberNotEqualToZeroHolder numberHolder = (NumberNotEqualToZeroHolder) holder;
            numberHolder.numberTextView.setText(String.valueOf(currentNumber.getNumber()));
        }
    }

    @Override
    public int getItemCount() {
        if (numbers == null) {
            return 0;
        }
        return numbers.size();
    }
}
