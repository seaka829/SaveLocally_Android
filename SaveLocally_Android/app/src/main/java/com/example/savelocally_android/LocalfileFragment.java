package com.example.savelocally_android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalfileFragment extends Fragment {

    EditText editText;
    Button saveButton;
    Button loadButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_localfile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getComponent(view);
        setButton();
    }

    /**
     * 画面上のコンポーネントを取得して、フィールド変数へ格納
     * @param view
     */
    private void getComponent(View view) {
        editText = view.findViewById(R.id.editText_localfile_text);
        saveButton = view.findViewById(R.id.button_localfile_save);
        loadButton = view.findViewById(R.id.button_localfile_load);
    }

    /**
     * ボタンクリック処理の設定
     */
    private void setButton() {
        // 保存ボタンのクリックリスナーの設定
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        // 読込ボタンのクリックリスナーの設定
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }

    private void save() {

    }

    private void load() {

    }
}
