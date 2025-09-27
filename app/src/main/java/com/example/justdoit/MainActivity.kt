package com.example.justdoit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.justdoit.ui.theme.JustDoItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustDoItTheme {
                // Instanciation du ViewModel (scoped à l'activité)
                val vm: NotesViewModel = viewModel()
                // Lancement du Graph de navigation en lui passant le ViewModel
                NavGraph(viewModel = vm)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustDoItTheme {
        val vm: NotesViewModel = viewModel()
        NavGraph(viewModel = vm)
    }
}