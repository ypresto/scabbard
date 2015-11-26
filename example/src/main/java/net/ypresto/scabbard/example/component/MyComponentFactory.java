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
package net.ypresto.scabbard.example.component;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import net.ypresto.scabbard.component.ComponentFactory;
import net.ypresto.scabbard.component.ComponentParameter;
import net.ypresto.scabbard.component.ScabbardActivityComponent;
import net.ypresto.scabbard.component.ScabbardApplicationComponent;
import net.ypresto.scabbard.component.ScabbardFragmentComponent;
import net.ypresto.scabbard.example.module.MyActivityModule;
import net.ypresto.scabbard.example.module.MyUserModule;

public class MyComponentFactory extends ComponentFactory {
    @Override
    public ScabbardApplicationComponent createApplicationComponent(Application application) {
        return DaggerMyApplicationComponent.create();
    }

    @Override
    public ScabbardActivityComponent createActivityComponent(ScabbardApplicationComponent applicationComponent, Activity activity, ComponentParameter componentParameter) {
        MyComponentParameter myComponentParameter = (MyComponentParameter) componentParameter;
        MyUserModule myUserModule = myComponentParameter != null ? new MyUserModule(myComponentParameter.getUserId()) : new MyUserModule();
        return ((MyApplicationComponent) applicationComponent).createActivityComponent(new MyActivityModule(activity), myUserModule);
    }

    @Override
    public ScabbardFragmentComponent createFragmentComponent(ScabbardActivityComponent activityComponent, Fragment fragment) {
        return ((MyActivityComponent) activityComponent).createFragmentComponent();
    }
}
