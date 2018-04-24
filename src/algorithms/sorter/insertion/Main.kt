package algorithms.sorter.insertion

import algorithms.sorter.insertion.interfaces.Element

object InsertionSort {
    fun sort(list: MutableList<Element>): MutableList<Element> {
        for (j in 1 until list.size) {
            println("Presort $list")
            val currentElem = list[j]
            for (i in j-1 downTo 0) {
                if (currentElem.value.toFloat() < list[i].value.toFloat() ) {
                    for (k in j downTo i+1) {
                        list[k] = list[k-1]
                    }
                    list[i] = currentElem
                }
            }
            println("Post-Sort $list\n")
        }
        return list
    }

    private class ExampleElement(override val value: Number) : Element {
        override fun toString(): String {
            return "Object { value: ${this.value} }"
        }
    }

    private fun generateRandomElements(size: Int, limit: Int): ArrayList<Element> {
        val list = ArrayList<Element>(size)
        for (i in 0 until size) {
            list.add(ExampleElement(Math.round(Math.random()*limit)))
        }
        return list
    }

    @JvmStatic
    fun main(args: Array<String>){
        val list = generateRandomElements(5, 100)
        println("Unsorted: $list, size: ${list.size}")
        sort(list)
        println("Sorted: $list, size: ${list.size}")
    }
}