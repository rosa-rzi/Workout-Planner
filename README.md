# Workout Planner
> Programming language: Java

> Tools: JUnit, Json

> IDE: IntelliJ

## About the Project

This project is a fitness-related console designed to help make it easier for people to organize their workouts. It was inspired by myFitnessPal however instead of focusing on the user's calories and diet this application focuses on the user's workout plan. It's designed to help users plan their workout schedule and keep track of their progress. The user can create different workouts; list exercises for a particular body part; visualize workout plan for the week and view their progress.




**Usage**

Anyone who goes to the gym would benefit from this application. Whether they're a beginner at the gym, or a personal trainer trying to give their clients a plan, this workout planner would benefit them greatly as it makes it easier for people to plan workouts.
##Motivation Behind the Project
When I first started going to the gym I had no plan or structure for my workouts. I would waste time thinking of what exercises to do next and not really knowing if I was gaining strength. Now I write out my workouts on a Google document ahead of time, but it's not efficient. For this reason I decided to make a workout planner for my project.

**User Stories**

- As a user, I want to be able to select a day and create a workout on my schedule
- As a user, I want to be able to add exercises to a workout
- As a user, I want to be able to create an exercise and input the following: name, number of sets/repetitions and rest time. (grade this)
- As a user, I want to be able to select a workout and view a list of exercises in the workout
- As a user, I want to be able to view all my workouts for the week
- As a user, I want to be able to save my workout plan to file
- As a user, I want to be able to be able to load my workout plan from file
 

**For Future**

If I had more time to work on the project I would do the following:
- move functionality in the WorkoutPlannerGUI into multiple different classes such as  Buttons, OpenDialogue, 
Panel, EventListener. I would create the new class, copy all fields and methods relating to its functionality
 into the new class,leave original methods in their original locations but replace the bodies with a call to the method in
its new class. Parameters are passed through the new methods from the original methods.
- reduce coupling between Schedule, Workout and TrainingDay class (currently a three-way association) by removing
trainingDay from the workout class and accessing it through Schedule instead by making the association between workout
and schedule bidirectional.
