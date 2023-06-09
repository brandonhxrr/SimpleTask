package com.brandonhxrr.simpletask.ui

import androidx.lifecycle.ViewModel
import com.brandonhxrr.simpletask.data.Task
import com.brandonhxrr.simpletask.data.TaskList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.properties.Delegates

class TaskViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    private lateinit var taskList: List<TaskList>
    private var selectedTab by Delegates.notNull<Int>()

    init {
        loadData()
    }

    fun onTabClicked(tabIndex: Int) {
        selectedTab = tabIndex
        updateUiState()
    }

    private fun updateUiState() {
        val updatedUiState = _uiState.value.copy(taskList = taskList, selectedTab = selectedTab)
        _uiState.value = updatedUiState
    }

    private fun loadData() {
        val lists : List<TaskList> = listOf(
            TaskList(
                tabName = "Tab1",
                tasks = listOf(
                    Task(false, "Tarea 1 de la tab 1"),
                    Task(false, "Tarea 2 de la tab 1"),
                )
            ),
            TaskList(
                tabName = "Tab2",
                tasks = listOf(
                    Task(false, "Tarea 1 de la tab 2"),
                    Task(false, "Tarea 2 de la tab 2"),
                    Task(false, "Tarea 3 de la tab 2")
                )
            )
        )

        selectedTab = 0

        taskList = lists
        updateUiState()
    }
}
