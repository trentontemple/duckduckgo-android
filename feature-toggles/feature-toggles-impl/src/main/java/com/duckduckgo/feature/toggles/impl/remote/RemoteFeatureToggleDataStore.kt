/*
 * Copyright (c) 2022 DuckDuckGo
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

package com.duckduckgo.feature.toggles.impl.remote

import com.duckduckgo.di.scopes.AppScope
import com.duckduckgo.feature.toggles.store.remote.RemoteFeatureTogglesRepository
import com.squareup.anvil.annotations.ContributesBinding
import dagger.SingleInstanceIn
import timber.log.Timber
import javax.inject.Inject

interface RemoteFeatureToggleDataStore {
    suspend fun updateFeatureToggles()
    fun get(
        featureName: String,
        defaultValue: Boolean
    ): Boolean?
}

@SingleInstanceIn(AppScope::class)
@ContributesBinding(AppScope::class)
class LocalRemoteFeatureToggleDataStore @Inject constructor(
    private val repository: RemoteFeatureTogglesRepository
) : RemoteFeatureToggleDataStore {
    private val features = mutableMapOf<String, Boolean>()
    override suspend fun updateFeatureToggles() {
        repository.getAllFeatures().forEach {
            features[it.featureName] = it.isEnabled
            Timber.d("${it.featureName} : ${it.isEnabled}")
        }
    }

    override fun get(
        featureName: String,
        defaultValue: Boolean
    ): Boolean? = if (features.containsKey(featureName)) features[featureName] else null
}