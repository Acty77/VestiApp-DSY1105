package com.yoshua.vestiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.yoshua.vestiapp.ui.navigation.AppNavigation
import com.yoshua.vestiapp.viewmodel.ProbadorViewModel

class MainActivity : ComponentActivity() {

    private val probadorViewModel: ProbadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation(viewModel = probadorViewModel)
        }
    }
}