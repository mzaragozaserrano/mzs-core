package com.mzaragozaserrano.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mzaragozaserrano.presentation.databinding.CoreItemMenuBinding

class NavMenuAdapter<T>(
    private val onBindItem: (CoreItemMenuBinding, T) -> Unit,
    private val onItemClicked: (T) -> Unit,
) : RecyclerView.Adapter<NavMenuAdapter.NavMenuViewHolder<T>>() {

    var list: List<T> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NavMenuViewHolder<T> = NavMenuViewHolder.from(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NavMenuViewHolder<T>, position: Int) {
        holder.bind(list[position], onItemClicked, onBindItem)
    }

    class NavMenuViewHolder<T>(private val binding: CoreItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T, onMenuItemSelected: (T) -> Unit, onBindItem: (CoreItemMenuBinding, T) -> Unit) {
            itemView.apply { setOnClickListener { onMenuItemSelected(item) } }
            onBindItem(binding, item)
        }

        companion object {
            fun <T> from(parent: ViewGroup): NavMenuViewHolder<T> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CoreItemMenuBinding.inflate(layoutInflater, parent, false)
                return NavMenuViewHolder(binding)
            }

        }

    }


}