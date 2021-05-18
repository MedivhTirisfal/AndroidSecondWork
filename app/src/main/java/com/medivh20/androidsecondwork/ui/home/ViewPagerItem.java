package com.medivh20.androidsecondwork.ui.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.medivh20.androidsecondwork.MainActivity;
import com.medivh20.androidsecondwork.R;

import java.util.ArrayList;

public class ViewPagerItem extends Fragment {
    private int index;
    private RvAdapter adapter;
    private RecyclerView rv;
    private FloatingActionButton floatButton;
    public static ArrayList<ArrayList<String>> list;

    static {
        list = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            list.add(new ArrayList<>());
        }
    }

    public static ViewPagerItem getInstance(int i) {
        return new ViewPagerItem(i);
    }

    private ViewPagerItem(int index) {
        this.index = index;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rv, container, false);
        initView(root);
        initEvent();
        return root;
    }

    private void initView(View root) {
        rv = root.findViewById(R.id.home_rv);
        floatButton = root.findViewById(R.id.home_floatButton);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RvAdapter(list.get(index), index);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        rv.setAdapter(adapter);
    }

    private void initEvent() {
        floatButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                final AppCompatEditText et = new AppCompatEditText(getContext());
                et.setSingleLine();
                new AlertDialog.Builder(getContext()).setTitle("请输入事务")
                        .setView(et, 60, 0, 60, 0)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (et.getText().toString().trim().isEmpty()) {
                                    Toast.makeText(requireContext(),"不能为空",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                list.get(index).add(et.getText().toString());
                                adapter.addItem(et.getText().toString());
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });

    }
}
