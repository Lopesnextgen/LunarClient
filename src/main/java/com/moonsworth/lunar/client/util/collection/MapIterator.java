package com.moonsworth.lunar.client.util.collection;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class MapIterator<T> implements Map<Integer, T> {
   private final Consumer<Consumer<Map<Integer, T>>> field1;
   private final T[] field2;
   private int size = 0;

   public MapIterator(int index1, Consumer<Consumer<Map<Integer, T>>> consumer2) {
      this.field2 = (T[])(new Object[index1]);
      this.field1 = arg1x -> {
         System.out.println("overflowed");
         consumer2.accept(arg1x);
      };
   }

   @Override
   public T get(Object obj1) {
      Object[] items2 = this.field2;
      if (obj1 instanceof Integer number3) {
         int index4 = number3;
         return (T)(index4 < items2.length && index4 >= 0 ? items2[index4] : null);
      } else {
         return null;
      }
   }

   public T put(Integer integer, T t) {
      if (integer != null && t != null) {
         Object[] items3 = this.field2;
         int index4 = integer;
         if (index4 < items3.length && index4 >= 0) {
            Object obj5 = items3[index4];
            items3[index4] = t;
            if (obj5 == null) {
               this.size++;
            }

            return (T)obj5;
         } else {
            this.field1.accept(arg3x -> {
               arg3x.putAll(this);
               arg3x.put(integer, (T)t);
            });
            return null;
         }
      } else {
         throw new IllegalArgumentException("Null keys or values are not permitted");
      }
   }

   @Override
   public int size() {
      return this.size;
   }

   @Override
   public boolean isEmpty() {
      return this.size == 0;
   }

   @Override
   public boolean containsKey(Object obj1) {
      Object[] items2 = this.field2;
      if (obj1 instanceof Integer number3) {
         int index4 = number3;
         return index4 < items2.length && index4 >= 0 ? items2[index4] != null : false;
      } else {
         return false;
      }
   }

   @Override
   public boolean containsValue(Object obj1) {
      if (obj1 == null) {
         return false;
      }

      for (Object obj5 : this.field2) {
         if (obj5 != null && obj5.equals(obj1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public T remove(Object obj1) {
      Object[] items2 = this.field2;
      if (obj1 instanceof Integer number3) {
         int index4 = number3;
         if (index4 < items2.length && index4 >= 0) {
            Object obj5 = items2[index4];
            if (obj5 != null) {
               this.size--;
            }

            items2[index4] = null;
            return (T)obj5;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   @Override
   public void putAll(@NotNull Map<? extends Integer, ? extends T> map1) {
      Object[] items2 = this.field2;

      for (Entry entry4 : map1.entrySet()) {
         if ((Integer)entry4.getKey() >= items2.length) {
            this.field1.accept(arg2x -> {
               arg2x.putAll(map1);
               arg2x.putAll(this);
            });
            return;
         }
      }

      for (Entry entry6 : map1.entrySet()) {
         this.put((Integer)entry6.getKey(), (T)entry6.getValue());
      }
   }

   @Override
   public void clear() {
      Object[] items1 = this.field2;
      int index2 = 0;

      for (int index3 = items1.length; index2 < index3; index2++) {
         items1[index2] = null;
      }
   }

   @NotNull
   @Override
   public Set<Integer> keySet() {
      return new AbstractSet<Integer>() {
         @Override
         public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
               private final Iterator<Entry<Integer, T>> it = MapIterator.this.entrySet().iterator();

               @Override
               public boolean hasNext() {
                  return this.it.hasNext();
               }

               public Integer next() {
                  return this.it.next().getKey();
               }

               @Override
               public void remove() {
                  this.it.remove();
               }
            };
         }

         @Override
         public int size() {
            return MapIterator.this.size();
         }
      };
   }

   @NotNull
   @Override
   public Collection<T> values() {
      return new AbstractCollection<T>() {
         @Override
         public Iterator<T> iterator() {
            return new Iterator<T>() {
               private final Iterator<Entry<Integer, T>> field1 = MapIterator.this.entrySet().iterator();

               @Override
               public boolean hasNext() {
                  return this.field1.hasNext();
               }

               @Override
               public T next() {
                  return this.field1.next().getValue();
               }

               @Override
               public void remove() {
                  this.field1.remove();
               }
            };
         }

         @Override
         public int size() {
            return MapIterator.this.size;
         }
      };
   }

   @NotNull
   @Override
   public Set<Entry<Integer, T>> entrySet() {
      return new AbstractSet<Entry<Integer, T>>() {
         @Override
         public Iterator<Entry<Integer, T>> iterator() {
            return new Iterator<Entry<Integer, T>>() {
               private int i = 0;
               private int offset = 0;

               @Override
               public boolean hasNext() {
                  return this.i < MapIterator.this.size;
               }

               public Entry<Integer, T> next() {
                  Object[] items1 = MapIterator.this.field2;

                  Object obj2;
                  do {
                     obj2 = items1[this.offset];
                     if (obj2 != null) {
                        break;
                     }

                     this.offset++;
                  } while (this.offset < items1.length);

                  this.i++;
                  return new SimpleEntry<>(this.offset++, (T)obj2);
               }

               @Override
               public void remove() {
                  MapIterator.this.remove(this.offset);
               }
            };
         }

         @Override
         public int size() {
            return MapIterator.this.size;
         }
      };
   }

   @Override
   public boolean equals(Object obj1) {
      return obj1 instanceof MapIterator mapiterator2 ? this.entrySet().equals(mapiterator2.entrySet()) : false;
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode(this.field2);
   }
}
