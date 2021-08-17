package com.samapp.week3

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class Registration : AppCompatActivity() {
    private lateinit var nametext1: EditText
    private lateinit var nametext2: EditText
    private lateinit var myroundbutton: Button
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        nametext1 = findViewById(R.id.Name)
        nametext2 = findViewById(R.id.Email)
        myroundbutton = findViewById(R.id.Myroundbutton)

        TimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }



        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        Datebtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@Registration,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
        Myroundbutton.setOnClickListener {

            val Name = nametext1 .text.toString().trim()
            val Email =   nametext2.text.toString().trim()
            if (Name.isEmpty()){
                nametext1 .error="User name required"

            } else if(Name.length< 5){
                nametext1.error="Name is too short"
            }
            else if (Email.isEmpty()){
                nametext2.error="Email required"

            }

            else if (!radioButton1.isChecked() and  !radioButton2.isChecked()){
                Toast.makeText(getApplicationContext(), "Please select a gender !",Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            else if (!CheckBox.isChecked()){
                Toast.makeText(getApplicationContext(), "Please accept the terms and condition !",Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }else {

//                if(radioButton1.isChecked){
//                    sendUserData(Name,Email,"Male",Date.text.toString(),textView.text.toString())
//                }else{
//                    sendUserData(Name,Email,"Female",Date.text.toString(),textView.text.toString())
//                }
            }
        }
    }
    private fun updateDateInView() {
        val myFormat = "DD/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        Date!!.text = sdf.format(cal.getTime())
    }
    private fun sendUserData(username: String, useremail: String,gender: String){
        val userInfo=UserInfo()
        userInfo.setName(username)
        userInfo.setEmail(useremail)
        val send=Intent(this,MainActivity::class.java)
        Toast.makeText(getApplicationContext(), "true",Toast.LENGTH_LONG).show()
        send.putExtra("name",username)
        send.putExtra("email",useremail)
        send.putExtra("gender",gender)
        send.putExtra("gender",gender)
//        send.putExtra("time",time)
        setResult(RESULT_OK, send)
        finish()
    }
}
class UserInfo : Serializable {
    private lateinit var name: String
    private lateinit var email: String
    fun getName(): String? {
        return name
    }
    @JvmName("setName1")
    fun setName(name: String?) {
        this.name = name!!
    }
    fun getEmail(): String? {
        return email
    }
    @JvmName("setEmail1")
    fun setEmail(email: String?) {
        this.email= email!!
    }
}