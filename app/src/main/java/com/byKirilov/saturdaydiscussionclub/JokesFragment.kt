package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class JokesFragment : Fragment() {

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String) =
            JokesFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        ARGS_NAME,
                        name
                    )
                }
            }
    }

    private lateinit var layout: View
    // private lateinit var database: DatabaseReference
    // val jokes = mutableListOf<String?>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        layout = inflater.inflate(
            R.layout.fragment_jokes,
            container,
            false
        )

        return layout
    }

    override fun onStart() {
        super.onStart()

        viewManager = LinearLayoutManager(activity)
        recyclerView = layout.findViewById<RecyclerView>(R.id.jokes_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        val jokes = mutableListOf<String>()

        val database = FirebaseDatabase.getInstance().getReference("jokes")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (snapshot in p0.children) {
                        val joke = snapshot.value.toString()
                        jokes.add(joke)
                    }
                    val adapter = JokesAdapter(jokes)
                    recyclerView.adapter = adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}
