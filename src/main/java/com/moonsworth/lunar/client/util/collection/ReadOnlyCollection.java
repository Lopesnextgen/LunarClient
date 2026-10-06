package com.moonsworth.lunar.client.util.collection;

import java.util.Collection;

interface ReadOnlyCollection {
   int size();

   boolean isEmpty();

   boolean contains(Object obj1);

   Object[] toArray();

   <E> E[] toArray(E[] items1);

   boolean containsAll(Collection<?> list1);

   int indexOf(Object obj1);

   int lastIndexOf(Object obj1);
}
