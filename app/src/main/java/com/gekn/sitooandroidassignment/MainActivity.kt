package com.gekn.sitooandroidassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gekn.sitooandroidassignment.ui.MainApp
import com.gekn.sitooandroidassignment.ui.theme.SitooAndroidAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SitooAndroidAssignmentTheme {
                MainApp()
            }
        }
    }
}
