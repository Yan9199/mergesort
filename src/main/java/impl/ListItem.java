package impl;

public class ListItem<T> {

    /**
     * The value of this list item.
     */
    public T key;

    /**
     * The successor node of this list item.
     */
    public ListItem<T> next;

    /**
     * Constructs and initializes an empty list item.
     */
    public ListItem() {
    }

    /**
     * Constructs and initializes a list item with the given key.
     *
     * @param key the key of this list item
     */
    public ListItem(T key) {
        this.key = key;
    }

    /**
     * Returns the length of the given list.
     *
     * @param head the list
     * @return the length of the list
     * @param <T> the type parameter of the list
     */
    public static <T> int getSequenceLength(ListItem<T> head) {
        int sequenceLength = 0;
        for (; head != null; head = head.next) sequenceLength++;
        return sequenceLength;
    }

    @Override
    public String toString() {
        return "key: " + key;
    }
}
