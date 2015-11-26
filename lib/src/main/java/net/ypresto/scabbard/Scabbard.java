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
import android.support.v4.app.FragmentActivity;

import net.ypresto.scabbard.component.ComponentFactory;
import net.ypresto.scabbard.component.ComponentParameter;
import net.ypresto.scabbard.component.ScabbardActivityComponent;
import net.ypresto.scabbard.component.ScabbardApplicationComponent;
import net.ypresto.scabbard.component.ScabbardFragmentComponent;
import net.ypresto.scabbard.component.ScabbardServiceComponent;

/**
 * Manages scoped components and provides methods to create and get them in application, activity, fragment, service.
 * You might want to define helper class which casts scabbard's component marker interfaces to actual components.
 */
public class Scabbard {
    private static final ApplicationScopeManager APPLICATION_SCOPE_MANAGER = new ApplicationScopeManager();

    /**
     * Creates application-scoped component (root scope) using given {@link ComponentFactory}.
     */
    public static ScabbardApplicationComponent createApplicationComponent(Application application, ComponentFactory componentFactory) {
        ScabbardApplicationComponent applicationComponent = componentFactory.createApplicationComponent(application);
        APPLICATION_SCOPE_MANAGER.registerScope(application, componentFactory, applicationComponent);
        return applicationComponent;
    }

    /**
     * Creates activity-scoped component using {@link ScabbardApplicationComponent} and {@link ComponentFactory}.
     */
    public static ScabbardActivityComponent createActivityComponent(Activity activity) {
        return createActivityComponent(activity, null);
    }

    /**
     * Creates activity-scoped component using {@link ScabbardApplicationComponent} and {@link ComponentFactory}, with parameter.
     */
    public static ScabbardActivityComponent createActivityComponent(Activity activity, ComponentParameter componentParameter) {
        ApplicationScopeInfo scopeInfo = APPLICATION_SCOPE_MANAGER.getScopeInfo(activity);
        ScabbardApplicationComponent applicationComponent = scopeInfo.getApplicationComponent();
        ScabbardActivityComponent activityComponent = scopeInfo.getComponentFactory().createActivityComponent(applicationComponent, activity, componentParameter);
        scopeInfo.getActivityComponentManager().setComponent(activity, activityComponent);
        return activityComponent;
    }

    /**
     * Creates fragment-scoped component using {@link ScabbardActivityComponent} and {@link ComponentFactory}.
     */
    public static ScabbardFragmentComponent createFragmentComponent(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        ApplicationScopeInfo scopeInfo = APPLICATION_SCOPE_MANAGER.getScopeInfo(activity);
        ComponentFactory componentFactory = scopeInfo.getComponentFactory();
        ScabbardActivityComponent activityComponent = scopeInfo.getActivityComponentManager().getComponent(activity);
        return componentFactory.createFragmentComponent(activityComponent, fragment);
    }

    /**
     * Creates service-scoped component using {@link ScabbardApplicationComponent} and {@link ComponentFactory}.
     */
    public static ScabbardServiceComponent createServiceComponent(Service service) {
        ApplicationScopeInfo scopeInfo = APPLICATION_SCOPE_MANAGER.getScopeInfo(service);
        ScabbardApplicationComponent applicationComponent = scopeInfo.getApplicationComponent();
        return scopeInfo.getComponentFactory().createServiceComponent(applicationComponent, service);
    }

    /**
     * Get application-scoped component for given context.
     * Useful for injecting to something outside of activity or service.
     */
    public static ScabbardApplicationComponent getApplicationComponent(Context context) {
        return APPLICATION_SCOPE_MANAGER.getScopeInfo(context).getApplicationComponent();
    }

    /**
     * Get activity-scoped component for given activity.
     * Useful for injecting to Fragment without creating fragment scope.
     * Use {@link #createFragmentComponent(Fragment)} if you want to scope it.
     */
    public static ScabbardActivityComponent getActivityComponent(Activity activity) {
        return APPLICATION_SCOPE_MANAGER.getScopeInfo(activity).getActivityComponentManager().getComponent(activity);
    }

    /**
     * Removes internal state for given application instance.
     *
     * @see Application#onTerminate()
     */
    public static void onTerminateApplication(Application application) {
        APPLICATION_SCOPE_MANAGER.unregisterScope(application);
    }
}
