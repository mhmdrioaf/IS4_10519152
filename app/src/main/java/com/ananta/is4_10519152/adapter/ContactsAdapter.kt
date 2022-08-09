package com.ananta.is4_10519152.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ananta.is4_10519152.R
import com.ananta.is4_10519152.dao.ContactEntity
import com.ananta.is4_10519152.helper.ContactDiffCallback

class ContactsAdapter internal constructor(
    private val listener: OnAdapterListener,
) : RecyclerView.Adapter<ContactsAdapter.Holder>() {
    private val listContacts = ArrayList<ContactEntity>()

    fun setListContacts(listContacts: List<ContactEntity>) {
        val diffCallback = ContactDiffCallback(this.listContacts, listContacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listContacts.clear()
        this.listContacts.addAll(listContacts)
        diffResult.dispatchUpdatesTo(this)
    }


    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_contact_name)
        val cvList: CardView = view.findViewById(R.id.cv_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val contact = listContacts[position]
        holder.tvName.text = contact.contactName

        holder.cvList.setOnClickListener {
            listener.onClick(contact)
        }
    }

    override fun getItemCount(): Int = listContacts.size
/*

This function is for the MySQL database use

    fun setData(data: List<Contacts.Contact>) {
        arrayList.clear()
        arrayList.addAll(data)
        notifyDataSetChanged()
    }
 */

    interface OnAdapterListener {
        fun onClick(contact: ContactEntity)
    }
}