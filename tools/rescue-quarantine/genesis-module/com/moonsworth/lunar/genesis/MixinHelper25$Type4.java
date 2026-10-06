package com.moonsworth.lunar.genesis;

enum MixinHelper25$Type4 implements MixinHelper25$Extension<Object> {
   INSTANCE;

   public Object apply(SupplierExtension<Object> var1) {
      return var1.get();
   }

   @Override
   public String toString() {
      return "Suppliers.supplierFunction()";
   }
}
