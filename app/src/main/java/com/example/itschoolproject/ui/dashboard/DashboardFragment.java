package com.example.itschoolproject.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
import java.util.LinkedList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private Button btnDel;
    private ImageButton addBtn;
    private ArrayList<Item> parseItems = new ArrayList<>();
    private EditText editText;
    int position;
    private final LinkedList<String> mWordList = new LinkedList<>();
    private int mCount = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.rv);
        addBtn = root.findViewById(R.id.addBtn);
        btnDel = root.findViewById(R.id.delBtn);
        editText = root.findViewById(R.id.editText);

        createExampleList();
        buildRecyclerView();


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 adapter.notifyItemInserted(parseItems.size() - 1);
            }
        });

        return root;
    }


        public void createExampleList(){
            parseItems.add(new Item("title"));
            parseItems.add(new Item("title"));
            parseItems.add(new Item("title"));
            parseItems.add(new Item("title"));
        }

        public void buildRecyclerView(){
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new Adapter(parseItems, getContext());
            recyclerView.setAdapter(adapter);
        }

}