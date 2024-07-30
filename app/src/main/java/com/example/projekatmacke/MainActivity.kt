package com.example.projekatmacke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.projekatmacke.navigation.CatNavigation
import com.example.projekatmacke.ui.theme.ProjekatMackeTheme

class MainActivity : ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjekatMackeTheme {
                CatNavigation()
            }
        }
    }
}


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ProjekatMackeTheme {
//        Greeting("Android")
//    }
//}