package com.sdrss.zomatocloneapp.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.gms.location.*
import com.sdrss.zomatocloneapp.R
import com.sdrss.zomatocloneapp.binding.BindingActivity
import com.sdrss.zomatocloneapp.data.remote.response.location.LocationSuggestion
import com.sdrss.zomatocloneapp.databinding.ActivitySearchLocationBinding
import com.sdrss.zomatocloneapp.utils.Constants
import com.sdrss.zomatocloneapp.utils.Utilities
import kotlinx.android.synthetic.main.activity_search_location.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class LocationSearchActivity : BindingActivity<ActivitySearchLocationBinding>() {

    lateinit var adapterSearchResult: LocationSuggestionListAdapter

    lateinit var mFusedLocationClient: FusedLocationProviderClient

    val locationSuggestion by lazy { LocationSuggestion() }

    override fun getLayoutResId(): Int = R.layout.activity_search_location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        binding.vm = getViewModel()
        binding.setLifecycleOwner(this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //Observe Data and setData
        binding.vm?.resultLiveData?.observe(this, Observer { dataList ->
            adapterSearchResult.data = dataList.toMutableList()
        })
        adapterSearchResult = binding.vm?.let { LocationSuggestionListAdapter(it) }!!
        rvLocationSuggestion.adapter = adapterSearchResult

        adapterSearchResult.listener = this::selectedLocation

        ivClearSearchInput.setOnClickListener {
            etSearchInputText.setText("")
            binding.vm?.isClearSearchInput?.set(true)
        }

        //Observe request for current location
        binding.vm?.requestForLocationLiveData?.observe(this, Observer {
            if (it) {
                if (locationSuggestion.latitude != 0.0 || locationSuggestion.longitude != 0.0) {
                    selectedLocation(locationSuggestion)
                } else {
                    getLastLocation()
                }
            }
        })

    }

    private fun selectedLocation(locationSuggestion: LocationSuggestion) {
        val intent = Intent()
        intent.putExtra(Constants.LOCATION_SUGGESTION_DATA, locationSuggestion)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            Utilities.hideKeyboard(this)
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getLastLocation() {
        if (Utilities.checkPermissions(this)) {
            if (Utilities.isLocationEnabled(this)) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        locationSuggestion.latitude = location.latitude
                        locationSuggestion.longitude = location.longitude
                        locationSuggestion.title = getAddress(location.latitude, location.longitude)
                        selectedLocation(locationSuggestion)

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

    private fun getAddress(latitude: Double, longitude: Double): String {
        var address = "Current Location"
        // Setup Geo coder
        val geocoder =
            Geocoder(applicationContext, Locale.getDefault())
        val listOfAddress = geocoder.getFromLocation(
            latitude, longitude, 1
        )
        if ((listOfAddress != null) and (listOfAddress.size > 0)) {
            address = listOfAddress[0].locality
        }

        return address
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
            locationSuggestion.latitude = mLastLocation.latitude
            locationSuggestion.longitude = mLastLocation.longitude
            locationSuggestion.title = getAddress(mLastLocation.latitude, mLastLocation.longitude)
            selectedLocation(locationSuggestion)
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
