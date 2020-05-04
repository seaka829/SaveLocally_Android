package com.example.savelocally_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 定数
    private final static int PREFERENCE   = 0;
    private final static int SQLITE       = 1;
    private final static int LOCALDATA    = 2;
    private final static String[] saveModeList = {"Preference", "SQLite", "Local Data"};

    // コンポーネント
    PreferenceFragment preferenceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSpinner();

        // フラグメントの生成
        preferenceFragment = new PreferenceFragment();
    }

    /**
     * 保存方法を選択するプルダウンの設定
     */
    private void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, saveModeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner_main_modeSelection);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                if(position == PREFERENCE) {
                    replaceFragment(preferenceFragment);
                }
                else if(position == SQLITE) {

                }
                else if(position == LOCALDATA) {

                }
            }
        });
    }

    /**
     * フラグメント（container）の置き換え
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
