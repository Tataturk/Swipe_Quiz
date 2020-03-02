package com.example.swipequiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.questions.view.*

public class QuestionsAdapter(private val questions:List<Questions>):
    RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    lateinit var context: Context
    var onItemClick: ((Questions) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.questions, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Questions) {
            itemView.tvQuestions.text = question.question
            itemView.setOnClickListener {
                onItemClick?.invoke(questions[adapterPosition])
            }
        }
    }
}