package net.ypresto.scabbard.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.ypresto.scabbard.example.helper.ComponentHelper;
import net.ypresto.scabbard.example.object.UserInformationObject;

import javax.inject.Inject;

public class UserInformationActivityFragment extends Fragment {
    @Inject UserInformationObject userInformationObject;

    public UserInformationActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentHelper.createFragmentComponent(this).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_information, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.user_information_text)).setText(userInformationObject.toString());
    }
}
