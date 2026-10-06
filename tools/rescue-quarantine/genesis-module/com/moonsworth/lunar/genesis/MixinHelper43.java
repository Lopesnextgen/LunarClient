package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;

@GwtCompatible
public abstract class MixinHelper43<K0, V0> {
   private static final int field1 = 8;

   private MixinHelper43() {
   }

   public static MixinHelper43.Data8<Object> method1() {
      return method2(8);
   }

   public static MixinHelper43.Data8<Object> method2(final int var0) {
      MixinHelper18_3.checkNonnegative(var0, "expectedKeys");
      return new MixinHelper43.Data8<Object>() {
         @Override
         <K, V> Map<K, Collection<V>> createMap() {
            return MixinHelper8_2.newHashMapWithExpectedSize(var0);
         }
      };
   }

   public static MixinHelper43.Data8<Object> method3() {
      return method4(8);
   }

   public static MixinHelper43.Data8<Object> method4(final int var0) {
      MixinHelper18_3.checkNonnegative(var0, "expectedKeys");
      return new MixinHelper43.Data8<Object>() {
         @Override
         <K, V> Map<K, Collection<V>> createMap() {
            return MixinHelper8_2.newLinkedHashMapWithExpectedSize(var0);
         }
      };
   }

   public static MixinHelper43.Data8<Comparable> method5() {
      return method6(Ordering.method1());
   }

   public static <K0> MixinHelper43.Data8<K0> method6(final java.util.Comparator<K0> var0) {
      Preconditions.checkNotNull(var0);
      return new MixinHelper43.Data8<K0>() {
         @Override
         <K extends K0, V> Map<K, Collection<V>> createMap() {
            return new TreeMap<>(var0);
         }
      };
   }

   public static <K0 extends Enum<K0>> MixinHelper43.Data8<K0> method7(final Class<K0> var0) {
      Preconditions.checkNotNull(var0);
      return new MixinHelper43.Data8<K0>() {
         @Override
         <K extends K0, V> Map<K, Collection<V>> createMap() {
            return new EnumMap<>(var0);
         }
      };
   }

   public abstract <K extends K0, V extends V0> Multimap<K, V> method8();

   public <K extends K0, V extends V0> Multimap<K, V> method9(Multimap<? extends K, ? extends V> var1) {
      Multimap var2 = this.method8();
      var2.method1(var1);
      return var2;
   }

   private static final class Data<V> implements SupplierExtension<SortedSet<V>>, Serializable {
      private final java.util.Comparator<? super V> field1;

      Data(java.util.Comparator<? super V> var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      public SortedSet<V> get() {
         return new TreeSet<>(this.field1);
      }
   }

   private static final class Data2<V extends Enum<V>> implements SupplierExtension<Set<V>>, Serializable {
      private final Class<V> field1;

      Data2(Class<V> var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      public Set<V> get() {
         return EnumSet.noneOf(this.field1);
      }
   }

   private static final class Data3<V> implements SupplierExtension<List<V>>, Serializable {
      private final int field1;

      Data3(int var1) {
         this.field1 = MixinHelper18_3.checkNonnegative(var1, "expectedValuesPerKey");
      }

      public List<V> get() {
         return new ArrayList<>(this.field1);
      }
   }

   private static final class Data4<V> implements SupplierExtension<Set<V>>, Serializable {
      private final int field1;

      Data4(int var1) {
         this.field1 = MixinHelper18_3.checkNonnegative(var1, "expectedValuesPerKey");
      }

      public Set<V> get() {
         return MixinHelper8_2.newHashSetWithExpectedSize(this.field1);
      }
   }

   public abstract static class Data5<K0, V0> extends MixinHelper43.Data9<K0, V0> {
      Data5() {
      }

      public abstract <K extends K0, V extends V0> MixinHelper1322<K, V> method4();

      public <K extends K0, V extends V0> MixinHelper1322<K, V> method3(Multimap<? extends K, ? extends V> var1) {
         return (MixinHelper1322<K, V>)super.<K, V>method2(var1);
      }
   }

   private static final class Data6<V> implements SupplierExtension<Set<V>>, Serializable {
      private final int field1;

      Data6(int var1) {
         this.field1 = MixinHelper18_3.checkNonnegative(var1, "expectedValuesPerKey");
      }

      public Set<V> get() {
         return MixinHelper8_2.newLinkedHashSetWithExpectedSize(this.field1);
      }
   }

   public abstract static class Data7<K0, V0> extends MixinHelper43<K0, V0> {
      Data7() {
      }

      public abstract <K extends K0, V extends V0> MixinHelper133<K, V> method2();

      public <K extends K0, V extends V0> MixinHelper133<K, V> method2(Multimap<? extends K, ? extends V> var1) {
         return (MixinHelper133<K, V>)super.<K, V>method9(var1);
      }
   }

   public abstract static class Data8<K0> {
      private static final int field1 = 2;

      Data8() {
      }

      abstract <K extends K0, V> Map<K, Collection<V>> createMap();

      public MixinHelper43.Data7<K0, Object> method1() {
         return this.method2(2);
      }

      public MixinHelper43.Data7<K0, Object> method2(final int var1) {
         MixinHelper18_3.checkNonnegative(var1, "expectedValuesPerKey");
         return new MixinHelper43.Data7<K0, Object>() {
            @Override
            public <K extends K0, V> MixinHelper133<K, V> method2() {
               return MixinHelper37.method2(Data8.this.createMap(), new MixinHelper43.Data3<>(var1));
            }
         };
      }

      public MixinHelper43.Data7<K0, Object> method3() {
         return new MixinHelper43.Data7<K0, Object>() {
            @Override
            public <K extends K0, V> MixinHelper133<K, V> method2() {
               return MixinHelper37.method2(Data8.this.createMap(), MixinHelper43.Type.instance());
            }
         };
      }

      public MixinHelper43.Data9<K0, Object> method4() {
         return this.method5(2);
      }

      public MixinHelper43.Data9<K0, Object> method5(final int var1) {
         MixinHelper18_3.checkNonnegative(var1, "expectedValuesPerKey");
         return new MixinHelper43.Data9<K0, Object>() {
            @Override
            public <K extends K0, V> MixinHelper132_2<K, V> method2() {
               return MixinHelper37.method3(Data8.this.createMap(), new MixinHelper43.Data4<>(var1));
            }
         };
      }

      public MixinHelper43.Data9<K0, Object> method6() {
         return this.method7(2);
      }

      public MixinHelper43.Data9<K0, Object> method7(final int var1) {
         MixinHelper18_3.checkNonnegative(var1, "expectedValuesPerKey");
         return new MixinHelper43.Data9<K0, Object>() {
            @Override
            public <K extends K0, V> MixinHelper132_2<K, V> method2() {
               return MixinHelper37.method3(Data8.this.createMap(), new MixinHelper43.Data6<>(var1));
            }
         };
      }

      public MixinHelper43.Data5<K0, Comparable> method8() {
         return this.method9(Ordering.method1());
      }

      public <V0> MixinHelper43.Data5<K0, V0> method9(final java.util.Comparator<V0> var1) {
         Preconditions.checkNotNull(var1, "comparator");
         return new MixinHelper43.Data5<K0, V0>() {
            @Override
            public <K extends K0, V extends V0> MixinHelper1322<K, V> method4() {
               return MixinHelper37.method4(Data8.this.createMap(), new MixinHelper43.Data<>(var1));
            }
         };
      }

      public <V0 extends Enum<V0>> MixinHelper43.Data9<K0, V0> method10(final Class<V0> var1) {
         Preconditions.checkNotNull(var1, "valueClass");
         return new MixinHelper43.Data9<K0, V0>() {
            @Override
            public <K extends K0, V extends V0> MixinHelper132_2<K, V> method2() {
               MixinHelper43.Data2 var1x = new MixinHelper43.Data2(var1);
               return MixinHelper37.method3(Data8.this.createMap(), var1x);
            }
         };
      }
   }

   public abstract static class Data9<K0, V0> extends MixinHelper43<K0, V0> {
      Data9() {
      }

      public abstract <K extends K0, V extends V0> MixinHelper132_2<K, V> method2();

      public <K extends K0, V extends V0> MixinHelper132_2<K, V> method2(Multimap<? extends K, ? extends V> var1) {
         return (MixinHelper132_2<K, V>)super.<K, V>method9(var1);
      }
   }

   private enum Type implements SupplierExtension<List<Object>> {
      INSTANCE;

      public static <V> SupplierExtension<List<V>> instance() {
         return INSTANCE;
      }

      public List<Object> get() {
         return new LinkedList<>();
      }
   }
}
