package com.example.todolistapp.ui.theme.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistapp.ui.theme.views.screens.authentification.ForgottenPassword
import com.example.todolistapp.ui.theme.views.screens.authentification.NewPassword
import com.example.todolistapp.ui.theme.views.screens.authentification.OTPCode
import com.example.todolistapp.ui.theme.views.screens.authentification.SignIn
import com.example.todolistapp.ui.theme.views.screens.user.Home
import com.example.todolistapp.ui.theme.views.screens.authentification.Registration
import com.example.todolistapp.Start

@Composable
fun Navigation(
    startDestination: String = StartRouteScreens.StartScreen.path
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = StartRouteScreens.StartScreen.path){
            Start(navController = navController)
        }
        composable(route = AuthRouteScreens.RegistrationScreen.path){
            Registration(navController = navController)
        }
        composable(route = AuthRouteScreens.SignInScreen.path) {
            SignIn(navController = navController)
        }
        composable(route = AuthRouteScreens.ForgottenPasswordScreen.path) {
            ForgottenPassword(navController = navController)
        }
        composable(route = "${AuthRouteScreens.OTPCodeScreen.path}/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) {
            val email = it.arguments?.getString("email") ?: ""
            OTPCode(navController = navController, email = email)
        }
        composable(route = AuthRouteScreens.NewPasswordScreen.path) {
            NewPassword(navController = navController)
        }
        composable(route = MainRouteScreens.HomeScreen.path) {
            Home(navController = navController)
        }
    }
}