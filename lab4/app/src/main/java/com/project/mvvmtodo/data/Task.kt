package com.project.mvvmtodo.data

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "task_table",
    foreignKeys = [ForeignKey(entity = Category::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("categoryId"),
                onDelete = ForeignKey.CASCADE)])
@Parcelize
data class Task(
    val title: String,
    val categoryId: Int,
    val categoryTitle: String = "",
    val status: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
}