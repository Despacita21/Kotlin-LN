package com.example.kotlin_ln

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.kotlin_ln.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 15

    private val quizData = mutableListOf(
        mutableListOf("What are Red Pikmin resistant to?", "Fire", "Electricity", "Water", "Poison"),
        mutableListOf("What is the name of the blue Captain in Pikmin 3?", "Alph", "Britney", "Charlie", "Olimar"),
        mutableListOf("What race is Olimar?", "Hocotatian", "Koppaite", "Japanese", "American"),
        mutableListOf("What Pikmin debuted in Pikmin 3?", "Rock", "Red", "Blue", "Purple"),
        mutableListOf("Who's Olimar's partner in Pikmin 2?", "Louie", "Alph", "Charlie", "President"),
        mutableListOf("After the Impact Site, what ws name of the first level?", "The Forest of Hope", "The Distant Spring", "Perplexing Pool", "Formidable Oak"),
        mutableListOf("In Pikmin 3, how many of each Pikmin type do you need to have out for them to sing?", "20", "10", "50", "100"),
        mutableListOf("What is the main Collectable in Pikmin 2?", "Treasure", "Fruit", "Ship Parts", "Pikmin"),
        mutableListOf("What are Pikmin named after?", "Pik Pik Carrots", "Ants", "Plants", "Flower"),
        mutableListOf("In Pikmin 1, which Pikmin type can only carry Bomb Rocks?", "Yellow", "Red", "Blue", "Only Olimar can"),
        mutableListOf("What is the name of Olimar's ship in Pikmin 1?", "S.S. Dolphin", "The Hocotate ship", "S.S Drake", "It didn't have a name"),
        mutableListOf("What was Captain Charlie's favorite item?", "A Rubber Duck", "Fruit Juice", "His Whistle", "Cosmic Drive Key"),
        mutableListOf("What console was Pikmin 3 originally released on?", "Wii U", "Switch", "DS", "Wii"),
        mutableListOf("How many Pikmin types appear in all the game?", "9", "7", "3", "5"),
        mutableListOf("Do Bulbmin Exist?", "Yes", "Of course not", "Only in fan art", "It's just a glitch")

    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        quizData.shuffle()

        showNextQuiz()
    }

    fun showNextQuiz()
    {
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]

        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        quiz.removeAt(0)

        quiz.shuffle()

        binding.answerButton1.text = quiz[0]
        binding.answerButton2.text = quiz[1]
        binding.answerButton3.text = quiz[2]
        binding.answerButton4.text = quiz[3]

        quizData.removeAt(0)
    }

    fun checkAnswer(view: View)
    {
        val answerButton: Button = findViewById(view.id)
        val buttonText = answerButton.text.toString()

        val alertTitle: String
        if(buttonText == rightAnswer)
        {
            alertTitle = "Correct!"
            rightAnswerCount++
        }

        else
        {
            alertTitle = "Wrong..."
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("OK") {dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    fun checkQuizCount()
    {
        if(quizCount == QUIZ_COUNT)
        {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount)
            startActivity(intent)
        }
        else
        {
            quizCount++
            showNextQuiz()
        }
    }
}