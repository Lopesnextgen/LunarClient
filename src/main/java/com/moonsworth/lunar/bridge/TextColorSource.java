package com.moonsworth.lunar.bridge;

public interface TextColorSource {
   default int getColor() {
      return this.method1(0.0F);
   }

   int method1(float var1);

   boolean method14();

   default TextColorSource method3(final MixinHelper4$Extension var1) {
      final TextColorSource var2 = this;
      return new TextColorSource() {
         @Override
         public int method1(float var1x) {
            return var1.transform(var2.method1(var1x));
         }

         @Override
         public boolean method14() {
            return var2.method14();
         }
      };
   }
}
