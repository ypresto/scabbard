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

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import net.ypresto.scabbard.example.component.MyComponentFactory;
import net.ypresto.scabbard.example.helper.ComponentHelper;
import net.ypresto.scabbard.example.object.ApplicationSingletonObject;

import javax.inject.Inject;

public class MyApplication extends Application {
    @Inject ApplicationSingletonObject object;

    @Override
    public void onCreate() {
        ComponentHelper.createApplicationComponent(this, new MyComponentFactory()).inject(this);
        super.onCreate();
        LeakCanary.install(this);
        Log.d("MyApplication", "@Singleton: " + object.toString());
    }
}
