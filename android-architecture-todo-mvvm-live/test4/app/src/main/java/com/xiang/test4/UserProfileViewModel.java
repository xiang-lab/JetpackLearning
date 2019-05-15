package com.xiang.test4;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {

    private String userId;
    private MutableLiveData<User> user;
    private UserRepository userRepo;

    public void init(String userId) {
        this.userId = userId;
        userRepo = new UserRepository();
        user = userRepo.getUser(userId);
    }

    public void refresh(String userId) {
        user = userRepo.getUser(userId);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

}
