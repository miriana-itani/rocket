package com.monese.rocket.utils

import com.monese.rocket.model.models.Launch

class CompareObjects {
    companion object {
        fun compare(oldItem: Launch, newItem: Launch): Boolean {
            return (oldItem.tbd == newItem.tbd && oldItem.lastDateUpdate == newItem.lastDateUpdate &&
                    oldItem.links == newItem.links && oldItem.details == newItem.details
                    && oldItem.missionId == newItem.missionId && oldItem.missionName == newItem.missionName
                    && oldItem.rocket == newItem.rocket)
        }
    }
}