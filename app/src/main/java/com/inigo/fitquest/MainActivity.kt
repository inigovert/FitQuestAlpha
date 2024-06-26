package com.inigo.fitquest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) {
                NavHost(navController, startDestination = Screen.StartWorkout.route) {
                    composable(Screen.StartWorkout.route) { StartWorkoutScreen() }
                    composable(Screen.CheckProgress.route) { CheckProgressScreen() }
                    composable(Screen.CheckRewards.route) { CheckRewardsScreen() }
                    composable(Screen.WorkoutLibrary.route) { WorkoutLibraryScreen() }
                    composable(Screen.SendFeedback.route) { SendFeedbackScreen() }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.StartWorkout,
        Screen.CheckProgress,
        Screen.CheckRewards,
        Screen.WorkoutLibrary,
        Screen.SendFeedback
    )
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.route) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
@Composable
fun StartWorkoutScreen() {
    Text("Start Workout", style = MaterialTheme.typography.h6)
}
@Composable
fun CheckProgressScreen() {
    Text("Check Progress", style = MaterialTheme.typography.h6)
}

@Composable
fun CheckRewardsScreen() {
    Text("Check Rewards", style = MaterialTheme.typography.h6)
}

@Composable
fun WorkoutLibraryScreen() {
    Column {
        Text("Workout Library", style = MaterialTheme.typography.h6, modifier = Modifier.padding(16.dp))
        ExerciseList(exercises)
    }
}

@Composable
fun ExerciseList(exercises: List<Exercise>) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(exercises) { exercise ->
            ExerciseItem(exercise)
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(exercise.name, style = MaterialTheme.typography.h6)
        Text(exercise.description, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun SendFeedbackScreen() {
    Text("Send Feedback", style = MaterialTheme.typography.h6)
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
