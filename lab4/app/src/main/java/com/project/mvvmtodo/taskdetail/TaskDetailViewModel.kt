package com.project.mvvmtodo.taskdetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.project.mvvmtodo.data.Task
import com.project.mvvmtodo.data.TaskDao

class TaskDetailViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao,
    @Assisted private val state: SavedStateHandle
): ViewModel() {

    val task = state.get<Task>("task")

    val taskTitle = task?.title
    val taskStatus = task?.status
    val taskCategory = task?.categoryTitle
}