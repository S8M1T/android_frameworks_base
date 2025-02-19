/*
 * Copyright (C) 2021 The Android Open Source Project
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

package com.android.systemui.media.taptotransfer.common

import android.graphics.drawable.Drawable

/**
 * A superclass chip state that will be subclassed by the sender chip and receiver chip.
 *
 * @property appIconDrawable a drawable representing the icon of the app playing the media.
 * @property appIconContentDescription a string to use as the content description for the icon.
 */
open class MediaTttChipState(
    internal val appIconDrawable: Drawable,
    internal val appIconContentDescription: String
)
