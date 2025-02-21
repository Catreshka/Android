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

    private val textOnScreen = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getTextById(stringId: Int): String {
        return resources.getString(stringId)
    }

    private fun sendLogEvent(message: String) {
        Log.d("Log",message)
    }

    private fun click() {
        var countButton = 0
        var countEditText = 0
        val mainScreen = findViewById<LinearLayout>(R.id.scroll_main_layout)
        mainScreen
            .allViews
            .filter { view -> view is EditText }
            .map { view -> view as EditText }
            .forEach { editText: EditText ->
                textOnScreen.add(editText.text.toString())
            }
        val textEditText = getTextById(R.string.text)
        val textEditButton = getTextById(R.string.button)
        val textLog = getTextById(R.string.log)
        val mistake = getTextById(R.string.mistake)
        val zeroText = getTextById(R.string.zerotext)
        if (textOnScreen.isEmpty()) {
            showToast(zeroText)
        } else {
            for (element in textOnScreen) {
                when (element) {
                    textEditText -> {
                        createEditView()
                        countEditText += 1
                        break
                    }
                    textEditButton -> {
                        createButton()
                        countButton +=1
                        break
                    }
                    else -> showToast(mistake)
                }
            }
            sendLogEvent("$countEditText $textEditText $countButton $textEditButton $textLog")
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

    private fun createButton() {
        val mainScreen = findViewById<LinearLayout>(R.id.scroll_main_layout)
        val buttonCreated = getTextById(R.string.button_created)
        sendLogEvent(buttonCreated)
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

    private fun createEditView() {
        val mainScreen = findViewById<LinearLayout>(R.id.scroll_main_layout)
        val textCreated = getTextById(R.string.text_created)
        sendLogEvent(textCreated)
        val addText = EditText(this)
        addText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        mainScreen.addView(addText)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val mainScreen = findViewById<LinearLayout>(R.id.scroll_main_layout)
        when (id) {
            R.id.action_add_button -> createButton()
            R.id.action_add_text -> createEditView()
            R.id.action_clear -> mainScreen.removeAllViews()
        }
        return super.onOptionsItemSelected(item)
    }
}