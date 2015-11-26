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
package net.ypresto.scabbard.example.helper;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import net.ypresto.scabbard.Scabbard;
import net.ypresto.scabbard.component.ComponentFactory;
import net.ypresto.scabbard.example.component.MyActivityComponent;
import net.ypresto.scabbard.example.component.MyApplicationComponent;
import net.ypresto.scabbard.example.component.MyComponentParameter;
import net.ypresto.scabbard.example.component.MyFragmentComponent;

public class ComponentHelper {
    public static MyApplicationComponent createApplicationComponent(Application application, ComponentFactory componentFactory) {
        return (MyApplicationComponent) Scabbard.createApplicationComponent(application, componentFactory);
    }

    public static MyActivityComponent createActivityComponent(Activity activity) {
        return (MyActivityComponent) Scabbard.createActivityComponent(activity);
    }

    public static MyActivityComponent createActivityComponentForUserId(Activity activity, int userId) {
        return (MyActivityComponent) Scabbard.createActivityComponent(activity, new MyComponentParameter(userId));
    }

    public static MyFragmentComponent createFragmentComponent(Fragment fragment) {
        return (MyFragmentComponent) Scabbard.createFragmentComponent(fragment);
    }
}
