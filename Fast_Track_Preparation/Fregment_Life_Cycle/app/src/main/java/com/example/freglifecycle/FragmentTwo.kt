package com.example.freglifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentTwo : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FragmentTwo", "onAttach() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentTwo", "onCreate() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentTwo", "onCreateView() called")
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentTwo", "onViewCreated() called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("FragmentTwo", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentTwo", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FragmentTwo", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FragmentTwo", "onStop() called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FragmentTwo", "onDestroyView() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentTwo", "onDestroy() called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("FragmentTwo", "onDetach() called")
    }
}
