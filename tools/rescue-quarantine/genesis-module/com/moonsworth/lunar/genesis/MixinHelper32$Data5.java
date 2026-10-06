package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;

abstract class MixinHelper32$Data5<N> {
   final N field1;

   MixinHelper32$Data5(N var1) {
      this.field1 = Preconditions.checkNotNull((N)var1);
   }

   static final class Data<N> extends MixinHelper32$Data5<N> {
      Data(N var1) {
         super((N)var1);
      }

      @Override
      public boolean equals(Object var1) {
         return var1 instanceof MixinHelper32$Data5.Data
            ? this.field1.equals(((MixinHelper32$Data5.Data)var1).COCRCRCICOICCIHCIIRCHOROIHCHRI)
            : false;
      }

      @Override
      public int hashCode() {
         return MixinHelper32$Data5.Data.class.hashCode() + this.field1.hashCode();
      }
   }

   static final class Data2<N> extends MixinHelper32$Data5<N> {
      Data2(N var1) {
         super((N)var1);
      }

      @Override
      public boolean equals(Object var1) {
         return var1 instanceof MixinHelper32$Data5.Data2
            ? this.field1.equals(((MixinHelper32$Data5.Data2)var1).COCRCRCICOICCIHCIIRCHOROIHCHRI)
            : false;
      }

      @Override
      public int hashCode() {
         return MixinHelper32$Data5.Data2.class.hashCode() + this.field1.hashCode();
      }
   }
}
