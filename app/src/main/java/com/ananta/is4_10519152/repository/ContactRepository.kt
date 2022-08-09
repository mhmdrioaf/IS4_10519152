package com.ananta.is4_10519152.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ananta.is4_10519152.dao.ContactDao
import com.ananta.is4_10519152.dao.ContactEntity
import com.ananta.is4_10519152.dao.DaoDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ContactRepository(application: Application) {
    private val mContactDao: ContactDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = DaoDatabase.getDatabase(application)
        mContactDao = db.contactDao()
    }

    fun getAllContacts(): LiveData<List<ContactEntity>> = mContactDao.getAll()

    fun insert(contact: ContactEntity) {
        executorService.execute { mContactDao.insert(contact) }
    }

    fun delete(contact: ContactEntity) {
        executorService.execute { mContactDao.delete(contact) }
    }

    fun update(contact: ContactEntity) {
        executorService.execute { mContactDao.update(contact) }
    }
}