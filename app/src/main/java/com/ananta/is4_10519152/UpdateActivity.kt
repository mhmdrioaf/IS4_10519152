package com.ananta.is4_10519152

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ananta.is4_10519152.api.ApiRetrofit
import com.ananta.is4_10519152.data.Contacts
import com.ananta.is4_10519152.data.Delete
import com.ananta.is4_10519152.data.Update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtNumber: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private val api by lazy { ApiRetrofit().endpoint }
    private val contact by lazy { intent.getSerializableExtra("contact") as Contacts.Contact}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar!!.title = resources.getString(R.string.edit)
        setView()
        setListener()
    }

    private fun setView() {
        edtName = findViewById(R.id.edt_name)
        edtNumber = findViewById(R.id.edt_number)
        btnSave = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btn_delete)

        edtName.setText(contact.contact_name)
        edtNumber.setText(contact.contact_number)

    }

    private fun setListener() {
        btnSave.setOnClickListener {
            if (edtName.text.toString().isNotEmpty() && edtNumber.text.toString().isNotEmpty()) {
                api.update(contact.contact_id!!, edtName.text.toString(), edtNumber.text.toString())
                    .enqueue(object: Callback<Update> {
                        override fun onResponse(call: Call<Update>, response: Response<Update>) {
                            if (response.isSuccessful) {
                                Toast.makeText(applicationContext, resources.getString(R.string.update_success), Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<Update>, t: Throwable) {
                            Toast.makeText(applicationContext, resources.getString(R.string.update_failed), Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    })
            }
        }

        btnDelete.setOnClickListener {
            api.delete(contact.contact_id!!)
                .enqueue(object: Callback<Delete> {
                    override fun onResponse(call: Call<Delete>, response: Response<Delete>) {
                        Toast.makeText(applicationContext, contact.contact_name + " " + resources.getString(R.string.delete_success), Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    override fun onFailure(call: Call<Delete>, t: Throwable) {
                        Toast.makeText(applicationContext, resources.getString(R.string.delete_failed), Toast.LENGTH_SHORT).show()
                        finish()
                    }

                })
        }
    }
}