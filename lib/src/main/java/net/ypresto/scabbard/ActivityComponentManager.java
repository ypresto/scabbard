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
package net.ypresto.scabbard;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import net.ypresto.scabbard.component.ScabbardActivityComponent;

import java.util.HashMap;

/**
 * Holds component for activity instance.
 */
/* package */ class ActivityComponentManager implements Application.ActivityLifecycleCallbacks {
    private final HashMap<Activity, ScabbardActivityComponent> mComponentHolder = new HashMap<>();

    public ScabbardActivityComponent getComponent(Activity activity) {
        return mComponentHolder.get(activity);
    }

    public void setComponent(Activity activity, ScabbardActivityComponent activityComponent) {
        mComponentHolder.put(activity, activityComponent);
    }

    public void removeComponent(Activity activity) {
        mComponentHolder.remove(activity);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeComponent(activity);
    }
}
