package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentListBinding
import com.udacity.shoestore.models.MainViewModel


class ListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(
            inflater,
            R.layout.fragment_list, container, false
        )

        //show options menu
        setHasOptionsMenu(true)


        //observe live data shoe list
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->

            //loop through list of shoes
            for (shoe in shoeList) {

                //create text view and set parameters
                val shoe_text_view = TextView(context)
                val params: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(10, 10, 20, 10)
                shoe_text_view.layoutParams = params
                shoe_text_view.textSize = 15f
                shoe_text_view.setBackgroundResource(R.drawable.list_item_border)
                shoe_text_view.setPadding(10, 10, 10, 10)


                //Create string to hold the text to be displayed for the shoe
                val shoeString = "Shoe Name: " + shoe.name + System.getProperty ("line.separator") + "Size: " +
                        shoe.size.toString() + System.getProperty ("line.separator") + "Company: " +
                        shoe.company + System.getProperty ("line.separator") + "Description: " +
                        shoe.description
                shoe_text_view.text = shoeString

                //add view to the layout
                binding.listLinearLayout.addView(shoe_text_view)

            }


        })


        binding.floatingActionButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_listFragment_to_detailFragment)

        }

        return binding.root
    }


    //inflate options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    //On logout reset shoe list and return to login screen
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.resetShoeList()
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}