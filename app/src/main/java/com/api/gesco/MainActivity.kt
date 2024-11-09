package com.api.gesco

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.api.gesco.components.Navigation
import com.api.gesco.gui.ActivitiesScreen
import com.api.gesco.gui.DisciplineScreen
import com.api.gesco.gui.EventsScreen
import com.api.gesco.gui.FrequencyScreen
import com.api.gesco.gui.GridHourScreen
import com.api.gesco.gui.HomeScreen
import com.api.gesco.gui.LoginScreen
import com.api.gesco.model.MyViewModel
import com.api.gesco.ui.theme.GescoTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GescoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Gesco(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Gesco(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentRoute = remember { mutableStateOf("login") }
    val viewModel : MyViewModel = viewModel()
    val context = LocalContext.current

    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute.value = destination.route ?: "route1"
    }

    Scaffold(
        bottomBar = {
            when (currentRoute.value) {
                "login" -> {}
                else -> Navigation(navController = navController)
            }
        },
        modifier = modifier
    ){innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") { LoginScreen(navController, viewModel, context) }
            composable("home") { HomeScreen(navController,viewModel, context)  }
            composable("gridHour") { GridHourScreen(navController,viewModel, context)  }
            composable("events") { EventsScreen(navController,viewModel, context)  }
            composable("frequency") { FrequencyScreen(navController,viewModel, context)  }
            composable("discipline") { DisciplineScreen(navController,viewModel, context)  }
            composable("activities") { ActivitiesScreen(navController,viewModel, context)  }
        }
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    GescoTheme {
//        Gesco("Android")
//    }
//}
