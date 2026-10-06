package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.Animation;
import com.moonsworth.lunar.client.ui.ColorAnimation;
import com.moonsworth.lunar.client.framework.feature.minimap.Minimap2Impl2;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.mod.render.markers.Markers.Type;
import com.moonsworth.lunar.client.mod.render.markers.Markers.Type2;
import com.moonsworth.lunar.client.mod.render.markers.Markers.Type3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump64;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector2d;
import org.joml.Vector3d;

public class Markers3 {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("minecraft", "textures/skins/wide/steve.png");
   private final Markers2 field2;
   private final com.moonsworth.lunar.client.mod.render.markers.Markers field3 = ThreadModuleDump63.method4().method40().method87();
   private final Markers3.Data field4 = new Markers3.Data();
   private final ColorAnimation field5 = new ColorAnimation(250L);
   private Vector2d field6 = null;
   private float field7;
   private boolean field8 = false;
   private Boolean field9 = null;
   private boolean field10 = false;

   public void method1(HudBaseRenderEvent var1) {
      if (this.field6 != null) {
         MixinHelper_4 var2 = var1.method2();
         var2.push();

         try {
            this.method3(var2);
            this.method5(var2, 3.0F);
            this.method6(var2, 3.0F);
         } finally {
            var2.pop();
         }
      }
   }

   public void method2() {
      this.field10 = false;
      this.field6 = null;
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 != null) {
         Vector3d var2 = this.field2.getPos();
         double var3 = var2.x;
         double var5 = var2.y;
         double var7 = var2.z;
         if (this.method15(var3, var5, var7)) {
            Vector2d var9 = ThreadModuleDump64.method3(
               var3, var5 + (ThreadModuleDump63.MC_VERSION <= 5 ? ThreadModuleDump63.method3().bridge$getRenderViewEntity().bridge$getEyeHeight() : 0.0F), var7
            );
            ThreadModuleDump71 var10 = LcuiScreen.method151();
            if (var9 != null && !(var9.x > var10.getScaledWidth()) && !(var9.y > var10.getScaledHeight())) {
               this.field6 = var9;
               this.field7 = (float)var2.distance(var1.bridge$getPosX(), var1.bridge$getPosY(), var1.bridge$getPosZ());
               if (this.field9 == null) {
                  Bridge3_23 var11 = ThreadModuleDump63.method8().bridge$getBlockAt((int)Math.floor(var3), (int)Math.floor(var5 + 0.5), (int)Math.floor(var7));
                  this.field9 = var11 != null && !var11.bridge$isAir();
               }

               this.field10 = true;
            }
         }
      }
   }

   private void method3(MixinHelper_4 var1) {
      ThreadModuleDump71 var2 = LcuiScreen.method151();
      int var3 = var2.getScaleFactor();
      double var4 = (double)Math.round(this.field6.x * var3) / var3;
      double var6 = (double)Math.round(this.field6.y * var3) / var3;
      this.method4(var4, var6, var2, var1);
      var1.method39((float)var4, (float)var6);
      float var8 = ThreadModuleDump63.method4().method40().method50().method3(var1.method43());
      var1.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
      float var9 = this.field2.scale() + 0.05F;
      var9 *= 1.0F + this.field5.method1() * 0.25F;
      var9 -= this.field7 / 325.0F;
      var9 /= var3;
      var9 *= 2.0F;
      var1.scale(var9, var9, 1.0F);
   }

   private void method4(double var1, double var3, ThreadModuleDump71 var5, MixinHelper_4 var6) {
      if (this.field2.method26() > 1000L) {
         boolean var7 = this.field8;
         float var8 = var5.getScaledWidth() / 2.0F;
         float var9 = var5.getScaledHeight() / 2.0F;
         float var10 = 5.0F / ThreadModuleDump63.method4().method40().method50().method3(var6.method43());
         this.field8 = var1 > var8 - var10 && var1 < var8 + var10 && var3 > var9 - var10 && var3 < var9 + var10;
         this.field5.method20(!this.field8);
         if (!this.field2.method9() || this.field5.method7() || (!var7 || this.field8) && (var7 || !this.field8)) {
            if (!this.field2.method9()) {
               this.field5.method20(false);
               this.field5.stop();
            }
         } else {
            this.field5.start();
         }
      }
   }

   public void method5(MixinHelper_4 var1, float var2) {
      var1.method44(var0 -> var0.method29().method13());
      float var3 = this.field5.method1();
      if (this.field2.method9()) {
         this.field4.tick();
         if (this.field4.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            var3 = this.field4.opacity * var3;
            var1.scale(this.field4.scale, this.field4.scale, 1.0F);
         }
      }

      method16(this.field2.method25().method5(), this.field2.method19(), var1, var2, var3);
   }

   private void method6(MixinHelper_4 var1, float var2) {
      boolean var3 = this.method9(this.field2.method12());
      boolean var4 = this.method9(this.field2.method13());
      boolean var5 = this.method9(this.field2.method15());
      boolean var6 = this.method9(this.field2.method14());
      boolean var7 = this.field2.method10();
      var1.push();
      byte var8 = 10;
      if (this.field9) {
         var1.method38(0.0F, var2 * 2.0F + 2.0F, 0.0F);
      } else {
         byte var9 = 0;
         if (var7) {
            if (var3 || var4 || var5) {
               var9 += 10;
            }
         } else {
            if (var3) {
               var9 += 10;
            }

            if (var4 || var5) {
               var9 += 10;
            }
         }

         if (var6) {
            var9 += 10;
         }

         var1.method38(0.0F, -(var2 * 2.0F + var9) + 2.0F, 0.0F);
      }

      if (var7) {
         this.method7(var1, 10);
      } else {
         this.method8(var1, 10);
      }

      if (var6) {
         Vector3d var17 = this.field2.getPos();
         double var10 = (int)(var17.x * 10.0) / 10.0;
         double var12 = (int)(var17.y * 10.0) / 10.0;
         double var14 = (int)(var17.z * 10.0) / 10.0;
         Component var16 = Component.text("(" + var10 + ", " + var12 + ", " + var14 + ")").color(NamedTextColor.GRAY);
         this.method12(var1, var16, (Boolean)this.field3.getBackground().get(), this.field2.method11());
         var1.method38(0.0F, 10.0F, 0.0F);
      }

      var1.pop();
   }

   private void method7(MixinHelper_4 var1, int var2) {
      boolean var3 = this.method9(this.field2.method12());
      boolean var4 = this.method9(this.field2.method13());
      boolean var5 = this.method9(this.field2.method15());
      float var6 = 0.0F;
      Component var7 = null;
      float var8 = 0.0F;
      Component var9 = null;
      float var10 = 0.0F;
      boolean var11 = false;
      if (var3) {
         var6 += 8.0F;
      }

      if (var4) {
         boolean var12 = true;
         if (this.field2.method25().method3() == Markers2.Type2.ENTITY) {
            SIterator2.Data var13 = this.field2.method3();
            var12 = this.method14().isPresent();
            var11 = var13.method3() != null;
            if (var11 && var12) {
               var9 = ((TextComponent)((TextComponent)Component.empty().append(Component.text("(").color(NamedTextColor.GRAY))).append(var13.method3()))
                  .append(((TextComponent)Component.empty().append(Component.text(")"))).color(NamedTextColor.GRAY));
            } else if (!var12) {
               var9 = this.field2.method4();
            }
         }

         if (var9 != null) {
            var6 += var10 = ThreadModuleDump63.method10().bridge$getStringWidth(var9) + 4.0F;
         }

         if (var12) {
            var6 += 9.5F;
         }
      }

      if (var5) {
         String var14 = (int)(this.field7 * 10.0F) / 10.0 + "m";
         if (var3 || var4) {
            var14 = "(" + var14 + ")";
         }

         var7 = Component.text(var14).color(NamedTextColor.GRAY);
         var6 += var8 = ThreadModuleDump63.method10().bridge$getStringWidth(var7);
      }

      if ((Boolean)this.field3.getBackground().get()) {
         this.method13(var1, -var6 / 2.0F - 1.0F, var6 / 2.0F + 1.0F);
      }

      var1.push();
      var1.method38(-var6 / 2.0F, -0.5F, 0.0F);
      var1.method44(var0 -> var0.method29().method12());
      if (var3) {
         var1.push();
         var1.scale(0.8F, 0.8F, 1.0F);
         var1.method38(1.0F, 1.0F, 0.0F);
         this.method11(var1, this.field2.getOwnerId());
         var1.pop();
         var1.method38(8.0F, 0.0F, 0.0F);
      }

      if (var4) {
         if (var11 || var9 == null) {
            if (this.field2.method25().method3() == Markers2.Type2.PLAYER) {
               var1.push();
               var1.scale(0.8F, 0.8F, 1.0F);
               var1.method38(1.0F, 1.0F, 0.0F);
               this.method10(var1);
               var1.pop();
            } else {
               this.method10(var1);
            }

            var1.method38(10.0F, 0.0F, 0.0F);
         }

         if (var9 != null) {
            var1.push();
            float var15 = var11 ? 1.5F : 0.5F;
            var1.method38(var10 / 2.0F - var15, 0.5F, 0.0F);
            this.method12(var1, var9, false, this.field2.method11());
            var1.pop();
            var1.method38(var10, 0.0F, 0.0F);
         }
      }

      if (var7 != null) {
         var1.method38(var8 / 2.0F - 0.5F, 0.5F, 0.0F);
         this.method12(var1, var7, false, this.field2.method11());
      }

      var1.pop();
      var1.method38(0.0F, var2, 0.0F);
   }

   private void method8(MixinHelper_4 var1, int var2) {
      boolean var3 = (Boolean)this.field3.getBackground().get();
      boolean var4 = this.field2.method11();
      boolean var5 = this.method9(this.field2.method15());
      Type3 var6 = this.method9(this.field2.method12()) ? this.field2.method16() : null;
      Type2 var7 = this.method9(this.field2.method13()) ? this.field2.method17() : null;
      Markers2.Data var8 = this.field2.method25();
      if (var6 == Type3.NAME) {
         TextComponent var9 = (TextComponent)Component.text(this.field2.getOwnerName()).append(Component.text(this.field2.method18()));
         this.method12(var1, var9, var3, var4);
         var1.method38(0.0F, var2, 0.0F);
      } else if (var6 == Type3.HEAD) {
         var1.push();
         TextComponent var14 = Component.text(this.field2.method18());
         float var10 = ThreadModuleDump63.method10().bridge$getStringWidth(var14);
         var1.method38(-(var10 + 8.0F) / 2.0F, 0.0F, 0.0F);
         if (var3) {
            this.method13(var1, -1.0F, var10 + 9.0F);
         }

         var1.push();
         var1.scale(0.9F, 0.9F, 1.0F);
         var1.method38(1.0F, -0.1F, 0.0F);
         this.method11(var1, this.field2.getOwnerId());
         var1.pop();
         var1.method38(var10 / 2.0F + 8.0F, 0.0F, 0.0F);
         this.method12(var1, var14, false, var4);
         var1.pop();
         var1.method38(0.0F, var2, 0.0F);
      }

      Component var15 = null;
      if (var5) {
         var15 = Component.text((int)(this.field7 * 10.0F) / 10.0 + "m").color(NamedTextColor.GRAY);
      }

      if (var7 == Type2.TEXT || var7 == Type2.ICON && var8.method3() == Markers2.Type2.ENTITY && this.method14().isEmpty()) {
         Component var18 = Component.empty().append(this.field2.method4());
         if (var15 != null) {
            var15 = ((TextComponent)((TextComponent)Component.text(" (").color(NamedTextColor.GRAY)).append(var15))
               .append(Component.text(")").color(NamedTextColor.GRAY));
            var18 = var18.append(var15);
         }

         this.method12(var1, var18, var3, var4);
         var1.method38(0.0F, var2, 0.0F);
      } else if (var7 == Type2.ICON) {
         float var17 = 0.0F;
         float var11 = 0.0F;
         Component var12 = null;
         if (var15 != null) {
            var15 = ((TextComponent)((TextComponent)Component.text("(").color(NamedTextColor.GRAY)).append(var15))
               .append(Component.text(")").color(NamedTextColor.GRAY));
            var17 = ThreadModuleDump63.method10().bridge$getStringWidth(var15);
         }

         SIterator2.Data var13 = this.field2.method3();
         if (var13 != null && var13.method3() != null) {
            var12 = ((TextComponent)((TextComponent)Component.empty().append(Component.text("(").color(NamedTextColor.GRAY))).append(var13.method3()))
               .append(((TextComponent)Component.empty().append(Component.text(")"))).color(NamedTextColor.GRAY));
            var11 = ThreadModuleDump63.method10().bridge$getStringWidth(var12) + 1.0F;
         }

         var1.push();
         var1.method38(-(var17 + var11 + 9.0F) / 2.0F, 0.0F, 0.0F);
         if (var3) {
            this.method13(var1, -1.0F, var17 + var11 + 12.0F);
         }

         var1.method38(0.0F, -0.5F, 0.0F);
         this.method10(var1);
         if (var12 != null) {
            var1.method38(var11 / 2.0F + 9.0F, 0.5F, 0.0F);
            this.method12(var1, var12, false, var4);
            var1.method38(var11 / 2.0F - 9.0F, 0.0F, 0.0F);
         }

         if (var15 != null) {
            var1.method38(var17 / 2.0F + 10.0F, 0.0F, 0.0F);
            this.method12(var1, var15, false, var4);
         }

         var1.pop();
         var1.method38(0.0F, var2, 0.0F);
      } else if (var15 != null) {
         this.method12(var1, var15, var3, var4);
         var1.method38(0.0F, var2, 0.0F);
      }
   }

   private boolean method9(Type var1) {
      return var1 != Type.NEVER && (var1 == Type.ALWAYS || this.field8);
   }

   private void method10(MixinHelper_4 var1) {
      Markers2.Data var2 = this.field2.method25();
      Markers2.Type2 var3 = var2.getScaleFactor();
      ItemStackBridge var4 = var3 != Markers2.Type2.ITEM && var3 != Markers2.Type2.BLOCK ? null : this.field2.method2();
      if (var4 != null) {
         var1.method44(var0 -> var0.method29().method6(var0x -> {
            var0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
            Bridge.method14().method2();
         }));
         var1.method44(var0 -> var0.method29().method5(var0x -> var0x.method48()));
         var1.push();
         var1.scale(0.5F, 0.5F, 1.0F);
         var1.method34(var4, 0, 0, ThreadModuleDump63.method3());
         var1.pop();
         var1.method44(var0 -> var0.method29().method6(var0x -> {
            Bridge.method14().method3();
            var0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
            var0x.ICRCRICCCORRHICIHHIHORROOHIROO();
         }));
      } else if (var3 == Markers2.Type2.PLAYER && this.field2.method1() != null) {
         this.method11(var1, this.field2.method1().id());
      } else if (var3 == Markers2.Type2.ENTITY && this.method14().isPresent()) {
         ResourceLocationBridge var5 = this.method14().get();
         var1.method44(var0 -> var0.method29().method12());
         var1.push();
         var1.scale(0.8F, 0.8F, 1.0F);
         var1.method38(2.0F, 1.0F, 0.0F);
         LcuiScreen.method33(var1, var5, 0.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, 1.0F, 1.0F);
         var1.pop();
      } else {
         var1.push();
         var1.scale(0.8F, 0.8F, 1.0F);
         var1.method38(3.0F, 1.5F, 0.0F);
         var1.method18(ThreadModuleDump63.method10(), "?", 0, 0, -1, this.field2.method11());
         var1.pop();
      }
   }

   private void method11(MixinHelper_4 var1, UUID var2) {
      ResourceLocationBridge var3 = field1;
      ClientPacketListenerBridge var4 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
      if (var4 != null) {
         List var5 = var4.bridge$getPlayerInfoMap();
         if (var5 != null) {
            var3 = var5.stream()
               .filter(var1x -> var1x.bridge$getGameProfile() != null && var1x.bridge$getGameProfile().getId().equals(var2))
               .findFirst()
               .map(
                  var0 -> {
                     if (ThreadModuleDump63.MC_VERSION >= 1) {
                        return var0.bridge$getLocationSkin();
                     } else {
                        return var0.bridge$getGameProfile() != null
                           ? (ResourceLocationBridge)ThreadModuleDump63.method3()
                              .bridge$getSkinManager()
                              .bridge$getSkinLocation(var0.bridge$getGameProfile(), com.mojang.authlib.minecraft.MinecraftProfileTexture.Type.SKIN)
                              .orElse(null)
                           : null;
                     }
                  }
               )
               .orElse(field1);
         }
      }

      var1.method44(var0 -> var0.method29().method12());
      LcuiScreen.method46(var1, var3, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F, 64.0F, 64.0F, -1);
      if (var3 == field1) {
         var1.push();
         var1.scale(0.75F, 0.75F, 1.0F);
         var1.method38(2.5F, 1.5F, 0.0F);
         var1.method18(ThreadModuleDump63.method10(), "?", 0, 0, -1, this.field2.method11());
         var1.pop();
      } else {
         Optional var6 = ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var2);
         if (var6.isPresent() && ((Bridge6_10)var6.get()).bridge$showHat()) {
            LcuiScreen.method46(var1, var3, 0.0F, 0.0F, 40.0F, 8.0F, 8.0F, 8.0F, 64.0F, 64.0F, -1);
         }
      }
   }

   private void method12(MixinHelper_4 var1, Component var2, boolean var3, boolean var4) {
      Bridge10_2 var5 = ThreadModuleDump63.method10();
      float var6 = var5.bridge$getStringWidth(var2) / 2.0F;
      var1.method44(var0 -> var0.method29().method14());
      if (var3) {
         this.method13(var1, -var6 - 1.0F, var6 + 1.0F);
      }

      var1.method44(var0 -> var0.method29().method12());
      int var7 = (int)(186.0F + 69.0F * this.field5.method1());
      int var8 = 16777215 | var7 << 24;
      var1.method11(var5, var2, -var6 + 0.5F, 0.0F, var8, var4);
   }

   private void method13(MixinHelper_4 var1, float var2, float var3) {
      int var4 = this.field3.getBackgroundColor().method14(0.0F);
      var1.method2(var2, -1.0F, var3, 8.0F, var4);
   }

   private Optional<ResourceLocationBridge> method14() {
      if (this.field2.method3() == null) {
         return Optional.empty();
      }

      String var1 = this.field2.method3().method2();
      if ("unknown".equals(var1)) {
         return Optional.empty();
      }

      if (var1.contains(":")) {
         var1 = var1.split(":")[1];
      }

      var1 = var1.toLowerCase(Locale.ROOT);
      var1 = Minimap2Impl2.field5.getOrDefault(var1, var1);
      ResourceLocationBridge var2 = ResourceLocationBridge.create("lunar", "mobs/" + var1 + ".png");
      return ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var2) == null ? Optional.empty() : Optional.of(var2);
   }

   private boolean method15(double var1, double var3, double var5) {
      BridgeExtension var7 = ThreadModuleDump63.method3().bridge$getRenderViewEntity();
      var1 = Math.abs(Math.floor(var1 / 16.0) - Math.floor(var7.bridge$getPosX() / 16.0));
      var3 = Math.abs(Math.floor(var3 / 16.0) - Math.floor(var7.bridge$getPosY() / 16.0));
      var5 = Math.abs(Math.floor(var5 / 16.0) - Math.floor(var7.bridge$getPosZ() / 16.0));
      int var8 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getRenderDistance();
      return Math.max(var1, var5) <= var8 && var3 <= var8;
   }

   public static void method16(Markers2.Type var0, int var1, MixinHelper_4 var2, float var3, float var4) {
      var2.push();
      switch (var0) {
         case NORMAL:
            var2.method42(45.0F);
            method17(var2, var3, method18(var1, var4));
            break;
         case DANGER:
            var2.push();
            var2.getScaleFactor9(-0.5F, -1.5F);
            var3 *= 1.5F;
            float var5 = var3;
            var2.method9(LunarRenderTypes.field19, null, -var3, var3 - MarkerModel.field1 * var3, var3 * 2.0F, MarkerModel.field1 * var3, var3x -> {
               int var4x = method18(var1, var4);
               var3x.method2(-var5, var5, 0.0).method9(var4x).method16();
               var3x.method2(var5, var5, 0.0).method9(var4x).method16();
               var3x.method2(0.0, var5 - MarkerModel.field1 * var5, 0.0).method9(var4x).method16();
            });
            var2.getScaleFactor9(-0.35F, -1.25F);
            var2.method40(0.65F, 0.65F);
            var2.method44(var0x -> var0x.method29().method12());
            var2.method18(ThreadModuleDump63.method10(), "!", 0, 0, method18(-1107296256, var4), false);
            var2.pop();
            break;
         case INFO:
            var2.push();
            var2.method40(1.25F, 1.25F);
            var2.method42(45.0F);
            method17(var2, var3, method18(1610612736, var4 * 0.5F));
            var2.pop();
            var2.push();
            var2.method40(0.8F, 0.8F);
            var2.getScaleFactor9(-0.5F, -3.5F);
            var2.method44(var0x -> var0x.method29().method12());
            var2.method18(ThreadModuleDump63.method10(), "!", 0, 0, method18(var1, var4), false);
            var2.pop();
            break;
         case INTEREST:
            var2.push();
            var2.scale(1.25F, 1.25F, 1.0F);
            var2.method42(45.0F);
            method17(var2, var3, method18(1610612736, var4 * 0.5F));
            var2.pop();
            var2.push();
            var2.scale(0.65F, 0.65F, 1.0F);
            var2.getScaleFactor8(-2.5F, -3.0F, 0.0F);
            var2.method44(var0x -> var0x.method29().method12());
            var2.method18(ThreadModuleDump63.method10(), "?", 0, 0, method18(var1, var4), false);
            var2.pop();
      }

      var2.pop();
   }

   private static void method17(MixinHelper_4 var0, float var1, int var2) {
      var0.method2(-var1, -var1, var1, var1, var2);
   }

   private static int method18(int var0, float var1) {
      float var2 = (var0 >> 24 & 0xFF) / 255.0F;
      var2 += (1.0F - var2) * var1;
      var1 = var2;
      return var0 & 16777215 | (int)(var1 * 255.0F) << 24;
   }

   @Generated
   public Markers3(Markers2 var1) {
      this.field2 = var1;
   }

   @Generated
   public boolean method19() {
      return this.field8;
   }

   @Generated
   public boolean method20() {
      return this.field10;
   }

   private static class Data extends Animation {
      private final ColorAnimation field10 = new ColorAnimation(375L);
      private final ColorAnimation field11 = new ColorAnimation(250L);
      private float opacity;
      private float scale;

      private Data() {
         super(500L);
      }

      private void tick() {
         if (!this.OHCHHRHOHOCCRCIHCIRORCOCCOHOHI()) {
            this.start();
            if (!this.field10.OHCHHRHOHOCCRCIHCIRORCOCCOHOHI()) {
               this.field10.start();
            }
         }

         if (this.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            if (this.field10.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
               this.opacity = this.field10.method1() * 0.5F;
            } else {
               this.opacity = 1.0F;
            }

            if (this.field10.OHCHHRHOHOCCRCIHCIRORCOCCOHOHI() && this.OIOOHIHRHORHROIOICHCHOIRRRCRIR() <= this.field10.getDurationMs()) {
               if (!this.field11.method5()) {
                  this.field11.start();
               }

               this.scale = this.field11.method1();
            }
         }
      }
   }
}
