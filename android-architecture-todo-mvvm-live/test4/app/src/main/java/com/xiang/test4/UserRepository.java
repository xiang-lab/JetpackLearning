package com.xiang.test4;

import androidx.lifecycle.MutableLiveData;

public class UserRepository {

    final MutableLiveData<User> data = new MutableLiveData<>();

    public MutableLiveData<User> getUser(String userId) {
        if ("xiasm".equals(userId)) {
            data.setValue(new User(userId, "夏胜明"));
        } else if ("123456".equals(userId)) {
            data.setValue(new User(userId, "哈哈哈"));
        } else {
            data.setValue(new User(userId, "unknow"));
        }
        return data;
    }
}
