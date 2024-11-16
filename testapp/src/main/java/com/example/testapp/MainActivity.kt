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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    val textOnScreen = arrayListOf<String>()
    fun click() {
        var countButton = 0
        var countEditText = 0
        val mainScreen = findViewById(R.id.scroll_main_layout) as LinearLayout
        mainScreen
            .allViews
            .filter { view -> view is EditText }
            .map { view -> view as EditText }
            .forEach { editText: EditText ->
                textOnScreen.add(editText.text.toString())
            }
        val textEditText = resources.getString(R.string.text)
        val textEditButton = resources.getString(R.string.button)
        val textLog = resources.getString(R.string.log)
        val mistake = resources.getString(R.string.mistake)
        val zeroText = resources.getString(R.string.zerotext)
        if (textOnScreen.isEmpty()) {
            Toast.makeText(this, zeroText, Toast.LENGTH_SHORT).show()
        } else {
            for (element in textOnScreen) {
                if (element==textEditText) {
                    createEditView()
                    countEditText += 1
                } else if (element==textEditButton) {
                    createButton()
                    countButton +=1
                } else {
                    Toast.makeText(this, mistake, Toast.LENGTH_SHORT).show()
                }
            }
            Log.d("Log","$countEditText $textEditText $countButton $textEditButton $textLog")
        }
        mainScreen
            .allViews
            .filter { view -> view is EditText }
            .map { view -> view as EditText }
            .forEach { editText: EditText ->
                editText.text.clear()
            }
        textOnScreen.clear()
    }

    fun createButton() {
        val mainScreen = findViewById(R.id.scroll_main_layout) as LinearLayout
        val buttonCreated = resources.getString(R.string.button_created)
        Log.d("Log",buttonCreated)
        val v: View = LayoutInflater.from(this).inflate(
            /* resource = */ R.layout.button,
            /* root = */ mainScreen,
            /* attachToRoot = */ false
        )
        val str: String = v.resources.getString(R.string.button_text)
        val btn = v as Button
        btn.text = str
        mainScreen.addView(btn)
        btn.setOnClickListener {
            click()
        }
    }

    fun createEditView() {
        val mainScreen = findViewById(R.id.scroll_main_layout) as LinearLayout
        val textCreated = resources.getString(R.string.text_created)
        Log.d("Log",textCreated)
        val addText = EditText(this)
        addText.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        mainScreen.addView(addText)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        val mainScreen = findViewById(R.id.scroll_main_layout) as LinearLayout
        if (id == R.id.action_add_button) {
            createButton()
        }
        if (id == R.id.action_add_text) {
            createEditView()
        }
        if (id == R.id.action_clear) {
            mainScreen.removeAllViews()
        }
        return super.onOptionsItemSelected(item)
    }
}