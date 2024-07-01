package com.example.intricare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.intricare.MainActivity.Companion.checkLoginCred
import com.example.intricare.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val userId = view.findViewById<EditText>(R.id.userId)
        val password = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login_btn)
        loginButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                checkLoginCred(
                    userId.text.toString(),
                    password.text.toString(),
                    requireActivity().supportFragmentManager,
                    requireActivity().applicationContext)
            }
        }

        return view
    }

}