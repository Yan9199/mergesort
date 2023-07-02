package impl;

import impl.functions.ListToIntFunction;

import java.util.Comparator;
import java.util.List;

public class MergeSort<T> {

    /**
     * Determines the toggle length when the sorting algorithm should be toggled (usage of another sorting algorithm).
     */
    private final ListToIntFunction<T> function;

    /**
     * The comparator used to compare the elements of the list.
     */
    private final Comparator<? super T> cmp;

    /**
     * Constructs and initializes a {@code MyCollections}.
     *
     * @param function the function determining the toggle length
     * @param cmp      the comparator used to compare the elements of the list
     */
    public MergeSort(ListToIntFunction<T> function, Comparator<? super T> cmp) {
        this.function = function;
        this.cmp = cmp;
    }

    /**
     * Sorts the list in place.
     *
     * @param list the list to sort
     */
    public void sort(List<T> list) {
        if (list.size() < 2) return;
        listItemToList(adaptiveMergeSortInPlace(listToListItem(list), function.apply(list)), list);
    }

    /**
     * Transfers all elements from a list to a list item sequence.
     *
     * @param list the list to transfer from
     * @return the list item sequence containing the element of the list
     */
    private ListItem<T> listToListItem(List<T> list) {
        ListItem<T> dummy = new ListItem<>(), pointer = dummy;
        for (T t : list) pointer = pointer.next = new ListItem<>(t);
        return dummy.next;
    }

    /**
     * Transfers all elements from a ListItem sequence to a list.
     *
     * @param head the list item sequence
     * @param list the list to transfer to
     */
    private void listItemToList(ListItem<T> head, List<T> list) {
        for (int i = 0; head != null; head = head.next, i++) list.set(i, head.key);
    }

    /**
     * Sorts the list in place using the merge sort algorithm. If the (sub-)sequence is smaller than the specified threshold, the
     * selection sort algorithm (in place) is used.
     *
     * @param head      the list to sort
     * @param threshold the threshold determining the toggle length
     * @return the sorted list
     */
    private ListItem<T> adaptiveMergeSortInPlace(ListItem<T> head, int threshold) {
        int elements = 0, optimalSize = 1;
        for (ListItem<T> p = head, next = head.next; next != null; next = (p = next).next, elements++)
            if (cmp.compare(p.key, next.key) > 0) {
                if ((elements += ListItem.getSequenceLength(p)) <= threshold) return selectionSortInPlace(head, elements);
                int count = 1, max = elements;
                for (ListItem<T> n = next, h = p; n != null; n = (h = n).next, count++)
                    if (cmp.compare(h.key, n.key) > 0) {
                        int d = Math.abs(2 * count - elements);
                        if (d < max) {
                            max = d;
                            optimalSize = count;
                        }
                    }
                ListItem<T> rightSection = split(head, optimalSize);
                return merge(adaptiveMergeSortInPlace(head, threshold), adaptiveMergeSortInPlace(rightSection, threshold));
            }
        return head;
    }

    /**
     * Splits the list into two subsequences.
     *
     * <p>The decomposition of the list into two subsequences is related to the searched optimal size and the number of
     * elements of runs, which is close to the optimal size.
     *
     * @param head        the list to split
     * @param optimalSize the optimal size after the split
     * @return the second part of the list
     */
    private ListItem<T> split(ListItem<T> head, int optimalSize) {
        while (--optimalSize > 0) head = head.next;
        ListItem<T> saveNext = head.next;
        head.next = null;
        return saveNext;
    }

    /**
     * Merges the two given sub-sequences into one sorted sequence.
     *
     * @param left  the left sub-sequence
     * @param right the right sub-sequence
     * @return the merged sorted sequence
     */
    private ListItem<T> merge(ListItem<T> left, ListItem<T> right) {
        ListItem<T> head, pointer;
        if (cmp.compare(left.key, right.key) > 0) right = (head = right).next;
        else left = (head = left).next;
        pointer = head;
        while (left != null && right != null) {
            if (cmp.compare(left.key, right.key) > 0) right = (pointer = pointer.next = right).next;
            else left = (pointer = pointer.next = left).next;
        }
        pointer.next = left == null ? right : left;
        return head;
    }

    /**
     * Sorts the list in place using the selection sort algorithm.
     *
     * @param head the list to sort
     * @return the sorted list
     */
    private ListItem<T> selectionSortInPlace(ListItem<T> head, int s) {
        for (int i = s - 1; i > 0; i--) {
            int maxIndex = getMaximumIndex(head, i);
            if (maxIndex != i) head = relocateMax(head, maxIndex, i);
        }
        return head;
    }

    /**
     * Relocates the maximum of the sublist to its end.
     *
     * @param head the list to sort
     * @param maxIndex the index of the max item to be relocated
     * @param end the index for the end of the sublist
     * @return the list with the max item relocated to the end of the sublist
     */
    private ListItem<T> relocateMax(ListItem<T> head, int maxIndex, int end) {
        ListItem<T> pointer = head;
        if (maxIndex > 0) {
            end -= maxIndex;
            while (--maxIndex > 0) pointer = pointer.next;
            ListItem<T> maxItem = pointer.next;
            pointer = pointer.next = pointer.next.next;
            while (--end > 0) pointer = pointer.next;
            maxItem.next = pointer.next;
            pointer.next = maxItem;
            return head;
        }
        ListItem<T> saveNext = head.next;
        while (end-- > 0) pointer = pointer.next;
        head.next = pointer.next;
        pointer.next = head;
        return saveNext;
    }

    /**
     * Returns the index of the last maximum element in the list.
     *
     * @param head the list to search
     * @param i    the upper bound of the sublist
     * @return the index of the last maximum element in the list
     */
    private int getMaximumIndex(ListItem<T> head, int i) {
        int maxIndex = 0, currentIndex = 0;
        T maxKey = head.key;
        while (i >= 0) {
            T currentKey = head.key;
            if (cmp.compare(maxKey, currentKey) <= 0) {
                maxKey = currentKey;
                maxIndex = currentIndex;
            }
            head = head.next;
            currentIndex++;
            i--;
        }
        return maxIndex;
    }
}
