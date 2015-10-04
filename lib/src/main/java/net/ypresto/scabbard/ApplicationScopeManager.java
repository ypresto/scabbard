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

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import net.ypresto.scabbard.component.ComponentFactory;
import net.ypresto.scabbard.component.ScabbardApplicationComponent;

import java.util.HashMap;

/**
 * Holds component factory, application component and child scopes.
 */
/* package */  class ApplicationScopeManager {
    private final HashMap<Application, ApplicationScopeInfo> scopeInfoHolder = new HashMap<>();

    public void registerScope(Application application, ComponentFactory componentFactory, ScabbardApplicationComponent applicationComponent) {
        if (application == null || componentFactory == null || applicationComponent == null) {
            throw new NullPointerException();
        }
        ActivityComponentManager activityComponentManager = new ActivityComponentManager();
        application.registerActivityLifecycleCallbacks(activityComponentManager);
        scopeInfoHolder.put(application, new ApplicationScopeInfo(componentFactory, applicationComponent, activityComponentManager));
    }

    public void unregisterScope(Application application) {
        ApplicationScopeInfo scopeInfo = scopeInfoHolder.remove(application);
        application.unregisterActivityLifecycleCallbacks(scopeInfo.getActivityComponentManager());
    }

    @NonNull
    public ApplicationScopeInfo getScopeInfo(Context context) {
        Application application = (Application) context.getApplicationContext();
        return scopeInfoHolder.get(application);
    }
}
