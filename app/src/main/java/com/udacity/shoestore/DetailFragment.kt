package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.MainViewModel


class DetailFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater,
            R.layout.fragment_detail,container,false)


        binding.mainViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.resetNewShoe()

        //On save add new shoe to list and navigate to list
        binding.saveButton.setOnClickListener { view : View ->

            viewModel.addToList()
            viewModel.resetNewShoe()
            view.findNavController().navigate(R.id.action_detailFragment_to_listFragment)

        }

        //On cancel navigate to list
        binding.cancelButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }

        return binding.root
    }


}