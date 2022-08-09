package com.ananta.is4_10519152

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ananta.is4_10519152.api.ApiRetrofit
import com.ananta.is4_10519152.data.Insert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtNumber: EditText
    private lateinit var addButton: Button

    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        supportActionBar?.title = resources.getString(R.string.add_contact)

        setView()
        setListener()
    }

    private fun setView() {
        edtName = findViewById(R.id.edt_name)
        edtNumber = findViewById(R.id.edt_number)
        addButton = findViewById(R.id.btn_save)
    }

    private fun setListener() {
        addButton.setOnClickListener{
            if (edtName.text.toString().isNotEmpty() && edtNumber.text.toString().isNotEmpty()) {
                api.create(edtName.text.toString(), edtNumber.text.toString())
                    .enqueue(object: Callback<Insert> {
                        override fun onResponse(call: Call<Insert>, response: Response<Insert>) {
                            if (response.isSuccessful) {
                                Toast.makeText(applicationContext, edtName.text.toString() + " " + resources.getString(R.string.add_success), Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<Insert>, t: Throwable) {
                            Toast.makeText(applicationContext, resources.getString(R.string.add_failed), Toast.LENGTH_SHORT).show()
                        }

                    })
            } else {
                Toast.makeText(applicationContext, resources.getString(R.string.empty_input), Toast.LENGTH_SHORT).show()
            }
        }
    }
}