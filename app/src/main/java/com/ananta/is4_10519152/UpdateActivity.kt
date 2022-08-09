package com.ananta.is4_10519152

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ananta.is4_10519152.dao.ContactEntity
import com.ananta.is4_10519152.viemodels.ContactAddUpdateViewModel
import com.ananta.is4_10519152.viemodels.ViewModelFactory

class UpdateActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtNumber: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private var isEdit = false
    private var contact: ContactEntity? = null

    private lateinit var contactAddUpdateViewModel: ContactAddUpdateViewModel

//    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar!!.title = resources.getString(R.string.edit)
        setView()
        setListener()

        contactAddUpdateViewModel = obtainViewModel(this@UpdateActivity)

        contact = intent.getParcelableExtra(EXTRA_CONTACT)
        if (contact != null) {
            isEdit = true
        } else {
            contact = ContactEntity()
        }

        if (isEdit) {
            if (contact != null) {
                contact?.let {
                    edtName.setText(it.contactName)
                    edtNumber.setText(it.contactNumber)
                }
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): ContactAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(ContactAddUpdateViewModel::class.java)
    }

    private fun setView() {
        edtName = findViewById(R.id.edt_name)
        edtNumber = findViewById(R.id.edt_number)
        btnSave = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btn_delete)

        edtName.setText(contact?.contactName)
        edtNumber.setText(contact?.contactNumber)

    }

    private fun setListener() {
//        btnSave.setOnClickListener {
//            if (edtName.text.toString().isNotEmpty() && edtNumber.text.toString().isNotEmpty()) {
//                api.update(contact.contact_id, edtName.text.toString(), edtNumber.text.toString())
//                    .enqueue(object: Callback<Update> {
//                        override fun onResponse(call: Call<Update>, response: Response<Update>) {
//                            if (response.isSuccessful) {
//                                Toast.makeText(applicationContext, resources.getString(R.string.update_success), Toast.LENGTH_SHORT).show()
//                                finish()
//                            }
//                        }
//
//                        override fun onFailure(call: Call<Update>, t: Throwable) {
//                            Toast.makeText(applicationContext, resources.getString(R.string.update_failed), Toast.LENGTH_SHORT).show()
//                            finish()
//                        }
//                    })
//            }
//        }


//        btnDelete.setOnClickListener {
//            api.delete(contact.contact_id!!)
//                .enqueue(object: Callback<Delete> {
//                    override fun onResponse(call: Call<Delete>, response: Response<Delete>) {
//                        Toast.makeText(applicationContext, contact.contactName + " " + resources.getString(R.string.delete_success), Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//
//                    override fun onFailure(call: Call<Delete>, t: Throwable) {
//                        Toast.makeText(applicationContext, resources.getString(R.string.delete_failed), Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//
//                })
//        }

        btnSave.setOnClickListener {
            val name = edtName.text.toString().trim()
            val number = edtNumber.text.toString().trim()

            if (name.isEmpty()) {
                edtName.error = resources.getString(R.string.empty_input)
            } else if (number.isEmpty()) {
                edtNumber.error = resources.getString(R.string.empty_input)
            } else {
                contact.let {
                    contact?.contactName = name
                    contact?.contactNumber = number
                    contactAddUpdateViewModel.update(contact as ContactEntity)
                    Toast.makeText(
                        applicationContext,
                        contact?.contactName + " " + resources.getString(R.string.update_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }

        btnDelete.setOnClickListener {
            contactAddUpdateViewModel.delete(contact as ContactEntity)
            Toast.makeText(
                applicationContext,
                contact?.contactName + " " + resources.getString(R.string.delete_success),
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    companion object {
        const val EXTRA_CONTACT = "extra_contact"
    }
}