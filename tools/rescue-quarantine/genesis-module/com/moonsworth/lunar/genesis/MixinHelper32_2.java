package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.graph.ElementOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.graph.Graphs;

final class MixinHelper32_2<N, V> implements MixinHelper3_13<N, V> {
   private static final Object field1 = new Object();
   private final Map<N, Object> field2;
   private final @Nullable List<MixinHelper32.MixinHelper32$Data5<N>> field3;
   private int predecessorCount;
   private int successorCount;

   private MixinHelper32_2(Map<N, Object> var1, @Nullable List<MixinHelper32.MixinHelper32$Data5<N>> var2, int var3, int var4) {
      this.field2 = Preconditions.checkNotNull(var1);
      this.field3 = var2;
      this.predecessorCount = Graphs.checkNonNegative(var3);
      this.successorCount = Graphs.checkNonNegative(var4);
      Preconditions.checkState(var3 <= var1.size() && var4 <= var1.size());
   }

   static <N, V> MixinHelper32_2<N, V> method1(ElementOrder<N> var0) {
      byte var1 = 4;
      ArrayList var2;
      switch (var0.method6()) {
         case UNORDERED:
            var2 = null;
            break;
         case STABLE:
            var2 = new ArrayList();
            break;
         default:
            throw new AssertionError(var0.method6());
      }

      return new MixinHelper32_2<>(new HashMap<>(var1, 1.0F), var2, 0, 0);
   }

   static <N, V> MixinHelper32_2<N, V> method2(N var0, Iterable<IterableBase<N>> var1, MixinHelper24_2<N, V> var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var2);
      HashMap var3 = new HashMap();
      ImmutableList.Data2 var4 = ImmutableList.method30();
      int var5 = 0;
      int var6 = 0;

      for (IterableBase var8 : var1) {
         if (var8.method5().equals(var0) && var8.method6().equals(var0)) {
            var3.put(var0, new MixinHelper32$Data4(var2.apply(var0)));
            var4.method2(new MixinHelper32$Data5.Data2<>(var0));
            var4.method2(new MixinHelper32$Data5.Data<>(var0));
            var5++;
            var6++;
         } else if (var8.method6().equals(var0)) {
            Object var9 = var8.method5();
            Object var10 = var3.put(var9, field1);
            if (var10 != null) {
               var3.put(var9, new MixinHelper32$Data4(var10));
            }

            var4.method2(new MixinHelper32$Data5.Data2<>(var9));
            var5++;
         } else {
            Preconditions.checkArgument(var8.method5().equals(var0));
            Object var12 = var8.method6();
            Object var13 = var2.apply(var12);
            Object var11 = var3.put(var12, var13);
            if (var11 != null) {
               Preconditions.checkArgument(var11 == field1);
               var3.put(var12, new MixinHelper32$Data4(var13));
            }

            var4.method2(new MixinHelper32$Data5.Data<>(var12));
            var6++;
         }
      }

      return new MixinHelper32_2<>(var3, var4.method6(), var5, var6);
   }

   @Override
   public Set<N> adjacentNodes() {
      return this.field3 == null ? Collections.unmodifiableSet(this.field2.keySet()) : new AbstractSet<N>() {
         public MixinHelperIterator3<N> method1() {
            final Iterator var1 = MixinHelper32_2.this.field3.iterator();
            final HashSet var2 = new HashSet();
            return new MixinHelperIterator32_2<N>() {
               @Override
               protected N computeNext() {
                  while (var1.hasNext()) {
                     MixinHelper32$Data5 var1x = (MixinHelper32$Data5)var1.next();
                     boolean var2x = var2.add(var1x.field1);
                     if (var2x) {
                        return var1x.field1;
                     }
                  }

                  return (N)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
               }
            };
         }

         @Override
         public int size() {
            return MixinHelper32_2.this.field2.size();
         }

         @Override
         public boolean contains(@Nullable Object var1) {
            return MixinHelper32_2.this.field2.containsKey(var1);
         }
      };
   }

   @Override
   public Set<N> predecessors() {
      return new AbstractSet<N>() {
         public MixinHelperIterator3<N> method1() {
            if (MixinHelper32_2.this.field3 == null) {
               final Iterator var2 = MixinHelper32_2.this.field2.entrySet().iterator();
               return new MixinHelperIterator32_2<N>() {
                  @Override
                  protected N computeNext() {
                     while (var2.hasNext()) {
                        Entry var1 = (Entry)var2.next();
                        if (MixinHelper32_2.isPredecessor(var1.getValue())) {
                           return (N)var1.getKey();
                        }
                     }

                     return (N)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
                  }
               };
            } else {
               final Iterator var1 = MixinHelper32_2.this.field3.iterator();
               return new MixinHelperIterator32_2<N>() {
                  @Override
                  protected N computeNext() {
                     while (var1.hasNext()) {
                        MixinHelper32$Data5 var1x = (MixinHelper32$Data5)var1.next();
                        if (var1x instanceof MixinHelper32$Data5.Data2) {
                           return var1x.field1;
                        }
                     }

                     return (N)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
                  }
               };
            }
         }

         @Override
         public int size() {
            return MixinHelper32_2.this.predecessorCount;
         }

         @Override
         public boolean contains(@Nullable Object var1) {
            return MixinHelper32_2.isPredecessor(MixinHelper32_2.this.field2.get(var1));
         }
      };
   }

   @Override
   public Set<N> successors() {
      return new AbstractSet<N>() {
         public MixinHelperIterator3<N> method1() {
            if (MixinHelper32_2.this.field3 == null) {
               final Iterator var2 = MixinHelper32_2.this.field2.entrySet().iterator();
               return new MixinHelperIterator32_2<N>() {
                  @Override
                  protected N computeNext() {
                     while (var2.hasNext()) {
                        Entry var1 = (Entry)var2.next();
                        if (MixinHelper32_2.isSuccessor(var1.getValue())) {
                           return (N)var1.getKey();
                        }
                     }

                     return (N)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
                  }
               };
            } else {
               final Iterator var1 = MixinHelper32_2.this.field3.iterator();
               return new MixinHelperIterator32_2<N>() {
                  @Override
                  protected N computeNext() {
                     while (var1.hasNext()) {
                        MixinHelper32$Data5 var1x = (MixinHelper32$Data5)var1.next();
                        if (var1x instanceof MixinHelper32$Data5.Data) {
                           return var1x.field1;
                        }
                     }

                     return (N)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
                  }
               };
            }
         }

         @Override
         public int size() {
            return MixinHelper32_2.this.successorCount;
         }

         @Override
         public boolean contains(@Nullable Object var1) {
            return MixinHelper32_2.isSuccessor(MixinHelper32_2.this.field2.get(var1));
         }
      };
   }

   @Override
   public Iterator<IterableBase<N>> incidentEdgeIterator(final N var1) {
      Preconditions.checkNotNull(var1);
      final Iterator var2;
      if (this.field3 == null) {
         var2 = Iterators.concat(Iterators.method17(this.predecessors().iterator(), new MixinHelper24_2<N, IterableBase<N>>() {
            public IterableBase<N> method1(N var1x) {
               return IterableBase.method1((N)var1x, (N)var1);
            }
         }), Iterators.method17(this.successors().iterator(), new MixinHelper24_2<N, IterableBase<N>>() {
            public IterableBase<N> method1(N var1x) {
               return IterableBase.method1((N)var1, (N)var1x);
            }
         }));
      } else {
         var2 = Iterators.method17(this.field3.iterator(), new MixinHelper24_2<MixinHelper32$Data5<N>, IterableBase<N>>() {
            public IterableBase<N> method1(MixinHelper32$Data5<N> var1x) {
               return var1x instanceof MixinHelper32$Data5.Data ? IterableBase.method1((N)var1, var1x.field1) : IterableBase.method1(var1x.field1, (N)var1);
            }
         });
      }

      final AtomicBoolean var3 = new AtomicBoolean(false);
      return new MixinHelperIterator32_2<IterableBase<N>>() {
         protected IterableBase<N> method3() {
            while (var2.hasNext()) {
               IterableBase var1x = (IterableBase)var2.next();
               if (var1x.method5().equals(var1x.method6())) {
                  if (var3.getAndSet(true)) {
                     continue;
                  }

                  return var1x;
               }

               return var1x;
            }

            return (IterableBase<N>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }
      };
   }

   @Override
   public V value(N var1) {
      Preconditions.checkNotNull(var1);
      Object var2 = this.field2.get(var1);
      if (var2 == field1) {
         return null;
      } else {
         return (V)(var2 instanceof MixinHelper32$Data4 ? MixinHelper32$Data4.method1((MixinHelper32$Data4)var2) : var2);
      }
   }

   @Override
   public void removePredecessor(N var1) {
      Preconditions.checkNotNull(var1);
      Object var2 = this.field2.get(var1);
      boolean var3;
      if (var2 == field1) {
         this.field2.remove(var1);
         var3 = true;
      } else if (var2 instanceof MixinHelper32$Data4) {
         this.field2.put((N)var1, MixinHelper32$Data4.method1((MixinHelper32$Data4)var2));
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         Graphs.checkNonNegative(--this.predecessorCount);
         if (this.field3 != null) {
            this.field3.remove(new MixinHelper32$Data5.Data2<>(var1));
         }
      }
   }

   @Override
   public V removeSuccessor(Object var1) {
      Preconditions.checkNotNull(var1);
      Object var2 = this.field2.get(var1);
      Object var3;
      if (var2 == null || var2 == field1) {
         var3 = null;
      } else if (var2 instanceof MixinHelper32$Data4) {
         this.field2.put((N)var1, field1);
         var3 = MixinHelper32$Data4.method1((MixinHelper32$Data4)var2);
      } else {
         this.field2.remove(var1);
         var3 = var2;
      }

      if (var3 != null) {
         Graphs.checkNonNegative(--this.successorCount);
         if (this.field3 != null) {
            this.field3.remove(new MixinHelper32$Data5.Data<>(var1));
         }
      }

      return (V)var3;
   }

   @Override
   public void addPredecessor(N var1, V var2) {
      Object var3 = this.field2.put((N)var1, field1);
      boolean var4;
      if (var3 == null) {
         var4 = true;
      } else if (var3 instanceof MixinHelper32$Data4) {
         this.field2.put((N)var1, var3);
         var4 = false;
      } else if (var3 != field1) {
         this.field2.put((N)var1, new MixinHelper32$Data4(var3));
         var4 = true;
      } else {
         var4 = false;
      }

      if (var4) {
         Graphs.checkPositive(++this.predecessorCount);
         if (this.field3 != null) {
            this.field3.add(new MixinHelper32$Data5.Data2<>((N)var1));
         }
      }
   }

   @Override
   public V addSuccessor(N var1, V var2) {
      Object var3 = this.field2.put((N)var1, var2);
      Object var4;
      if (var3 == null) {
         var4 = null;
      } else if (var3 instanceof MixinHelper32$Data4) {
         this.field2.put((N)var1, new MixinHelper32$Data4(var2));
         var4 = MixinHelper32$Data4.method1((MixinHelper32$Data4)var3);
      } else if (var3 == field1) {
         this.field2.put((N)var1, new MixinHelper32$Data4(var2));
         var4 = null;
      } else {
         var4 = var3;
      }

      if (var4 == null) {
         Graphs.checkPositive(++this.successorCount);
         if (this.field3 != null) {
            this.field3.add(new MixinHelper32$Data5.Data<>((N)var1));
         }
      }

      return (V)var4;
   }

   private static boolean isPredecessor(@Nullable Object var0) {
      return var0 == field1 || var0 instanceof MixinHelper32$Data4;
   }

   private static boolean isSuccessor(@Nullable Object var0) {
      return var0 != field1 && var0 != null;
   }
}
