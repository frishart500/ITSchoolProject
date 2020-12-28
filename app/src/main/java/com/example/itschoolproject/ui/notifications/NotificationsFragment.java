package com.example.itschoolproject.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itschoolproject.Adapter;
import com.example.itschoolproject.Item;
import com.example.itschoolproject.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Item> parseItems = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = root.findViewById(R.id.rv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        parseItems.add(new Item(R.mipmap.ic_launcher ,"Иван Иванович", "Химия"));
        parseItems.add(new Item(R.mipmap.ic_launcher ,"Иван Иванович", "Химия"));
        parseItems.add(new Item(R.mipmap.ic_launcher ,"Иван Иванович", "Химия"));
        parseItems.add(new Item(R.mipmap.ic_launcher ,"Иван Иванович", "Химия"));
        parseItems.add(new Item(R.mipmap.ic_launcher ,"Иван Иванович", "Химия"));
        parseItems.add(new Item(R.mipmap.ic_launcher ,"Иван Иванович", "Химия"));

        adapter = new Adapter(parseItems, getContext());
        recyclerView.setAdapter(adapter);


        return root;
    }
}