package com.ananta.is4_10519152

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ananta.is4_10519152.adapter.ContactsAdapter
import com.ananta.is4_10519152.api.ApiRetrofit
import com.ananta.is4_10519152.data.Contacts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endpoint }

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = resources.getString(R.string.contact)
        setView()
        setListener()
        setRecycler()

    }

    override fun onStart() {
        super.onStart()
        getContact()
    }

    private fun setView() {
        recyclerView = findViewById(R.id.rv_contacts)
        fabAdd = findViewById(R.id.fab_add)
    }

    private fun setListener() {
        fabAdd.setOnClickListener{
            val intent = Intent(this@MainActivity, InsertActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecycler() {
        contactsAdapter = ContactsAdapter(arrayListOf(), object : ContactsAdapter.OnAdapterListener {
            override fun onClick(contact: Contacts.Contact) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("contact", contact)
                startActivity(intent)
            }

        })
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = contactsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getContact() {
        api.read().enqueue(object: Callback<Contacts> {
            override fun onResponse(call: Call<Contacts>, response: Response<Contacts>) {
                val listData = response.body()!!.result
                contactsAdapter.setData(listData)
            }

            override fun onFailure(call: Call<Contacts>, t: Throwable) {
                Log.e("onFailure:", t.message.toString())
            }

        })
    }
}