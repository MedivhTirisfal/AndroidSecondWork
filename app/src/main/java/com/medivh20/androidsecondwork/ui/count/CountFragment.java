package com.medivh20.androidsecondwork.ui.count;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.medivh20.androidsecondwork.R;


public class CountFragment extends Fragment {

    private AppCompatEditText minutes;
    private AppCompatEditText seconds;
    private ProgressBar bar;
    private AppCompatButton button;
    private int choice = 0;
    private CountDownTimer cdt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_count, container, false);
        initView(root);
        initEvent();
        return root;
    }

    private void initView(View root) {
        minutes = root.findViewById(R.id.count_minutes);
        seconds = root.findViewById(R.id.count_seconds);
        bar = root.findViewById(R.id.count_progressBar);
        button = root.findViewById(R.id.count_start);
    }

    private void initEvent() {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (choice == 0) {

                    if (seconds.getText().toString().isEmpty()) seconds.setText("00");
                    if (Integer.parseInt(seconds.getText().toString()) >= 60) {
                        Toast.makeText(getContext(), "秒钟不能大于60", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    choice = 1;
                    minutes.setEnabled(false);
                    seconds.setEnabled(false);
                    if (minutes.getText().toString().isEmpty()) minutes.setText("0");
                    long time = (Integer.parseInt(minutes.getText().toString()) * 60 + Integer.parseInt(seconds.getText().toString())) * 1000;
                    cdt = new CountDownTimer(time, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    long m = ((millisUntilFinished + 500) / 1000) / 60;
                                    long s = ((millisUntilFinished + 500) / 1000) % 60;
                                    bar.setMax((int) (time / 1000));
                                    bar.setProgress((int) ((time - millisUntilFinished) / 1000));
                                    minutes.setText(String.valueOf(m));
                                    if (s >= 10) {
                                        seconds.setText(String.valueOf(s));
                                    } else {
                                        seconds.setText("0" + s);
                                    }
                                }
                            });
                        }

                        @Override
                        public void onFinish() {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    minutes.setEnabled(true);
                                    seconds.setEnabled(true);
                                    minutes.setText("0");
                                    seconds.setText("00");
                                    button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_500));
                                    button.setText("开始");
                                    choice = 0;
                                    bar.setProgress((int) (time / 1000));
                                }
                            });
                        }
                    };
                    button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey));
                    button.setText("停止");
                    cdt.start();
                } else {
                    minutes.setEnabled(true);
                    seconds.setEnabled(true);
                    button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_500));
                    button.setText("开始");
                    choice = 0;
                    cdt.cancel();

                }

            }
        });
    }
}