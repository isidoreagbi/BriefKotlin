package com.example.todolistapp.ui.theme.views

sealed class AuthRouteScreens(val path: String){
    object RegistrationScreen : AuthRouteScreens("registration")
    object SignInScreen : AuthRouteScreens("sign-in")
    object ForgottenPasswordScreen : AuthRouteScreens("forgotten-password")
    object OTPCodeScreen : AuthRouteScreens("otp-code")
    object NewPasswordScreen : AuthRouteScreens("new-password")
}
sealed class StartRouteScreens(val path: String) {
    object StartScreen : StartRouteScreens("start")
}

sealed class MainRouteScreens(val path: String) {
    object HomeScreen : MainRouteScreens("home")
}