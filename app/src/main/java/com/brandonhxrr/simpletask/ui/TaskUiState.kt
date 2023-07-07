package com.brandonhxrr.simpletask.ui

import com.brandonhxrr.simpletask.data.TaskList

data class TaskUiState(
    val taskList: List<TaskList> = ArrayList(),
    val selectedTab: Int = 0
)