package com.dogactnrvrdi.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dogactnrvrdi.countries.R
import com.dogactnrvrdi.countries.databinding.FragmentDetailsBinding
import com.dogactnrvrdi.countries.util.downloadFromUrl
import com.dogactnrvrdi.countries.util.placeholderProgressBar
import com.dogactnrvrdi.countries.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    //Binding
    private lateinit var dataBinding: FragmentDetailsBinding

    //Country UUID
    private var countryUuid = 0

    //ViewModel
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = DetailsFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = country
            }
        })
    }
}