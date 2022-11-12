package com.example.grupo12firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val marksRef = FirebaseDatabase.getInstance().getReference("marks")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val save_button = findViewById<Button>(R.id.save_button)
        save_button.setOnClickListener { saveMarkFromForm() }

        marksRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val nota = dataSnapshot.getValue(Nota::class.java)
                if (nota != null) writeMark(nota)
            }
        })
    }

    private fun saveMarkFromForm() {
        val name_editText = findViewById<EditText>(R.id.nombre_editText)//NOMBRE
        val subject_editText = findViewById<EditText>(R.id.asignatura_editText)//ASIGNATURA
        val mark_editText = findViewById<EditText>(R.id.nota_editText)//NOTA

        val nota = Nota(
            name_editText.text.toString(),
            subject_editText.text.toString(),
            mark_editText.text.toString().toDouble()
        )
        marksRef.push().setValue(nota)//Propio de Firebase
    }

    private fun writeMark(mark: Nota) {
        val list_textView = findViewById<TextView>(R.id.list_textView)
        val text = list_textView.text.toString() + mark.toString() + "\n"
        list_textView.text = text
    }
}