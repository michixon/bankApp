package com.example.bankapp

import android.app.Application

class GlobalVariables : Application() {
    private var actualBlik = "";

    public fun getActualBlik(): String {
        return actualBlik;
    }

    public fun setActualBlik(newBlik: String) {
        this.actualBlik = newBlik
    }
}