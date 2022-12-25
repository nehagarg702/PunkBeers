package com.example.punkbeers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.punkbeers.databinding.BeerItemLayoutBinding
import com.example.punkbeers.interfaces.OnRecyclerItemClickListener
import com.example.punkbeers.model.Beer

class BeerAdapter(private var beerList : ArrayList<Beer>, private var clickListener : OnRecyclerItemClickListener)
    : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    class BeerViewHolder(var binding : BeerItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer) {
            binding.beers = beer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(BeerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beerList[position])
        holder.binding.cardRow.setOnClickListener { clickListener.onItemClick(position) }
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            url?.let {
                Glide.with(view.context)
                    .load(url)
                    .fitCenter()
                    .into(view)
            }
        }
    }
}