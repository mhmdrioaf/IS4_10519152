package com.ananta.is4_10519152.viemodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ananta.is4_10519152.dao.ContactEntity
import com.ananta.is4_10519152.repository.ContactRepository

class ContactAddUpdateViewModel(application: Application) : ViewModel() {
    private val mContactRepository: ContactRepository = ContactRepository(application)

    fun insert(contact: ContactEntity) {
        mContactRepository.insert(contact)
    }

    fun update(contact: ContactEntity) {
        mContactRepository.update(contact)
    }

    fun delete(contact: ContactEntity) {
        mContactRepository.delete(contact)
    }

}