package nu.dropud.bundr.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_init.*
import nu.dropud.bundr.R
import nu.dropud.bundr.common.extension.ChildEventAdded
import nu.dropud.bundr.common.extension.ChildEventRemoved
import nu.dropud.bundr.common.extension.areAllPermissionsGranted
import nu.dropud.bundr.common.extension.rxChildEvents
import nu.dropud.bundr.feature.base.BaseActivity
import nu.dropud.bundr.feature.main.MainActivity
import nu.dropud.bundr.feature.main.video.VideoFragment

class InitActivity : AppCompatActivity() {

    companion object {
        private val NECESSARY_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
        private const val CHECK_PERMISSIONS_AND_CONNECT_REQUEST_CODE = 1
    }

    var availableChugger: Long = 0;
    var fireReference = FirebaseDatabase.getInstance().getReference("online_devices/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init)

        FirebaseMessaging.getInstance().subscribeToTopic("hot_singles_in_your_area")

        val button = findViewById<Button>(R.id.chug_button)
        button.setOnClickListener {
            startActivity(Intent(this@InitActivity, MainActivity::class.java))
        }

        val context = applicationContext

        if (!(context.areAllPermissionsGranted(*NECESSARY_PERMISSIONS))) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO), CHECK_PERMISSIONS_AND_CONNECT_REQUEST_CODE)

        }

        fireReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val childrenCount = dataSnapshot.childrenCount
                if (childrenCount == 1L) {
                    textView.text = "" + dataSnapshot.childrenCount + " chugger are waiting in your area"
                } else {
                    textView.text = "" +  dataSnapshot.childrenCount + " chuggers are waiting in your area"
                }
            }
        })
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
