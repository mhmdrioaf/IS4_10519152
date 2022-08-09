package com.ananta.is4_10519152.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY contact_name DESC")
    fun getAll(): LiveData<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE contact_name LIKE :name LIMIT 1")
    fun findByName(name: String?): LiveData<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contacts: ContactEntity)

    @Update
    fun update(vararg contact: ContactEntity)

    @Delete
    fun delete(contact: ContactEntity)
}