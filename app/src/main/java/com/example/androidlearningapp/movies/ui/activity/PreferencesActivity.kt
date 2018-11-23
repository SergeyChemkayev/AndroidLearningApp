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

    lateinit var preferencesApi: PreferencesApi

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, PreferencesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        preferencesApi = PreferencesManager(getPreferences(Context.MODE_PRIVATE))
        getData()
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId
        when (itemId) {
            android.R.id.home -> onBackPressed()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun saveData() {
        preferencesApi.setCheckBoxValue(preferences_check_box.isChecked)
        preferencesApi.setText(preferences_edit_text.text.toString())
    }

    private fun getData() {
        preferences_check_box.isChecked = preferencesApi.getCheckBoxValue()
        preferences_edit_text.setText(preferencesApi.getText())
    }
}
