package com.udacity.shoestore.models


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel(){


    //Live data array list to hold list of shoes
    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList : MutableLiveData<ArrayList<Shoe>>
    get()=_shoeList

    //Live data for new shoe to be added
    private val _newShoe = MutableLiveData<Shoe>()
    val newShoe : MutableLiveData<Shoe>
        get()=_newShoe

    init {
        _shoeList.value = ArrayList()
    }

    //adds the new shoe to the list of shoes
    fun addToList (){
          _shoeList.value?.add(_newShoe.value!!)
    }

    //resets the new shoe container
    fun resetNewShoe(){
        _newShoe.value = Shoe("",0.0,"","")
    }

    //resets shoe list
    fun resetShoeList(){
        _shoeList.value?.clear()
    }
}

