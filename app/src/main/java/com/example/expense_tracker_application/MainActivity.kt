package com.example.expense_tracker_application

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var transactions: ArrayList<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions = arrayListOf(
            Transaction("Weekend budget", 400.00),
            Transaction("Bananas", -4.00),
            Transaction("Gasoline", -40.00),
            Transaction("Breakfast", -9.00),
            Transaction("Water bottles", -4.00),
            Transaction("Suncream", -8.00),
            Transaction("Park", -15.00),
        )

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }

        updateDashboard()

        val addBtn: FloatingActionButton = findViewById(R.id.addBtn)
        addBtn.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateDashboard() {
        val totalAmount: Double = transactions.map { it.amount }.sum()
        val budgetAmount = transactions.filter { it.amount > 0 }.map { it.amount }.sum()
        val expenseAmount = totalAmount - budgetAmount

        val balance: TextView = findViewById(R.id.balance)
        balance.text = "$%.2f.".format(totalAmount)
        val budget: TextView = findViewById(R.id.budget)
        budget.text = "$%.2f.".format(budgetAmount)
        val expense: TextView = findViewById(R.id.expense)
        expense.text = "$%.2f.".format(expenseAmount)
    }
}
