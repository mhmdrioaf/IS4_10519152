package com.ananta.is4_10519152

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ananta.is4_10519152.adapter.ContactsAdapter
import com.ananta.is4_10519152.dao.ContactEntity
import com.ananta.is4_10519152.viemodels.MainViewModel
import com.ananta.is4_10519152.viemodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

//    private val api by lazy { ApiRetrofit().endpoint }

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var tvEmptyContact: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = resources.getString(R.string.contact)
        setView()
        setListener()
        setRecycler()

        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllContacts().observe(this, contactObserver)

    }

    private fun setView() {
        recyclerView = findViewById(R.id.rv_contacts)
        fabAdd = findViewById(R.id.fab_add)
        tvEmptyContact = findViewById(R.id.tv_empty_list)
    }

    private fun setListener() {
        fabAdd.setOnClickListener{
            val intent = Intent(this@MainActivity, InsertActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecycler() {

        contactsAdapter = ContactsAdapter(object : ContactsAdapter.OnAdapterListener {
            //            override fun onClick(contact: Contacts.Contact) {
//                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
//                intent.putExtra("contact", contact)
//                startActivity(intent)
//            }
//
//            override fun onClick(contact: ContactEntity) {
//                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
//                intent.putExtra("contact", contact)
//                startActivity(intent)
//            }
            override fun onClick(contact: ContactEntity) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra(UpdateActivity.EXTRA_CONTACT, contact)
                startActivity(intent)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = contactsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

//    private fun getContact() {
//        api.read().enqueue(object: Callback<Contacts> {
//            override fun onResponse(call: Call<Contacts>, response: Response<Contacts>) {
//                val listData = response.body()!!.result
//                contactsAdapter.setData(listData)
//            }
//
//            override fun onFailure(call: Call<Contacts>, t: Throwable) {
//                Log.e("onFailure:", t.message.toString())
//            }
//
//        })
//    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    private val contactObserver = Observer<List<ContactEntity>> { contactList ->
        if (contactList.isNotEmpty()) {
            contactsAdapter.setListContacts(contactList)
            tvEmptyContact.visibility = View.GONE
        } else if (contactList.isEmpty()) {
            contactsAdapter.setListContacts(contactList)
            tvEmptyContact.visibility = View.VISIBLE
        }
    }
}