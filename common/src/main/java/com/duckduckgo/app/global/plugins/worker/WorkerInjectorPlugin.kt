/*
 * Copyright (c) 2020 DuckDuckGo
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

package com.duckduckgo.app.global.plugins.worker

import androidx.work.ListenableWorker
import com.duckduckgo.app.global.plugins.PluginPoint
import com.duckduckgo.di.DaggerSet
import com.duckduckgo.di.scopes.AppScope
import javax.inject.Inject
import dagger.SingleInstanceIn

interface WorkerInjectorPlugin {
    /**
     * @return whether the worker has been injected
     */
    fun inject(worker: ListenableWorker): Boolean
}

@SingleInstanceIn(AppScope::class)
class WorkerInjectorPluginPoint @Inject constructor(
    private val injectorPlugins: DaggerSet<WorkerInjectorPlugin>
) : PluginPoint<WorkerInjectorPlugin> {
    override fun getPlugins(): List<WorkerInjectorPlugin> {
        return injectorPlugins.toList()
    }
}
