package com.brandonhxrr.simpletask

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandonhxrr.simpletask.ui.Main
import com.brandonhxrr.simpletask.ui.Screens
import com.brandonhxrr.simpletask.ui.TaskScreen
import com.brandonhxrr.simpletask.ui.theme.SimpleTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Start()
                }
            }
        }
    }
}

@Composable
fun Start() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.name
    ){
        composable(Screens.MainScreen.name){
            Main()
            val timer = object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    navController.navigate(Screens.TasksScreen.name)
                }
            }

            timer.start()

        }
        composable(Screens.TasksScreen.name){
            TaskScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleTaskTheme {
        Main()
    }
}