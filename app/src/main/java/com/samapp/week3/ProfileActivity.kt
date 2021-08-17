package com.samapp.week3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    lateinit var homebtn: Button
    lateinit var del: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val bundle: Bundle? = intent.extras
        val name = bundle!!.getString("name")
        val img = bundle!!.getInt("img")

        val email = bundle!!.getString("email")
//        Glide.with(this)
//            .load(img)
//            .into(imageView2);
        textView4.text = "Name: " + name
        textView5.text = "Email: " + email

        homebtn = findViewById(R.id.button1)
        del = findViewById(R.id.delete)
        homebtn.setOnClickListener {

            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }
        del.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage("Are You sure to delete?")
            builder1.setCancelable(true)
            builder1.setPositiveButton(

                "Yes"
            ) { dialog, id -> dialog.cancel() }
            builder1.setNegativeButton(
                "No"
            ) { dialog, id -> dialog.cancel() }
            val alert11 = builder1.create()
            alert11.show()
            true
        }
    }
}
