package com.example.pushnotificaitonwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pushnotificaitonwithapi.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
const val TOPIC="/topics/myTopic"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val TAG ="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        binding.button11.setOnClickListener {
            println(binding.baslik.text.toString())
            val title=binding.baslik.text.toString()
            val message=binding.messagetext.text.toString()
            if(title.isNotEmpty() && message.isNotEmpty()){

                PushNotification(



                    NotificationData(title,message),
                    TOPIC

                ).also {
                    sendNotification(it)
                }


            }
        }




    }


    private fun sendNotification(notification: PushNotification)= CoroutineScope(Dispatchers.IO).launch {

        try {
            val response=RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful){

                Log.d(TAG,"Response :${Gson().toJson(response)}")

            }else{

                Log.e(TAG,response.errorBody().toString())

            }



        }catch (e :Exception){


            Log.e(TAG,e.toString())
        }



    }
}