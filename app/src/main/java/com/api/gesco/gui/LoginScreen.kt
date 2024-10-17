package com.api.gesco.gui

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.api.gesco.R
import com.api.gesco.model.ModelLogin
import com.api.gesco.model.MyViewModel
import com.api.gesco.service.config.SessionManager.Companion.USER_TOKEN

@Composable
fun LoginScreen(navController: NavController, viewModel: MyViewModel, context: Context) {
    val preferences: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.login.collectAsState()

    LaunchedEffect(Unit) {
        email = "aluno@gmail.com"
        password = "123"
    }

    LaunchedEffect(loginState) {
        if (loginState.token.isNotEmpty()) {
            Log.e("Login", "Sucesso: ${loginState.token}")
            viewModel.fetchAluno(context)
            navController.navigate("home")

        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
    ) {
        Text(
            text = stringResource(R.string.welcome),
            color = colorResource(R.color.azul),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.height(30.dp))


        OutlinedTextField(
            value = email,
            onValueChange = { it ->
                email = it
            },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(25.dp),
            label = {
                Text(text = stringResource(R.string.email))
            },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = colorResource(id = R.color.azul),
                unfocusedLabelColor = colorResource(id = R.color.azul),
                focusedContainerColor = colorResource(id = R.color.white),
                unfocusedContainerColor = colorResource(id = R.color.white),
                cursorColor = colorResource(id = R.color.black),
            )
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { it ->
                password = it
            },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(25.dp),
            label = {
                Text(text = stringResource(R.string.password))
            },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = colorResource(id = R.color.azul),
                unfocusedLabelColor = colorResource(id = R.color.azul),
                focusedContainerColor = colorResource(id = R.color.white),
                unfocusedContainerColor = colorResource(id = R.color.white),
                cursorColor = colorResource(id = R.color.black),
            )
        )

        Spacer(Modifier.height(50.dp))

        Button(
            onClick = {
                var body = ModelLogin(
                    email = email,
                    senha = password
                )
                viewModel.fetchLogin(body, context, preferences)

            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFF9F45C), Color(0xFFFFB600)),
                    )
                )
        ) {
            Text(
                text = stringResource(R.string.login),
                fontSize = 24.sp,
                color = colorResource(R.color.azul)
            )
        }
    }
}