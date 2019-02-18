package com.bhanupro.room_rxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.bhanupro.room_rxjava.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.widget.Toast
import com.bhanupro.room_rxjava.Injection


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }
    private lateinit var mUserName:TextView
    private lateinit var mUserNameInput:EditText
    private lateinit var updateBtn:Button

    private lateinit var viewModelFactory:ViewModelFactory
    private lateinit var userViewModel: UserViewModel
    private var disposable:CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mUserName = findViewById(R.id.user_name)
        mUserNameInput = findViewById(R.id.user_name_input)
        updateBtn = findViewById(R.id.update_user)

        viewModelFactory = Injection.provideViewModelFactory(this)
        userViewModel = ViewModelProviders.of(this,viewModelFactory).get(UserViewModel::class.java)
        updateBtn.setOnClickListener {
            updateUserName()
        }
    }
    private fun updateUserName(){
        var userName = mUserNameInput.text.toString()

        updateBtn.isEnabled = false

        disposable.add(userViewModel.updateUserName(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateBtn.isEnabled = true
            },{
                Toast.makeText(this@MainActivity,it.message,Toast.LENGTH_SHORT).show()
            } ))
    }

    override fun onStart() {
        super.onStart()

        disposable.add(userViewModel.getUserName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mUserName.text = it
            },{
                Toast.makeText(this@MainActivity,it.message,Toast.LENGTH_SHORT).show()
            }))
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}
