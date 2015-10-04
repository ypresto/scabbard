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
package net.ypresto.scabbard.holder;

import net.ypresto.scabbard.Scabbard;
import net.ypresto.scabbard.component.ScabbardActivityComponent;

/**
 * Implement this in your custom {@link android.app.Activity} subclass.
 */
public interface ActivityComponentHolder {
    /**
     * Provides activity component for {@link Scabbard}.
     */
    ScabbardActivityComponent getActivityComponent();
}