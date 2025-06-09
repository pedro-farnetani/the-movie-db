package com.tmdb.core.navigation.utils

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

fun fadeEnterTransition(duration: Int = 600): EnterTransition {
    return fadeIn(tween(duration))
}

fun fadeExitTransition(duration: Int = 200): ExitTransition {
    return fadeOut(tween(duration))
}
