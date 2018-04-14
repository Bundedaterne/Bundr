package nu.dropud.bundr.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import nu.dropud.bundr.R
import nu.dropud.bundr.common.extension.areAllPermissionsGranted
import nu.dropud.bundr.feature.base.BaseActivity
import nu.dropud.bundr.feature.main.MainActivity
import nu.dropud.bundr.feature.main.video.VideoFragment

class InitActivity : AppCompatActivity() {

    companion object {
        private val NECESSARY_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
        private const val CHECK_PERMISSIONS_AND_CONNECT_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init)
        val button = findViewById<Button>(R.id.chug_button)
        button.setOnClickListener {
            startActivity(Intent(this@InitActivity, MainActivity::class.java))
        }

        val context = applicationContext

        if (! (context.areAllPermissionsGranted(*NECESSARY_PERMISSIONS))) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO), CHECK_PERMISSIONS_AND_CONNECT_REQUEST_CODE)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) = when (requestCode) {
        CHECK_PERMISSIONS_AND_CONNECT_REQUEST_CODE -> {
            val grantResult = grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (!grantResult) {
                error("Memes")
            } else {

            }
        }
        else -> {
            error("Unknown permission request code $requestCode")
        }
    }
}
