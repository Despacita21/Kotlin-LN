package com.example.kotlin_ln

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_ln.databinding.ActivityMainBinding
import com.example.kotlin_ln.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)
        binding.resultLabel.text = getString(R.string.result_score, score)

        binding.tryAgainButton.setOnClickListener{
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }

    }
}