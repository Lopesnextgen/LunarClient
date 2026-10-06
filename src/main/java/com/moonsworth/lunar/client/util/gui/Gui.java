package com.moonsworth.lunar.client.util.gui;

import java.util.Collection;

interface Gui {
   int size();

   boolean isEmpty();

   boolean contains(Object var1);

   Object[] toArray();

   <E> E[] toArray(E[] var1);

   boolean containsAll(Collection<?> var1);

   int indexOf(Object var1);

   int lastIndexOf(Object var1);
}
