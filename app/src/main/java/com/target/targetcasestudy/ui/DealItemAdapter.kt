package com.target.targetcasestudy.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.Product
import com.target.targetcasestudy.data.ApiData
import java.util.*
import kotlin.collections.ArrayList


class DealItemAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<DealItemAdapter.ProductViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var products = ArrayList<Product>()
    var onItemClick: ((Product) -> Unit)? = null

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productItemViewTitle: TextView = itemView.findViewById(R.id.deal_list_item_title)
        val productItemViewPrice: TextView = itemView.findViewById(R.id.deal_list_item_price)
        val dealListItemImageView: ImageView = itemView.findViewById(R.id.deal_list_item_image_view)
        val dealListItemAisle: CircularTextView = itemView.findViewById(R.id.aisle)
        val progressImage: ProgressBar = itemView.findViewById(R.id.progressImage)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(products[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = inflater.inflate(R.layout.deal_list_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = products[position]
        holder.productItemViewTitle.text = current.title
        holder.productItemViewPrice.text = current.regular_price.display_string
        Picasso.get().load(current.image_url).into(holder.dealListItemImageView,object: com.squareup.picasso.Callback {
            override fun onSuccess() {
            holder.progressImage.visibility  = View.GONE

            }

            override fun onError(e: java.lang.Exception?) {
                holder.progressImage.visibility  = View.GONE
            }
        })
        holder.dealListItemAisle.text = current.aisle.toUpperCase(Locale.ROOT)
        holder.dealListItemAisle.setStrokeWidth(1);
        holder.dealListItemAisle.setStrokeColor("#FFB6C1");
        holder.dealListItemAisle.setSolidColor("#FFFFFF");

    }



    override fun getItemCount() = products.size

    fun addUsers(users: ApiData) {
        this.products.apply {
            clear()
            addAll(users.products)
        }

    }
}


