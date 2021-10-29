package com.ofekrus.giniappstask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ofekrus.giniappstask.model.NumberItem;

import java.util.List;

public class NumbersAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private List<NumberItem> numbers;

    public NumbersAdapter(Context context, List<NumberItem> numbers) {
        this.context = context;
        this.numbers = numbers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        NumberItem numberItem = numbers.get(position);
        TextView numberTextView;

        if (numberItem.isPairEqualToZero()) {
            convertView = inflater.inflate(R.layout.number_item_red, null);
            numberTextView = convertView.findViewById(R.id.red_number_text_view);

        } else {
            convertView = inflater.inflate(R.layout.number_item_orange, null);
            numberTextView = convertView.findViewById(R.id.orange_number_text_view);
        }
        numberTextView.setText(String.valueOf(numberItem.getNumber()));

        return convertView;
    }

    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
