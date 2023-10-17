package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat







class MainActivity : AppCompatActivity() {
    var canAdd = false
    var canAddDecimal = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun allClear(view:View){
        exprssionTV.text = ""
        resultTV.text=""
    }
    fun bckSpace(view:View){
        val len = exprssionTV.length()
        if(len>0){
        exprssionTV.text = exprssionTV.text.subSequence(0,len-1)
             }
    }
    fun getNumber(view:View){
        var view  = view as Button
            if(view.text=="." && canAddDecimal){
                exprssionTV.append(view.text)
               if(exprssionTV.length()!=0)
                canAddDecimal=false
            }else if(view.text!="."){
                exprssionTV.append(view.text)

            }
            canAdd=true


    }
    fun operator(view:View){
        if(view is Button && canAdd){
            exprssionTV.append(view.text)
            canAdd=false
            canAddDecimal=true

        }
    }
    fun answer(view:View){
        try{
        var result = exprssionTV.text.toString()
        val answer = Expression(result).calculate()
        if(!answer.isNaN()){
            resultTV.text = DecimalFormat("0.######").format(answer).toString()

        }
            else resultTV.text="Error"
        }
        catch (e: Exception) {
            // Show Error Message
            resultTV.text = "Error"
        }



    }
}
