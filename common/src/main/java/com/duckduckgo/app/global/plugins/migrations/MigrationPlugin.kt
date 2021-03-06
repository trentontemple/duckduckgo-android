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

package com.duckduckgo.app.global.plugins.migrations

import com.duckduckgo.app.global.plugins.PluginPoint
import com.duckduckgo.di.DaggerSet
import com.duckduckgo.di.scopes.AppScope
import javax.inject.Inject
import dagger.SingleInstanceIn

interface MigrationPlugin {
    fun run()
    val version: Int
}

@SingleInstanceIn(AppScope::class)
class MigrationPluginPoint @Inject constructor(
    private val injectorPlugins: DaggerSet<MigrationPlugin>
) : PluginPoint<MigrationPlugin> {
    override fun getPlugins(): Collection<MigrationPlugin> {
        return injectorPlugins
    }
}
