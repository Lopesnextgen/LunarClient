package com.moonsworth.lunar.genesis;

@Annotation2
public final class MixinHelper9$Data10 extends MixinHelper9$Data15 {
   private final MixinHelper9$Data15 field3;

   private MixinHelper9$Data10(MixinHelper9$Data16 var1, MixinHelper9$Data16 var2, MixinHelper9$Data15 var3) {
      super(var1, var2);
      this.field3 = var3;
      this.initCause(var3);
   }

   public MixinHelper9$Data15 method1() {
      return this.field3;
   }

   @Override
   public String getMessage() {
      StringBuilder var1 = new StringBuilder(super.getMessage());

      for (Throwable var2 = this.field3; var2 != null; var2 = var2.getCause()) {
         var1.append(", ").append(var2.getMessage());
      }

      return var1.toString();
   }
}
