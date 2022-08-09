package com.ananta.is4_10519152.dao

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "contacts")
@Parcelize
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    var contact_id: Int = 0,

    @ColumnInfo(name = "contact_name")
    var contactName: String? = null,

    @ColumnInfo(name = "contact_number")
    var contactNumber: String? = null
) : Parcelable
