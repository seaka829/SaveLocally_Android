package com.example.savelocally_android;

import java.util.ArrayList;

public class MessageList {

    private ArrayList<Message> messageList;
    private int count = 0;
    private int infoCount = 0;
    private int warningCount = 0;

    MessageList() {
        messageList = new ArrayList<>();
    }

    /**
     * エラーレベルINFOでメッセージの追加
     * @param str
     */
    public void addMessageI(String str) {
        Message message = new Message();
        message.setMessageI(str);
        messageList.add(message);
        count++;
        infoCount++;
    }

    /**
     * エラーレベルWARNINGでメッセージの追加
     * @param str
     */
    public void addMessageW(String str) {
        Message message = new Message();
        message.setMessageW(str);
        messageList.add(message);
        count++;
        warningCount++;
    }

    /**
     * メッセージリストのリセット
     */
    public void clear() {
        messageList = null;
        infoCount = 0;
        warningCount = 0;
    }

    /**
     * 後続処理の可否
     * WARNINGメッセージが1件以上存在する場合false
     * @return
     */
    public boolean canProcess() {
        if(getWarningCount() >= 1) {
            return false;
        }
        return true;
    }

    // getter, setter
    public ArrayList<Message> getMessageList() { return messageList; }
    public void setMessageList(ArrayList<Message> messageList) { this.messageList = messageList; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public int getInfoCount() { return infoCount; }
    public void setInfoCount(int infoCount) { this.infoCount = infoCount; }
    public int getWarningCount() { return warningCount; }
    public void setWarningCount(int warningCount) { this.warningCount = warningCount; }
}
