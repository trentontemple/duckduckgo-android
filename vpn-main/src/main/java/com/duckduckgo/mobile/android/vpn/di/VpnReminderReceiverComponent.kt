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

import com.duckduckgo.di.scopes.AppScope
import com.duckduckgo.di.scopes.ReceiverScope
import com.duckduckgo.mobile.android.vpn.service.VpnReminderReceiver
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Binds
import dagger.Module
import dagger.SingleInstanceIn
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@SingleInstanceIn(ReceiverScope::class)
@MergeSubcomponent(
    scope = ReceiverScope::class
)
interface VpnReminderReceiverComponent : AndroidInjector<VpnReminderReceiver> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<VpnReminderReceiver>
}

@ContributesTo(AppScope::class)
interface VpnReminderReceiverComponentProvider {
    fun provideVpnReminderReceiverComponentFactory(): VpnReminderReceiverComponent.Factory
}

@Module
@ContributesTo(AppScope::class)
abstract class VpnReminderReceiverBindingModule {
    @Binds
    @IntoMap
    @ClassKey(VpnReminderReceiver::class)
    abstract fun VpnReminderReceiverComponent.Factory.bind(): AndroidInjector.Factory<*>
}
