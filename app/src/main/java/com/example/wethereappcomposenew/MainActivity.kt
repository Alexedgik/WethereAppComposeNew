package com.example.wethereappcomposenew

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wethereappcomposenew.screens.MainCard
import com.example.wethereappcomposenew.screens.TabLayout
import com.example.wethereappcomposenew.ui.theme.WethereAppComposeNewTheme
const val API_KEY = "633dd0f8abab43f0a05104712222605"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WethereAppComposeNewTheme {
                getData("Kharkiv", this)
                Image(
                    painter = painterResource(id = R.drawable.wether_bg),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    MainCard()
                    TabLayout()
                }

            }
        }
    }
}

private fun getData(city: String, context: Context) {
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=$city" +
            "&days=" +
            "3" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        {
            responce ->
            Log.d("MyLog", "Responce: $responce")
        },
        {
          Log.d("MyLog", "VolleyError: $it")
        }
    )
    queue.add(sRequest)
}

