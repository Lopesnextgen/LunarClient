package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_13;
import com.moonsworth.lunar.bridge.MixinHelper.Extension;
import com.moonsworth.lunar.bridge.MixinHelper.Extension2;
import com.moonsworth.lunar.bridge.MixinHelper.Extension3;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;
import net.kyori.adventure.text.Component;

@Annotation2(max = 7)
public abstract class Click2Base implements MixinHelper_13 {
   public static class Data extends Click2Base implements Extension3 {
      private final Component field1;

      public int bridge$getHeight(Bridge10_2 var1) {
         return var1.method19();
      }

      public int bridge$getWidth(Bridge10_2 var1) {
         return (int)var1.bridge$getStringWidth(this.field1);
      }

      public Component bridge$getComponent() {
         return this.field1;
      }

      @Generated
      public Data(Component var1) {
         this.field1 = var1;
      }
   }

   public static class Data2 extends Click2Base implements Extension2 {
      private final Extension field1;
      private final int field2;
      private final int field3;

      public Extension method1() {
         return this.field1;
      }

      public int bridge$getHeight(Bridge10_2 var1) {
         return this.field3;
      }

      public int bridge$getWidth(Bridge10_2 var1) {
         return this.field2;
      }

      @Generated
      public Data2(Extension var1, int var2, int var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }
}
