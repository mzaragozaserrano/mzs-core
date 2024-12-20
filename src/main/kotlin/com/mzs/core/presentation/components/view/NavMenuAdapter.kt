package com.mzs.core.presentation.components.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class NavMenuAdapter<Binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> Binding,
    private val onBindItem: (Pair<Int, Int>, Binding) -> Unit,
    private val onItemClicked: (Pair<Int, Int>) -> Unit,
) : RecyclerView.Adapter<NavMenuAdapter.NavMenuViewHolder<Binding>>() {

    var list: List<Pair<Int, Int>> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NavMenuViewHolder<Binding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(layoutInflater, parent, false)
        return NavMenuViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NavMenuViewHolder<Binding>, position: Int) {
        holder.bind(
            item = list[position],
            onBindItem = onBindItem,
            onMenuItemSelected = onItemClicked
        )
    }

    class NavMenuViewHolder<Binding : ViewBinding>(private val binding: Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Pair<Int, Int>,
            onBindItem: (Pair<Int, Int>, Binding) -> Unit,
            onMenuItemSelected: (Pair<Int, Int>) -> Unit,
        ) {
            itemView.setOnClickListener { onMenuItemSelected(item) }
            onBindItem(item, binding)
        }
    }

}
