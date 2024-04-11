package com.mataecheverry.project_ravelry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mataecheverry.project_ravelry.ui.Aplicacio
import com.mataecheverry.project_ravelry.ui.AppDisplay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           AppDisplay {
               Aplicacio()
           }
        }
    }

}

