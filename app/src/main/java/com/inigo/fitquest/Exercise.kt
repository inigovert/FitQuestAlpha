package com.inigo.fitquest

data class Exercise(val name: String, val description: String)
val exercises = listOf(
    Exercise("Push-ups", "A basic exercise for chest and triceps."),
    Exercise("Squats", "A lower body workout targeting the thighs, hips, and buttocks."),
    Exercise("Burpees", "A full body exercise used for strength training and aerobic exercise."),
    Exercise("Planks", "Core strengthening exercise."),
    Exercise("Lunges", "Target leg and glute muscles.")
)

