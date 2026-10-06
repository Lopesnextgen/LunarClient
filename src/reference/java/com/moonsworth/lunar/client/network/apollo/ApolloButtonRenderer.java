package com.moonsworth.lunar.client.network.apollo;

import com.lunarclient.apollo.button.v1.Button;
import com.lunarclient.apollo.button.v1.ButtonClientAction;
import com.lunarclient.apollo.button.v1.ButtonContentPart;
import com.lunarclient.apollo.button.v1.ButtonShape;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.ResourceLocationIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.util.Slayer;

public class ApolloButtonRenderer {
   private static final int field1 = 16;
   private static final float field2 = 128.0F;
   private static final float field3 = 2.0F;
   private static final float field4 = 6.0F;
   private static final ResourceLocationBridge field5 = ResourceLocationBridge.create("lunar", "apollo/button_circle.png");
   private static final ResourceLocationBridge field6 = ResourceLocationBridge.create("lunar", "apollo/button_circle_border.png");
   private static final float field7 = 0.25F;
   private static final float field8 = 4.0F;
   private static final int field9 = 30;
   private static final int field10 = 100;
   private static final float field11 = 0.15F;
   private static final int field12 = 687865855;
   private static final int field13 = -2145049307;
   private final String field14;
   private final float field15;
   private final float field16;
   private final float field17;
   private final float field18;
   private final ApolloButtonRenderer.Type3 field19;
   private final int field20;
   private final int field21;
   private final int field22;
   private final int field23;
   private final List<ApolloButtonRenderer.Data23> field24;
   private float field25;
   private float field26 = -1.0F;
   private final List<Component> field27;
   @Nullable
   private final String field28;
   @Nullable
   private final String field29;
   @Nullable
   private final ButtonClientAction field30;

   private ApolloButtonRenderer(
      String var1,
      float var2,
      float var3,
      float var4,
      float var5,
      ApolloButtonRenderer.Type3 var6,
      int var7,
      int var8,
      int var9,
      int var10,
      List<ApolloButtonRenderer.Data23> var11,
      float var12,
      List<Component> var13,
      @Nullable String var14,
      @Nullable String var15,
      @Nullable ButtonClientAction var16
   ) {
      this.field14 = var1;
      this.field15 = var2;
      this.field16 = var3;
      this.field17 = var4;
      this.field18 = var5;
      this.field19 = var6;
      this.field20 = var7;
      this.field21 = var8;
      this.field22 = var9;
      this.field23 = var10;
      this.field24 = var11;
      this.field25 = var12;
      this.field27 = var13;
      this.field28 = var14;
      this.field29 = var15;
      this.field30 = var16;
   }

   @Nullable
   public static ApolloButtonRenderer method1(Button var0, float var1, float var2) {
      String var3 = var0.getId();
      if (var3.isEmpty()) {
         method16(var0, "missing id");
         return null;
      }

      if (var0.hasPosition() && var0.hasSize()) {
         float var4 = var0.getPosition().getX();
         float var5 = var0.getPosition().getY();
         float var6 = var0.getSize().getWidth();
         float var7 = var0.getSize().getHeight();
         if (Float.isFinite(var4) && Float.isFinite(var5) && Float.isFinite(var6) && Float.isFinite(var7)) {
            if (!(var6 <= 0.0F) && !(var7 <= 0.0F) && !(var4 < 0.0F) && !(var5 < 0.0F) && !(var4 + var6 > var1) && !(var5 + var7 > var2)) {
               ApolloButtonRenderer.Type3 var8 = var0.getShape() == ButtonShape.BUTTON_SHAPE_CIRCLE ? ApolloButtonRenderer.Type3.CIRCLE : ApolloButtonRenderer.Type3.CIRCLE;
               int var9 = var0.hasBackgroundColor() ? method13(var0.getBackgroundColor().getColor()) : 687865855;
               int var10 = var0.hasBorderColor() ? method13(var0.getBorderColor().getColor()) : -2145049307;
               int var11 = var0.hasHoveredBackgroundColor() ? method13(var0.getHoveredBackgroundColor().getColor()) : method14(var9);
               int var12 = var0.hasHoveredBorderColor() ? method13(var0.getHoveredBorderColor().getColor()) : method14(var10);
               ArrayList var13 = new ArrayList();

               for (ButtonContentPart var15 : var0.getContent().getPartsList()) {
                  if (var13.size() >= 30) {
                     Slayer.method4("Apollo", "Button '" + var3 + "' content truncated to 30 parts");
                     break;
                  }

                  ApolloButtonRenderer.Data23 var16 = method3(var15);
                  if (var16 != null) {
                     var13.add(var16);
                  }
               }

               if (var13.isEmpty()) {
                  method16(var0, "no valid content parts");
                  return null;
               }

               float var19 = method15(var0.getContent().getScale());
               ArrayList var20 = new ArrayList();

               for (String var17 : var0.getTooltip().getAdventureJsonLinesList()) {
                  if (var20.size() >= 100) {
                     Slayer.method4("Apollo", "Button '" + var3 + "' tooltip truncated to 100 lines");
                     break;
                  }

                  Component var18 = Rewindhandlers3.method4(var17);
                  if (var18 != null) {
                     var20.add(var18);
                  }
               }

               String var22 = null;
               String var23 = null;
               ButtonClientAction var24 = null;
               switch (var0.getOnClickCase()) {
                  case RUN_COMMAND:
                     var22 = var0.getRunCommand();
                     break;
                  case OPEN_URL:
                     var23 = var0.getOpenUrl();
                     break;
                  case CLIENT_ACTION:
                     var24 = var0.getClientAction();
                     if (var24 == ButtonClientAction.BUTTON_CLIENT_ACTION_UNSPECIFIED || var24 == ButtonClientAction.UNRECOGNIZED) {
                        method16(var0, "unsupported client action " + var0.getClientActionValue());
                        return null;
                     }
               }

               return new ApolloButtonRenderer(var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var19, var20, var22, var23, var24);
            } else {
               method16(var0, "button does not fit within the " + var1 + "x" + var2 + " box");
               return null;
            }
         } else {
            method16(var0, "non-finite position or size");
            return null;
         }
      } else {
         method16(var0, "missing position or size");
         return null;
      }
   }

   public void method2(ButtonUpdate var1) {
      if (var1.hasContent()) {
         ArrayList var2 = new ArrayList();

         for (ButtonContentPart var4 : var1.getContent().getPartsList()) {
            if (var2.size() >= 30) {
               Slayer.method4("Apollo", "Button '" + this.field14 + "' content truncated to 30 parts");
               break;
            }

            ApolloButtonRenderer.Data23 var5 = method3(var4);
            if (var5 != null) {
               var2.add(var5);
            }
         }

         if (!var2.isEmpty()) {
            this.field24.clear();
            this.field24.addAll(var2);
            this.field25 = method15(var1.getContent().getScale());
            this.field26 = -1.0F;
         }
      }

      if (var1.hasTooltip()) {
         this.field27.clear();

         for (String var7 : var1.getTooltip().getAdventureJsonLinesList()) {
            if (this.field27.size() >= 100) {
               Slayer.method4("Apollo", "Button '" + this.field14 + "' tooltip truncated to 100 lines");
               break;
            }

            Component var8 = Rewindhandlers3.method4(var7);
            if (var8 != null) {
               this.field27.add(var8);
            }
         }
      }
   }

   @Nullable
   private static ApolloButtonRenderer.Data23 method3(ButtonContentPart var0) {
      switch (var0.getPartCase()) {
         case ADVENTURE_JSON_TEXT:
            Component var9 = Rewindhandlers3.method4(var0.getAdventureJsonText());
            return var9 == null ? null : ApolloButtonRenderer.Data23.method1(var9);
         case ICON:
            Icon var1 = NetworkTypes.fromProtobuf(var0.getIcon());
            if (var1 instanceof ItemStackIcon var10) {
               ItemStackBridge var11 = Rewindhandlers3.method2(var10);
               return var11 == null ? null : ApolloButtonRenderer.Data23.method2(var11);
            } else {
               String var2 = null;
               float var3 = 16.0F;
               float var4 = 16.0F;
               if (var1 instanceof SimpleResourceLocationIcon var5) {
                  var2 = var5.getResourceLocation();
                  var3 = var4 = method17(var5.getSize());
               } else if (var1 instanceof AdvancedResourceLocationIcon var6) {
                  if (!Float.isFinite(var6.getMinU()) || !Float.isFinite(var6.getMaxU()) || !Float.isFinite(var6.getMinV()) || !Float.isFinite(var6.getMaxV())) {
                     Rewindhandlers3.method3(null, var6.getResourceLocation());
                     return null;
                  }

                  var2 = var6.getResourceLocation();
                  var3 = method17(var6.getWidth());
                  var4 = method17(var6.getHeight());
               } else if (var1 instanceof ResourceLocationIcon var7) {
                  var2 = var7.getResourceLocation();
               }

               if (var2 == null) {
                  Rewindhandlers3.method3(null, String.valueOf(var1));
                  return null;
               } else {
                  try {
                     return ApolloButtonRenderer.Data23.method3(ResourceLocationBridge.create(var2), var1, var3, var4);
                  } catch (Exception var8) {
                     Rewindhandlers3.method3(var8, var2);
                     return null;
                  }
               }
            }
         default:
            return null;
      }
   }

   public void method4(MixinHelper_4 var1, float var2, float var3, boolean var4) {
      float var5 = var2 + this.field15;
      float var6 = var3 + this.field16;
      int var7 = var4 ? this.field22 : this.field20;
      int var8 = var4 ? this.field23 : this.field21;
      switch (this.field19) {
         case CIRCLE:
            this.method6(var1, var5, var6, Math.min(3.0F, Math.min(this.field17, this.field18) / 2.0F), var7, var8);
            break;
         case CIRCLE:
            int var9 = Math.round(Math.min(this.field17, this.field18));
            int var10 = Math.round(var5 + (this.field17 - var9) / 2.0F);
            int var11 = Math.round(var6 + (this.field18 - var9) / 2.0F);
            var1.method24(field5, var10, var11, var9, var9, var7);
            if (var8 != var7) {
               var1.method24(field6, var10, var11, var9, var9, var8);
            }
      }
   }

   public void method5(MixinHelper_4 var1, float var2, float var3) {
      this.method9(var1, var2 + this.field15 + this.field17 / 2.0F, var3 + this.field16 + this.field18 / 2.0F);
   }

   private void method6(MixinHelper_4 var1, float var2, float var3, float var4, int var5, int var6) {
      int var7 = Math.round(var3);
      int var8 = Math.round(var3 + this.field18);
      int var9 = Math.min((int)Math.ceil(var4), (var8 - var7) / 2);

      for (int var10 = var7; var10 < var7 + var9; var10++) {
         this.method7(var1, var2, var3, var4, var5, var6, var10);
      }

      int var14 = var7 + var9;
      int var11 = var8 - var9;
      if (var11 > var14) {
         int var12 = Math.round(var2);
         int var13 = Math.round(var2 + this.field17);
         var1.method1(var12, var14, var12 + 1, var11, var6);
         var1.method1(var12 + 1, var14, var13 - 1, var11, var5);
         var1.method1(var13 - 1, var14, var13, var11, var6);
      }

      for (int var15 = var8 - var9; var15 < var8; var15++) {
         this.method7(var1, var2, var3, var4, var5, var6, var15);
      }
   }

   private void method7(MixinHelper_4 var1, float var2, float var3, float var4, int var5, int var6, int var7) {
      float var8 = Math.max(var4 - 1.0F, 0.0F);
      float var9 = var7 + 0.5F - var3;
      float var10 = method8(var9, this.field18, var4);
      int var11 = Math.round(var2 + var10);
      int var12 = Math.round(var2 + this.field17 - var10);
      if (var12 > var11) {
         float var13 = var9 - 1.0F;
         if (!(var13 <= 0.0F) && !(var13 >= this.field18 - 2.0F)) {
            float var14 = method8(var13, this.field18 - 2.0F, var8);
            int var15 = Math.round(var2 + 1.0F + var14);
            int var16 = Math.round(var2 + this.field17 - 1.0F - var14);
            if (var16 <= var15) {
               var1.method1(var11, var7, var12, var7 + 1, var6);
            } else {
               if (var15 > var11) {
                  var1.method1(var11, var7, var15, var7 + 1, var6);
               }

               var1.method1(var15, var7, var16, var7 + 1, var5);
               if (var12 > var16) {
                  var1.method1(var16, var7, var12, var7 + 1, var6);
               }
            }
         } else {
            var1.method1(var11, var7, var12, var7 + 1, var6);
         }
      }
   }

   private static float method8(float var0, float var1, float var2) {
      float var3 = 0.0F;
      if (var0 < var2) {
         var3 = var2 - var0;
      } else if (var0 > var1 - var2) {
         var3 = var0 - (var1 - var2);
      }

      return var3 >= var2 ? var2 : var2 - (float)Math.sqrt(var2 * var2 - var3 * var3);
   }

   private void method9(MixinHelper_4 var1, float var2, float var3) {
      if (this.field26 < 0.0F) {
         float var4 = 0.0F;

         for (ApolloButtonRenderer.Data23 var6 : this.field24) {
            var4 += var6.method4();
         }

         this.field26 = var4 + 2.0F * (this.field24.size() - 1);
      }

      boolean var13 = this.field25 != 1.0F;
      if (var13) {
         var1.push();
         var1.method39(var2, var3);
         var1.method40(this.field25, this.field25);
      }

      float var14 = var13 ? 0.0F : var2;
      float var15 = var13 ? 0.0F : var3;
      int var7 = ThreadModuleDump63.method10().method19();
      float var8 = var14 - this.field26 / 2.0F;
      boolean var9 = this.field25 >= 1.0F;

      for (ApolloButtonRenderer.Data23 var11 : this.field24) {
         Component var12 = var11.field1;
         if (var12 != null) {
            var1.method10(ThreadModuleDump63.method10(), var12, Math.round(var8), Math.round(var15 - var7 / 2.0F) + 1, -1, var9);
         } else if (var11.field2 != null) {
            var1.method34(var11.field2, Math.round(var8), Math.round(var15) - 8, ThreadModuleDump63.method3());
         } else if (var11.field3 != null) {
            this.method10(var1, var11, var8, var15);
         }

         var8 += var11.method4() + 2.0F;
      }

      if (var13) {
         var1.pop();
      }
   }

   private void method10(MixinHelper_4 var1, ApolloButtonRenderer.Data23 var2, float var3, float var4) {
      float var5 = var2.field5;
      float var6 = var2.field6;
      if (var2.field4 instanceof AdvancedResourceLocationIcon var7) {
         float var12 = Math.max(var7.getMaxU() - var7.getMinU(), 1.0E-4F);
         float var9 = Math.max(var7.getMaxV() - var7.getMinV(), 1.0E-4F);
         float var10 = var5 / var12;
         float var11 = var6 / var9;
         var1.method25(var2.field3, var3, var4 - var6 / 2.0F, var7.getMinU() * var10, var7.getMinV() * var11, var5, var6, var10, var11, -1);
      } else {
         var1.method24(var2.field3, Math.round(var3), Math.round(var4 - var6 / 2.0F), Math.round(var5), Math.round(var6), -1);
      }
   }

   public void method11(MixinHelper_4 var1, int var2, int var3) {
      if (!this.field27.isEmpty()) {
         var1.method38(0.0F, 0.0F, 400.0F);
         LcuiScreen.method84(var1, this.field27, var2, var3);
         var1.method38(0.0F, 0.0F, -400.0F);
      }
   }

   public boolean method12(float var1, float var2, double var3, double var5) {
      float var7 = var1 + this.field15;
      float var8 = var2 + this.field16;
      if (this.field19 == ApolloButtonRenderer.Type3.CIRCLE) {
         float var9 = Math.min(this.field17, this.field18) / 2.0F;
         double var10 = var3 - (var7 + this.field17 / 2.0F);
         double var12 = var5 - (var8 + this.field18 / 2.0F);
         return var10 * var10 + var12 * var12 <= var9 * var9;
      } else {
         return var3 >= var7 && var3 <= var7 + this.field17 && var5 >= var8 && var5 <= var8 + this.field18;
      }
   }

   private static int method13(int var0) {
      return var0 >>> 24 == 0 ? var0 | 0xFF000000 : var0;
   }

   private static int method14(int var0) {
      int var1 = var0 >>> 24;
      int var2 = var0 >> 16 & 0xFF;
      int var3 = var0 >> 8 & 0xFF;
      int var4 = var0 & 0xFF;
      var2 += Math.round((255 - var2) * 0.15F);
      var3 += Math.round((255 - var3) * 0.15F);
      var4 += Math.round((255 - var4) * 0.15F);
      return var1 << 24 | var2 << 16 | var3 << 8 | var4;
   }

   private static float method15(float var0) {
      return Float.isFinite(var0) && !(var0 <= 0.0F) ? Math.min(Math.max(var0, 0.25F), 4.0F) : 1.0F;
   }

   private static void method16(Button var0, String var1) {
      Slayer.method4("Apollo", "Received invalid button from the server: " + var1);
      String var2 = String.valueOf(var0);
      Slayer.method4("Apollo", "Button: " + (var2.length() > 300 ? var2.substring(0, 300) + "..." : var2));
   }

   private static float method17(float var0) {
      return Float.isFinite(var0) && !(var0 <= 0.0F) ? Math.min(var0, 128.0F) : 16.0F;
   }

   @Generated
   public String getId() {
      return this.field14;
   }

   @Generated
   public float getX() {
      return this.field15;
   }

   @Generated
   public float getY() {
      return this.field16;
   }

   @Generated
   public float getWidth() {
      return this.field17;
   }

   @Generated
   public float getHeight() {
      return this.field18;
   }

   @Generated
   public List<Component> method18() {
      return this.field27;
   }

   @Nullable
   @Generated
   public String getRunCommand() {
      return this.field28;
   }

   @Nullable
   @Generated
   public String getOpenUrl() {
      return this.field29;
   }

   @Nullable
   @Generated
   public ButtonClientAction getClientAction() {
      return this.field30;
   }

   private static final class Data23 {
      @Nullable
      private final Component field1;
      @Nullable
      private final ItemStackBridge field2;
      @Nullable
      private final ResourceLocationBridge field3;
      @Nullable
      private final Icon field4;
      private final float field5;
      private final float field6;
      private float field7 = -1.0F;

      private Data23(@Nullable Component var1, @Nullable ItemStackBridge var2, @Nullable ResourceLocationBridge var3, @Nullable Icon var4, float var5, float var6) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
      }

      private static ApolloButtonRenderer.Data23 method1(Component var0) {
         return new ApolloButtonRenderer.Data23(var0, null, null, null, 0.0F, 0.0F);
      }

      private static ApolloButtonRenderer.Data23 method2(ItemStackBridge var0) {
         return new ApolloButtonRenderer.Data23(null, var0, null, null, 16.0F, 16.0F);
      }

      private static ApolloButtonRenderer.Data23 method3(ResourceLocationBridge var0, Icon var1, float var2, float var3) {
         return new ApolloButtonRenderer.Data23(null, null, var0, var1, var2, var3);
      }

      private float method4() {
         if (this.field7 < 0.0F) {
            this.field7 = this.field1 != null ? ThreadModuleDump63.method10().bridge$getStringWidth(this.field1) : this.field5;
         }

         return this.field7;
      }
   }

   public enum Type3 {
      CIRCLE,
      CIRCLE;
   }
}
