package com.brandonhxrr.simpletask.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandonhxrr.simpletask.R
import com.brandonhxrr.simpletask.ui.theme.SimpleTaskTheme

class TasksScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {
                    TaskScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Row(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ){
                            Image(painter = painterResource(id = R.drawable.tasklogo),
                                contentDescription = "App logo",
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stringResource(id = R.string.app_name),
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Tareas",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 32.dp),
                    color = Color(0xFF7286D3)
                )
            }
        }
    )
}




@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    SimpleTaskTheme(false) {
        TaskScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun TasksScreenPreviewDark() {
    SimpleTaskTheme(true) {
        TaskScreen()
    }
}