package com.moonsworth.lunar.genesis;
import com.google.common.collect.Interner;

public class MixinHelper27$Data6 {
   private final MixinHelper26 field1 = new MixinHelper26();
   private boolean strong = true;

   private MixinHelper27$Data6() {
   }

   public MixinHelper27$Data6 method1() {
      this.strong = true;
      return this;
   }

   @Annotation3("java.lang.ref.WeakReference")
   public MixinHelper27$Data6 method2() {
      this.strong = false;
      return this;
   }

   public MixinHelper27$Data6 method3(int var1) {
      this.field1.method4(var1);
      return this;
   }

   public <E> Interner<E> method4() {
      if (!this.strong) {
         this.field1.method5();
      }

      return new MixinHelper27$Data7<>(this.field1);
   }
}
