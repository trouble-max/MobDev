package com.example.lab2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var inputTV: TextView
    lateinit var outputTV: TextView
    var wasPoint = false
    var wasOperator = false
    var operators = listOf('+', '÷', '-', '×')
    var nPoint = 0
    var nOper = 0
    var leftBracet = 0
    var rightBracet = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputTV = findViewById(R.id.inputText)
        outputTV = findViewById(R.id.outputText)
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
                outputTV.text = result.toString()
                println(inputTV.text)
                println(outputTV.text)
            } catch(ex: ArithmeticException) {
                outputTV.text = "Error"
            }
        }
    }

    fun backspaceAction(view: View) {
        val length: Int = inputTV.length()
        if (length > 1){
            if (getLast() == ','){
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

    fun getLast(): Char{
        return inputTV.text.last()
    }

    fun getInputStr(): String {
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
}