package com.example.bankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bankapp.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    lateinit var binding: ActivityConfirmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val blikCode = (this.application as GlobalVariables).getActualBlik()
//        binding.priceTxt.text = blikCode
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://192.168.0.203:8080/blik/price/$blikCode"

        val stringReq = StringRequest(
            Request.Method.GET,
            url,
            {
                    response ->

                val strResponse = response.toString()
                binding.priceTxt.text = strResponse
            },
            {
                    response ->
                binding.priceTxt.text = "Błąd połączenia z serwerem"
                Log.e("APIII", response.toString())
            }
        )
        queue.add(stringReq)
    }
}