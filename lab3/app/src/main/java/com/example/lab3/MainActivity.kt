package com.example.lab3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var inputTV: TextView
    private lateinit var outputTV: TextView
    private lateinit var buttonZero: Button
    private lateinit var buttonComa: Button
    private lateinit var buttonEqual: Button
    private lateinit var buttonPlus: Button
    private var wasPoint = false
    private var wasOperator = false
    private var operators = listOf('+', '÷', '-', '×')
    private var nPoint = 0
    private var nOper = 0
    private var leftBracet = 0
    private var rightBracet = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputTV = findViewById(R.id.inputText)
        outputTV = findViewById(R.id.outputText)
        buttonZero = findViewById(R.id.zero)
        buttonComa = findViewById(R.id.coma)
        buttonEqual = findViewById(R.id.equal)
        buttonPlus = findViewById(R.id.plus)
        calcPadding1(buttonZero, 1.1f)
        calcPadding1(buttonComa, 1.1f)
        calcPadding1(buttonEqual, 1.1f)
        calcPadding1(buttonPlus, 1.125f)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("input", inputTV.text)
        outState.putCharSequence("output", outputTV.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inputTV.text = savedInstanceState.getCharSequence("input")
        outputTV.text = savedInstanceState.getCharSequence("output")
    }

    fun numberClick(view: View) {
        if(view is Button) {
            if (view.text == ","){
                if(!wasPoint && !wasOperator && !(operators.contains(getLast())) && (nPoint - nOper < 1)){
                    inputTV.append(view.text)
                    wasPoint = true
                    nPoint++
                }
            }
            else {
                if (inputTV.text.toString() == "0") {
                    inputTV.text = view.text
                }
                else {
                    inputTV.append(view.text)
                }
                wasPoint = false
                wasOperator = false
            }
        }
    }

    fun operatorAction(view: View) {
        if(view is Button && !wasOperator && !(operators.contains(getLast())) && getLast() != ','){
            inputTV.append(view.text)
            wasOperator = true
            wasPoint = false
            nOper++
        }
    }

    fun calculateAction(view: View) {
        if (!wasOperator && !wasPoint) {
            val updInput = getInputStr()
            val expression = ExpressionBuilder(updInput).build()
            try {
                val result = expression.evaluate()
                val correction = result.toInt()
                if (correction - result == 0.0){
                    outputTV.text = correction.toString()
                }
                else{
                    outputTV.text = result.toFloat().toString()
                }
            } catch(ex: ArithmeticException) {
                outputTV.text = "Error"
            }
        }
    }

    fun backspaceAction(view: View) {
        val length: Int = inputTV.length()
        if (length > 1){
            if (this.getLast() == ','){
                wasPoint = false
                nPoint--
            }
            if (operators.contains(getLast())){
                wasOperator = false
                nOper--
            }
            inputTV.text = inputTV.text.substring(0, length - 1)
        }
        else if(inputTV.text.toString() != "0"){
            inputTV.text = "0"
        }
    }

    private fun getLast(): Char{
        return inputTV.text.last()
    }

    private fun getInputStr(): String {
        var newInput = ""
        for (c in inputTV.text) {
            if (c == '÷') {
                newInput += '/'
                continue
            }
            if (c == '×') {
                newInput += '*'
                continue
            }
            if (c == ',') {
                newInput += '.'
                continue
            }
            newInput += c
        }
        return newInput

    }

    fun advancedOperatorAction(view: View) {}

    private fun calcPadding1(view: View, weight: Float) {
        val scale = resources.displayMetrics.density
        val corr = view.height - (view.height / weight)
        val padding = (corr * scale + 0.5f).toInt()
        view.setPadding(0, 0, 0, padding)
    }
}
