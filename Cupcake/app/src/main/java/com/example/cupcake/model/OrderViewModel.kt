package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int>
        get() = this._quantity

    private val _flavour = MutableLiveData<String>()
    val flavour: LiveData<String> = this._flavour

    private val _pickUpDate = MutableLiveData<String>()
    val pickUpDate: LiveData<String> = this._pickUpDate

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = this._price

    val dateOptions = getPickUpOptions()

    init {
        resetOrder()
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavour.value = ""
        _pickUpDate.value = dateOptions[0]
        _price.value = 0.0
    }


    fun setQuantity(numberCupcakes: Int) {
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

    fun getPickUpOptions(): List<String> {
        val options = mutableListOf<String>()

        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
}