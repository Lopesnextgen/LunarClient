package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.framework.feature.tiertagger.mixin.TierFallbackMode;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityLabel.EventRenderEntityLabelLines;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Tiertagger {
   private final TierTagger field1;
   private final TierBadgeFormatter field2;
   private final Cache<UUID, Tiertagger.Data> field3 = Caffeine.newBuilder()
      .expireAfterAccess(2L, TimeUnit.MINUTES)
      .initialCapacity(35)
      .maximumSize(100L)
      .build();

   public void clearCache() {
      this.field3.invalidateAll();
   }

   public long method1() {
      return this.field3.estimatedSize();
   }

   @VersionGate(min = 16)
   public void method2(EventRenderEntityLabelLines data31) {
      if (!data31.isCancelled() && data31.method2() != null && Ref.method8() != null) {
         EntityPlayerBridge bridgeextension2222 = data31.method2();
         Tiertagger.Data data3 = this.method5(bridgeextension2222);
         if (data3 != null) {
            if ((Boolean)this.field1.field17.get()) {
               if (data31.isModified() && data3.method1() != null) {
                  data31.method5(data3.method1());
               }
            } else {
               String text4 = bridgeextension2222.bridge$getName();

               for (int index5 = 0; index5 < data31.method3(); index5++) {
                  if (data31.method4(index5).contains(text4)) {
                     data31.method3(index5, this.method4(data3, data31.method2(index5)));
                     return;
                  }
               }
            }
         }
      }
   }

   public void method3(EventRenderNameTag highlightimpl111) {
      if (!highlightimpl111.isCancelled() && !highlightimpl111.getLines().isEmpty()) {
         if (highlightimpl111.method2() instanceof EntityPlayerBridge bridgeextension2222) {
            if (bridgeextension2222.method2()) {
               return;
            }

            Tiertagger.Data data5 = this.method5(bridgeextension2222);
            if (data5 == null) {
               return;
            }

            if ((Boolean)this.field1.field17.get()) {
               if (data5.method1() != null) {
                  highlightimpl111.getLines().add(data5.method1());
               }

               return;
            }

            Component component4 = highlightimpl111.method3();
            if (component4 == null) {
               return;
            }

            highlightimpl111.method1(this.method4(data5, component4));
         }
      }
   }

   @NotNull
   private Component method4(Tiertagger.Data data1, @NotNull Component component2) {
      Builder builder3 = Component.text();
      if (data1.method1() != null) {
         builder3.append(data1.method1());
      }

      builder3.append(component2);
      if (data1.method2() != null) {
         builder3.append(data1.method2());
      }

      return builder3.build();
   }

   @Nullable
   private Tiertagger.Data method5(EntityPlayerBridge bridgeextension2221) {
      if (bridgeextension2221 != null && this.field1.isEnabled() && !NpcUtils.method3(bridgeextension2221, ServerBrandWatcher.method8(KeystrokesType.HYPIXEL))) {
         UUID uuid2 = bridgeextension2221.bridge$getUniqueID();
         Tiertagger.Data data3 = (Tiertagger.Data)this.field3.getIfPresent(uuid2);
         if (data3 != null) {
            return data3;
         }

         TierPlayerProfile tiertagger44 = this.field1.field10.getTierProvider().method4(uuid2);
         if (tiertagger44 == null) {
            return null;
         }

         boolean flag5 = (Boolean)this.field1.field17.get();
         Component component6 = Component.text(" | ").color(TextColor.color(11184810));
         Component component7 = this.method7(tiertagger44);
         if (this.field1.field12.get() != com.moonsworth.lunar.client.framework.feature.tiertagger.mixin.TiertaggerShownStatistic.RANK) {
            ValuePair files6_218 = this.method6(tiertagger44);
            TierRanking tiertagger3_219 = (TierRanking)files6_218.field1;
            TierRanking tiertagger3_210 = (TierRanking)files6_218.field2;
            if (tiertagger3_219 == null) {
               return null;
            }

            boolean flag11 = flag5 && tiertagger3_210 != null;
            Component component12 = this.method8(tiertagger3_219, flag11);
            Component component13 = tiertagger3_210 == null ? null : this.method8(tiertagger3_210, flag11);
            if (flag5) {
               Object obj23;
               if (component13 == null) {
                  obj23 = component12;
               } else {
                  obj23 = ((Builder)((Builder)((Builder)Component.text().append(component12)).append(component6)).append(component13)).build();
               }

               if (component7 != null) {
                  obj23 = obj23.append(component7);
               }

               Tiertagger.Data data21 = new Tiertagger.Data((Component)obj23, null);
               this.field3.put(uuid2, data21);
               return data21;
            } else if (component13 != null) {
               Component component22 = this.field1.method13() ? component13 : component12;
               Component component16 = this.field1.method13() ? component12 : component13;
               Component component17 = component6.append(component16);
               if (component7 != null) {
                  component17 = component17.append(component7);
               }

               Tiertagger.Data data20 = new Tiertagger.Data(component22.append(component6), component17);
               this.field3.put(uuid2, data20);
               return data20;
            } else {
               Component component15 = component7 == null ? component12 : component12.append(component7);
               Tiertagger.Data data14 = this.field1.method13() ? new Tiertagger.Data(null, component6.append(component15)) : new Tiertagger.Data(component15.append(component6), null);
               this.field3.put(uuid2, data14);
               return data14;
            }
         } else {
            if (tiertagger44.method2().isEmpty()) {
               return null;
            }

            Component component8 = Component.text("#" + tiertagger44.method2().getAsInt()).color(TextColor.color(255, 255, 255));
            if (component7 != null) {
               component8 = component8.append(component7);
            }

            Tiertagger.Data data9;
            if (flag5) {
               data9 = new Tiertagger.Data(component8, null);
            } else if (this.field1.method13()) {
               data9 = new Tiertagger.Data(null, component6.append(component8));
            } else {
               data9 = new Tiertagger.Data(component8.append(component6), null);
            }

            this.field3.put(uuid2, data9);
            return data9;
         }
      } else {
         return null;
      }
   }

   @NotNull
   private ValuePair<TierRanking, TierRanking> method6(TierPlayerProfile tiertagger41) {
      TierRanking tiertagger3_22 = tiertagger41.method4().get(this.field1.field13.get());
      TierFallbackMode gui2extension23 = (TierFallbackMode)this.field1.field11.get();
      if (gui2extension23 == TierFallbackMode.HIGHEST_ALWAYS || gui2extension23 == TierFallbackMode.HIGHEST_FALLBACK && tiertagger3_22 == null) {
         tiertagger3_22 = tiertagger41.method4().values().stream().min(Comparator.comparingInt(arg0 -> arg0.method2().method1())).orElse(null);
      }

      TierRanking tiertagger3_24 = null;
      if (!TierGameMode.field5.equals(this.field1.field14.get())) {
         tiertagger3_24 = tiertagger41.method4().get(this.field1.field14.get());
      }

      if (tiertagger3_22 == null) {
         tiertagger3_22 = tiertagger3_24;
         tiertagger3_24 = null;
      } else if (tiertagger3_24 != null && tiertagger3_24.method1().equals(tiertagger3_22.method1())) {
         tiertagger3_24 = null;
      }

      return ValuePair.method1(tiertagger3_22, tiertagger3_24);
   }

   @Nullable
   private Component method7(TierPlayerProfile tiertagger41) {
      return !this.field1.field19.get()
         ? null
         : (Component)tiertagger41.method1().map(arg0 -> (TextComponent)Component.text(" " + arg0.name()).color(TextColor.color(arg0.color))).orElse(null);
   }

   @NotNull
   private Component method8(TierRanking tiertagger3_21, boolean flag2) {
      Component component3;
      if (tiertagger3_21.method4().orElse(false) && (Boolean)this.field1.field20.get() && tiertagger3_21.method3().isPresent()) {
         TierPlacement tiertagger55 = tiertagger3_21.method3().get();
         component3 = ((TextComponent)Component.text("R").color(TextColor.color(30, 40, 55))).append(this.field2.method1(tiertagger55.tier(), tiertagger55.method3()));
      } else {
         TierPlacement tiertagger54 = tiertagger3_21.method2();
         component3 = this.field2.method1(tiertagger54.tier(), tiertagger54.method3());
      }

      if (flag2) {
         component3 = ((TextComponent)Component.text(tiertagger3_21.method1().niceName() + " ").color(TextColor.color(tiertagger3_21.method1().method2()))).append(component3);
      }

      if ((Boolean)this.field1.field18.get() && tiertagger3_21.method1().method1().isPresent()) {
         component3 = ((TextComponent)Component.text(tiertagger3_21.method1().method1().get() + " ").color(TextColor.color(tiertagger3_21.method1().method2()))).append(component3);
      }

      return component3;
   }

   @Generated
   public Tiertagger(TierTagger tiertagger1, TierBadgeFormatter tiertagger32) {
      this.field1 = tiertagger1;
      this.field2 = tiertagger32;
   }

   private class Data {
      @Nullable
      private final Component field1;
      @Nullable
      private final Component field2;

      private Data(@Nullable Component component1, @Nullable Component component2) {
         this.field1 = component1;
         this.field2 = component2;
      }

      @Nullable
      public Component method1() {
         return this.field1;
      }

      @Nullable
      public Component method2() {
         return this.field2;
      }
   }
}
