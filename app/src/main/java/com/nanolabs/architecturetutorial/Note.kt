package com.nanolabs.architecturetutorial

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(){

    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    @ColumnInfo(name = "title")
     var title:String?=null

    @ColumnInfo(name = "description")
     var description:String?=null

    @ColumnInfo(name = "priority")
     var priority:Int?=0
}