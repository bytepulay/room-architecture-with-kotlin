package com.nanolabs.architecturetutorial

import androidx.room.ColumnInfo

data class NoteDataClass(@ColumnInfo(name = "title")
                         var myTitle:String,

                         @ColumnInfo(name = "description")
                         var myDescription:String,

                         @ColumnInfo(name = "priority")
                         var myPriority:Int )
