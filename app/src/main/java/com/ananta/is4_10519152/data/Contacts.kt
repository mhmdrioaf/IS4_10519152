package com.ananta.is4_10519152.data

import java.io.Serializable

data class Contacts(
    val result: List<Contact>
) {
    data class Contact (val contact_id: Int?, val contact_name: String?, val contact_number: String?) : Serializable
}
