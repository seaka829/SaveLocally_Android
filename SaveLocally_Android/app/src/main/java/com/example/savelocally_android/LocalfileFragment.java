package com.example.savelocally_android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LocalfileFragment extends Fragment {

    // 定数
    private static String FILE = "file.txt";

    // コンポーネント
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

    /**
     * 保存ボタンクリック時の処理
     */
    private void save() {
        String text = editText.getText().toString();
        MessageList messageList = new MessageList();
        saveByLocalfile(text, messageList);
        Toast.makeText(getActivity(), messageList.getMessageAll(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 読込ボタンクリック時の処理
     */
    private void load() {
        MessageList messageList = new MessageList();
        String text = loadByLocalfile(messageList);
        if(messageList.getWarningCount() == 0) {
            editText.setText(text);
        }
        Toast.makeText(getActivity(), messageList.getMessageAll(), Toast.LENGTH_SHORT).show();
    }

    /**
     * ローカルファイルへのデータの保存
     * エラーが存在した場合はメッセージリストに追加
     * @param text
     * @param messageList
     */
    private void saveByLocalfile(String text, MessageList messageList) {
        try {
            OutputStream out = getContext().openFileOutput(FILE, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append(text);
            writer.close();
            messageList.addMessageI("保存完了");
        } catch (IOException e) {
            e.printStackTrace();
            messageList.addMessageW("テキスト保存に失敗しました。");
        }
    }

    /**
     * ローカルファイルに保存したデータを取得
     * エラーが存在した場合はメッセージリストに追加
     * @param messageList
     * @return
     */
    private String loadByLocalfile(MessageList messageList) {
        String text = "";
        try {
            InputStream in = getContext().openFileInput(FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String buf;
            while((buf = reader.readLine()) != null){
                text += buf + "\n";
            }
            reader.close();
            messageList.addMessageI("読込完了");
        } catch (IOException e) {
            e.printStackTrace();
            messageList.addMessageW("テキスト読込に失敗しました。");
        }
        return text;
    }
}
