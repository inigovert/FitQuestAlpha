package com.inigo.fitquest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector) {
    object StartWorkout : Screen("start_workout", Icons.Filled.FitnessCenter)
    object CheckProgress : Screen("check_progress", Icons.Filled.Timeline)
    object CheckRewards : Screen("check_rewards", Icons.Filled.CardGiftcard)
    object WorkoutLibrary : Screen("workout_library", Icons.Filled.LibraryBooks)
    object SendFeedback : Screen("send_feedback", Icons.Filled.Feedback)
}