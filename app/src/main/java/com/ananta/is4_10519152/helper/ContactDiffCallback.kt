package com.ananta.is4_10519152.helper

import androidx.recyclerview.widget.DiffUtil
import com.ananta.is4_10519152.dao.ContactEntity

class ContactDiffCallback(
    private val mOldContactList: List<ContactEntity>,
    private val mNewContactList: List<ContactEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldContactList.size
    }

    override fun getNewListSize(): Int {
        return mNewContactList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldContactList[oldItemPosition].contact_id == mNewContactList[newItemPosition].contact_id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldContactList[oldItemPosition]
        val newEmployee = mNewContactList[newItemPosition]

        return oldEmployee.contactName == newEmployee.contactName && oldEmployee.contactNumber == newEmployee.contactNumber
    }
}