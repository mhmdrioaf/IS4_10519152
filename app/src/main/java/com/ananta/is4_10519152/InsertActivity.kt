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

class InsertActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtNumber: EditText
    private lateinit var addButton: Button

    private var isEdit = false
    private var contact: ContactEntity? = null
    private var position = 0

    private lateinit var contactAddUpdateViewModel: ContactAddUpdateViewModel

//    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        supportActionBar?.title = resources.getString(R.string.add_contact)

        contactAddUpdateViewModel = obtainViewModel(this@InsertActivity)

        setView()
        setListener()

        contact = intent.getParcelableExtra(EXTRA_CONTACT)
        if (contact != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            contact = ContactEntity()
        }

        if (isEdit) {
            if (contact != null) {
                contact?.let { contact ->
                    edtName.setText(contact.contactName)
                    edtNumber.setText(contact.contactNumber)
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
        addButton = findViewById(R.id.btn_save)
    }

    private fun setListener() {
//        addButton.setOnClickListener{
//            if (edtName.text.toString().isNotEmpty() && edtNumber.text.toString().isNotEmpty()) {
//                api.create(edtName.text.toString(), edtNumber.text.toString())
//                    .enqueue(object: Callback<Insert> {
//                        override fun onResponse(call: Call<Insert>, response: Response<Insert>) {
//                            if (response.isSuccessful) {
//                                Toast.makeText(applicationContext, edtName.text.toString() + " " + resources.getString(R.string.add_success), Toast.LENGTH_SHORT).show()
//                                finish()
//                            }
//                        }
//
//                        override fun onFailure(call: Call<Insert>, t: Throwable) {
//                            Toast.makeText(applicationContext, resources.getString(R.string.add_failed), Toast.LENGTH_SHORT).show()
//                        }
//
//                    })
//            } else {
//                Toast.makeText(applicationContext, resources.getString(R.string.empty_input), Toast.LENGTH_SHORT).show()
//            }
//        }

        addButton.setOnClickListener {
            val name = edtName.text.toString().trim()
            val number = edtNumber.text.toString().trim()

            if (name.isEmpty()) {
                edtName.error = resources.getString(R.string.empty_input)
            } else if (number.isEmpty()) {
                edtNumber.error = resources.getString(R.string.empty_input)
            } else {
                contact.let { contact ->
                    contact?.contactName = name
                    contact?.contactNumber = number
                    contactAddUpdateViewModel.insert(contact as ContactEntity)
                    Toast.makeText(
                        applicationContext,
                        contact.contactName + " " + resources.getString(R.string.add_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT = "extra_contact"
        const val EXTRA_POSITION = "extra_position"
    }
}