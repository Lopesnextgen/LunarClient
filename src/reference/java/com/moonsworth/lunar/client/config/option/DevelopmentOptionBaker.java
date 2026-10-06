package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.util.Iterator;
import java.util.Set;
import org.jspecify.annotations.Nullable;

public class DevelopmentOptionBaker extends FeatureOptionBaker {
   public static final String field2 = "test";
   private static final int field3 = 20;
   private static final int field4 = 30;
   private int field5 = 0;

   public DevelopmentOptionBaker(@Nullable String var1) {
      super(var1);
      if (!"test".equals(var1) && LunarBuildData.field4) {
         throw new IllegalAccessError("DevOptionBaker can only be used in a development environment!");
      }
   }

   @Override
   protected void method4(OptionGraphNode var1, Set<OptionGraphNode> var2) {
      Iterator var3 = var1.getChildren().iterator();

      while (var3.hasNext()) {
         OptionGraphNode var4 = (OptionGraphNode)var3.next();
         if (var4.method4() || this.method1(var4, var2)) {
            var3.remove();
            var2.add(var4);
         }

         if (this.field5 > 30) {
            throw new StackOverflowError("Found option with hierarchy deeper than 30 for id: " + this.getId());
         }

         if (this.field5 > 20) {
            System.out.println("Depth detection: " + var4.method3().getId());
         }

         this.field5++;
         this.method4(var4, var2);
         this.field5--;
      }
   }
}
