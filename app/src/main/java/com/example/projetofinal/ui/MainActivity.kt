package com.example.projetofinal.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofinal.R
import com.example.projetofinal.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), UserAdapter.Listener {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupObservers()

        viewModel.initDb(applicationContext)

        userAdapter = UserAdapter(mutableListOf(), this)

        val recyclerView: RecyclerView = findViewById(R.id.list)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = userAdapter

        findViewById<FloatingActionButton>(R.id.fbAdd).setOnClickListener {
            openUserDialog()
        }
    }

    private fun setupObservers() {
        viewModel.users.observe(this) { users ->
            userAdapter.addUsers(users)
        }
    }

    private fun openUserDialog(user: User? = null) {
        val dialog = AddUserDialog.newInstance(user, object : AddUserDialog.Listener {
            override fun onAddUser(user: User) {
                viewModel.addUser(user)
            }

            override fun onEditUser(user: User) {
                viewModel.updateUser(user)
            }
        })

        dialog.show(supportFragmentManager, "AddUserDialog")
    }

    override fun onClick(user: User) {
        openUserDialog(user)
    }

    override fun onLongClick(user: User) {
        viewModel.deleteUser(user)
    }
}