package com.example.onesignaldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.onesignal.OneSignal

class MainActivity : AppCompatActivity() {

    //app id from onesignal
    companion object {
        const val ONESIGNAL_APP_ID = "0edc605b-d5ce-4269-a4eb-654d136fd9a0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //registering to onesignal

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)


        OneSignal.setInAppMessageClickHandler() {
            val clickName = it.clickName
            val clickUrl = it.clickUrl
            val closesMessage = it.doesCloseMessage()
            val firstClick = it.isFirstClick
            Log.i(
                "MessageClickHandler",
                "$clickName (*) $clickUrl (*) $closesMessage (*) $firstClick"
            )
        }

        OneSignal.setNotificationOpenedHandler() {
            val actionID = it.action.actionId
            val type = it.action.type
            val title = it.notification.title
            Log.i("NotificationOpened", "$actionID (*) $type (*) $title")
        }
    }
}