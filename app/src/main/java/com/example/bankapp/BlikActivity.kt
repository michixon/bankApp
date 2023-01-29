package com.example.bankapp

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bankapp.databinding.ActivityBlikBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import java.util.*

class BlikActivity : AppCompatActivity() {
    var counter = 120
    lateinit var binding: ActivityBlikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blik)

        var pushToken :String = ""
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task: Task<String> ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            pushToken = task.result
            Log.i("PUSH_TOKEN", "pushToken: $pushToken")
        }

        binding = ActivityBlikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newCodeBlikGenerate.setOnClickListener(){
            //val intent = Intent(this, BlikActivity::class.java)
            //startActivity(intent)

        val queue = Volley.newRequestQueue(this)
            val url: String = "http://192.168.0.203:8080/blik/createCode/$pushToken"

        val stringReq = StringRequest(
            Request.Method.GET,
            url,
            {
                response ->

                    val strResponse = response.toString()
                (this.application as GlobalVariables).setActualBlik(strResponse)
                binding.BlikCode.text = (this.application as GlobalVariables).getActualBlik()
            },
            {
                response ->
                    binding.BlikCode.text = "Błąd połączenia z serwerem"
                    Log.e("APIII", response.toString())
            }
        )
        queue.add(stringReq)

        object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.CodeExp.setText("Kod wygasa za")
                binding.TimerTextView.text= (counter.toString()+"s")
                counter--
                binding.newCodeBlikGenerate.isEnabled = false
            }

            override fun onFinish() {
                binding.TimerTextView.setText("Kod wygasł")
                binding.newCodeBlikGenerate.isEnabled = true
               counter= 120

            }
        }.start()

        }

    }
}

