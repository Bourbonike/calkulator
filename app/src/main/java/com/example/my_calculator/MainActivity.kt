package com.example.my_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.my_calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)
        binding.btn0.setOnClickListener { setTextFields("0") }
        //1.2.3.4.5.6.7.8.9
        //findViewById<TextView>(R.id.btn_0).setOnClickListener { setTextFields("0") }
        binding.btn1.setOnClickListener { setTextFields("1") }
        binding.btn2.setOnClickListener { setTextFields("2") }
        binding.btn3.setOnClickListener { setTextFields("3") }
        binding.btn4.setOnClickListener { setTextFields("4") }
        binding.btn5.setOnClickListener { setTextFields("5") }
        binding.btn6.setOnClickListener { setTextFields("6") }
        binding.btn7.setOnClickListener { setTextFields("7") }
        binding.btn8.setOnClickListener { setTextFields("8") }
        binding.btn9.setOnClickListener { setTextFields("9") }
        //plus_btn
        findViewById<TextView>(R.id.minus_btn).setOnClickListener { setTextFields("-") }
        findViewById<TextView>(R.id.plus_btn).setOnClickListener { setTextFields("+") }
        findViewById<TextView>(R.id.mult_btn).setOnClickListener { setTextFields("*") }
        findViewById<TextView>(R.id.disivion_btn).setOnClickListener { setTextFields("/") }
        binding.square?.setOnClickListener { setTextFields("√") }
        //
        findViewById<TextView>(R.id.bracket_open_btn).setOnClickListener { setTextFields("(") }
        findViewById<TextView>(R.id.bracket_close_btn).setOnClickListener { setTextFields(")") }
        //clear_btn
        findViewById<TextView>(R.id.clear_btn).setOnClickListener {
            findViewById<TextView>(R.id.math_operation).text = ""
            findViewById<TextView>(R.id.result_text).text = ""
        }
        findViewById<TextView>(R.id.back_btn).setOnClickListener {
            val str = findViewById<TextView>(R.id.math_operation).text.toString()
            if (str.isNotEmpty())
                findViewById<TextView>(R.id.math_operation).text = str.substring(0, str.length - 1)
            findViewById<TextView>(R.id.result_text).text = ""
        }
        findViewById<TextView>(R.id.equal_btn).setOnClickListener {
            try {
                val ex =
                    ExpressionBuilder(findViewById<TextView>(R.id.math_operation).text.toString()).build()
                val result = ex.evaluate()
                val LongRes = result.toLong()
                if (result == LongRes.toDouble())
                    findViewById<TextView>(R.id.result_text).text = LongRes.toString()
                else
                    findViewById<TextView>(R.id.result_text).text = result.toString()
            } catch (e: Exception) {
                Log.d("Ошибка", "сообщение:${e.message}")

            }
        }
    }

    fun setTextFields(str: String) {
        if (findViewById<TextView>(R.id.result_text).text != "") {
            findViewById<TextView>(R.id.math_operation).text =
                findViewById<TextView>(R.id.result_text).text
            findViewById<TextView>(R.id.result_text).text = ""
        }
        findViewById<TextView>(R.id.math_operation).append(str)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("KEY", findViewById<TextView>(R.id.result_text).text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        findViewById<TextView>(R.id.result_text).text = savedInstanceState.getString("KEY")
        super.onRestoreInstanceState(savedInstanceState)
    }
}
