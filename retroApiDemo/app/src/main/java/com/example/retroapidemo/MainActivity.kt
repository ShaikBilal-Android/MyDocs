package com.example.retroapidemo
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}
import android.os.Bundle
import android.telephony.TelephonyCallback.DataActivationStateListener
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var dstSpinner: Spinner
    private lateinit var mndSpinner: Spinner
    private lateinit var vilSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dstSpinner = findViewById(R.id.spinnerDistrict)
        mndSpinner = findViewById(R.id.spinnerMandal)
        vilSpinner = findViewById(R.id.spinnerVillage)

        // Initialize Room Database
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "location-database"
        ).build()

        // Insert some data and query
        GlobalScope.launch {
            insertSampleData()
            queryData()
        }
    }

    private suspend fun insertSampleData() {
        val district = District(name = "District1,District2,District3")
        val districtId = db.districtDao().insertDistrict(district)

        val mandal = Mandal(name = "Mandal1,Mandal2,Mandal3", districtId = districtId.toInt())
        val mandalId = db.mandalDao().insertMandal(mandal)

        db.villageDao().insertVillage(Village(name = "Village1", mandalId = mandalId.toInt()))
        db.villageDao().insertVillage(Village(name = "Village2", mandalId = mandalId.toInt()))
    }

    private suspend fun queryData() {
        val districts = db.districtDao().getAllDistricts()
        val mandals = db.mandalDao().getMandalsForDistrict(districts.first().districtId)
        val villages = db.villageDao().getVillagesForMandal(mandals.first().mandalId)

//        districts.setSpinner


        // Log or display the data
        println("Districts: $districts")
        println("Mandals: $mandals")
        println("Villages: $villages")
    }
}
