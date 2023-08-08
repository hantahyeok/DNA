package com.ksh.dna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ksh.dna.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var text1 = ""
    var text2 = ""
    var allText = ""
    var percentage = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iv.setOnClickListener {
            // Dialog만들기
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            mBuilder.show()
        }

        binding.btn1.setOnClickListener {

            text1 = binding.et1.text.toString()
            text2 = binding.et2.text.toString()

//            val distance = hammingDistance(text1, text2)

            allText = text1 + text2

//            A T C G + U


            if (text1.length == text2.length) {
                binding.tvError.visibility = View.INVISIBLE

                if (test(allText)) {
                    binding.tvError.visibility = View.INVISIBLE

                    // 염기서열 거리 구하기
                    val count: Int = hammingDistance(text1, text2)
                    if (text1.isNotEmpty()) {

                        percentage = ((count.toDouble() / text1.length.toDouble()) * 100)
                        binding.tv2.setText(count.toString())
                        binding.tv1.setText(String.format("%.2f", percentage) + "%")
                    }

                    if(text1.isEmpty()){
                        binding.tvError.setText("값을 입력해주세요!")
                        binding.tvError.visibility = View.VISIBLE
                    }


                } else {
                    binding.tvError.setText("염기서열만 입력해주세요!")
                    binding.tvError.visibility = View.VISIBLE
                }

            }

            if (text1.length != text2.length) {
                binding.tvError.setText("두개의 길이가 달라요!")
                binding.tvError.visibility = View.VISIBLE
            }
        }

        binding.btn2.setOnClickListener {
            binding.et1.setText("")
            binding.et2.setText("")

            binding.tv1.setText("0%")
            binding.tv2.setText("0")

            binding.tvError.visibility = View.INVISIBLE
        }

    }

    fun hammingDistance(str1: String, str2: String): Int {

        var distance = 0
        for (i in str1.indices) {
            if (str1[i] == str2[i]) {
                distance++
            }
        }
        return distance
    }

    fun test(allText: String): Boolean {
        for (char in allText) {
            if (char != 'A' && char != 'T' && char != 'C' && char != 'G' && char != 'U' && char != 'a' && char != 't' && char != 'c' && char != 'g' && char != 'u') {
                return false
            }
        }
        return true
    }
}