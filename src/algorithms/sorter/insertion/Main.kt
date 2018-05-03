package algorithms.sorter.insertion

import algorithms.sorter.utils.Element

object InsertionSortTester {
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
        println("Test Insertion Sort")
        val list = generateRandomElements(10, 100)
        println("Unsorted: $list, size: ${list.size}")
        insertionSort(list, true)
        println("Ascending sort: $list, size: ${list.size}")
        insertionSort(list, false)
        println("Descending sort: $list, size: ${list.size}")
    }
}

fun insertionSort(list: MutableList<Element>, isAscending: Boolean = true): MutableList<Element> {
    for (j in 1 until list.size) {
        val currentElem = list[j]
        for (i in 0 until j) {
            val isInserting = if (isAscending) {
                currentElem.value.toFloat() < list[i].value.toFloat()
            } else {
                currentElem.value.toFloat() > list[i].value.toFloat()
            }
            if (isInserting) {
                for (k in j downTo i+1) {
                    list[k] = list[k-1]
                }
                list[i] = currentElem
                break
            }
        }
    }
    return list
}