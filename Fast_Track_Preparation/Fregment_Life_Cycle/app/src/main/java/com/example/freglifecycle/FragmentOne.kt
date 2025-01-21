package com.example.freglifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentOne : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FragmentOne", "onAttach() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentOne", "onCreate() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentOne", "onCreateView() called")
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentOne", "onViewCreated() called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("FragmentOne", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentOne", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FragmentOne", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FragmentOne", "onStop() called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FragmentOne", "onDestroyView() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentOne", "onDestroy() called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("FragmentOne", "onDetach() called")
    }
}
