package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bankapp.databinding.ActivityBlikBinding
import java.util.*

class BlikActivity : AppCompatActivity() {
    var counter = 120
    lateinit var binding: ActivityBlikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blik)

        binding = ActivityBlikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newCodeBlikGenerate.setOnClickListener(){
            //val intent = Intent(this, BlikActivity::class.java)
            //startActivity(intent)

        val queue = Volley.newRequestQueue(this)
        val url: String = "http://192.168.1.108:8080/blik/createCode"

        val stringReq = StringRequest(
            Request.Method.GET,
            url,
            {
                response ->

                    val strResponse = response.toString()
                binding.BlikCode.text = strResponse
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

