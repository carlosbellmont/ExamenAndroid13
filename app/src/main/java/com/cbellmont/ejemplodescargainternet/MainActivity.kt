package com.cbellmont.ejemplodescargainternet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cbellmont.ejemplodescargainternet.databinding.ActivityMainBinding
import kotlinx.coroutines.async


// IMPORTANT: Passing the activity to a the receiver is not a good practice, it may cause issues
// with the activity-s lifecycle. We are doing it just to keep the focus on the target of this example
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setContentView(R.layout.activity_main)
        val resultado = lifecycleScope.async{
            GetAllFilms.send()
        }
        onFilmsReceived(resultado.await())
    }

    fun onFilmsReceived(listFilms : List<Film>) {
        binding.tvFilms.text = ""
        listFilms.forEach {
            binding.tvFilms.append(it.toString())
        }
    }
}