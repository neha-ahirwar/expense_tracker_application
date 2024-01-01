package com.example.expense_tracker_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

import android.widget.Button
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var closeBtn: ImageButton
    private lateinit var labelLayout: TextInputLayout
    private lateinit var amountLayout: TextInputLayout
    private lateinit var descriptionInput: TextInputEditText
    private lateinit var labelInput: TextInputEditText
    private lateinit var amountInput: TextInputEditText
    private lateinit var addTransactionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        closeBtn = findViewById(R.id.closeBtn)
        labelLayout = findViewById(R.id.labelLayout)
        amountLayout = findViewById(R.id.amountLayout)
        descriptionInput = findViewById(R.id.descriptionInput)
        labelInput = findViewById(R.id.labelInput)
        amountInput = findViewById(R.id.amountInput)
        addTransactionBtn = findViewById(R.id.addTransactionBtn)

        labelInput.addTextChangedListener {
            if (it!!.count() > 0)
                labelLayout.error = null
        }

        addTransactionBtn.setOnClickListener {
            val label = labelInput.text.toString()
            val description = descriptionInput.text.toString()
            val amount = amountInput.text.toString().toDoubleOrNull()

            if (label.isEmpty())
                labelLayout.error = "Please enter a valid label"

            if (amount == null)
                amountLayout.error = "Please enter a valid amount"
        }

        closeBtn.setOnClickListener {
            finish()
        }
    }
}
