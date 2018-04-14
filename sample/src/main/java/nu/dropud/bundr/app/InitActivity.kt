package nu.dropud.bundr.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import nu.dropud.bundr.R
import nu.dropud.bundr.feature.main.MainActivity

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        val button = findViewById<Button>(R.id.chug_button)
        button.setOnClickListener {
            startActivity(Intent(this@InitActivity, MainActivity::class.java))
        }
    }
}
