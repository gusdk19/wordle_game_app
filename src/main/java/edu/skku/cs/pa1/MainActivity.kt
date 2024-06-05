package edu.skku.cs.pa1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import edu.skku.cs.pa1.R

class MainActivity : AppCompatActivity() {

    private lateinit var answer: String

    var wordAdapter: WordAdapter? = null;

    var letterAdapter1: LetterAdapter? = null;
    var letterAdapter2: LetterAdapter? = null;
    var letterAdapter3: LetterAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = applicationContext.assets.open("wordle_words.txt")
        val fileContent = inputStream.readBytes().toString(Charsets.UTF_8)
        val fileWords = fileContent.split("\n")
        answer = fileWords.random()
        println("Randomly selected: $answer")

        // initialize listviews
        val listWord = findViewById<ListView>(R.id.wordList)
        val listLetter1 = findViewById<ListView>(R.id.letterList1)
        val listLetter2 = findViewById<ListView>(R.id.letterList2)
        val listLetter3 = findViewById<ListView>(R.id.letterList3)

        val items = ArrayList<Word>()

        val list1Chars = arrayListOf<Char>()
        val list2Chars = arrayListOf<Char>()
        val list3Chars = arrayListOf<Char>()

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            val inputWord = editText.text.toString().trim() // user가 입력한 단어

            if (fileContent.contains(inputWord)) {
                items.add(
                    Word(
                        inputWord[0],
                        inputWord[1],
                        inputWord[2],
                        inputWord[3],
                        inputWord[4]
                    )
                )

                for (i in 0..4) {
                    val letter = inputWord[i]
                    when {
                        answer[i] == letter -> {
                            if(!list1Chars.contains(letter))
                            list1Chars.add(letter)
                            // Remove the letter from list2 if it exists
                            list2Chars.remove(letter)
                        }
                        answer.contains(letter) -> {
                            if (!list1Chars.contains(letter) && !list2Chars.contains(letter)) {
                                list2Chars.add(letter)
                            }
                        }
                        else -> {
                            if (!list3Chars.contains(letter)) {
                                list3Chars.add(letter)
                            }
                        }
                    }
                }

                wordAdapter?.notifyDataSetChanged()
                letterAdapter1?.notifyDataSetChanged()
                letterAdapter2?.notifyDataSetChanged()
                letterAdapter3?.notifyDataSetChanged()

                editText.text.clear()
            } else {
                Toast.makeText(this, "Word '$inputWord' not in dictionary!", Toast.LENGTH_SHORT).show()
            }

            wordAdapter = WordAdapter(items, answer, this)
            listWord.adapter = wordAdapter

            letterAdapter1 =
                LetterAdapter(
                    list3Chars,
                    ContextCompat.getColor(this, R.color.gray),
                    ContextCompat.getColor(this, R.color.white),
                    this
                )

            letterAdapter2 =
                LetterAdapter(
                    list2Chars,
                    ContextCompat.getColor(this, R.color.yellow),
                    ContextCompat.getColor(this, R.color.black),
                    this
                )

            letterAdapter3 =
                LetterAdapter(
                    list1Chars,
                    ContextCompat.getColor(this, R.color.green),
                    ContextCompat.getColor(this, R.color.black),
                    this
                )

            listLetter1.adapter = letterAdapter1
            listLetter2.adapter = letterAdapter2
            listLetter3.adapter = letterAdapter3

        }
    }
}
