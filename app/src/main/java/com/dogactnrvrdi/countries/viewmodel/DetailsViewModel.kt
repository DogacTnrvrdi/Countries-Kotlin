package com.dogactnrvrdi.countries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dogactnrvrdi.countries.model.Country
import com.dogactnrvrdi.countries.service.CountryDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int) {

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}