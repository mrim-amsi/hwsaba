package com.example.firstass

import Adpter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity(), Adpter.onClick {
    private val db: FirebaseFirestore? = FirebaseFirestore.getInstance()

    private val List = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataToFirestore()
       button.setOnClickListener {
          if(editTextTextPersonName.text.toString().isNotEmpty()&& editTextNumber.text.toString().isNotEmpty()&&editTextTextEmailAddress.text.toString().isNotEmpty())
           addDataToFirestore(editTextTextPersonName.text.toString(),editTextNumber.text.toString() , editTextTextEmailAddress.text.toString())

   //   addDataToFirestore("aaa", "123", "111")
       }
    }

     fun addDataToFirestore(Name: String, phone: String, email: String) {
        val user: MutableMap<String, Any> = HashMap()
        val UUIDId = UUID.randomUUID().toString()
        user["id"] =UUIDId
        user["name"] = Name
        user["phone"] = phone
        user["email"] = email

        db!!.collection("users")!!.document(UUIDId)
            .set(user)
                .addOnSuccessListener { documentReference -> getDataToFirestore() }
                .addOnFailureListener { e -> Log.w("LogForTest", "Error adding document", e) }

    }
    private fun getDataToFirestore() {
        List.clear()
        db!!.collection("users")
            .get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    List?.add(document.toObject(Model::class.java));
                }
                val adapter = Adpter(this, List ,this )
                rc.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false)
                rc.adapter = adapter
                adapter.notifyDataSetChanged()
                }


            }

    override fun onClickItem(id: String, position: Int) {
        db!!.collection("users").document(id)
            .delete()
            .addOnSuccessListener {
                List.removeAt(position)
                val adapter = Adpter(this, List ,this )
                rc.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false)
                rc.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e -> Log.w("fortest", "Error deleting document", e) }
    }
}


