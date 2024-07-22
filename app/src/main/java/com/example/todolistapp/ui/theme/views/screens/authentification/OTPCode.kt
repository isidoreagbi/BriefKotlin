package com.example.todolistapp.ui.theme.views.screens.authentification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.ui.theme.components.DefaultTextField
import com.example.todolistapp.ui.theme.views.AuthRouteScreens
import com.example.todolistapp.ui.theme.views.MainRouteScreens
import com.example.todolistapp.ui.theme.views.StartRouteScreens
import com.example.todolistapp.ui.theme.views.layout.BackTopAppBar
import com.example.todolistapp.R
import com.example.todolistapp.ui.theme.theme.TodolistAppTheme
import com.example.todolistapp.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun OTPCode(navController: NavController, email: String = ""){
    val authViewModel = viewModel(modelClass = AuthViewModel::class.java)
    val authState = authViewModel.state
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var code by rememberSaveable { mutableStateOf(email) }
    var isCodeError by rememberSaveable { mutableStateOf(false) }
    var isCodeErrorText by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    TodolistAppTheme {
        Scaffold(
            topBar = {
                BackTopAppBar(navController = navController)
            }
        ){innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimensionResource(id = R.dimen.size_4)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(id = R.dimen.size_8))
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
                    Text(
                        text = stringResource(id = R.string.otp_code),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = R.string.otp_code_text),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_3))
                    ) {
                        DefaultTextField(
                            value = code,
                            onValueChange = { code = it },
                            isError = isCodeError,
                            errorText = isCodeErrorText,
                            label = stringResource(id = R.string.otp_code),
                            placeholder = stringResource(id = R.string.otp_code_placeholder)
                        )
                        Button(
                            onClick ={
                                isLoading = true
                                scope.launch{
                                    authViewModel.checkOtpCode(email = email, code = code)
                                    if (authState.otpCodes.isEmpty()) {
                                        isCodeError = true
                                        isCodeErrorText = context.getString(R.string.otp_code_error_text)
                                        isLoading = false

                                    } else {
                                        //authViewModel.activateUserByEmail(email = email)
                                        navController.navigate(MainRouteScreens.HomeScreen.path) {
                                            popUpTo(StartRouteScreens.StartScreen.path) { inclusive = true }
                                            popUpTo(AuthRouteScreens.RegistrationScreen.path) { inclusive = true }
                                            popUpTo(AuthRouteScreens.OTPCodeScreen.path) { inclusive = true }
                                        }
                                    }
                                }
                            },
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_3)),
                            modifier = Modifier.fillMaxWidth(),
                        ){
                            Text(text = if(isLoading) stringResource(id = R.string.loading) else stringResource(id = R.string.submit))
                        }
                    }
                }
            }

        }
    }
}
@Preview
@Composable
fun OTPCodePreview(){
    TodolistAppTheme {
        OTPCode(navController = rememberNavController())

    }

}