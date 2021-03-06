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

import net.ypresto.scabbard.component.ScabbardActivityComponent;
import net.ypresto.scabbard.example.MainActivity;
import net.ypresto.scabbard.example.module.MyActivityModule;
import net.ypresto.scabbard.example.scope.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = MyActivityModule.class)
public interface MyActivityComponent extends ScabbardActivityComponent {
    MyFragmentComponent createFragmentComponent();

    void inject(MainActivity target);
}
