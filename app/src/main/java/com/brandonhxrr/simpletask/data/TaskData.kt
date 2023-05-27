package com.brandonhxrr.simpletask.data

class TaskData {
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

}
