package com.example.swipequiz

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.questions.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Questions>()
    private val questionsAdapter = QuestionsAdapter(questions)


    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        rv_questions.layoutManager = LinearLayoutManager(this)
        rv_questions.adapter = questionsAdapter

        for (i in Questions.QUESTIONS_STRING.indices)   {
            questions.add(Questions(Questions.QUESTIONS_STRING[i],Questions.QUESTIONS_ANSWERS[i]))
        }
        createItemTouchHelper().attachToRecyclerView(rv_questions)
        questionsAdapter.onItemClick = { question ->
            Log.d("question",question.toString())
            Toast.makeText(this,question.question,Toast.LENGTH_SHORT)
        }


    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val answer = Questions.QUESTIONS_ANSWERS[position]
                if (direction==4)   {
                    //Left Swipe
                    //Toast.makeText(this@MainActivity,Questions.QUESTIONS_STRING[position],Toast.LENGTH_SHORT).show()

                    if (!answer)    {
                        questions.removeAt(position)
                        questionsAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@MainActivity,getString(R.string.incorrect),Toast.LENGTH_SHORT).show()
                    }
                } else  {
                    //Right Swipe
                    //Toast.makeText(this@MainActivity,Questions.QUESTIONS_ANSWERS[position].toString(),Toast.LENGTH_SHORT).show()
                    if (answer) {
                        questions.removeAt(position)
                        questionsAdapter.notifyDataSetChanged()
                    } else  {
                        Toast.makeText(this@MainActivity,getString(R.string.incorrect),Toast.LENGTH_SHORT).show()
                    }
                }
                questionsAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

}
