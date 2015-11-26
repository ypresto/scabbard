package net.ypresto.scabbard.example;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import net.ypresto.scabbard.example.helper.ComponentHelper;

public class UserInformationActivity extends FragmentActivity {
    public static final String EXTRA_USER_ID = BuildConfig.APPLICATION_ID + ".userId";

    public static Intent createIntent(Context context, int userId) {
        return new Intent(context, UserInformationActivity.class).putExtra(EXTRA_USER_ID, userId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentHelper.createActivityComponentForUserId(this, getUserId()).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
    }

    private int getUserId() {
        int intExtra = getIntent().getIntExtra(EXTRA_USER_ID, -1);
        if (intExtra <= 0) throw new IllegalArgumentException("User id should be positive integer.");
        return intExtra;
    }
}
