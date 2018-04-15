package nu.dropud.bundr.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.id.edit
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.android.synthetic.main.fragment_video.*
import nu.dropud.bundr.R
import nu.dropud.bundr.feature.main.MainActivity


class SplashScreen : AppCompatActivity() {

    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Perhaps set content view here
        setContentView(R.layout.activity_splash_screen)
        prefs = getSharedPreferences("bundr.dropud.nu", Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        if (true){//prefs!!.getBoolean("firstrun", true)) {
            val startPlayer = MediaPlayer.create(applicationContext, R.raw.start)
            startPlayer.start()
            prefs!!.edit().putBoolean("firstrun", false).commit();
            object : CountDownTimer(6000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    startActivity(Intent(this@SplashScreen, InitActivity::class.java))
                }
            }.start()
            val fadeInAnimation1 = AlphaAnimation(0.0f, 1f)
            val fadeInAnimation2 = AlphaAnimation(0.0f, 1f)
            val fadeInAnimation3 = AlphaAnimation(0.0f, 1f)
            fadeInAnimation1.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationEnd(animation: Animation?) {
                    text2.text = "ˈtʃʌɡɪŋ"
                    text2.startAnimation(fadeInAnimation2)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
            fadeInAnimation2.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationEnd(animation: Animation?) {
                    text3.text = "To drink alcohol really fast without breathing. \n People usually chant this at the person who is drinking. "
                    text3.startAnimation(fadeInAnimation3)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
            fadeInAnimation1.duration = 1500;
            fadeInAnimation2.duration = 1500;
            fadeInAnimation3.duration = 2000;
            text1.text = "Chugging"
            text1.startAnimation(fadeInAnimation1)

        } else {
            startActivity(Intent(this@SplashScreen, InitActivity::class.java))
        }
    }
}
