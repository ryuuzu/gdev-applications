package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData(0)
    val quantity: LiveData<Int>
        get() = this._quantity

    private val _flavour = MutableLiveData("")
    val flavour: LiveData<String> = this._flavour

    private val _pickUpDate = MutableLiveData("")
    val pickUpDate: LiveData<String> = this._pickUpDate

    private val _price = MutableLiveData(0.0)
    val price: LiveData<Double> = this._price


    fun setQuantitiy(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavour(desiredFlavour: String) {
        _flavour.value = desiredFlavour
    }

    fun setDate(pickUpDate: String) {
        _pickUpDate.value = pickUpDate
    }

    fun hasNoFlavourSet(): Boolean {
        return _flavour.value.isNullOrEmpty()
    }
}