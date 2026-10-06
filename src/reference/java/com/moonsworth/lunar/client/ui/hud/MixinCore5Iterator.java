package com.moonsworth.lunar.client.ui.hud;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.Generated;

public class MixinCore5Iterator implements MixinCore5 {
   public static MixinCore5Iterator.Type field1 = MixinCore5Iterator.Type.START;
   public static MixinCore5Iterator.Type field2 = MixinCore5Iterator.Type.CENTER;
   public static MixinCore5Iterator.Type field3 = MixinCore5Iterator.Type.END;
   public static MixinCore5Iterator.Data2 field4 = new MixinCore5Iterator.Data2();
   private final List<MixinCore5> field5 = new ArrayList<>();
   private final MixinCore6<Float> field6 = new MixinCore6<>(0.0F, false, true);
   private final MixinCore6<Float> field7 = new MixinCore6<>(0.0F, false, true);
   private boolean vertical = false;
   private MixinCore5Iterator.Type field8 = MixinCore5Iterator.Type.START;
   private Float field9 = null;
   private Float field10 = null;

   public MixinCore5Iterator() {
   }

   public MixinCore5Iterator(boolean var1) {
      this.vertical = var1;
   }

   public MixinCore5Iterator(MixinCore5Iterator.Type var1) {
      if (var1 == null) {
         throw new NullPointerException("Alignment cant be null!");
      }

      this.field8 = var1;
   }

   public MixinCore5Iterator(boolean var1, MixinCore5Iterator.Type var2) {
      this.vertical = var1;
      if (var2 == null) {
         throw new NullPointerException("Alignment cant be null!");
      }

      this.field8 = var2;
   }

   public MixinCore5Iterator method1(float var1) {
      this.field6.method1(var1);
      return this;
   }

   public MixinCore5Iterator method2(Supplier<Float> var1) {
      this.field6.method2(var1);
      return this;
   }

   public MixinCore5Iterator method3(float var1) {
      this.field7.method1(var1);
      return this;
   }

   public MixinCore5Iterator method4(int var1, MixinCore5 var2) {
      if (var2 == null) {
         throw new NullPointerException("Component cant be null!");
      }

      this.method9(var1 + 1);
      this.field5.set(var1, var2);
      return this;
   }

   public MixinCore5Iterator method5(MixinCore5 var1) {
      if (var1 == null) {
         throw new NullPointerException("Component cant be null!");
      }

      this.field5.add(var1);
      return this;
   }

   public MixinCore5Iterator method6(MixinCore5Iterator.Type var1, MixinCore5 var2) {
      return this.method5(new MixinCore5Iterator.Data(var1, var2));
   }

   public MixinCore5Iterator method7(List<MixinCore5> var1) {
      for (MixinCore5 var3 : var1) {
         this.method5(var3);
      }

      return this;
   }

   public MixinCore5Iterator method8() {
      this.field5.clear();
      return this;
   }

   private void method9(int var1) {
      while (this.field5.size() < var1) {
         this.field5.add(new MixinCore5Handler());
      }
   }

   @Override
   public void clearCache() {
      this.field9 = null;
      this.field10 = null;

      for (MixinCore5 var2 : this.field5) {
         var2.clearCache();
      }

      this.field7.clearCache();
      this.field6.clearCache();
   }

   @Override
   public float getWidth() {
      if (this.field9 != null) {
         return this.field9;
      }

      if (!this.vertical) {
         float var5 = 0.0F;
         boolean var6 = true;

         for (MixinCore5 var8 : this.field5) {
            var5 += var8.getWidth();
            if (!this.method13(var8)) {
               if (!var6) {
                  var5 += this.field7.get();
               }

               var6 = false;
            }
         }

         this.field9 = Math.max(var5, this.field6.get());
         return this.field9;
      } else {
         float var1 = 0.0F;

         for (MixinCore5 var3 : this.field5) {
            float var4 = var3.getWidth();
            if (var4 > var1) {
               var1 = var4;
            }
         }

         this.field9 = var1;
         return var1;
      }
   }

   @Override
   public float getHeight() {
      if (this.field10 != null) {
         return this.field10;
      }

      if (this.vertical) {
         float var5 = 0.0F;
         boolean var6 = true;

         for (MixinCore5 var8 : this.field5) {
            var5 += var8.getHeight();
            if (!this.method13(var8)) {
               if (!var6) {
                  var5 += this.field7.get();
               }

               var6 = false;
            }
         }

         this.field10 = Math.max(var5, this.field6.get());
         return this.field10;
      } else {
         float var1 = 0.0F;

         for (MixinCore5 var3 : this.field5) {
            float var4 = var3.getHeight();
            if (var4 > var1) {
               var1 = var4;
            }
         }

         this.field10 = var1;
         return var1;
      }
   }

   @Override
   public void method1(float var1, float var2, HudRenderContext var3) {
      if (this.vertical) {
         this.method12(var1, var2, var3);
      } else {
         this.method11(var1, var2, var3);
      }
   }

   private void method11(float var1, float var2, HudRenderContext var3) {
      float var4 = this.getHeight();
      float var5 = 0.0F;
      int var6 = 0;

      for (MixinCore5 var8 : this.field5) {
         var5 += var8.getWidth();
         if (var8 instanceof MixinCore5Iterator.Data2) {
            var6++;
         }
      }

      float var12 = Math.max(0.0F, this.field6.get() - var5);
      boolean var13 = true;

      for (MixinCore5 var10 : this.field5) {
         if (!this.method13(var10)) {
            if (!var13) {
               var1 += this.field7.get();
            }

            var13 = false;
         }

         MixinCore5Iterator.Type var11 = this.field8;
         if (var10 instanceof MixinCore5Iterator.Data) {
            var11 = ((MixinCore5Iterator.Data)var10).method2();
         }

         switch (var11) {
            case START:
               var10.method1(var1, var2, var3);
               break;
            case CENTER:
               var10.method1(var1, var2 + (var4 - var10.getHeight()) / 2.0F, var3);
               break;
            case END:
               var10.method1(var1, var2 + var4 - var10.getHeight(), var3);
         }

         var1 += var10.getWidth();
         if (var10 instanceof MixinCore5Iterator.Data2) {
            var1 += var12 / var6;
         }
      }
   }

   private void method12(float var1, float var2, HudRenderContext var3) {
      float var4 = this.getWidth();
      float var5 = 0.0F;
      int var6 = 0;

      for (MixinCore5 var8 : this.field5) {
         var5 += var8.getHeight();
         if (var8 instanceof MixinCore5Iterator.Data2) {
            var6++;
         }
      }

      float var12 = Math.max(0.0F, this.field6.get() - var5);
      boolean var13 = true;

      for (MixinCore5 var10 : this.field5) {
         if (!(var10 instanceof MixinCore5Iterator.Data2)) {
            if (!var13) {
               var2 += this.field7.get();
            }

            var13 = false;
         }

         MixinCore5Iterator.Type var11 = this.field8;
         if (var10 instanceof MixinCore5Iterator.Data) {
            var11 = ((MixinCore5Iterator.Data)var10).method2();
         }

         switch (var11) {
            case START:
               var10.method1(var1, var2, var3);
               break;
            case CENTER:
               var10.method1(var1 + (var4 - var10.getWidth()) / 2.0F, var2, var3);
               break;
            case END:
               var10.method1(var1 + var4 - var10.getWidth(), var2, var3);
         }

         var2 += var10.getHeight();
         if (var10 instanceof MixinCore5Iterator.Data2) {
            var2 += var12 / var6;
         }
      }
   }

   private boolean method13(MixinCore5 var1) {
      return var1 instanceof MixinCore5Iterator.Data2 || var1 instanceof MixinCore5Impl && ((MixinCore5Impl)var1).isHidden();
   }

   public static class Data extends MixinCore5Handler_2<MixinCore5Iterator.Data> {
      private MixinCore5Iterator.Type field2;

      public Data(MixinCore5Iterator.Type var1, MixinCore5 var2) {
         super(var2);
         this.field2 = var1;
      }

      public MixinCore5Iterator.Data method1(MixinCore5Iterator.Type var1) {
         this.field2 = var1;
         return this;
      }

      @Generated
      public MixinCore5Iterator.Type method2() {
         return this.field2;
      }
   }

   public static class Data2 implements MixinCore5 {
      @Override
      public void clearCache() {
      }

      @Override
      public float getWidth() {
         return 0.0F;
      }

      @Override
      public float getHeight() {
         return 0.0F;
      }

      @Override
      public void method1(float var1, float var2, HudRenderContext var3) {
      }
   }

   public enum Type {
      START,
      CENTER,
      END;
   }
}
