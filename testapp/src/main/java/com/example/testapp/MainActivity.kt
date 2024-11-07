package com.example.testapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var itemsList: MutableList<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        val mainScreen = findViewById(R.id.scroll_main_layout) as LinearLayout
        if (id == R.id.action_add_button) {
            Log.d("myLogs","button created")
            val v: View = LayoutInflater.from(this).inflate(
                /* resource = */ R.layout.button,
                /* root = */ mainScreen,
                /* attachToRoot = */ false
            )
            val str: String = v.resources.getString(R.string.button_text)
            val btn = v as Button
            btn.text = str
            mainScreen.addView(btn)
        }
        if (id == R.id.action_add_text) {
            Log.d("myLogs","text created")
            val addText = EditText(this)
            addText.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            mainScreen.addView(addText)
        }
        if (id == R.id.action_clear) {
            mainScreen.removeAllViews()
        }
        return super.onOptionsItemSelected(item)
    }
}