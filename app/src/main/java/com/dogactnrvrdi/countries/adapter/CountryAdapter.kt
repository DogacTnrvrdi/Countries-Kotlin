package com.dogactnrvrdi.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dogactnrvrdi.countries.R
import com.dogactnrvrdi.countries.databinding.CountryRowBinding
import com.dogactnrvrdi.countries.model.Country
import com.dogactnrvrdi.countries.view.CountriesFragmentDirections
import kotlinx.android.synthetic.main.country_row.view.*

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {

    class CountryViewHolder(val binding: CountryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CountryRowBinding>(
            inflater, R.layout.country_row, parent, false
        )
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.country = countryList[position]
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = CountriesFragmentDirections.actionCountriesFragmentToDetailsFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}