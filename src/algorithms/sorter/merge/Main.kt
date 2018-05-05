package algorithms.sorter.merge

import algorithms.sorter.utils.Element
import algorithms.sorter.utils.SortAlgorithmEnum
import algorithms.sorter.insertion.insertionSorter

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
        mergeSorter(list, true)
        println("Ascending sort: $list, size: ${list.size}")
        mergeSorter(list, false)
        println("Descending sort: $list, size: ${list.size}")
    }
}

private fun merge(list: MutableList<Element>, startIndex: Int, middleIndex: Int, endIndex: Int, isAscending: Boolean): MutableList<Element> {
    // iterate through every element of second list backward
    for (i in endIndex - 1 downTo middleIndex) {
        // store the last object of the first list
        val lastFirstArrayElem = list[middleIndex - 1] //Can't store only index since the index value will be change
        // keep track of exact index first list has moved up to
        var newElemPositionIndex = middleIndex - 1
        for (j in middleIndex - 2 downTo startIndex) {
            //checking whether or not should the current elem be moved
            val shouldMove = if (isAscending) {
                list[j].value.toFloat() > list[i].value.toFloat()
            } else {
                list[j].value.toFloat() < list[i].value.toFloat()
            }
            if (shouldMove) {
                // if it does, store the current elem in the position ahead of it and the index of the current elem
                // will be where the current elem of second list be place
                newElemPositionIndex = j
                list[j + 1] = list[j]
            } else {
                break
            }
        }
        // check to see if need to be swap with the last element of first list
        // (edge case when only last elem of first list is the only value greater than current elem of second list)
        val shouldSwapWithLastFirstArrayElem = if (isAscending) {
            lastFirstArrayElem.value.toFloat() > list[i].value.toFloat()
        } else {
            lastFirstArrayElem.value.toFloat() < list[i].value.toFloat()
        }
        // if position has been changed or should swap with last first list, swap the current elem of second list with
        // that position and put the last value of first list to current elem of second list
        if  (newElemPositionIndex != middleIndex - 1 || shouldSwapWithLastFirstArrayElem) {
            list[newElemPositionIndex] = list[i]
            list[i] = lastFirstArrayElem
        }
    }
    return list.subList(startIndex, endIndex)
}

private fun mergeSort(list: MutableList<Element>, startIndex: Int, endIndex: Int, isAscending: Boolean, sortAlgorithm: SortAlgorithmEnum, sortSize: Int): MutableList<Element> {
    return if (endIndex - startIndex > sortSize) {
        val middleIndex = startIndex + ((endIndex - startIndex) / 2)
        mergeSort(list, startIndex, middleIndex, isAscending, sortAlgorithm, sortSize)
        mergeSort(list, middleIndex, endIndex, isAscending, sortAlgorithm, sortSize)
        merge(list, startIndex, middleIndex, endIndex, isAscending)
    } else {
        val subList = list.subList(startIndex, endIndex)
        if (subList.size != 1) {
            when (sortAlgorithm) {
                SortAlgorithmEnum.MERGE_SORT, SortAlgorithmEnum.INSERTION_SORT -> insertionSorter(subList, isAscending)
            }
        }
        subList
    }
}

fun mergeSorter(list: MutableList<Element>, isAscending: Boolean = true, sortAlgorithm: SortAlgorithmEnum = SortAlgorithmEnum.MERGE_SORT, sortSize: Int = 10): MutableList<Element> {
    return mergeSort(list, 0, list.size, isAscending, sortAlgorithm, sortSize)
}


/**
 * ATTEMPT FOR IN-PLACE MERGING
 */

//private fun swapElem(list: MutableList<Element>, firstIndex: Int, secondIndex: Int) {
//    val tempElement = list[firstIndex]
//    list[firstIndex] = list[secondIndex]
//    list[secondIndex] = tempElement
//}
//    for (i in startIndex until endIndex) {
//        if (i == secondPointer) {
//            if (secondPointer != endIndex - 1) secondPointer++
//            else break
//        }
//        if (i == tempValueHolderIndex && tempValueHolderIndex < secondPointer) {
//            tempValueHolderIndex++
//        }
//        val shouldSwap = if (isAscending) {
//            list[i].value.toFloat() > list[secondPointer].value.toFloat() ||
//            list[i].value.toFloat() > list[tempValueHolderIndex].value.toFloat()
//        } else {
//            list[i].value.toFloat() < list[secondPointer].value.toFloat() ||
//            list[i].value.toFloat() < list[tempValueHolderIndex].value.toFloat()
//        }
//        val shouldTempValueSwapWithSecondPointerValue = when {
//            tempValueHolderIndex == secondPointer -> false
//            isAscending -> list[tempValueHolderIndex].value.toFloat() > list[secondPointer].value.toFloat()
//            else -> list[tempValueHolderIndex].value.toFloat() < list[secondPointer].value.toFloat()
//        }
//        if (shouldTempValueSwapWithSecondPointerValue) {
//            swapElem(list, tempValueHolderIndex, secondPointer)
//
//            if (secondPointer != endIndex - 1) secondPointer++
//        }
//        if (shouldSwap && i != tempValueHolderIndex) {
//            swapElem(list, i, tempValueHolderIndex)
//            if (i > middleIndex && tempValueHolderIndex < secondPointer) {
//                tempValueHolderIndex++
//            }
//            if (secondPointer == tempValueHolderIndex && secondPointer != endIndex - 1) secondPointer++
//        }
//        if (i > secondPointer && i + 1 < endIndex) {
//            secondPointer = i + 1
//            tempValueHolderIndex = secondPointer
//        }
//    }