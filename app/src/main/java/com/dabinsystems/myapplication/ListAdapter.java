package com.dabinsystems.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dabinsystems.myapplication.databinding.ListItemBinding;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter {

    private ArrayList<ListData> mCableInfoList;
    private ArrayList<ListItemBinding> mHolderList;
    private Context mContext;
    private RecyclerView.ViewHolder holder;
    private int mSelectedPos = -1;

    public ListAdapter(ArrayList<ListData> CableInfoList, Context context) {
        this.mCableInfoList = CableInfoList;
        mContext = context;
        mHolderList = new ArrayList<ListItemBinding>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(mContext), parent, false);
        holder = new ListItemHolder(binding);
        holder.setIsRecyclable(true);
        Log.d("onCreateViewHolder", "onCreateViewHolder");

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        Log.d("position", position + "");

        ListItemHolder listItemHolder = (ListItemHolder) holder;

        final ListItemBinding binding = listItemHolder.binding;

        binding.tvName.setText(mCableInfoList.get(position).getName());
        binding.tvName.setTag(binding.tvName.getText().toString());

        binding.parent.setBackgroundColor(Color.WHITE);
        binding.parent.setTag("none");

        if(!mHolderList.contains(listItemHolder)) mHolderList.add(listItemHolder.binding);

        if(mSelectedPos != -1 && position == mSelectedPos) {

            binding.parent.setBackgroundColor(Color.YELLOW);
            binding.parent.setTag("select");
            Log.d("mSelectedRelativeLayout", "in " + position + " " + mSelectedPos);

        }

        binding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.parent.getTag().equals("none")) {

                    for (int i = 0; i < mHolderList.size(); i++) {

                        mHolderList.get(i).parent.setBackgroundColor(Color.WHITE);
                        mHolderList.get(i).parent.setTag("none");
                    }

                    binding.parent.setBackgroundColor(Color.YELLOW);
                    binding.parent.setTag("select");

                    mSelectedPos = position;

                    Log.d("selected", position + "");


                } else {

                    binding.parent.setBackgroundColor(Color.WHITE);
                    binding.parent.setTag("none");
                    mSelectedPos = -1;

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mCableInfoList.size();
    }

    private class ListItemHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;

        ListItemHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public RecyclerView.ViewHolder getHolder() {

        return holder;
    }

//    public CableListItemBinding getBinding() {
//        return binding;
//    }

}