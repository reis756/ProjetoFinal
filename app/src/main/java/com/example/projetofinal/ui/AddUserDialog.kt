package com.example.projetofinal.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.projetofinal.R
import com.example.projetofinal.model.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class AddUserDialog : DialogFragment() {

    private lateinit var editTextName: TextInputEditText
    private lateinit var editTextAge: TextInputEditText

    private var user: User? = null
    private var toEdit: Boolean = false
    private var listener: Listener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_user, null)

        editTextName = view.findViewById(R.id.editName)
        editTextAge = view.findViewById(R.id.editAge)

        if (user != null) {
            editTextName.setText(user?.name)
            editTextAge.setText(user?.age.toString())
        }

        return MaterialAlertDialogBuilder(requireActivity(), theme)
            .setView(view)
            .setTitle("Adicionar Usuário")
            .setMessage("Adicione aqui")
            .setPositiveButton("OK") { dialog, _ ->
                val user = User(
                    user?.id ?: 0,
                    editTextName.text.toString(),
                    editTextAge.text.toString().toIntOrNull() ?: 0
                )

                if (toEdit)
                    listener?.onEditUser(user)
                else
                    listener?.onAddUser(user)

                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            .create()

    }

    interface Listener {
        fun onAddUser(user: User)
        fun onEditUser(user: User)
    }

    companion object {
        fun newInstance(user: User?, listener: Listener) : AddUserDialog = AddUserDialog().apply {
            if (user != null) {
                this.user = user
                toEdit = true
            }
            this.listener = listener
        }
    }

}