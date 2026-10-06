package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation3
interface MixinHelper6_5<K, V> {
   AbstractMapLoader$Extension<K, V> getValueReference();

   void setValueReference(AbstractMapLoader$Extension<K, V> var1);

   @Nullable MixinHelper6_5<K, V> getNext();

   int getHash();

   @Nullable K getKey();

   long getAccessTime();

   void setAccessTime(long var1);

   MixinHelper6_5<K, V> getNextInAccessQueue();

   void setNextInAccessQueue(MixinHelper6_5<K, V> var1);

   MixinHelper6_5<K, V> getPreviousInAccessQueue();

   void setPreviousInAccessQueue(MixinHelper6_5<K, V> var1);

   long getWriteTime();

   void setWriteTime(long var1);

   MixinHelper6_5<K, V> getNextInWriteQueue();

   void setNextInWriteQueue(MixinHelper6_5<K, V> var1);

   MixinHelper6_5<K, V> getPreviousInWriteQueue();

   void setPreviousInWriteQueue(MixinHelper6_5<K, V> var1);
}
