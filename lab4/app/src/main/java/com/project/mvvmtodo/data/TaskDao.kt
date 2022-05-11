package com.project.mvvmtodo.data

import androidx.room.*
import com.project.mvvmtodo.tasks.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<Task>> =
        when(sortOrder) {
            SortOrder.BY_TITLE -> getTasksSortedByTitle(query, hideCompleted)
            SortOrder.BY_PRIORITY -> getTasksSortedByPriority(query, hideCompleted)
        }

    @Query("SELECT * FROM task_table WHERE (status != :hideCompleted OR status = 0) AND title LIKE '%' || :searchQuery || '%' ORDER BY title ASC")
    fun getTasksSortedByTitle(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE (status != :hideCompleted OR status = 0) AND title LIKE '%' || :searchQuery || '%' ORDER BY categoryId DESC")
    fun getTasksSortedByPriority(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM category_table WHERE id = :id")
    fun getCategory(id: Int): Category

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM task_table WHERE status = 1")
    suspend fun deleteCompletedTasks()
}