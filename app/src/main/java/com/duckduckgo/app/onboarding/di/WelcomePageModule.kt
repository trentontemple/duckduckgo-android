/*
 * Copyright (c) 2018 DuckDuckGo
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

package com.duckduckgo.app.onboarding.di

import android.content.Context
import com.duckduckgo.app.global.DefaultRoleBrowserDialog
import com.duckduckgo.app.global.RealDefaultRoleBrowserDialog
import com.duckduckgo.app.global.install.AppInstallStore
import com.duckduckgo.app.onboarding.store.OnboardingStore
import com.duckduckgo.app.onboarding.ui.page.WelcomePageViewModelFactory
import com.duckduckgo.app.statistics.VariantManager
import com.duckduckgo.app.statistics.pixels.Pixel
import dagger.Module
import dagger.Provides

@Module
class WelcomePageModule {

    @Provides
    fun welcomePageViewModelFactory(
        appInstallStore: AppInstallStore,
        context: Context,
        pixel: Pixel,
        defaultRoleBrowserDialog: DefaultRoleBrowserDialog,
        onboardingStore: OnboardingStore,
        variantManager: VariantManager
    ) = WelcomePageViewModelFactory(appInstallStore, context, pixel, defaultRoleBrowserDialog, onboardingStore, variantManager)

    @Provides
    fun defaultRoleBrowserDialog(
        appInstallStore: AppInstallStore
    ): DefaultRoleBrowserDialog = RealDefaultRoleBrowserDialog(appInstallStore)
}
