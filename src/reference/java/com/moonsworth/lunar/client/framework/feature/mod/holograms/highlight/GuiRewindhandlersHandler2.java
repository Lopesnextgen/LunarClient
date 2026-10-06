package com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.lunarclient.ApiUtils;
import com.lunarclient.RequestParameter;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler10;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler11;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiHandler9;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui_2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22 field7 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22.class
   );
   private static final long field8 = 300000L;
   private static final Pattern field9 = Pattern.compile("^(?<item>.+) x(?<quantity>[\\d,]+)$");
   private static final Pattern field10 = Pattern.compile("^(?<quantity>[\\d,]+)x (?<item>.+)$");
   private final Map<String, GuiRewindhandlersHandler2.Data3> field11 = new HashMap<>();
   private final Map<String, GuiRewindhandlersHandler2.Data2> field12 = new HashMap<>();
   private final List<Gui_2> field13 = List.of(
      new GuiHandler5(),
      new GuiHandler2(),
      new GuiHandler4(),
      new GuiHandler6(),
      new GuiHandler7(),
      new GuiHandler9(),
      new GuiIterator(),
      new GuiHandler3(),
      new GuiHandler11(),
      new GuiHandler8(),
      new GuiHandler10(),
      new GuiHandler()
   );
   private long field14;

   public GuiRewindhandlersHandler2() {
      this.handle(EventEverySecond.class, this::method6);
   }

   public Gui method1(String var1) {
      int var2 = 1;
      Matcher var3 = field9.matcher(var1);
      if (var3.matches()) {
         var1 = var3.group("item");
         var2 = Integer.parseInt(var3.group("quantity").replaceAll(",", ""));
      } else {
         var3 = field10.matcher(var1);
         if (var3.matches()) {
            var1 = var3.group("item");
            var2 = Integer.parseInt(var3.group("quantity").replaceAll(",", ""));
         }
      }

      for (Gui_2 var5 : this.field13) {
         if (var5.method1(var1, this)) {
            Gui var6 = var5.method2(this);
            var5.cleanup();
            var6.setValue(var6.getValue() * var2);
            return var6;
         }

         var5.cleanup();
      }

      return Gui.method3().method1(false).method3(Gui.Type.NO_OBTAINER).method4();
   }

   public Gui method2(String var1) {
      return this.method3(var1, false);
   }

   public Gui method3(String var1, boolean var2) {
      GuiRewindhandlersHandler2.Data2 var3 = this.method5(var1);
      GuiRewindhandlersHandler2.Data3 var4 = this.method4(var1);
      if (var3 != null) {
         int var5 = (int)(var2 ? var3.field4.method2() : var3.method4().method6());
         return Gui.method2(var5);
      } else {
         return var4 != null ? Gui.method2((int)var4.method1()) : Gui.method1();
      }
   }

   @Nullable
   public GuiRewindhandlersHandler2.Data3 method4(String var1) {
      return this.field11.get(var1);
   }

   @Nullable
   public GuiRewindhandlersHandler2.Data2 method5(String var1) {
      return this.field12.get(var1);
   }

   private void method6(EventEverySecond var1) {
      if (System.currentTimeMillis() - this.field14 > 300000L) {
         this.field14 = System.currentTimeMillis();
         ApiUtils.getApiResponseStringAsync("https://api.lunarclientprod.com/game/skyblock/lowest-bins", new RequestParameter[0])
            .thenAccept(
               var1x -> {
                  if (var1x != null) {
                     try {
                        JsonObject var2 = (JsonObject)ThreadModuleDump48.field22.fromJson(var1x, JsonObject.class);
                        if (var2.has("success") && var2.get("success").getAsBoolean() && var2.has("bins") && var2.get("bins").isJsonArray()) {
                           this.field11.clear();

                           for (JsonElement var5 : var2.get("bins").getAsJsonArray()) {
                              GuiRewindhandlersHandler2.Data3 var6 = (GuiRewindhandlersHandler2.Data3)ThreadModuleDump48.field22
                                 .fromJson(var5.getAsJsonObject(), GuiRewindhandlersHandler2.Data3.class);
                              this.field11.put(var6.field1, var6);
                           }
                        }
                     } catch (Exception var7) {
                     }
                  }
               }
            );
         ApiUtils.getApiResponseStringAsync("https://thirdpartycache.lunarclientprod.com/hypixel/ursa-minor/v2/bazaar", new RequestParameter[0])
            .thenAccept(
               var1x -> {
                  if (var1x != null) {
                     try {
                        JsonObject var2 = (JsonObject)ThreadModuleDump48.field22.fromJson(var1x, JsonObject.class);
                        if (var2.has("success") && var2.get("success").getAsBoolean() && var2.has("products") && var2.get("products").isJsonObject()) {
                           this.field12.clear();
                           JsonObject var3 = var2.get("products").getAsJsonObject();

                           for (Entry var5 : var3.entrySet()) {
                              GuiRewindhandlersHandler2.Data2 var6 = (GuiRewindhandlersHandler2.Data2)ThreadModuleDump48.field22
                                 .fromJson(((JsonElement)var5.getValue()).getAsJsonObject(), GuiRewindhandlersHandler2.Data2.class);
                              this.field12.put(var6.field1, var6);
                           }
                        }
                     } catch (Exception var7) {
                     }
                  }
               }
            );
      }
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22 method7() {
      return this.field7;
   }

   @Generated
   public Map<String, GuiRewindhandlersHandler2.Data3> method8() {
      return this.field11;
   }

   @Generated
   public Map<String, GuiRewindhandlersHandler2.Data2> method9() {
      return this.field12;
   }

   @Generated
   public List<Gui_2> method10() {
      return this.field13;
   }

   @Generated
   public long method11() {
      return this.field14;
   }

   public class Data {
      @SerializedName("amount")
      private final int field1;
      @SerializedName("pricePerUnit")
      private final float field2;
      @SerializedName("orders")
      private final int field3;

      public Data(int var1, float var2, int var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @SerializedName("amount")
      public int amount() {
         return this.field1;
      }

      @SerializedName("pricePerUnit")
      public float method1() {
         return this.field2;
      }

      @SerializedName("orders")
      public int method2() {
         return this.field3;
      }
   }

   public class Data2 {
      @SerializedName("product_id")
      private final String field1;
      @SerializedName("buy_summary")
      private final GuiRewindhandlersHandler2.Data[] field2;
      @SerializedName("sell_summary")
      private final GuiRewindhandlersHandler2.Data[] field3;
      @SerializedName("quick_status")
      private final GuiRewindhandlersHandler2.Data4 field4;

      public Data2(String var1, GuiRewindhandlersHandler2.Data[] var2, GuiRewindhandlersHandler2.Data[] var3, GuiRewindhandlersHandler2.Data4 var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      @SerializedName("product_id")
      public String method1() {
         return this.field1;
      }

      @SerializedName("buy_summary")
      public GuiRewindhandlersHandler2.Data[] method2() {
         return this.field2;
      }

      @SerializedName("sell_summary")
      public GuiRewindhandlersHandler2.Data[] method3() {
         return this.field3;
      }

      @SerializedName("quick_status")
      public GuiRewindhandlersHandler2.Data4 method4() {
         return this.field4;
      }
   }

   public class Data3 {
      @SerializedName("itemId")
      private final String field1;
      @SerializedName("latestPrice")
      private final double field2;
      @SerializedName("dailyAveragePrice")
      private final double field3;
      @SerializedName("lastSeen")
      private final String field4;

      public Data3(String var1, double var2, double var4, String var6) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var4;
         this.field4 = var6;
      }

      @SerializedName("itemId")
      public String itemId() {
         return this.field1;
      }

      @SerializedName("latestPrice")
      public double method1() {
         return this.field2;
      }

      @SerializedName("dailyAveragePrice")
      public double method2() {
         return this.field3;
      }

      @SerializedName("lastSeen")
      public String method3() {
         return this.field4;
      }
   }

   public class Data4 {
      @SerializedName("productId")
      private final String field1;
      @SerializedName("sellPrice")
      private final float field2;
      @SerializedName("sellVolume")
      private final int field3;
      @SerializedName("sellMovingWeek")
      private final int field4;
      @SerializedName("sellOrders")
      private final int field5;
      @SerializedName("buyPrice")
      private final float field6;
      @SerializedName("buyVolume")
      private final int field7;
      @SerializedName("buyMovingWeek")
      private final int field8;
      @SerializedName("buyOrders")
      private final int field9;

      public Data4(String var1, float var2, int var3, int var4, int var5, float var6, int var7, int var8, int var9) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
         this.field8 = var8;
         this.field9 = var9;
      }

      @SerializedName("productId")
      public String method1() {
         return this.field1;
      }

      @SerializedName("sellPrice")
      public float method2() {
         return this.field2;
      }

      @SerializedName("sellVolume")
      public int method3() {
         return this.field3;
      }

      @SerializedName("sellMovingWeek")
      public int method4() {
         return this.field4;
      }

      @SerializedName("sellOrders")
      public int method5() {
         return this.field5;
      }

      @SerializedName("buyPrice")
      public float method6() {
         return this.field6;
      }

      @SerializedName("buyVolume")
      public int method7() {
         return this.field7;
      }

      @SerializedName("buyMovingWeek")
      public int method8() {
         return this.field8;
      }

      @SerializedName("buyOrders")
      public int method9() {
         return this.field9;
      }
   }
}
