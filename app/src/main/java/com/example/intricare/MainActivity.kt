package com.example.intricare

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.intricare.data.LoginRequest
import com.example.intricare.data.Message
import com.example.intricare.data.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    init {
        getData()
        lifecycleScope.launch {
            delay(1000)
            val loginCred = UserCredPreferences(applicationContext).getCredentials()
            CoroutineScope(Dispatchers.IO).launch {
                checkLoginCred(
                    loginCred.username,
                    loginCred.password,
                    supportFragmentManager,
                    applicationContext
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        actionBar?.title = "INTRICARE"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                UserCredPreferences(applicationContext).clearCredentials()
                replaceFragment(supportFragmentManager, LoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        var postList: List<Post>? = null

        private fun getData() {
            var list: List<Post>? = null
            try {
                lateinit var res: Response<Message>
                CoroutineScope(Dispatchers.IO).launch {
                    runBlocking {
                        res = Api.retrofitService.getPostOffices().execute()
                    }
                    if (res.isSuccessful) {
                        val data = res.body()!!
                        postList = data.postOffice
                    }
                }
            } catch (e: Exception) {
                println("output1221 is $e")
            }
        }

        fun checkLoginCred(
            userId: String,
            password: String,
            fragmentManager: FragmentManager,
            context: Context
        ) {
            val response = Api.loginRetrofitService.getLoginConfirmation(
                LoginRequest(
                    userId,
                    password
                )
            ).execute()
            if (response.isSuccessful) {
                CoroutineScope(Dispatchers.Main).launch {
                    replaceFragment(fragmentManager, HomeFragment())
                    UserCredPreferences(context).saveCredentials(userId, password)
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    replaceFragment(fragmentManager, LoginFragment())
                }
            }
        }

        private fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment) {
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragment_holder, fragment)
                commit()
            }
        }
    }
}