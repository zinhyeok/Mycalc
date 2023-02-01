package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView? = null
    private var lastNumeric = false
    private var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){

        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix ="-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var first_num = prefix+splitValue[0]
                    var second_num = splitValue[1]
                    tvInput?.text = removeZeroAfterDot((first_num.toDouble() - second_num.toDouble()).toString())
                }
                else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var first_num = prefix+splitValue[0]
                    var second_num = splitValue[1]
                    tvInput?.text = removeZeroAfterDot((first_num.toDouble() + second_num.toDouble()).toString())
                }
                else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var first_num = prefix+splitValue[0]
                    var second_num = splitValue[1]
                    tvInput?.text = removeZeroAfterDot((first_num.toDouble() * second_num.toDouble()).toString())
                }
                else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var first_num = prefix+splitValue[0]
                    var second_num = splitValue[1]
                    tvInput?.text = removeZeroAfterDot((first_num.toDouble() / second_num.toDouble()).toString())
                }
                else{

                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    fun onOperator(view: View){
        tvInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if(result.endsWith(".0")){
            value = value.substring(0,value.length -2)
        }
        return value
    }
    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        } else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}