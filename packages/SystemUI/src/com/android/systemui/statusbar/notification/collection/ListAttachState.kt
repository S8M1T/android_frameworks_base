/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.android.systemui.statusbar.notification.collection

import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter

/**
 * Stores the state that [ShadeListBuilder] assigns to this [ListEntry]
 */
data class ListAttachState private constructor(
    /**
     * Null if not attached to the current shade list. If top-level, then the shade list root. If
     * part of a group, then that group's GroupEntry.
     */
    var parent: GroupEntry?,

    /**
     * Identifies the notification order in the entire notification list
     */
    var stableIndex: Int = -1,

    /**
     * The section that this ListEntry was sorted into. If the child of the group, this will be the
     * parent's section. Null if not attached to the list.
     */
    var section: NotifSection?,

    /**
     * If a [NotifFilter] is excluding this entry from the list, then that filter. Always null for
     * [GroupEntry]s.
     */
    var excludingFilter: NotifFilter?,

    /**
     * The [NotifPromoter] promoting this entry to top-level, if any. Always null for [GroupEntry]s.
     */
    var promoter: NotifPromoter?,

    /**
     * If the [VisualStabilityManager] is suppressing group or section changes for this entry,
     * suppressedChanges will contain the new parent or section that we would have assigned to
     * the entry had it not been suppressed by the VisualStabilityManager.
     */
    var suppressedChanges: SuppressedAttachState
) {

    /** Copies the state of another instance. */
    fun clone(other: ListAttachState) {
        parent = other.parent
        stableIndex = other.stableIndex
        section = other.section
        excludingFilter = other.excludingFilter
        promoter = other.promoter
        suppressedChanges.clone(other.suppressedChanges)
    }

    /** Resets back to a "clean" state (the same as created by the factory method) */
    fun reset() {
        parent = null
        section = null
        excludingFilter = null
        promoter = null
        stableIndex = -1
        suppressedChanges.reset()
    }

    companion object {
        @JvmStatic
        fun create(): ListAttachState {
            return ListAttachState(
                    null,
                    -1,
                    null,
                    null,
                    null,
                SuppressedAttachState.create())
        }
    }
}
