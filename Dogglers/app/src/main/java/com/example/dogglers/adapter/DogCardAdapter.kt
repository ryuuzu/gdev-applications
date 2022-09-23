/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    val dataset = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val dogImageView = view?.findViewById<ImageView>(R.id.dog_image)
        val dogNameTextView = view?.findViewById<TextView>(R.id.dog_name)
        val dogAgeTextView = view?.findViewById<TextView>(R.id.dog_age)
        val dogHobbiesTextView = view?.findViewById<TextView>(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val layoutToUse: Int = if (layout == Layout.VERTICAL || layout == Layout.HORIZONTAL) {
            R.layout.vertical_horizontal_list_item
        } else {
            R.layout.grid_list_item
        }

        val adapterLayout = LayoutInflater.from(parent.context).inflate(layoutToUse, parent, false)
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val item = dataset[position]
        holder.dogImageView?.setImageResource(item.imageResourceId)
        holder.dogNameTextView?.text = item.name

        val resources = context?.resources

        holder.dogAgeTextView?.text = resources?.getString(R.string.dog_age, item.age)
        holder.dogHobbiesTextView?.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
    }
}
