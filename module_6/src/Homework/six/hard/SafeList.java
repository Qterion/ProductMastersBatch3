package Homework.six.hard;

import java.util.*;

public class SafeList<T> implements List<T> {
    private ArrayList<T> internalList;

    public SafeList() {
        this.internalList = new ArrayList<>();
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return internalList.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public Object[] toArray() {
        return internalList.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return internalList.toArray(a);
    }

    @Override
    public boolean add(T t) {
        // Do not allow null
        if (t == null) {
            return false;
        }
        // Do not allow duplicates
        if (internalList.contains(t)) {
            return false;
        }
        return internalList.add(t);
    }

    @Override
    public boolean remove(Object o) {
        try {
            return internalList.remove(o);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        try {
            return internalList.containsAll(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        try {
            boolean modified = false;
            for (T element : c) {
                if (add(element)) {
                    modified = true;
                }
            }
            return modified;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        try {
            boolean modified = false;
            int currentIndex = index;
            for (T element : c) {
                // Check for null and duplicates
                if (element != null && !internalList.contains(element)) {
                    if (currentIndex >= 0 && currentIndex <= internalList.size()) {
                        internalList.add(currentIndex, element);
                        currentIndex++;
                        modified = true;
                    }
                }
            }
            return modified;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        try {
            return internalList.removeAll(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        try {
            return internalList.retainAll(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void clear() {
        try {
            internalList.clear();
        } catch (Exception e) {
            // Silently fail
        }
    }

    @Override
    public T get(int index) {
        try {
            // Return null for invalid index instead of throwing error
            if (index < 0 || index >= internalList.size()) {
                return null;
            }
            return internalList.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T set(int index, T element) {
        try {
            // Do not allow null
            if (element == null) {
                return null;
            }
            // Do not allow duplicates (except replacing same index)
            if (internalList.contains(element)) {
                int existingIndex = internalList.indexOf(element);
                if (existingIndex != index) {
                    return null;
                }
            }
            // Check valid index
            if (index < 0 || index >= internalList.size()) {
                return null;
            }
            return internalList.set(index, element);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void add(int index, T element) {
        try {
            // Do not allow null
            if (element == null) {
                return;
            }
            // Do not allow duplicates
            if (internalList.contains(element)) {
                return;
            }
            // Check valid index
            if (index >= 0 && index <= internalList.size()) {
                internalList.add(index, element);
            }
        } catch (Exception e) {
            // Silently fail
        }
    }

    @Override
    public T remove(int index) {
        try {
            if (index < 0 || index >= internalList.size()) {
                return null;
            }
            return internalList.remove(index);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int indexOf(Object o) {
        try {
            return internalList.indexOf(o);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        try {
            return internalList.lastIndexOf(o);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        return internalList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        try {
            if (index < 0 || index > internalList.size()) {
                return internalList.listIterator(0);
            }
            return internalList.listIterator(index);
        } catch (Exception e) {
            return internalList.listIterator(0);
        }
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        try {
            if (fromIndex < 0 || toIndex > internalList.size() || fromIndex > toIndex) {
                return new ArrayList<>();
            }
            return internalList.subList(fromIndex, toIndex);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}

