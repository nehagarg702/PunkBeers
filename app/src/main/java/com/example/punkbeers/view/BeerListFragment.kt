package com.example.punkbeers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.punkbeers.PunkBeerApplication
import com.example.punkbeers.R
import com.example.punkbeers.adapter.BeerAdapter
import com.example.punkbeers.databinding.FragmentFirstBinding
import com.example.punkbeers.interfaces.OnRecyclerItemClickListener
import com.example.punkbeers.model.Beer
import com.example.punkbeers.utils.Constants
import com.example.punkbeers.viewmodel.BeerViewModel
import com.example.punkbeers.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BeerListFragment : Fragment(), OnRecyclerItemClickListener {

    private var _binding: FragmentFirstBinding? = null
    lateinit var viewModel: BeerViewModel
    @Inject
    lateinit var factory :ViewModelFactory

    private val binding get() = _binding!!
    lateinit var beerAdapter : BeerAdapter
    var beerList = ArrayList<Beer>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PunkBeerApplication.appComponent.inject(this)
        setUpBeerAdapter()
        viewModel = ViewModelProvider(requireActivity(),factory)[BeerViewModel::class.java]
        binding.viewmodel = viewModel
        viewModel.beerList.observe(viewLifecycleOwner){
            beerList.clear()
            beerList.addAll(it)
            beerAdapter.notifyDataSetChanged()
        }
    }

    fun setUpBeerAdapter(){
        beerAdapter = BeerAdapter(beerList,this)
        binding.searchRecyclerView.adapter = beerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.BEER_OBJECT,beerList[position])
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
    }
}