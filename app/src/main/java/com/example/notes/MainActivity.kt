package com.example.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, { list ->
            list?.let {
                adapter.updateList(it)
            }
        })


    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNode(note)
        Toast.makeText(this,"${note.text}Delete",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_LONG).show()
        }
    }


}