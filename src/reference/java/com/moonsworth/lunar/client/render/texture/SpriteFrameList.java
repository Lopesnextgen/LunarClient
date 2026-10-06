package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.client.render.texture.AnimatedSpriteImpl;
import com.moonsworth.lunar.client.render.texture.ResourcePackSprite;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface SpriteFrameList {
   Optional<AnimatedSprite> method1(IResourcePackBridge var1);

   static SpriteFrameList.Data method2() {
      return new SpriteFrameList.Data();
   }

   class Data {
      private final List<SpriteFrameList> field1 = new ArrayList<>();

      public SpriteFrameList.Data method1(String var1) {
         if (var1 != null && !var1.isEmpty()) {
            this.method9(var1x -> ResourcePackSprite.method2(var1x, var1, 12, 12));
         }

         return this;
      }

      public SpriteFrameList.Data method2(String var1, int var2, int var3) {
         if (var1 != null && !var1.isEmpty()) {
            this.method9(var3x -> ResourcePackSprite.method2(var3x, var1, var2, var3));
         }

         return this;
      }

      public SpriteFrameList.Data method3(String... var1) {
         if (var1 != null) {
            for (String var5 : var1) {
               this.method1(var5);
            }
         }

         return this;
      }

      public SpriteFrameList.Data method4(String var1, float var2, float var3, float var4, float var5, int var6, int var7) {
         if (var1 != null && !var1.isEmpty()) {
            this.method9(var7x -> ResourcePackSprite.method1(var7x, var1, var2, var3, var4, var5, var6, var7));
         }

         return this;
      }

      public SpriteFrameList.Data method5(String... var1) {
         if (var1 != null && var1.length > 0) {
            this.method9(var1x -> {
               ArrayList var2 = new ArrayList();

               for (String var6 : var1) {
                  ResourcePackSprite.method2(var1x, var6, 12, 12).ifPresent(var2::add);
               }

               if (var2.isEmpty()) {
                  return Optional.empty();
               }

               boolean var7 = var1x == ThreadModuleDump63.method3().bridge$getMcDefaultResourcePack();
               return Optional.of(new AnimatedSpriteImpl(var2, var7));
            });
         }

         return this;
      }

      public SpriteFrameList.Data method6(SpriteFrameList.Data var1) {
         if (var1 != null) {
            List var2 = var1.build();
            if (!var2.isEmpty()) {
               this.method9(var2x -> {
                  List var3 = this.method8(var2x, var2);
                  if (var3.isEmpty()) {
                     return Optional.empty();
                  }

                  boolean var4 = var2x == ThreadModuleDump63.method3().bridge$getMcDefaultResourcePack();
                  return Optional.of(new AnimatedSpriteImpl(var3, var4));
               });
            }
         }

         return this;
      }

      public SpriteFrameList.Data method7(SpriteFrameList.Data var1) {
         if (var1 != null) {
            List var2 = var1.build();
            if (!var2.isEmpty()) {
               this.method9(var2x -> {
                  List var3 = this.method8(var2x, var2);
                  if (var3.isEmpty()) {
                     return Optional.empty();
                  }

                  boolean var4 = var2x == ThreadModuleDump63.method3().bridge$getMcDefaultResourcePack();
                  return Optional.of(new com.moonsworth.lunar.client.render.texture.SpriteImage(var3, var4));
               });
            }
         }

         return this;
      }

      private List<AnimatedSprite> method8(IResourcePackBridge var1, List<SpriteFrameList> var2) {
         ArrayList var3 = new ArrayList();

         for (SpriteFrameList var5 : var2) {
            var5.method1(var1).ifPresent(var3::add);
         }

         return var3;
      }

      private void method9(final Function<IResourcePackBridge, Optional<AnimatedSprite>> var1) {
         this.field1.add(new SpriteFrameList() {
            @Override
            public Optional<AnimatedSprite> method1(IResourcePackBridge var1x) {
               return ((Optional)var1.apply(var1x)).map(var1xxx -> {
                  var1xxx.method3(this);
                  return (AnimatedSprite)var1xxx;
               });
            }
         });
      }

      public List<SpriteFrameList> build() {
         return this.field1;
      }
   }
}
