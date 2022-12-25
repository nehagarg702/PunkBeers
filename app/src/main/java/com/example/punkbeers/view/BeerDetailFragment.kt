package com.example.punkbeers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.punkbeers.R
import com.example.punkbeers.databinding.FragmentSecondBinding
import com.example.punkbeers.model.Beer
import com.example.punkbeers.utils.Constants

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BeerDetailFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setUpActionBar()
        setUpFoodList()
        return binding.root

    }

    private fun setUpFoodList() {
        arguments?.getParcelable<Beer>(Constants.BEER_OBJECT)?.foodPairing?.forEach {
            val food = TextView(requireActivity())
            food.text = it
            binding.beerFoodPairing.addView(food)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpActionBar() {
        setHasOptionsMenu(true)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.beerToolbar)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val title = arguments?.getParcelable<Beer>(Constants.BEER_OBJECT)?.name
        binding.beerToolbarLayout.title = title
        binding.beerToolbarLayout.setExpandedTitleColor(
            ContextCompat.getColor(activity, android.R.color.transparent)
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.beer =  arguments?.getParcelable<Beer>(Constants.BEER_OBJECT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}