package com.mzs.core.presentation.components.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class NavMenuAdapter<Type, Binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> Binding,
    private val onBindItem: (Type, Binding) -> Unit,
    private val onItemClicked: (Type) -> Unit,
) : RecyclerView.Adapter<NavMenuAdapter.NavMenuViewHolder<Type, Binding>>() {

    var list: List<Type> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NavMenuViewHolder<Type, Binding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(layoutInflater, parent, false)
        return NavMenuViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NavMenuViewHolder<Type, Binding>, position: Int) {
        holder.bind(
            item = list[position],
            onBindItem = onBindItem,
            onMenuItemSelected = onItemClicked
        )
    }

    class NavMenuViewHolder<Type, Binding : ViewBinding>(private val binding: Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Type,
            onBindItem: (Type, Binding) -> Unit,
            onMenuItemSelected: (Type) -> Unit,
        ) {
            itemView.setOnClickListener { onMenuItemSelected(item) }
            onBindItem(item, binding)
        }
    }

}
