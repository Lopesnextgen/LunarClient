package com.moonsworth.lunar.client.mod.player;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_26;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.BridgeExtension_4;
import com.moonsworth.lunar.bridge.Bridge_4;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.highlight.HighlightBase;
import com.moonsworth.lunar.client.highlight.mixin.highlight.HighlightBase2;
import com.moonsworth.lunar.client.lighting.Gui2Extension;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension4222;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension497;
import com.moonsworth.lunar.client.lighting.rewindhandlers.RewindhandlersType;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump75;
import com.moonsworth.lunar.files.Files6_2;
import com.moonsworth.lunar.ichor.util.FatalIchorError2;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class EnchantmentSpriteTextDecoration extends Framework7Extension2 {
   private static final List<Files6_2<Predicate<Bridge6_4>, Function<Bridge_4, Bridge2_26>>> field8 = List.of(
      Files6_2.method1(Bridge6_4::bridge$isItemSword, Bridge_4::method3),
      Files6_2.method1(Bridge6_4::bridge$isArmor, Bridge_4::method1),
      Files6_2.method1(Bridge6_4::bridge$isItemBow, Bridge_4::method4),
      Files6_2.method1(Bridge6_4::bridge$isItemBasicTool, Bridge_4::method2)
   );
   private final LightingExtension443 field9 = (LightingExtension443)((LightingExtension443.Data2)Lighting.method7("enchantmentSpriteTextInHotbar")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field10 = (LightingExtension443)((LightingExtension443.Data2)Lighting.method7("enchantmentSpriteTextInInventory")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field11 = (LightingExtension443)((LightingExtension443.Data2)Lighting.method7("enchantmentSpriteTextIgnoreInvalid")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field12 = (LightingExtension443)((LightingExtension443.Data2)Lighting.method7("enchantmentSpriteTextSpecificMatchOnly")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension497<EnchantmentSpriteTextDecoration.Type> field13 = (LightingExtension497<EnchantmentSpriteTextDecoration.Type>)Lighting.method10(
         "alignment", EnchantmentSpriteTextDecoration.Type.TOP_RIGHT
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension497<EnchantmentSpriteTextDecoration.Type2> field14 = (LightingExtension497<EnchantmentSpriteTextDecoration.Type2>)Lighting.method10(
         "enchantmentSpriteTextDisplayStrategy", EnchantmentSpriteTextDecoration.Type2.MAX
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field15 = (LightingExtension443)((LightingExtension443.Data2)Lighting.method7("enchantmentSpriteTextRomanNumerals")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field16 = (LightingExtension4222)((LightingExtension4222.Data)Lighting.method8("textColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(16777215))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field17 = (LightingExtension4222)Lighting.method8("enchantmentSpriteTextMendingColorOverride")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public EnchantmentSpriteTextDecoration(InventoryMod var1) {
      super(false);
      this.method11(Framework.field16, Framework4.method3(var1));
      this.handle(HighlightBase.Data6.class, this::method2);
      this.handle(HighlightBase2.Data10.class, this::method3);
   }

   @Override
   public String getId() {
      return "ENCHANTMENT_SPRITE_TEXT_DECORATION";
   }

   @Override
   public void method2(LightingExtension23 var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         RewindhandlersType.GENERAL,
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new LightingExtension[]{this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15}
         )
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(RewindhandlersType.COLOR, var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field16});
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field17}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(2);
      });
   }

   private void method2(HighlightBase.Data6 var1) {
      if (this.field10.get()) {
         BridgeExtension_4 var2 = var1.IHIRRHICIIHIHCRRHOHHOOHOHCHHHI().bridge$getItemStack();
         String var3 = this.method5(var2);
         if (var3 != null) {
            EnchantmentSpriteTextDecoration.Type var4 = this.field13.get();
            int var5 = var4.getInventoryXOffset();
            TextComponent var6 = Component.text(var3);
            if (var4 == EnchantmentSpriteTextDecoration.Type.TOP_LEFT) {
               var5 += (int)ThreadModuleDump63.method10().bridge$getStringWidth(var6);
            }

            var1.method4(var6, var5, -1, this.method6(var2));
         }
      }
   }

   private void method3(HighlightBase2.Data10 var1) {
      if (this.field9.get()) {
         List var2 = ThreadModuleDump63.method7().bridge$getInventory().bridge$getMainInventory();
         int var3 = Math.min(9, var2.size());

         for (int var4 = 0; var4 < var3; var4++) {
            this.method4(var1.IORHRCIIHRIHIOIRROIRHHCORRIOHH(), (BridgeExtension_4)var2.get(var4), var4 * 20 + var1.getX(), var1.getY() + 2);
         }

         if (ThreadModuleDump63.MC_VERSION > 1) {
            this.method4(
               var1.IORHRCIIHRIHIOIRROIRHHCORRIOHH(),
               ThreadModuleDump63.method7().bridge$getInventory().bridge$getOffhandInventory().get(0),
               var1.getX() - 29,
               var1.getY() + 2
            );
         }
      }
   }

   private void method4(MixinHelper_4 var1, BridgeExtension_4 var2, int var3, int var4) {
      String var5 = this.method5(var2);
      if (var5 != null) {
         EnchantmentSpriteTextDecoration.Type var6 = this.field13.get();
         TextComponent var7 = Component.text(var5);
         var3 += var6.getHotbarXOffset();
         if (var6 == EnchantmentSpriteTextDecoration.Type.TOP_RIGHT) {
            var3 -= (int)ThreadModuleDump63.method10().bridge$getStringWidth(var7) - 3;
         }

         var1.push();
         var1.method38(0.0F, 0.0F, 301.0F);
         var1.method10(ThreadModuleDump63.method10(), var7, var3, var4, this.method6(var2), true);
         var1.pop();
         if (ThreadModuleDump63.MC_VERSION >= 6 && ThreadModuleDump63.MC_VERSION <= 16) {
            ThreadModuleDump63.method3().bridge$getRenderBuffers().bridge$bufferSource().bridge$endLastBatch();
         }
      }
   }

   @Nullable
   private String method5(BridgeExtension_4 var1) {
      if (var1 == null) {
         return null;
      } else {
         Map var2 = var1.bridge$getEnchantments();
         if (var2.isEmpty()) {
            return null;
         } else {
            boolean var3 = var1.bridge$getItem().bridge$isItemEnchantedBook();
            boolean var4 = !var3 && this.field11.get();
            boolean var5 = !var3 && this.field12.get();
            BiPredicate var6 = var4 ? Bridge2_26::bridge$canApply : (var0, var1x) -> true;
            int var7 = var5 ? method7(var1) : this.field14.get().getGetter().apply(var2, var1, var6);
            if (var7 == 0) {
               return null;
            } else {
               return this.field15.get() ? ThreadModuleDump75.method1(var7) : String.valueOf(var7);
            }
         }
      }
   }

   private int method6(BridgeExtension_4 var1) {
      if (ThreadModuleDump63.MC_VERSION > 2) {
         int var2 = this.field17.getColor();
         Bridge_4 var3 = Bridge.method32();
         if (var2 != -16777216 && var1.method1(var3.method5()) > 0) {
            return var2;
         }
      }

      return this.field16.getColor();
   }

   private static int method7(BridgeExtension_4 var0) {
      Bridge6_4 var1 = var0.bridge$getItem();

      for (Files6_2 var3 : field8) {
         if (((Predicate)var3.field1).test(var1)) {
            return var0.method1((Bridge2_26)((Function)var3.field2).apply(Bridge.method32()));
         }
      }

      return 0;
   }

   private static int method8(Map<Bridge2_26, Integer> var0, BridgeExtension_4 var1, BiPredicate<Bridge2_26, BridgeExtension_4> var2) {
      int var3 = 0;

      for (Entry var5 : var0.entrySet()) {
         if ((Integer)var5.getValue() > var3 && var2.test((Bridge2_26)var5.getKey(), var1)) {
            var3 = (Integer)var5.getValue();
         }
      }

      return var3;
   }

   private static int method9(Map<Bridge2_26, Integer> var0, BridgeExtension_4 var1, BiPredicate<Bridge2_26, BridgeExtension_4> var2) {
      int var3 = Integer.MAX_VALUE;

      for (Entry var5 : var0.entrySet()) {
         if ((Integer)var5.getValue() < var3 && var2.test((Bridge2_26)var5.getKey(), var1)) {
            var3 = (Integer)var5.getValue();
         }
      }

      return var3 == Integer.MAX_VALUE ? 0 : var3;
   }

   private static int method10(Map<Bridge2_26, Integer> var0, BridgeExtension_4 var1, BiPredicate<Bridge2_26, BridgeExtension_4> var2) {
      int var3 = 0;

      for (Entry var5 : var0.entrySet()) {
         if (var2.test((Bridge2_26)var5.getKey(), var1)) {
            var3 += var5.getValue();
         }
      }

      return Math.max(0, (int)Math.ceil((float)var3 / var0.size()));
   }

   public enum Type implements Gui2Extension {
      TOP_LEFT("topLeft", 0, 3),
      TOP_RIGHT("topRight", 17, 17);

      @Annotation(OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO)
      private final String translationKey;
      private final int inventoryXOffset;
      private final int hotbarXOffset;

      @Override
      public String id() {
         return this.translationKey;
      }

      @Override
      public String getLanguagePath() {
         return "features.ENCHANTMENT_SPRITE_TEXT_DECORATION.info";
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id(), new Object[0]);
      }

      @Generated
      Type(String var3, int var4, int var5) {
         this.translationKey = var3;
         this.inventoryXOffset = var4;
         this.hotbarXOffset = var5;
      }

      @Generated
      public String getTranslationKey() {
         return this.translationKey;
      }

      @Generated
      public int getInventoryXOffset() {
         return this.inventoryXOffset;
      }

      @Generated
      public int getHotbarXOffset() {
         return this.hotbarXOffset;
      }
   }

   public enum Type2 implements Gui2Extension {
      MAX("max", EnchantmentSpriteTextDecoration::method8),
      MIN("min", EnchantmentSpriteTextDecoration::method9),
      AVERAGE("average", EnchantmentSpriteTextDecoration::method10);

      @Annotation(OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO)
      private final String translationKey;
      private final FatalIchorError2<Map<Bridge2_26, Integer>, BridgeExtension_4, BiPredicate<Bridge2_26, BridgeExtension_4>, Integer> getter;

      @Override
      public String id() {
         return this.translationKey;
      }

      @Override
      public String getLanguagePath() {
         return "features.ENCHANTMENT_SPRITE_TEXT_DECORATION.info";
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id(), new Object[0]);
      }

      @Generated
      Type2(String var3, FatalIchorError2<Map<Bridge2_26, Integer>, BridgeExtension_4, BiPredicate<Bridge2_26, BridgeExtension_4>, Integer> var4) {
         this.translationKey = var3;
         this.getter = var4;
      }

      @Generated
      public String getTranslationKey() {
         return this.translationKey;
      }

      @Generated
      public FatalIchorError2<Map<Bridge2_26, Integer>, BridgeExtension_4, BiPredicate<Bridge2_26, BridgeExtension_4>, Integer> getGetter() {
         return this.getter;
      }
   }
}
