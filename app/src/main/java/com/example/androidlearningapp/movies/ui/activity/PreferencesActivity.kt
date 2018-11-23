package com.example.androidlearningapp.movies.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.androidlearningapp.R
import com.example.androidlearningapp.movies.data.api.PreferencesApi
import com.example.androidlearningapp.movies.data.api.PreferencesManager
import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity : AppCompatActivity() {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, PreferencesActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var preferencesApi: PreferencesApi = PreferencesManager(getPreferences(Context.MODE_PRIVATE))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        getData()
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId
        return when (itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveData() {
        preferencesApi.setCheckBoxValue(preferencesCheckBox.isChecked)
        preferencesApi.setText(preferencesEditText.text.toString())
    }

    private fun getData() {
        preferencesCheckBox.isChecked = preferencesApi.getCheckBoxValue()
        preferencesEditText.setText(preferencesApi.getText())
    }
}
