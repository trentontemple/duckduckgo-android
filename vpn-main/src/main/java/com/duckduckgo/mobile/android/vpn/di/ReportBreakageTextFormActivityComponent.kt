/*
 * Copyright (c) 2021 DuckDuckGo
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

package com.duckduckgo.mobile.android.vpn.di

import com.duckduckgo.di.scopes.ActivityScope
import com.duckduckgo.di.scopes.AppScope
import com.duckduckgo.di.scopes.VpnScope
import com.duckduckgo.mobile.android.vpn.breakage.ReportBreakageTextFormActivity
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.*
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@WrongScope(
    comment = "To use the right scope we first need to enable dagger component nesting",
    correctScope = ActivityScope::class,
)
@SingleInstanceIn(VpnScope::class)
@MergeSubcomponent(
    scope = VpnScope::class
)
interface ReportBreakageTextFormActivityComponent : AndroidInjector<ReportBreakageTextFormActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<ReportBreakageTextFormActivity>
}

@ContributesTo(AppScope::class)
interface ReportBreakageTextFormActivityComponentProvider {
    fun provideReportBreakageTextFormActivityComponentFactory(): ReportBreakageTextFormActivityComponent.Factory
}

@Module
@ContributesTo(AppScope::class)
abstract class ReportBreakageTextFormActivityBindingModule {
    @Binds
    @IntoMap
    @ClassKey(ReportBreakageTextFormActivity::class)
    abstract fun ReportBreakageTextFormActivityComponent.Factory.bind(): AndroidInjector.Factory<*>
}
