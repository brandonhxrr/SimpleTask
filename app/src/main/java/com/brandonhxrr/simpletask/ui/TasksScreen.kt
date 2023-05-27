package com.brandonhxrr.simpletask.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandonhxrr.simpletask.R
import com.brandonhxrr.simpletask.data.Task
import com.brandonhxrr.simpletask.data.TaskData
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TaskScreen() {
    val data : TaskData = TaskData()

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
                                modifier = Modifier.align(Alignment.CenterVertically),
                                color = MaterialTheme.colorScheme.onBackground
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
        content = { padding ->
            var state by remember { mutableStateOf(0) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = padding.calculateTopPadding()),
            ) {
                ScrollableTabRow(selectedTabIndex = state, edgePadding = 0.dp) {
                    data.lists.forEachIndexed { index, taskList ->
                        Tab(
                            selected = state == index,
                            onClick = { state = index },
                            text = { Text(text = taskList.tabName, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Tab(
                        selected = state == data.lists.size,
                        onClick = {state = data.lists.size},
                        text = {
                            Row{
                                Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Localized description"
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Nueva lista", modifier = Modifier.align(Alignment.CenterVertically))
                            }
                               },
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onBackground
                    )
                }
                AnimatedContent(
                    targetState = state,
                    transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                    ) with slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                    )
                }) {
                    tab -> TabContent(data.lists[tab].tasks)
                }
            }
        }
    )
}


@Composable
fun TabContent(tabItems: List<Task>){
    LazyColumn {
        items(tabItems.size){taskNumber ->
            TaskElement(task = tabItems[taskNumber])
        }
    }
}

@Composable
fun TaskElement(task: Task){
    Card(
       modifier = Modifier
           .padding(5.dp)
           .fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Checkbox(checked = task.done, onCheckedChange = {task.done = !task.done}, modifier = Modifier.align(Alignment.CenterVertically))
                Text(text = task.name, modifier = Modifier.align(Alignment.CenterVertically))
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