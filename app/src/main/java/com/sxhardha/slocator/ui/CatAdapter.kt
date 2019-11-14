package com.sxhardha.slocator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sxhardha.slocator.R
import com.sxhardha.slocator.model.Cat
import kotlinx.android.synthetic.main.single_item_cat.view.*


class CatAdapter(private val picasso: Picasso) :
    ListAdapter<Cat, CatAdapter.CatViewHolder>(DIFF_UTIL_CATS) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder =
        CatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_item_cat,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, picasso)
        }
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(it: Cat?, picasso: Picasso) = with(itemView) {
            picasso.load(it?.url).fit().into(ivCat)
            tvCatId.text = "Cat ID: ${it?.id}"
        }
    }
}

val DIFF_UTIL_CATS = object : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
        oldItem.id == newItem.id && oldItem.url == newItem.url
}