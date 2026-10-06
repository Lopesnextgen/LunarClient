package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge7_6;
import com.moonsworth.lunar.bridge.horsestats.LanguageEntry;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.calculator.CalculatorType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.Language_v1_7;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LanguageManager.class)
public abstract class LanguageManagerMixin implements Bridge7_6 {
   @Shadow
   public String currentLanguage;
   @Final
   @Shadow
   public Map<String, Language> languageMap;
   @Shadow
   public Map languageMap$v1_7;

   @Shadow
   public abstract boolean isCurrentLanguageBidirectional();

   @Shadow
   public abstract boolean isCurrentLocaleUnicode();

   @Shadow
   public abstract void setCurrentLanguage(Language var1);

   @Shadow
   public abstract void setCurrentLanguage(Language_v1_7 var1);

   @Inject(method = "<init>(Lnet/minecraft/client/resources/data/MetadataSerializer;Ljava/lang/String;)V", at = @At("TAIL"))
   private void lunar$onConstruct(CallbackInfo var1) {
      this.lunar$handleLanguageUpdate();
   }

   @Inject(method = {"setCurrentLanguage$v1_7", "setCurrentLanguage$v1_8"}, at = @At("TAIL"))
   private void lunar$onSetLanguage(CallbackInfo var1) {
      this.lunar$handleLanguageUpdate();
   }

   @Unique
   private void lunar$handleLanguageUpdate() {
      if (Client.method109() != null && Client.method109().method67() != null) {
         Client.method109().method67().setLanguage(this.currentLanguage);
      } else {
         ThreadModuleDump80.language = this.currentLanguage;
      }
   }

   @Unique
   private String lunar$normalizeCountryCode(String var1) {
      return Arrays.stream(var1.split("_")).reduce("", (var0, var1x) -> !var0.isEmpty() ? var0 + "_" + var1x.toUpperCase() : var1x);
   }

   @Override
   public List<LanguageEntry> bridge$getLanguages() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.languageMap.values().stream().map(var1 -> {
         String var2 = this.lunar$normalizeCountryCode(var1.languageCode);
         return new LanguageEntry(var2, var1.region, var1.name, CalculatorType.isSupported(var2));
      }).collect(Collectors.toList()) : this.languageMap$v1_7.values().stream().map(var1 -> {
         Language_v1_7 var2 = (Language_v1_7)var1;
         String var3 = this.lunar$normalizeCountryCode(var2.languageCode);
         return new LanguageEntry(var3, var2.region, var2.name, CalculatorType.isSupported(var3));
      }).collect(Collectors.toList());
   }

   @Override
   public void bridge$setCurrentLanguage(String var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var1 = var1.toLowerCase();
      }

      Minecraft var2 = Minecraft.getMinecraft();
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Language var3 = this.languageMap.get(var1);
         this.setCurrentLanguage(var3);
         var2.gameSettings.translator = var3.languageCode;
      } else {
         Language_v1_7 var4 = (Language_v1_7)this.languageMap$v1_7.get(var1);
         this.setCurrentLanguage(var4);
         var2.gameSettings.translator = var4.languageCode;
      }

      var2.refreshResources();
      var2.fontRendererObj.setUnicodeFlag(this.isCurrentLocaleUnicode() || var2.gameSettings.forceUnicodeFont);
      var2.fontRendererObj.setBidiFlag(this.isCurrentLanguageBidirectional());
      var2.gameSettings.saveOptions();
   }
}
