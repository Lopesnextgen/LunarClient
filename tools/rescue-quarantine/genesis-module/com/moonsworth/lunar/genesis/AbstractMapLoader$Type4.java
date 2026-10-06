package com.moonsworth.lunar.genesis;
import com.google.common.base.Equivalence;

enum AbstractMapLoader$Type4 {
   STRONG {
      @Override
      <K, V> AbstractMapLoader$Extension<K, V> referenceValue(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, V var3, int var4) {
         return var4 == 1 ? new AbstractMapLoader$Data18<>((V)var3) : new AbstractMapLoader$Data23<>((V)var3, var4);
      }

      @Override
      Equivalence<Object> defaultEquivalence() {
         return Equivalence.method7();
      }
   },
   SOFT {
      @Override
      <K, V> AbstractMapLoader$Extension<K, V> referenceValue(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, V var3, int var4) {
         return var4 == 1 ? new AbstractMapLoader$Data43<>(var1.field4, (V)var3, var2) : new AbstractMapLoader$Data34<>(var1.field4, (V)var3, var2, var4);
      }

      @Override
      Equivalence<Object> defaultEquivalence() {
         return Equivalence.method8();
      }
   },
   WEAK {
      @Override
      <K, V> AbstractMapLoader$Extension<K, V> referenceValue(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, V var3, int var4) {
         return var4 == 1 ? new AbstractMapLoader$Data15<>(var1.field4, (V)var3, var2) : new AbstractMapLoader$Data45<>(var1.field4, (V)var3, var2, var4);
      }

      @Override
      Equivalence<Object> defaultEquivalence() {
         return Equivalence.method8();
      }
   };

   AbstractMapLoader$Type4() {
   }

   abstract <K, V> AbstractMapLoader$Extension<K, V> referenceValue(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, V var3, int var4);

   abstract Equivalence<Object> defaultEquivalence();
}
