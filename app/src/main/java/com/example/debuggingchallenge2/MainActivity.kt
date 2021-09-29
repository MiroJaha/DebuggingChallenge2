package com.example.debuggingchallenge2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

    private fun callData(){
        var count2=0
        val size = sharedPreferences.getInt("size",0)
        if(size!=0){
            count=size+1
            arrayListOfCountriesAndCapitals= arrayListOf()
            for(i in 1..size) {
                val savedData = arrayListOf<Any>()
                savedData.add(sharedPreferences.getString("${count2++}", "")!!)
                savedData.add(sharedPreferences.getString("${count2++}", "")!!)
                savedData.add(sharedPreferences.getString("${count2++}", "")!!)
                arrayListOfCountriesAndCapitals.add(savedData)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        callData()

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
        recyclerAdapter.setOnItemClickListener(object : ListSelectionRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "${arrayListOfCountriesAndCapitals[position][1]}   ${arrayListOfCountriesAndCapitals[position][2]}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStop() {
        var count2=0
        for(i in arrayListOfCountriesAndCapitals)
            with(sharedPreferences.edit()) {
                putInt("size", arrayListOfCountriesAndCapitals.size)
                putString("${count2++}", i[0].toString())
                putString("${count2++}", i[1].toString())
                putString("${count2++}", i[2].toString())
                apply()
            }
        super.onStop()
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