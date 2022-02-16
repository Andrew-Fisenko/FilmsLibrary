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

class ServiceFragment : Fragment(){
    private var _binding: ServiceFragmentLayoutBinding? = null
    private val binding get() = _binding!!

//  private var isBound = false
//  private var boundService: BoundService.ServiceBinder? = null

//    private val testReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            Toast.makeText(
//                context,
//                "FROM SERVICE: ${intent?.getBooleanExtra(MyForegroundService.INTENT_SERVICE_DATA, false)}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

//    // Обработка соединения с сервисом
//    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
//        // При соединении с сервисом
//        override fun onServiceConnected(name: ComponentName, service: IBinder) {
//            boundService = service as BoundService.ServiceBinder
//            isBound = boundService != null
//            Log.i("SERVICE", "BOUND SERVICE")
//            Log.i("SERVICE", "next fibonacci: ${service.nextFibonacci}")
//        }
//
//        // При разрыве соединения с сервисом
//        override fun onServiceDisconnected(name: ComponentName) {
//            isBound = false
//            boundService = null
//        }
//    }
//
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
            textView.text = startCalculations(binding.editText.text.toString().toInt())
            mainContainer.addView(TextView(it.context).apply {
                text = getString(R.string.service_working)
                textSize = resources.getDimension(R.dimen.main_container_text_size)
            })

//            val data = binding.editText.text.toString().toInt()
//
//            launch {
//                val task = async(Dispatchers.Default) { startCalculations(data) }
//                textView.text = task.await()

                /*if (isActive) {

                }*/
//            }
            //coroutine.cancel()
        }

        /* val handlerThread = HandlerThread("my handler thread")
         handlerThread.start()
         val handler = Handler(handlerThread.looper)
         handler.post {

         }
         handler.post {
             //..
             //..
             //..
         }
         handlerThread.quit()
         handlerThread.quitSafely()*/

        /*handler.postDelayed({

        }, 500)*/

//        MyForegroundService.start(requireContext())
    }
//
//    override fun onStart() {
//        super.onStart()
//        if (!isBound) {
//            val bindServiceIntent = Intent(context, BoundService::class.java)
//            activity?.bindService(bindServiceIntent, boundServiceConnection, Context.BIND_AUTO_CREATE)
//        }
//        activity?.registerReceiver(testReceiver, IntentFilter(MyForegroundService.INTENT_ACTION_KEY))
//    }
//
//    override fun onStop() {
//        activity?.unregisterReceiver(testReceiver)
//        if (isBound) {
//            activity?.unbindService(boundServiceConnection)
//        }
//        super.onStop()
//    }

    private fun startCalculations(seconds: Int): String {
        val date = Date()
        var diffInSec: Long
        do {
            val currentDate = Date()
            val diffInMs: Long = currentDate.time - date.time
            diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
        } while (diffInSec < seconds)

        return diffInSec.toString()
    }

    companion object {
        fun newInstance() = ServiceFragment()
    }
}