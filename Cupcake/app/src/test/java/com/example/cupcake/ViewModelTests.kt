package com.example.cupcake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cupcake.model.OrderViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule

class ViewModelTests {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    fun quantity_twelve_cupcake() {
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.quantity.value)
    }

    fun price_12_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever { }
        viewModel.setQuantity(12)
        assertEquals("$27.00", viewModel.price.value)
    }
}