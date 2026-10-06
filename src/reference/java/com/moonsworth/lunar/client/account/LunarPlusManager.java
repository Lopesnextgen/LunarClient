package com.moonsworth.lunar.client.account;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.common.v1.Color;
import com.lunarclient.websocket.cosmetic.v2.LoginResponse;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Generated;
import com.moonsworth.lunar.client.config.InternalSettings;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.translation.TranslationManager;

public class LunarPlusManager extends ItemSetHandler<Integer> {
   private final GuiIterator field2 = new GuiIterator();
   private boolean field3;

   @Override
   protected Set<Integer> method3() {
      return new LinkedHashSet<>();
   }

   public int method2() {
      return ThreadModuleDump63.method3().bridge$getSession() != null && ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile() != null
         ? ThreadModuleDump63.method4().method41().method9().method7(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId())
         : 0;
   }

   public int method5() {
      if (ThreadModuleDump63.method3().bridge$getSession() != null && ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile() != null) {
         CosmeticManager.Data var1 = ThreadModuleDump63.method4()
            .method53()
            .method63()
            .get(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId());
         return var1 == null ? 16777215 : ThreadModuleDump23.method11(var1.method5(), var1.method6(), var1.method7(), 1.0F);
      } else {
         return 16777215;
      }
   }

   public void method4(LoginResponse var1) {
      int var2 = var1.getPlusColor().getColor();
      this.method9(var2 > 0);
      this.method3().clear();
      this.method3().addAll(var1.getAvailableLunarPlusColorsList().stream().map(Color::getColor).toList());
      Horsestats var3 = ThreadModuleDump63.method3().bridge$getSession();
      if (var3 != null && var3.bridge$getProfile() != null) {
         UUID var4 = var3.bridge$getProfile().getId();
         InternalSettings var5 = ThreadModuleDump63.method4().method41().method9();
         if (var2 <= 0) {
            var5.method8(var4, -1);
         } else {
            var5.method8(var4, var2);
         }
      } else {
         this.method7();
      }
   }

   public void method6() {
      this.method9(false);
      Horsestats var1 = ThreadModuleDump63.method3().bridge$getSession();
      if (var1 != null && var1.bridge$getProfile() != null) {
         UUID var2 = var1.bridge$getProfile().getId();
         InternalSettings var3 = ThreadModuleDump63.method4().method41().method9();
         var3.method8(var2, -1);
         this.method7();
      } else {
         this.method7();
      }
   }

   public void method7() {
      TranslationManager var1 = ThreadModuleDump63.method4().method67();
      JsonObject var2 = new JsonObject();
      var2.addProperty("isLunarPlus", this.method9());
      var2.addProperty("plusColor", String.format("#%06X", 16777215 & this.method2()));
      var2.addProperty("logoColor", String.format("#%06X", 16777215 & this.method5()));
      this.field2.method3("lunarPlus", var2);
      this.field2.method3("active", this.method2());
      JsonArray var3 = new JsonArray();
      List var4 = this.method3().stream().toList();

      for (int var5 = 0; var5 < var4.size(); var5++) {
         Integer var6 = (Integer)var4.get(var5);
         String var7;
         if (var5 == 0) {
            var7 = var1.method2("gui.lunarPlus", "originalLunarPlus");
         } else {
            var7 = var1.method2("gui.lunarPlus", "monthLunarPlus", var5 * 3);
         }

         JsonObject var8 = new JsonObject();
         var8.addProperty("color", var6);
         var8.addProperty("name", var7);
         var8.addProperty("index", var5);
         var3.add(var8);
      }

      this.field2.method3("availableColors", var3);
   }

   @Generated
   public GuiIterator method8() {
      return this.field2;
   }

   @Generated
   public boolean method9() {
      return this.field3;
   }

   @Generated
   public void method9(boolean var1) {
      this.field3 = var1;
   }
}
