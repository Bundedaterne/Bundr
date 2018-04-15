package nu.dropud.bundr.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.id.edit
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.fragment_video.*
import nu.dropud.bundr.feature.main.MainActivity


class SplashScreen : AppCompatActivity() {

    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Perhaps set content view here

        prefs = getSharedPreferences("com.mycompany.myAppName", Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        if (prefs!!.getBoolean("firstrun", false)) {
            startActivity(Intent(this@SplashScreen, InitActivity::class.java))
        } else {
            object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    startActivity(Intent(this@SplashScreen, InitActivity::class.java))
                }
            }.start()
        }
    }
}
