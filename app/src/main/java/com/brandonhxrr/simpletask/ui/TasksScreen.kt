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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brandonhxrr.simpletask.R
import com.brandonhxrr.simpletask.data.Task
import com.brandonhxrr.simpletask.data.TaskList
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
fun TaskScreen(
    taskViewModel: TaskViewModel = viewModel()
) {
    //val data = TaskData()
    val taskUiState by taskViewModel.uiState.collectAsState()

    println("SIXEEE: " + taskUiState.taskList.size)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.tasklogo),
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
            var text by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = padding.calculateTopPadding()),
            ) {
                ScrollableTabRow(selectedTabIndex = taskUiState.selectedTab,
                    edgePadding = 0.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp),
                    divider = { Divider(color = MaterialTheme.colorScheme.background) }) {

                    var showDialog by remember { mutableStateOf(false) }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                showDialog = false
                            },
                            icon = {
                                Icon(Icons.Rounded.List, null)
                            },
                            title = {
                                Text(text = "Nueva lista")
                            },
                            text = {
                                OutlinedTextField(
                                    value = text,
                                    onValueChange = { text = it },
                                    label = { Text("Título de la lista") }
                                )
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        showDialog = false
                                        taskUiState.taskList.plus(TaskList(text, listOf()))
                                    }
                                ) {
                                    Text("Hecho")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        showDialog = false
                                    }
                                ) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }


                    taskUiState.taskList.forEachIndexed { index, taskList ->
                        Tab(
                            selected = taskUiState.selectedTab == index,
                            onClick = { taskViewModel.onTabClicked(index) },
                            text = {
                                Text(
                                    text = taskList.tabName,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Tab(
                        selected = taskUiState.selectedTab == taskUiState.taskList.size,
                        onClick = { showDialog = true },
                        text = {
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Localized description"
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Nueva lista",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        },
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                AnimatedContent(
                    targetState = taskUiState.selectedTab,
                    transitionSpec = {
                        slideInHorizontally(
                            initialOffsetX = { -it },
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        ) with slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        )
                    }, label = ""
                ) { tab ->
                    TabContent(taskUiState.taskList[tab].tasks)
                }
            }
        }
    )
}

@Composable
fun TabContent(tabItems: List<Task>) {
    LazyColumn {
        items(tabItems.size) { taskNumber ->
            TaskElement(task = tabItems[taskNumber])
        }
    }
}

@Composable
fun TaskElement(task: Task) {
    val isChecked = remember { mutableStateOf(task.done) }

    LaunchedEffect(isChecked.value) {
        task.done = isChecked.value
    }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = { isChecked.value = !isChecked.value },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
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