package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int>
        get() = this._quantity

    private val _flavour = MutableLiveData<String>()
    val flavour: LiveData<String> = this._flavour

    private val _pickUpDate = MutableLiveData<String>()
    val pickUpDate: LiveData<String> = this._pickUpDate

    private val _formatter = SimpleDateFormat("E, MMM d", Locale.getDefault())

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    var dateOptions = getPickUpOptions()

    init {
        resetOrder()
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavour.value = ""
        _pickUpDate.value = dateOptions[0]
        _price.value = 0.0
    }

    private fun refreshPickUpOptions() {
        dateOptions = getPickUpOptions()
        _pickUpDate.value = dateOptions[0]
    }


    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavour(desiredFlavour: String) {
        _flavour.value = desiredFlavour
        refreshPickUpOptions()
        updatePrice()
    }

    fun setDate(pickUpDate: String) {
        _pickUpDate.value = pickUpDate
        updatePrice()
    }

    fun hasNoFlavourSet(): Boolean {
        return _flavour.value.isNullOrEmpty()
    }

    private fun getPickUpOptions(): List<String> {
        val options = mutableListOf<String>()

        val calendar = Calendar.getInstance()

        if (_flavour.value.equals("Special Flavor")) {
            calendar.add(Calendar.DATE, 1)
        }

        repeat(4) {
            options.add(_formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (_pickUpDate.value.equals(_formatter.format(Calendar.getInstance().time))) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
}