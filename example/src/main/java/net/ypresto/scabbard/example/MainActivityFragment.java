/*
 * Copyright (C) 2015 Yuya Tanaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ypresto.scabbard.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.ypresto.scabbard.example.helper.ComponentHelper;
import net.ypresto.scabbard.example.object.ActivitySingletonObject;
import net.ypresto.scabbard.example.object.ApplicationSingletonObject;
import net.ypresto.scabbard.example.object.FragmentSingletonObject;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @Inject ApplicationSingletonObject applicationSingletonObject;
    @Inject ActivitySingletonObject activitySingletonObject;
    @Inject Model1 model1;
    @Inject Model2 model2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentHelper.createFragmentComponent(this).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        render(view);
        return view;
    }

    private void render(View view) {
        setTextWithFormatById(view, R.id.app_singleton, R.string.app_singleton, applicationSingletonObject.toString());
        setTextWithFormatById(view, R.id.activity_singleton, R.string.activity_singleton, activitySingletonObject.toString());
        setTextWithFormatById(view, R.id.model1, R.string.model1, model1.fragmentSingletonObject.toString());
        setTextWithFormatById(view, R.id.model2, R.string.model2, model2.fragmentSingletonObject.toString());
        view.findViewById(R.id.relaunch_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        view.findViewById(R.id.replace_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container, new MainActivityFragment()).commit();
            }
        });
    }

    private void setTextWithFormatById(View view, @IdRes int viewId, @StringRes int stringId, String text) {
        ((TextView) view.findViewById(viewId)).setText(getString(stringId, text));
    }

    public static class Model1 {
        @Inject FragmentSingletonObject fragmentSingletonObject;

        @Inject
        Model1() {
        }
    }

    public static class Model2 {
        @Inject FragmentSingletonObject fragmentSingletonObject;

        @Inject
        Model2() {
        }
    }
}
