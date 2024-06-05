package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import edu.skku.cs.pa1.R

class LetterAdapter(val data: ArrayList<Char>, val bgColor: Int, val textColor: Int, val context: Context) : BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.letter_list, null)

        //define the letter
        val letter = generatedView.findViewById<TextView>(R.id.textView)

        letter.text = data[p0].toString()
        letter.setBackgroundColor(bgColor)
        letter.setTextColor(textColor)

        return generatedView
    }
}