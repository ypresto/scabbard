scabbard
=================

Dagger 2 scoped bindings (singletons) per Android lifecycles.

## Why

Scoped bindings of [Dagger 2](https://github.com/google/dagger),
provides singletons per lifecycles, is awesome feature which is not in
Dagger 1.
However Dagger itself does not provide way to manage component instances
per Android lifecycles, required to use this feature (as it is pure-java
library).

This library provides straight forward steps to manage and use scoped
components in Application, Activity, Fragment, Service.
Only things you should do are to implement every interface and to call
helper methods from `onCreate()`.

- Encapsulates component instantiation logic into `ComponentFactory`, which is
  held by Application subclass (so you can easily replace it in test).
- Provides static methods to easily create components with `this` of
  Activity/Service/Fragment.

## Limitations

- Designed for Dagger 2. Scoped bindings is not supported in Dagger 1.
- One component per one Activity/Fragment/Service design is not
  supported.
- Support Library v4 is required for fragment scope. Native fragment is
  not supported for now (NOTE: AppCompatActivity is almost required for
  material design).

## Step By Step Setup

See example directory for working android application and more details.

### 1. Add dependency to `build.gradle`.

TBD. Use jitpack for now.

### 2. Implement `ComponentFactory` to provide actual Dagger components.

```java
public class MyComponentFactory implements ComponentFactory {
    @Override
    public ScabbardApplicationComponent createApplicationComponent(Application application) {
        return DaggerMyApplicationComponent.create();
    }

    @Override
    public ScabbardActivityComponent createActivityComponent(ScabbardApplicationComponent applicationComponent, Activity activity) {
        return ((MyApplicationComponent) applicationComponent).createActivityComponent();
    }

    ...
```

### 3. Implement `ComponentFactoryHolder` and `ApplicationComponentHolder` in your custom Applciation class.


```java
public class MyApplication extends Application implements ComponentFactoryHolder, ApplicationComponentHolder {
    private final MyComponentFactory componentFactory = new MyComponentFactory();
    private MyApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        mApplicationComponent = ComponentHelper.createApplicationComponent(this);
        mApplicationComponent.inject(this);
        super.onCreate();
        ...
    }

    @Override
    public ScabbardApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    // NOTE: You can override this method in test Application to mock injected instances.
    @Override
    public ComponentFactory getComponentFactory() {
        return mComponentFactory;
    }
```


### 4. Make your components extend `Scabbard***Component` marker interfaces.

```java
public interface MyActivityComponent extends ScabbardActivityComponent {
```

### 5. Implement `ActivityComponentHolder` in your all activities

NOTE: You can skip this step if fragment-scoped component is not necessary.

You might want to have a base activity.

```java
// NOTE: Any application-specific logic should not be placed here!
public abstract class MyBaseActivity extends AppCompatActivity implements ActivityComponentHolder {
    private MyActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivityComponent = ComponentHelper.createActivityComponent(this);
        inject(mActivityComponent);
        super.onCreate(savedInstanceState);
    }

    @Override
    public MyActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected abstract void inject(MyActivityComponent component);
}
```

Then implement in every activity.

```java
@Override
protected void inject(MyActivityComponent component) {
    component.inject(this);
}
```

### 6. Create ComponentHelper for casting.

This library uses marker interface for component return types, as actual
component types are created by app developer.
It is more convenient if you create helper class which delegates to methods of
`Scabbard` class and casts to actual components.

```java
public class ComponentHelper {
    public static MyApplicationComponent createApplicationComponent(Application application) {
        return (MyApplicationComponent) Scabbard.createApplicationComponent(application);
    }

    ...
```

### 7. Call helper methods from onCreate().

```java
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentHelper.createFragmentComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        ...
    }
```

## Usage of scoped bindings

Refer [document of Dagger 2](http://google.github.io/dagger/)

## TODO

Please open issue if you want belows or other things to be implemented.

- Component holders can be replaced using HashMap and ActivityLifecycleCallbacks.
- Creating ComponentHelper to cast is not efficient way. Code generation maybe
  useful to automate it.
- Singleton scope kept during configuration change (i.e. orientation change)
  using retained fragment.

## License

```
Copyright (C) 2015 Yuya Tanaka

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
