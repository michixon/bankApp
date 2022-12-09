package com.example.bankapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bankapp.databinding.ActivityBlikBinding
import org.json.JSONObject
import java.util.*
import kotlin.random.Random


class BlikActivity : AppCompatActivity() {
    lateinit var binding: ActivityBlikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blik)

        binding = ActivityBlikBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val queue = Volley.newRequestQueue(this)
        val url: String = "http://192.168.1.136:8080/blik/createCode"

        val stringReq = StringRequest(
            Request.Method.GET,
            url,
            {
                response -> binding.randomBlikCode.setText()
            },
            {  }
        )
        queue.add(stringReq)

        binding.blik.setOnClickListener(){
//            var randomCode = Random.nextInt(100000, 1000000)
//            binding.randomBlikCode.setText(randomCode.toString())

        }
    }
}