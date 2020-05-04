package com.example.savelocally_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class PreferenceFragment extends Fragment {

    // コンポーネント
    EditText keyEditText;
    EditText valueEditText;
    Button saveButton;
    Button loadButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preference, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getComponent(view);
        setButton();
        saveButton.setText(R.string.app_name);
    }

    /**
     * 画面上のコンポーネントを取得して、フィールド変数へ格納
     * @param view
     */
    private void getComponent(View view) {
        keyEditText = view.findViewById(R.id.editText_preference_key);
        valueEditText = view.findViewById(R.id.editText_preference_value);
        saveButton = view.findViewById(R.id.button_preference_save);
        loadButton = view.findViewById(R.id.button_preference_load);
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

    /**
     * 保存ボタンクリック時の処理
     */
    private void save() {
        // エディットテキストの値を取得
        String key = keyEditText.getText().toString();
        String value = valueEditText.getText().toString();

        // 保存前のチェック
        MessageList messageList = new MessageList();
        checkBeforeSave(key, value, messageList);

        if(messageList.canProcess()) {
            // 保存処理
            saveByPreference(key, value);
            Toast.makeText(getActivity(), "保存完了", Toast.LENGTH_SHORT).show();
        }
        else {
            // messageListを表示
        }
    }

    /**
     * 読込ボタンクリック時の処理
     */
    private void load() {
        // エディットテキストの値を取得
        String key = keyEditText.getText().toString();
        String value = valueEditText.getText().toString();

        // 読込前のチェック
        MessageList messageList = new MessageList();
        checkBeforeLoad(key, value, messageList);

        if(messageList.canProcess()) {
            // 読込処理
            valueEditText.setText(loadByPreference(key));
            Toast.makeText(getActivity(), "読込完了", Toast.LENGTH_SHORT).show();
        }
        else {
            // messageListを表示
        }
    }

    /**
     * データ保存前のチェック
     * エラーが存在した場合はメッセージリストに追加
     * @param key
     * @param value
     * @param messageList
     */
    private void checkBeforeSave(String key, String value, MessageList messageList) {
        if(key.equals("") || value.equals("")) {
            messageList.addMessageW("キーまたは値が空です。");
        }
    }

    /**
     * Preferenceによるデータの保存
     * @param key
     * @param value
     * @return
     */
    private boolean saveByPreference(String key, String value) {
        SharedPreferences preferences = getContext().getSharedPreferences("preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * データ読込前のチェック
     * エラーが存在した場合はメッセージリストに追加
     * @param key
     * @param value
     * @param messageList
     */
    private void checkBeforeLoad(String key, String value, MessageList messageList) {
        if(key.equals("")) {
            messageList.addMessageW("キーが空です。");
        }
    }

    /**
     * Preferenceによるデータの読込
     * @param key
     * @return
     */
    private String loadByPreference(String key) {
        SharedPreferences preferences = getContext().getSharedPreferences("preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        return preferences.getString(key, null);
    }
}
