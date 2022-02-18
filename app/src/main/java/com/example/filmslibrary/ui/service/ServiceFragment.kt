package com.example.filmslibrary.ui.service

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.filmslibrary.R

import com.example.filmslibrary.databinding.ServiceFragmentLayoutBinding
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ServiceFragment : Fragment() {
    private var _binding: ServiceFragmentLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ServiceFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
//            textView.text = startCalculations(binding.editText.text.toString().toInt())
            mainContainer.addView(TextView(it.context).apply {
                text = getString(R.string.service_working)
                textSize = resources.getDimension(R.dimen.main_container_text_size)
            })
        }
    }

    companion object {
        fun newInstance() = ServiceFragment()
    }
}