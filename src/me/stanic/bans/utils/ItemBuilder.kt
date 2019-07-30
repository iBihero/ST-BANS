package me.stanic.bans.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*

class ItemBuilder {

    private var itemStack: ItemStack? = null

    constructor(itemStack: ItemStack) {
        this.itemStack = itemStack
    }

    @JvmOverloads
    constructor(m: Material, quantia: Int = 1) {
        itemStack = ItemStack(m, quantia)
    }

    fun setName(nome: String): ItemBuilder {
        val im = itemStack!!.itemMeta
        im.displayName = nome
        itemStack!!.itemMeta = im
        return this
    }

    fun addLores(linha: List<String>): ItemBuilder {
        val im = itemStack!!.itemMeta
        var lore: MutableList<String> = ArrayList()
        if (im.hasLore()) lore = ArrayList(im.lore)
        for (s in linha) {
            lore.add(s)
        }
        im.lore = lore
        itemStack!!.itemMeta = im
        return this
    }

    fun toItemStack(): ItemStack? {
        return itemStack
    }
}