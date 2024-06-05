package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import edu.skku.cs.pa1.R

class WordAdapter(val data: ArrayList<Word>, val answer: String, val context: Context): BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.word_list, null)

        val textViewFirst = generatedView.findViewById<TextView>(R.id.textView1)
        val textViewSecond = generatedView.findViewById<TextView>(R.id.textView2)
        val textViewThird = generatedView.findViewById<TextView>(R.id.textView3)
        val textViewFourth = generatedView.findViewById<TextView>(R.id.textView4)
        val textViewFifth = generatedView.findViewById<TextView>(R.id.textView5)

        val words = arrayListOf(textViewFirst, textViewSecond, textViewThird, textViewFourth, textViewFifth)

        textViewFirst.text = data[p0].word1.toString()
        textViewSecond.text = data[p0].word2.toString()
        textViewThird.text = data[p0].word3.toString()
        textViewFourth.text = data[p0].word4.toString()
        textViewFifth.text = data[p0].word5.toString()

        val textArray = charArrayOf(
            data[p0].word1,
            data[p0].word2,
            data[p0].word3,
            data[p0].word4,
            data[p0].word5
        )

        for (i in 0..4) {
            if (answer[i] == textArray[i]) {
                words[i].setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                words[i].setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            else if (answer.contains(textArray[i])) {
                if (textArray.slice(0..i).count { it == textArray[i] } <= answer.count { it == textArray[i] }) {
                    words[i].setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    words[i].setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                else {
                words[i].setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
                words[i].setTextColor(ContextCompat.getColor(context, R.color.white))
                }
            }
            else {
                words[i].setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
                words[i].setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }

        return generatedView
    }
}