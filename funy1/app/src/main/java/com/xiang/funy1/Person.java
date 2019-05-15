package com.xiang.funy1;

import java.util.ArrayList;
import java.util.List;

public class Person {
    List<OnKickListner> mKickList;

    public Person() {
        mKickList = new ArrayList<>();
    }

    public boolean registKickItem(OnKickListner item) {
        mKickList.add(item);
        return true;
    }

    private int getTimestamp() {
        return 0;
    }

    public void run() {
        while (true) {
            int kickTimestamp;
            kickTimestamp = getTimestamp();
            for (OnKickListner l : mKickList) {
                l.onKick(kickTimestamp);
            }
        }
    }
}
