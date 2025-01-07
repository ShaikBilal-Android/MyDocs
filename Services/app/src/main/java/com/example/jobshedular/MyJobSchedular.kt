package com.example.jobshedular

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log


class MyJobSchedular: JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {

        Log.d("MAIN","JobScheduling Success")
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("MAIN","JobScheduling Failed")
        return false
    }
}