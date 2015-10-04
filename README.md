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

This library automatically manages scoped components in Application,
Activity, Fragment, Service. Only things you should do are:

- Implement `ComponentFacotry`.
- Mark your components with `Scabbard***Component` interface.
- (Optional) Wrap helper to cast to actual component type from
  `Scabbard***Component`.
- Call helper methods from `onCreate()`, like
  `ComponentHelper.createApplicationComponent(this).inject(this);`

## Requirements / Limitations

- Designed for Dagger 2. Scoped bindings is not supported in Dagger 1.
- Android >= 4.0. Because ActivityLifecycleCallbacks is used to release 
  component.
- One component per one Activity/Fragment/Service design is not
  supported.
- Support Library v4 is required for fragment scope. Native fragment is
  not supported for now (NOTE: AppCompatActivity is almost required for
  material design).

## Installation

Gradle dependency:

TBD. Use [jitpack](https://jitpack.io/) for now.

## Setup and examples

- See example directory for working android application and more details.
- [Step by Step Setup](https://github.com/ypresto/scabbard/wiki/Step-by-Step-Setup)
- Refer [document of Dagger 2](http://google.github.io/dagger/) for usage of scoped bindings.

## TODO

Please open issue if you want belows or other things to be implemented.

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
