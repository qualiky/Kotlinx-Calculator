package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var lastNumeric = false
    var lastDecimal = false
    var lastCalculated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun appendDigit(view: View) {
        binding.tvDisplay.append((view as AppCompatButton).text)
        lastNumeric = true
    }

    fun clearText(view: View) {
        binding.tvDisplay.text = ""
        lastDecimal = false
        lastNumeric = false
    }

    fun addDecimalPoint(view: View){
        if (lastNumeric && !lastDecimal) {
            binding.tvDisplay.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }

    fun addOperator(view: View) {
        if(((view as AppCompatButton).text == "-" || lastNumeric) && !isOperatorAdded(binding.tvDisplay.text.toString()) && !lastCalculated) {
            binding.tvDisplay.append((view as AppCompatButton).text)
            lastNumeric = false
            lastDecimal = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            false
        } else (value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/"))
    }

    fun showResult (view: View) {
        if (lastNumeric) {
            var tvValue = binding.tvDisplay.text.toString()

            try {
                if(tvValue.contains("+")){
                    if(tvValue.startsWith("-")){
                        val tvEdited = tvValue.removePrefix("-")

                        if(tvEdited.contains("+")){
                            val spVal = tvEdited.split("+")
                            var one: String = spVal[0]
                            var two: String = spVal[1]

                            val doubleOne = one.toDouble()
                            val doubleTwo = two.toDouble()

                            binding.tvDisplay.text = removeLastZero(((doubleOne * -1) + doubleTwo).toString())
                        }


                    } else {
                        val splitVal = tvValue.split("+")

                        var one = splitVal[0]
                        var two = splitVal[1]

                        binding.tvDisplay.text = removeLastZero((one.toDouble() + two.toDouble()).toString())
                    }
                } else if (tvValue.contains("*")){
                    if(tvValue.startsWith("-")){
                        val tvEdited = tvValue.removePrefix("-")

                        if(tvEdited.contains("*")){
                            val spVal = tvEdited.split("*")
                            var one: String = spVal[0]
                            var two: String = spVal[1]

                            Log.d("DataPrinter: ", "first val mul: $one and second val mul $two")

                            val doubleOne = one.toDouble()
                            val doubleTwo = two.toDouble()

                            binding.tvDisplay.text = removeLastZero(((doubleOne * -1) * doubleTwo).toString())
                        }


                    } else {
                        val splitVal = tvValue.split("*")

                        var one = splitVal[0]
                        var two = splitVal[1]

                        binding.tvDisplay.text = removeLastZero((one.toDouble() * two.toDouble()).toString())
                    }
                } else if (tvValue.contains("/")){
                    if(tvValue.startsWith("-")){
                        val tvEdited = tvValue.removePrefix("-")

                        if(tvEdited.contains("/")){
                            val spVal = tvEdited.split("/")
                            var one: String = spVal[0]
                            var two: String = spVal[1]

                            val doubleOne = one.toDouble()
                            val doubleTwo = two.toDouble()

                            binding.tvDisplay.text = removeLastZero(((doubleOne * -1) / doubleTwo).toString())
                        }


                    } else {
                        val splitVal = tvValue.split("/")

                        var one = splitVal[0]
                        var two = splitVal[1]

                        binding.tvDisplay.text = removeLastZero((one.toDouble() / two.toDouble()).toString())
                    }
                } else if(tvValue.contains("-")) {
                    if(tvValue.startsWith("-")){
                        val tvEdited = tvValue.removePrefix("-")

                        if(tvEdited.contains("-")){
                            val spVal = tvEdited.split("-")
                            var one: String = spVal[0]
                            var two: String = spVal[1]

                            val doubleOne = one.toDouble()
                            val doubleTwo = two.toDouble()

                            binding.tvDisplay.text = removeLastZero(((doubleOne * -1) - doubleTwo).toString())
                        }


                    } else {
                        val splitVal = tvValue.split("-")

                        var one = splitVal[0]
                        var two = splitVal[1]

                        binding.tvDisplay.text = removeLastZero((one.toDouble() - two.toDouble()).toString())
                    }
                }

            } catch (e: ArithmeticException) {

            }
        }
        lastDecimal = false
        lastNumeric = false
        lastCalculated = true
    }

    private fun removeLastZero(value: String) : String {
        var result = value
        if(value.elementAt(value.length-1).equals("0") && value.elementAt(value.length-2).equals(".")){
            result = value.substring(0,value.length-2)
        }
        return result
    }
}