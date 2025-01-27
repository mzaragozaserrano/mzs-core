package com.mzs.core.presentation.components.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mzs.core.presentation.vo.MenuItemVO

class NavMenuAdapter<Binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> Binding,
    private val onBindItem: (MenuItemVO, Binding) -> Unit,
    private val onItemClicked: (MenuItemVO) -> Unit,
) : RecyclerView.Adapter<NavMenuAdapter.NavMenuViewHolder<Binding>>() {

    var list: List<MenuItemVO> = emptyList()

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
            item: MenuItemVO,
            onBindItem: (MenuItemVO, Binding) -> Unit,
            onMenuItemSelected: (MenuItemVO) -> Unit,
        ) {
            itemView.setOnClickListener { onMenuItemSelected(item) }
            onBindItem(item, binding)
        }
    }

}
