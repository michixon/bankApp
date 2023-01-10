package com.example.bankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bankapp.databinding.ActivityAccountBalanceBinding

class AccountBalanceActivity : AppCompatActivity() {
    lateinit var binding: ActivityAccountBalanceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_balance)
        var accountBalance = 530
        binding = ActivityAccountBalanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.AccountBalance.setText(accountBalance.toString() + " zł")
        binding.btnMinus10.setOnClickListener {
            accountBalance -= 10
            binding.progressBar.progress = accountBalance.toInt()
            binding.AccountBalance.setText(accountBalance.toString() + " zł")
        }
        binding.btnPlus10.setOnClickListener {
            accountBalance += 10
            binding.progressBar.progress = accountBalance.toInt()
            binding.AccountBalance.setText(accountBalance.toString() + " zł")
        }

    }
}