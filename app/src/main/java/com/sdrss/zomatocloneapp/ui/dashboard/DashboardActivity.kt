package com.sdrss.zomatocloneapp.ui.dashboard

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import com.google.android.gms.location.*
import com.sdrss.zomatocloneapp.R
import com.sdrss.zomatocloneapp.binding.BindingActivity
import com.sdrss.zomatocloneapp.data.remote.response.location.LocationSuggestion
import com.sdrss.zomatocloneapp.databinding.DashboardActivityBinding
import com.sdrss.zomatocloneapp.ui.search.LocationSearchActivity
import com.sdrss.zomatocloneapp.utils.Constants
import com.sdrss.zomatocloneapp.utils.Utilities
import kotlinx.android.synthetic.main.dashboard_activity.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DashboardActivity : BindingActivity<DashboardActivityBinding>() {

    private val LOCATION_SEARCH_ACTIVITY: Int = 12345

    lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun getLayoutResId(): Int = R.layout.dashboard_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = getViewModel()
        binding.setLifecycleOwner(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DashboardFragment.newInstance())
                .commitNow()
        }

        //Navigate to Search Location Screen
        rlYourLocationLayout.setOnClickListener {
            val intent = Intent(this, LocationSearchActivity::class.java)
            startActivityForResult(intent, LOCATION_SEARCH_ACTIVITY)
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == LOCATION_SEARCH_ACTIVITY) {
            try {
                val locationSuggestion =
                    data?.getParcelableExtra<LocationSuggestion>(Constants.LOCATION_SUGGESTION_DATA)

                locationSuggestion?.let { data ->
                    tvYourLocationValue.text = data.title
                    binding.vm?.let { vm ->
                        vm.locationSuggestion = data
                        vm.clearAdapterData.value = true
                        vm.doSearch()
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getLastLocation() {
        if (Utilities.checkPermissions(this)) {
            if (Utilities.isLocationEnabled(this)) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        binding.vm?.latitude = location.latitude
                        binding.vm?.longitude = location.longitude
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            Utilities.requestPermissions(this)
        }
    }

    @SuppressLint("MissingPermission")
    fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            Toast.makeText(
                applicationContext,
                "${mLastLocation.latitude} ${mLastLocation.longitude}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == Utilities.PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
            }
        }
    }
}
