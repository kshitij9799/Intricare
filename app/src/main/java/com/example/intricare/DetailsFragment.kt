package com.example.intricare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.intricare.data.Post

class DetailsFragment(private val post: Post) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val name = view.findViewById<TextView>(R.id.name)
        val description = view.findViewById<TextView>(R.id.description)
        val pinCode = view.findViewById<TextView>(R.id.pinCode)
        val branchType = view.findViewById<TextView>(R.id.branchType)
        val deliveryStatus = view.findViewById<TextView>(R.id.deliveryStatus)
        val taluk = view.findViewById<TextView>(R.id.taluk)
        val circle = view.findViewById<TextView>(R.id.circle)
        val district = view.findViewById<TextView>(R.id.district)
        val division = view.findViewById<TextView>(R.id.division)
        val region = view.findViewById<TextView>(R.id.region)
        val state = view.findViewById<TextView>(R.id.state)
        val country = view.findViewById<TextView>(R.id.country)

        name.text = post.name
        if (post.description.isEmpty() && post.description.isBlank()) {
            description.text = getString(R.string.no_description)
        } else {description.text = post.description}
        pinCode.text = post.pinCode
        branchType.text = post.branchType
        deliveryStatus.text = post.deliveryStatus
        taluk.text = post.taluk
        circle.text = post.circle
        district.text = post.district
        division.text = post.division
        region.text = post.region
        state.text = post.state
        country.text = post.country

        return view
    }

}