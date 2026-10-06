package com.moonsworth.lunar.client.render.texture;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Weigher;
import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.render.texture.AnimatedSpriteImpl;
import com.moonsworth.lunar.client.event.resourcepack.ResourcesReloadEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.Generated;
import org.checkerframework.checker.index.qual.NonNegative;

public class VanillaTextureIndex implements LoadableHandler {
   public static final int field1 = 12;
   public static final int field2 = 12;
   private static final String field3 = ThreadModuleDump63.MC_VERSION > 5 ? "textures/item/" : "textures/items/";
   private static final String field4 = ThreadModuleDump63.MC_VERSION > 5 ? "textures/block/" : "textures/blocks/";
   private static final List<SpriteFrameList> field5 = SpriteFrameList.method2()
      .method5(field4 + "stone.png", field4 + "cobblestone.png")
      .method5(method14("oak"), method14("spruce"), method14("birch"), method14("jungle"), method14("acacia"))
      .method5(method15("oak"), method15("spruce"), method15("birch"), method15("jungle"), method15("acacia"))
      .method5(
         field4 + "diamond_ore.png",
         field4 + "coal_ore.png",
         field4 + "iron_ore.png",
         field4 + "gold_ore.png",
         field4 + "redstone_ore.png",
         field4 + "emerald_ore.png",
         field4 + "lapis_ore.png"
      )
      .method5(
         field3 + (ThreadModuleDump63.MC_VERSION > 5 ? "bucket.png" : "bucket_empty.png"),
         field3 + (ThreadModuleDump63.MC_VERSION > 5 ? "water_bucket.png" : "bucket_water.png"),
         field3 + (ThreadModuleDump63.MC_VERSION > 5 ? "lava_bucket.png" : "bucket_lava.png")
      )
      .method3(field3 + (ThreadModuleDump63.MC_VERSION > 5 ? "golden_apple.png" : "apple_golden.png"), field3 + "ender_pearl.png")
      .method5(
         field3 + (ThreadModuleDump63.MC_VERSION > 5 ? "bow.png" : "bow_standby.png"),
         field3 + "bow_pulling_0.png",
         field3 + "bow_pulling_1.png",
         field3 + "bow_pulling_2.png"
      )
      .method5(field3 + "diamond_sword.png", field3 + "iron_sword.png")
      .method5(field3 + "diamond_pickaxe.png", field3 + "iron_pickaxe.png")
      .method5(
         field3 + "iron_helmet.png",
         field3 + "diamond_chestplate.png",
         field3 + "chainmail_leggings.png",
         field3 + (ThreadModuleDump63.MC_VERSION > 5 ? "golden_boots.png" : "gold_boots.png")
      )
      .method1(
         ThreadModuleDump63.MC_VERSION > 5
            ? field3 + "totem_of_undying.png"
            : (ThreadModuleDump63.MC_VERSION == 5 ? field3 + "totem.png" : field3 + "flint_and_steel.png")
      )
      .method7(
         ThreadModuleDump63.MC_VERSION < 19
            ? SpriteFrameList.method2()
               .method4("textures/gui/icons.png", 0.0625F, 0.0F, 0.09375F, 0.03125F, 9, 9)
               .method4("textures/gui/icons.png", 0.203125F, 0.0F, 0.234375F, 0.03125F, 9, 9)
            : SpriteFrameList.method2().method2("textures/gui/sprites/hud/heart/container.png", 9, 9).method2("textures/gui/sprites/hud/heart/full.png", 9, 9)
      )
      .method7(
         ThreadModuleDump63.MC_VERSION < 19
            ? SpriteFrameList.method2()
               .method4("textures/gui/icons.png", 0.0625F, 0.0F, 0.09375F, 0.03125F, 9, 9)
               .method4("textures/gui/icons.png", 0.23828125F, 0.0F, 0.26953125F, 0.03125F, 9, 9)
            : SpriteFrameList.method2().method2("textures/gui/sprites/hud/heart/container.png", 9, 9).method2("textures/gui/sprites/hud/heart/half.png", 9, 9)
      )
      .method7(
         ThreadModuleDump63.MC_VERSION < 19
            ? SpriteFrameList.method2().method4("textures/gui/icons.png", 0.0625F, 0.0F, 0.09375F, 0.03125F, 9, 9)
            : SpriteFrameList.method2().method2("textures/gui/sprites/hud/heart/container.png", 9, 9)
      )
      .method4(ThreadModuleDump63.MC_VERSION > 5 ? null : "textures/particle/particles.png", 0.0625F, 0.25F, 0.1171875F, 0.3046875F, 9, 9)
      .method2(ThreadModuleDump63.MC_VERSION <= 5 ? null : "textures/particle/critical_hit.png", 9, 9)
      .method4(ThreadModuleDump63.MC_VERSION > 5 ? null : "textures/particle/particles.png", 0.125F, 0.25F, 0.1796875F, 0.3046875F, 9, 9)
      .method2(ThreadModuleDump63.MC_VERSION <= 5 ? null : "textures/particle/enchanted_hit.png", 9, 9)
      .build();
   private final Cache<String, VanillaTextureIndex.Data2> field6;
   private final VanillaTextureIndex.Data2 field7 = new VanillaTextureIndex.Data2(new ArrayList<>(), 16, false, false, false);
   private final Consumer<ResourcesReloadEvent> field8;
   private final Consumer<EventClientTick> field9;
   private long field10;

   public VanillaTextureIndex() {
      this.field6 = Caffeine.newBuilder().expireAfterAccess(1L, TimeUnit.MINUTES).removalListener((var1, var2, var3) -> {
         if (var2 != null && var2 != VanillaTextureIndex.Data2.field6 || var2 == this.field7) {
            List var4 = var2.field1.stream().filter(var0 -> !var0.method5()).toList();
            ThreadModuleDump37.method8().execute(() -> var4.forEach(AnimatedSprite::destroy));
         }
      }).weigher(new Weigher<String, VanillaTextureIndex.Data2>() {
         public @NonNegative int method1(String var1, VanillaTextureIndex.Data2 var2) {
            if (var2 != null && var2 != VanillaTextureIndex.Data2.field6 && var2 != VanillaTextureIndex.this.field7) {
               double var3 = 0.0;

               for (AnimatedSprite var6 : var2.field1) {
                  if (!var6.method5()) {
                     for (com.moonsworth.lunar.client.render.texture.ResourcePackSprite var8 : VanillaTextureIndex.method13(var6)) {
                        if (!var8.ICRHIRHCRCRIHHRIICHRCOIRCHHIHH()) {
                           var3 += var8.getWidth() * var8.getHeight() * 4 / 1000000.0;
                        }
                     }
                  }
               }

               return (int)Math.max(1.0, var3);
            } else {
               return 0;
            }
         }
      }).maximumWeight(384L).build();
      this.field8 = var1 -> this.field6.invalidateAll();
      this.field9 = var1 -> this.field10++;
   }

   @Override
   public void init() {
      com.moonsworth.lunar.client.event.ClientEventBus.method29().method2(ResourcesReloadEvent.class, this.field8);
      com.moonsworth.lunar.client.event.ClientEventBus.method29().method2(EventClientTick.class, this.field9);
   }

   @Override
   public void close() {
      com.moonsworth.lunar.client.event.ClientEventBus.method29().method6(ResourcesReloadEvent.class, this.field8);
      com.moonsworth.lunar.client.event.ClientEventBus.method29().method6(EventClientTick.class, this.field9);
      this.field6.invalidateAll();
   }

   public Optional<VanillaTextureIndex.Data2> method1(IResourcePackBridge var1) {
      VanillaTextureIndex.Data2 var2 = (VanillaTextureIndex.Data2)this.field6.getIfPresent(var1.bridge$getPackName());
      if (var2 == null) {
         this.field6.put(var1.bridge$getPackName(), VanillaTextureIndex.Data2.field6);
         this.method4(var1);
         return Optional.empty();
      } else {
         return var2 == VanillaTextureIndex.Data2.field6 ? Optional.empty() : Optional.of(var2);
      }
   }

   public void method2(MixinHelper_4 var1, VanillaTextureIndex.Data2 var2, int var3, int var4, int var5, int var6) {
      var1.push();
      var1.method38(var3, var4, 0.0F);
      int var7 = 34;
      int var8 = 3;
      int var9 = 0;

      for (AnimatedSprite var11 : var2.field1) {
         if (var11.method1()) {
            AnimatedSprite.Data3 var12 = var11.method2(var1, var7, var8, 12, 12);
            if (var12 != AnimatedSprite.Data3.field3) {
               var7 += var12.method2() + 1;
               var9 = Math.max(var12.method3(), var9);
               if (var7 > 137) {
                  var7 = 34;
                  var8 += var9 + 2;
                  var9 = 0;
               }
            }
         }
      }

      var1.push();
      var1.method38(var5 - var5 / 3.0F + 6.0F, 0.0F, 0.0F);
      LcuiScreen.method94(var1, 0.0F, 0.0F, 1.0F, var6, -1);
      var1.method38(4.5F, -4.0F, 0.0F);
      var1.scale(0.75F, 0.75F, 1.0F);
      var1.method18(ThreadModuleDump63.method10(), "Res: §a" + var2.field2 + "x", 0, 9, -1, true);
      var1.method18(ThreadModuleDump63.method10(), this.method3("animations", var2.field5), 0, 18, -1, true);
      var1.method18(ThreadModuleDump63.method10(), this.method3("lightmap", var2.field3), 0, 27, -1, true);
      var1.method18(ThreadModuleDump63.method10(), this.method3("sounds", var2.field4), 0, 36, -1, true);
      var1.pop();
      var1.pop();
   }

   private String method3(String var1, boolean var2) {
      var1 = Client.method109().method67().method2("gui.resourcePacks", var1);
      return var1 + ": " + (var2 ? "" + AdventureChatFormatting.GREEN + '✔' : "" + AdventureChatFormatting.RED + '✕');
   }

   private void method4(IResourcePackBridge var1) {
      if (var1 != ThreadModuleDump63.method3().bridge$getMcDefaultResourcePack()) {
         ThreadModuleDump37.method4(
            () -> {
               boolean var2 = false;
               boolean var3 = var1.bridge$hasPath("assets/minecraft/sounds/") || var1.bridge$hasPath("assets/minecraft/sounds.json");
               boolean var4 = var1.bridge$hasPath("assets/minecraft/mcpatcher/lightmap/world0.png")
                  || var1.bridge$hasPath("assets/minecraft/optifine/lightmap/world0.png")
                  || var1.bridge$hasPath("shader/assets/minecraft/shaders/core/lightmap.fsh")
                  || var1.bridge$hasPath("shader2/assets/minecraft/shaders/core/lightmap.fsh")
                  || var1.bridge$hasPath("assets/minecraft/shaders/core/lightmap.fsh");
               ArrayList var5 = new ArrayList();

               for (SpriteFrameList var7 : field5) {
                  Optional var8 = var7.method1(var1);
                  if (var8.isEmpty()) {
                     var8 = this.method6(var7);
                     if (var8.isEmpty()) {
                        continue;
                     }
                  }

                  var5.add((AnimatedSprite)var8.get());
                  if (!var2 && this.method5((AnimatedSprite)var8.get())) {
                     var2 = true;
                  }
               }

               if (!var5.isEmpty()) {
                  int var9 = this.method7(var5);
                  this.field6.put(var1.bridge$getPackName(), new VanillaTextureIndex.Data2(var5, var9, var4, var3, var2));
               }
            }
         );
      }
   }

   private boolean method5(AnimatedSprite var1) {
      if (var1 instanceof com.moonsworth.lunar.client.render.texture.ResourcePackSprite var6) {
         return var6.method7();
      } else {
         if (var1 instanceof AnimatedSpriteImpl var2) {
            Iterator var4 = var2.getImages().iterator();
            if (var4.hasNext()) {
               AnimatedSprite var5 = (AnimatedSprite)var4.next();
               return this.method5(var5);
            }
         } else if (var1 instanceof com.moonsworth.lunar.client.render.texture.SpriteImage var3) {
            Iterator var7 = var3.getImages().iterator();
            if (var7.hasNext()) {
               AnimatedSprite var8 = (AnimatedSprite)var7.next();
               return this.method5(var8);
            }
         }

         return false;
      }
   }

   private synchronized Optional<AnimatedSprite> method6(SpriteFrameList var1) {
      for (AnimatedSprite var3 : this.field7.field1) {
         if (var3.method4().equals(var1)) {
            return Optional.of(var3);
         }
      }

      Optional var4 = var1.method1(ThreadModuleDump63.method3().bridge$getMcDefaultResourcePack());
      if (var4.isEmpty()) {
         this.field7.field1.add(new AnimatedSprite.Data4(var1, true));
         return Optional.empty();
      } else {
         this.field7.field1.add((AnimatedSprite)var4.get());
         return var4;
      }
   }

   private int method7(List<AnimatedSprite> var1) {
      List var2 = this.method8(var1);
      if (var2.isEmpty()) {
         return 16;
      }

      VanillaTextureIndex.Data var3 = var2.stream().max(Comparator.comparing(VanillaTextureIndex.Data::count)).get();
      double var4 = var2.stream().mapToInt(VanillaTextureIndex.Data::count).sum();
      if (var3.field2 / var4 >= 0.8) {
         return var3.field1;
      }

      double var6 = 0.38;
      if (var2.size() == 2) {
         VanillaTextureIndex.Data var21 = this.method11(var2);
         return var21.field2 / var4 > var6 ? var21.field1 : this.method12(var2).field1;
      }

      List var8 = var2.stream().filter(var1x -> var1x.field1 >= var3.field1 && var1x.field2 > 1).toList();
      if (var8.isEmpty()) {
         return var3.field1;
      }

      if (var8.size() <= 2) {
         VanillaTextureIndex.Data var22 = this.method11(var8);
         return var22.field2 / var4 > var6 ? var22.field1 : var3.field1;
      }

      double var9 = Math.log(2.0);
      double var11 = Math.log(var3.field1) / var9;
      double var13 = 0.0;

      for (VanillaTextureIndex.Data var16 : var8) {
         double var17 = Math.log(var16.field1) / var9;
         double var19 = var17 - var11;
         var13 += var19 * var16.field2;
      }

      var13 /= var3.field2;
      if (var13 > 0.5) {
         var13 = Math.round(var13);
         int var25 = (int)Math.pow(2.0, var11 + var13);
         int var26 = this.method10(var25, var2);
         if (var26 / (var4 - var3.field2) > var6) {
            return var25;
         }
      }

      return var3.field1;
   }

   private List<VanillaTextureIndex.Data> method8(List<AnimatedSprite> var1) {
      HashMap var2 = new HashMap();

      for (AnimatedSprite var4 : var1) {
         this.method9(var4, var2);
      }

      return var2.entrySet().stream().map(var0 -> new VanillaTextureIndex.Data((Integer)var0.getKey(), (Integer)var0.getValue())).toList();
   }

   private void method9(AnimatedSprite var1, Map<Integer, Integer> var2) {
      if (var1 instanceof AnimatedSpriteImpl var3) {
         var3.getImages().forEach(var2x -> this.method9(var2x, var2));
      } else if (var1 instanceof com.moonsworth.lunar.client.render.texture.SpriteImage var4) {
         int var6 = var4.getImages().size();
         if (var6 > 0) {
            this.method9(var4.getImages().get(var6 - 1), var2);
         }
      } else if (var1 instanceof com.moonsworth.lunar.client.render.texture.ResourcePackSprite var5
         && !var5.ICRHIRHCRCRIHHRIICHRCOIRCHHIHH()
         && var5.getWidth() == var5.getHeight()) {
         int var7 = var5.getWidth();
         var2.put(var7, var2.getOrDefault(var7, 0) + 1);
      }
   }

   private int method10(int var1, List<VanillaTextureIndex.Data> var2) {
      return var2.stream().filter(var1x -> var1x.field1 == var1).findAny().map(var0 -> var0.field2).orElse(0);
   }

   private VanillaTextureIndex.Data method11(List<VanillaTextureIndex.Data> var1) {
      return var1.stream().max(Comparator.comparing(VanillaTextureIndex.Data::method1)).orElseThrow();
   }

   private VanillaTextureIndex.Data method12(List<VanillaTextureIndex.Data> var1) {
      return var1.stream().min(Comparator.comparing(VanillaTextureIndex.Data::method1)).orElseThrow();
   }

   private static List<com.moonsworth.lunar.client.render.texture.ResourcePackSprite> method13(AnimatedSprite var0) {
      if (var0 instanceof com.moonsworth.lunar.client.render.texture.ResourcePackSprite var6) {
         return Collections.singletonList(var6);
      } else {
         ArrayList var1 = new ArrayList();
         if (var0 instanceof AnimatedSpriteImpl var2) {
            for (AnimatedSprite var5 : var2.getImages()) {
               var1.addAll(method13(var5));
            }
         } else if (var0 instanceof com.moonsworth.lunar.client.render.texture.SpriteImage var3) {
            for (AnimatedSprite var8 : var3.getImages()) {
               var1.addAll(method13(var8));
            }
         }

         return var1;
      }
   }

   private static String method14(String var0) {
      return field4 + (ThreadModuleDump63.MC_VERSION > 5 ? var0 + "_log.png" : "log_" + var0 + ".png");
   }

   private static String method15(String var0) {
      return field4 + (ThreadModuleDump63.MC_VERSION > 5 ? var0 + "_planks.png" : "planks_" + var0 + ".png");
   }

   @Generated
   public long method16() {
      return this.field10;
   }

   private class Data {
      private final int field1;
      private final int field2;

      private Data(int var1, int var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public int method1() {
         return this.field1;
      }

      public int count() {
         return this.field2;
      }
   }

   public class Data2 {
      private final Collection<AnimatedSprite> field1;
      private final int field2;
      private final boolean field3;
      private final boolean field4;
      private final boolean field5;
      public static VanillaTextureIndex.Data2 field6 = new VanillaTextureIndex.Data2(null, 0, false, false, false);

      public Data2(Collection<AnimatedSprite> var1, int var2, boolean var3, boolean var4, boolean var5) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
      }

      public Collection<AnimatedSprite> method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public boolean method3() {
         return this.field3;
      }

      public boolean method4() {
         return this.field4;
      }

      public boolean method5() {
         return this.field5;
      }
   }
}
