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
package net.ypresto.scabbard.component;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.support.v4.app.Fragment;

/**
 * Factory interface for components. Implement this to provide components used by scabbard.
 */
public abstract class ComponentFactory {
    /**
     * Creates application-scoped component.
     *
     * @param application Application instance for instantiating modules.
     */
    public abstract ScabbardApplicationComponent createApplicationComponent(Application application);

    /**
     * Creates activity-scoped component.
     *
     * @param applicationComponent Parent component.
     * @param activity             Activity instance for instantiating modules.
     * @param componentParameter   Optional parameter object used for instantiating modules.
     */
    public abstract ScabbardActivityComponent createActivityComponent(ScabbardApplicationComponent applicationComponent, Activity activity, ComponentParameter componentParameter);

    /**
     * Creates fragment-scoped component.
     *
     * @param activityComponent Parent component.
     * @param fragment          Fragment instance for instantiating modules.
     */
    public ScabbardFragmentComponent createFragmentComponent(ScabbardActivityComponent activityComponent, Fragment fragment) {
        throw new UnsupportedOperationException("createFragmentComponent is not implemented.");
    }

    /**
     * Creates service-scoped component.
     *
     * @param applicationComponent Parent component.
     * @param service              Service instance for instantiating modules.
     */
    public ScabbardServiceComponent createServiceComponent(ScabbardApplicationComponent applicationComponent, Service service) {
        throw new UnsupportedOperationException("createServiceComponent is not implemented.");
    }
}
