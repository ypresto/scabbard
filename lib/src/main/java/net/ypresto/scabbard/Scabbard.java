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
import android.app.Service;
import android.content.Context;
import android.support.v4.app.Fragment;

import net.ypresto.scabbard.component.ComponentFactory;
import net.ypresto.scabbard.component.ScabbardActivityComponent;
import net.ypresto.scabbard.component.ScabbardApplicationComponent;
import net.ypresto.scabbard.component.ScabbardFragmentComponent;
import net.ypresto.scabbard.component.ScabbardServiceComponent;
import net.ypresto.scabbard.holder.ActivityComponentHolder;
import net.ypresto.scabbard.holder.ApplicationComponentHolder;
import net.ypresto.scabbard.holder.ComponentFactoryHolder;

/**
 * Provides predefined methods for create and look up components which are scoped in application,
 * activity, fragment, service.
 * You might want to define helper class which casts scabbard's component marker interfaces to actual components.
 */
public class Scabbard {
    /**
     * Creates application-scoped component using {@link ComponentFactory}.
     * It will look up {@link ComponentFactoryHolder} using given application instance.
     *
     * @param application Application instance used to look up holder. It is also passed to the factory.
     * @see ComponentFactoryHolder
     */
    public static ScabbardApplicationComponent createApplicationComponent(Application application) {
        ComponentFactory componentFactory = getComponentFactory(application);
        return componentFactory.createApplicationComponent(application);
    }

    /**
     * Creates activity-scoped component using {@link ScabbardApplicationComponent} and
     * {@link ComponentFactory}.
     * It will look up {@link ApplicationComponentHolder} and {@link ComponentFactoryHolder}
     * using given activity instance.
     *
     * @param activity Activity instance used to look up holders. It is also passed to the factory.
     * @see ComponentFactoryHolder
     */
    public static ScabbardActivityComponent createActivityComponent(Activity activity) {
        ScabbardApplicationComponent applicationComponent = getApplicationComponent(activity);
        ComponentFactory componentFactory = getComponentFactory(activity);
        return componentFactory.createActivityComponent(applicationComponent, activity);
    }

    /**
     * Creates fragment-scoped component using {@link ScabbardActivityComponent} and
     * {@link ComponentFactory}.
     * Requires the activity hosts fragment to implement {@link ActivityComponentHolder}.
     *
     * @param fragment Fragment instance used to look up holders. It is also passed to the factory.
     * @see ComponentFactoryHolder
     */
    public static ScabbardFragmentComponent createFragmentComponent(Fragment fragment) {
        ActivityComponentHolder holder = (ActivityComponentHolder) fragment.getActivity();
        ComponentFactory componentFactory = getComponentFactory(fragment.getActivity());
        return componentFactory.createFragmentComponent(holder.getActivityComponent(), fragment);
    }

    /**
     * Creates service-scoped component using {@link ScabbardApplicationComponent} and
     * {@link ComponentFactory}.
     * It will look up {@link ApplicationComponentHolder} and {@link ComponentFactoryHolder}
     * using given service instance.
     *
     * @param service Service instance used to look up holders. It is also passed to the factory.
     * @see ComponentFactoryHolder
     */
    public static ScabbardServiceComponent createServiceComponent(Service service) {
        ScabbardApplicationComponent applicationComponent = getApplicationComponent(service);
        ComponentFactory componentFactory = getComponentFactory(service);
        return componentFactory.createServiceComponent(applicationComponent, service);
    }

    /**
     * Looks up application-scoped component by given context instance.
     * Useful for injecting to something outside of activity or service.
     *
     * @param context Context used to look up holder.
     * @see ApplicationComponentHolder
     */
    public static ScabbardApplicationComponent getApplicationComponent(Context context) {
        ApplicationComponentHolder holder = (ApplicationComponentHolder) context.getApplicationContext();
        return holder.getApplicationComponent();
    }

    /**
     * Looks up application-scoped component by given context instance.
     * Useful to inject to fragment, as you might not need fragment-scoped component.
     *
     * @param activity used to look up holder.
     * @see ActivityComponentHolder
     */
    public static ScabbardActivityComponent getActivityComponent(Activity activity) {
        ActivityComponentHolder holder = (ActivityComponentHolder) activity;
        return holder.getActivityComponent();
    }

    private static ComponentFactory getComponentFactory(Context context) {
        return ((ComponentFactoryHolder) context.getApplicationContext()).getComponentFactory();
    }
}
