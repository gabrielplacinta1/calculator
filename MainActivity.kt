package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val tempInputValue = binding.costOfService.text.toString()
        val inputValue: Double
        if (tempInputValue == "") {
            return
        } else {
            inputValue = tempInputValue.toDouble()
        }
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButton2 -> 0.20
            R.id.radioButton3 -> 0.18
            else -> 0.15
        }
        var tipAmount = inputValue * tipPercentage
        val roundUp = binding.switch1.isChecked
        if (roundUp) {
            tipAmount = kotlin.math.ceil(tipAmount)
        }
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}

