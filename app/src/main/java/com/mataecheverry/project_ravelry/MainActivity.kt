package com.mataecheverry.project_ravelry

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.mataecheverry.project_ravelry.ui.Aplicacio
import com.mataecheverry.project_ravelry.ui.AppDisplay

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           AppDisplay {
               Aplicacio()
           }
        }
    }

}

