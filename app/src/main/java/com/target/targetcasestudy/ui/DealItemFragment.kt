package com.target.targetcasestudy.ui

import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.opengl.Visibility
import android.os.Bundle
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.Product
import kotlinx.android.synthetic.main.fragment_deal_item.*


class DealItemFragment(val product : Product) : Fragment() {
  private lateinit var image: ImageView
  private lateinit var regPrice :TextView
  private lateinit var dealItemPrice :TextView
  private lateinit var desc :TextView
  private lateinit var addToList :Button
  private lateinit var addToCart :Button
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =  inflater.inflate(R.layout.fragment_deal_item, container, false)

    image = view.findViewById<ImageView>(R.id.image_url)
    dealItemPrice = view.findViewById(R.id.deal_item_price)
    regPrice = view.findViewById(R.id.regPrice)
    desc = view.findViewById(R.id.desc)
    addToList = view.findViewById(R.id.button1)
    addToCart = view.findViewById(R.id.button2)

    Picasso.get().load(product.image_url).into(image);

    if(product.sale_price!=null){
      regPrice.text = product.sale_price.display_string
      regPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
    }else{
      regPrice.visibility = View.INVISIBLE
    }
   // dealItemPrice.text = product.sale_price.display_string

    if(product.regular_price!=null){
      dealItemPrice.text = product.regular_price.display_string


    }else{
      dealItemPrice.visibility = View.INVISIBLE
    }
    desc.text = product.description


    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }
}
