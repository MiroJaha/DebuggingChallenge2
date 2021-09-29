package com.example.debuggingchallenge2

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: ListSelectionRecyclerViewAdapter
    lateinit var listsRecyclerView: RecyclerView
    lateinit var fabButton: FloatingActionButton
    lateinit var alertDialogSubmitBtn: Button
    lateinit var mainLayout: ConstraintLayout
    private var count = 1
    private lateinit var sharedPreferences: SharedPreferences
    private var arrayListOfCountriesAndCapitals : ArrayList<ArrayList<Any>> = arrayListOf(
        arrayListOf(count++,"Saudi Arabia", "Riyadh"),
        arrayListOf(count++,"Nigeria", "Abuja"),
        arrayListOf(count++,"Rwanda", "Kigali"),
        arrayListOf(count++,"USA", "Washington"),
        arrayListOf(count++,"China", "Beijing"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout=findViewById(R.id.mailL)
        fabButton = findViewById(R.id.fabBtn)
        setupRecyclerView()

        fabButton.setOnClickListener {
            val singleUserEntryList = arrayListOf<Any>()

            //AlertDialog
            val (dialogView, alertDialog) = setupAlertDialog()

            //Initialize edit texts
            val countryET = dialogView.findViewById<EditText>(R.id.countryEt)
            val capitalET = dialogView.findViewById<EditText>(R.id.capitalEt)

            alertDialogSubmitBtn.setOnClickListener {

                //Store user's input text to variables
                val countryText = countryET.text.toString()
                val capitalText = capitalET.text.toString()

                //Checking Input
                if(countryText.isNotBlank()&&capitalText.isNotBlank()) {

                    //Add both texts to list
                    singleUserEntryList.add(count++)
                    singleUserEntryList.add(countryText)
                    singleUserEntryList.add(capitalText)

                    //Add single entry list to Global list
                    arrayListOfCountriesAndCapitals.add(singleUserEntryList)

                }
                else
                    Snackbar.make(mainLayout,"Please Enter Correct Values",Snackbar.LENGTH_LONG).show()

                alertDialog.dismiss()
            }

        }
    }

    private fun setupAlertDialog(): Pair<View, AlertDialog> {
        //Inflate layout for Alert Dialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_layout, null)

        val builder = AlertDialog.Builder(this).setView(dialogView).setTitle("Country/Capital Form")
        val alertDialog = builder.show()
        alertDialogSubmitBtn = dialogView.findViewById(R.id.submitBtn)
        return Pair(dialogView, alertDialog)
    }

    private fun setupRecyclerView() {
        recyclerAdapter = ListSelectionRecyclerViewAdapter(arrayListOfCountriesAndCapitals)
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = recyclerAdapter
    }
}