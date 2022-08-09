package com.ananta.is4_10519152.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ananta.is4_10519152.R
import com.ananta.is4_10519152.data.Contacts

class ContactsAdapter(
    private val arrayList: ArrayList<Contacts.Contact>,
    private val listener: OnAdapterListener,
    ): RecyclerView.Adapter<ContactsAdapter.Holder>() {
    class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_contact_name)
        val cvList: CardView = view.findViewById(R.id.cv_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val contact = arrayList[position]
        holder.tvName.text = contact.contact_name

        holder.cvList.setOnClickListener {
            listener.onClick( contact )
        }
    }

    override fun getItemCount(): Int = arrayList.size

    fun setData(data: List<Contacts.Contact>) {
        arrayList.clear()
        arrayList.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(contact: Contacts.Contact)
    }
}