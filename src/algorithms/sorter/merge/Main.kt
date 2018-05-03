package algorithms.sorter.merge

import algorithms.sorter.utils.Element
import algorithms.sorter.utils.SortAlgorithmEnum
import algorithms.sorter.insertion.insertionSort

object MergeSortTester {
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
        println("Test Merge Sort")
        val list = generateRandomElements(10, 100)
        println("Unsorted: $list, size: ${list.size}")
        sort(list, true)
        println("Ascending sort: $list, size: ${list.size}")
        sort(list, false)
        println("Descending sort: $list, size: ${list.size}")
    }
}

private fun merge(list: MutableList<Element>, startIndex: Int, middleIndex: Int, endIndex: Int): MutableList<Element> {
    return list.subList(startIndex, endIndex)
}

private fun mergeSort(list: MutableList<Element>, startIndex: Int, endIndex: Int, isAscending: Boolean, sortAlgorithm: SortAlgorithmEnum, sortSize: Int): MutableList<Element> {
    val middleIndex = startIndex + (endIndex - startIndex / 2)
    if (endIndex - startIndex > sortSize) {
        mergeSort(list, startIndex, middleIndex, isAscending, sortAlgorithm, sortSize)
        mergeSort(list, middleIndex, endIndex, isAscending, sortAlgorithm, sortSize)
    } else {
        val subList = list.subList(startIndex, endIndex)
        when (sortAlgorithm) {
            SortAlgorithmEnum.MERGE_SORT, SortAlgorithmEnum.INSERTION_SORT ->
        }
        return subList
    }
    return merge(list, startIndex, middleIndex, endIndex)
}

fun sort(list: MutableList<Element>, isAscending: Boolean = true, sortAlgorithm: SortAlgorithmEnum = SortAlgorithmEnum.MERGE_SORT, sortSize: Int = 1): MutableList<Element> {
    return mergeSort(list, 0, list.size, isAscending, sortAlgorithm, sortSize)
}