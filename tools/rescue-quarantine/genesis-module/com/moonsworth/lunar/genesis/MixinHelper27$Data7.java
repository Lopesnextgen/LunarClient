package com.moonsworth.lunar.genesis;
import com.google.common.base.Equivalence;
import com.google.common.collect.Interner;

@Annotation4
final class MixinHelper27$Data7<E> implements Interner<E> {
   @Annotation4
   final AbstractMapLoader<E, MixinHelper26.Type, ?, ?> field1;

   private MixinHelper27$Data7(MixinHelper26 var1) {
      this.field1 = AbstractMapLoader.method2(var1.method1(Equivalence.method7()));
   }

   @Override
   public E intern(E var1) {
      MixinHelper26.Type var4;
      do {
         AbstractMapLoader.Extension4 var2 = this.field1.method15(var1);
         if (var2 != null) {
            Object var3 = var2.getKey();
            if (var3 != null) {
               return (E)var3;
            }
         }

         var4 = this.field1.putIfAbsent((E)var1, MixinHelper26.Type.VALUE);
      } while (var4 != null);

      return (E)var1;
   }
}
