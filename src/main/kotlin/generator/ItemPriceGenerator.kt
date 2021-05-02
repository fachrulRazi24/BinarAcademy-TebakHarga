package generator

import model.ItemPrice

object ItemPriceGenerator {
    fun getItems(): ArrayList<ItemPrice>{
        val items = arrayListOf<ItemPrice>()
        items.add(ItemPrice("Xiaomi Mi 11", 9999000))
        items.add(ItemPrice("TV Xiaomi Mi 4A", 2100000))
        items.add(ItemPrice("Samsung S21", 18000000))
        items.add(ItemPrice("Samsung A52", 5500000))
        items.add(ItemPrice("Powerbank Anker 10000 mAh", 300000))
        return items
    }
}