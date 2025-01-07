package com.example.jobshedular

import android.app.job.JobInfo
import android.app.job.JobInfo.NETWORK_TYPE_ANY
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    // Global variable declaration for future use
    lateinit var jobSchedular: JobScheduler
    lateinit var jobInfo: JobInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Step 01 : Create a obj  component JobScheduler
        // Step 1.1 : Declare a service by calling getSystemService() and type cast it with JobSchedular
        jobSchedular = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        // step 02 : Create a object for JobInfo Component
        // Step 2.1 : Set the required conditions for performing a Job
        jobInfo = JobInfo.Builder(12, ComponentName(packageName,MyJobSchedular::class.java.name))
            .setRequiredNetworkType(NETWORK_TYPE_ANY)
            .build()
    }

    fun scheduleJob(view: View) {
        // Step 03 : Call the 3rd component JobScheduler and passed the JobInfo object

        jobSchedular.schedule(jobInfo)

    }
}