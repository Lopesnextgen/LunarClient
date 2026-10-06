package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.base.Preconditions;

final class TreeTraverser$PostOrderNode<T> {
   final T field1;
   final Iterator<T> field2;

   TreeTraverser$PostOrderNode(T value1, Iterator<T> iterator2) {
      this.field1 = (T)Preconditions.checkNotNull(value1);
      this.field2 = (Iterator<T>)Preconditions.checkNotNull(iterator2);
   }
}
