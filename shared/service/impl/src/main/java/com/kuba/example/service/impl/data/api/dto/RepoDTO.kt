/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kuba.example.service.impl.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RepoDTO(
    val id: Int,
    val name: String,
    val description: String?,
    val owner: Owner,

    @Json(name = "stargazers_count")
    val stars: Int
) {

    @JsonClass(generateAdapter = true)
    data class Owner(
        val login: String,
        val url: String?
    )
}
