package com.xiang.test4;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class UserProfileFragment extends AppCompatActivity {

    public UserProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userId = "12345";

        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.init(userId);

        // 如果哪个控件要根据user的变更而及时更新，就在onChanged()方法里处理就可以了。
        // 到这里，LiveData已经能够分析完了，其实LiveData的实现还是要依赖于Lifecycle。
        viewModel.getUser().observe(UserProfileFragment.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
//                    tvUser.setText(user.toString());
                }
            }
        });

    }

}
