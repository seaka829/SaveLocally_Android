package com.example.savelocally_android;

public class Message {

    // 定数
    private final static int INFO = 1;
    private final static int WARNING = 2;

    // フィールド変数
    private int errorLevel = 0;
    private String message = null;

    /**
     * エラーレベルINFOでメッセージの設定
     * @param str
     */
    public void setMessageI(String str) {
        setErrorLevel(INFO);
        setMessage(str);
    }

    /**
     * エラーレベルWARNINGでメッセージの設定
     * @param str
     */
    public void setMessageW(String str) {
        setErrorLevel(WARNING);
        setMessage(str);
    }

    // getter, setter
    public int getErrorLevel() { return errorLevel; }
    public void setErrorLevel(int errorLevel) { this.errorLevel = errorLevel; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
