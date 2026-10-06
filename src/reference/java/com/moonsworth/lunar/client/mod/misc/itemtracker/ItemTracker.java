package com.moonsworth.lunar.client.mod.misc.itemtracker;

import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_24;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.ArmorHudOptionWidget;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.itemtracker.Itemtracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.event.player.InventoryUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap.Entry;
import java.util.List;
import java.util.Locale;
import lombok.Generated;

public class ItemTracker extends AbstractFeature {
   private static final int field8 = 42;
   private final Object2ObjectLinkedOpenHashMap<String, ItemTracker.Data2> field9 = new Object2ObjectLinkedOpenHashMap();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("textShadow").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("skyblockOnly").method31();
   private final ListOption<String> field12 = (ListOption<String>)OptionFactory.<String>method31("itemBlacklist", Codec.STRING.listOf())
      .method5(var1 -> {
         this.field9.clear();
         this.field14 = null;
         this.field19 = true;
      })
      .method31();
   private final Object2ObjectLinkedOpenHashMap<String, String> field13 = new Object2ObjectLinkedOpenHashMap<String, String>(42, 0.25F) {
      public void rehash(int var1) {
      }
   };
   private Itemtracker field14;
   private Int2BooleanOpenHashMap field15;
   private int field16;
   private boolean field17 = true;
   private int field18 = 10;
   private boolean field19;

   public ItemTracker() {
      super(false);
      this.method5(Framework.field1, new ItemTracker.Data());
      this.handle(ServerJoinEvent.class, var1 -> this.reset());
      this.handle(EventWorldLifecycle.EventWorldChanged.class, var1 -> this.reset());
      this.handle(EventClientTick.class, this::method2);
      this.handle(InventoryUpdateEvent.class, var1 -> this.field19 = true);
   }

   private void reset() {
      this.field9.clear();
      this.field13.clear();
      this.field14 = null;
      this.field18 = 20;
      this.field19 = false;
   }

   @Override
   public String getId() {
      return "ITEM_TRACKER";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      FloatOption var2 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2("popupDurationSec")
               .method4(5.0F))
            .method8(0.1F, 30.0F))
         .method31();
      ListOption var3 = (ListOption)((ListOption.Data)OptionFactory.method31("ignoreSlots", Codec.STRING.listOf())
            .method7(ArmorHudOptionWidget::new))
         .method31();
      var1.method11(new ClientOption[]{this.field10});
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field11})).method6(1);
      var1.method11(new ClientOption[]{var2, this.field12, var3});
      var2.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> this.field16 = Math.round(var1x * 20.0F));
      if (this.field15 == null) {
         this.field15 = new Int2BooleanOpenHashMap(42);
      }

      var3.CICORRHIOIIOORRRICCORIOIOCIHII(var2x -> {
         for (String var4 : var2x) {
            this.field15.put(this.method7(var4), ((List)var3.get()).contains(var4));
         }

         this.field9.clear();
         this.field14 = null;
         this.field19 = true;
      });
   }

   private void method2(EventClientTick var1) {
      if (!this.field11.isHidden() && this.field11.get() && !Click3.hasIsland()) {
         this.field17 = true;
         this.field9.clear();
      } else {
         this.field17 = false;
         if (this.field18 > 0) {
            this.field18--;
         } else {
            Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
            if (var2 != null) {
               ObjectBidirectionalIterator var3 = this.field9.object2ObjectEntrySet().fastIterator();

               while (var3.hasNext()) {
                  Entry var4 = (Entry)var3.next();
                  ItemTracker.Data2 var5 = (ItemTracker.Data2)var4.getValue();
                  if (var5 != null && var5.method2()) {
                     var3.remove();
                  }
               }

               if (this.mc.bridge$getCurrentScreen() == null) {
                  if (this.field19) {
                     this.method4(this.method5(var2.bridge$getInventory()));
                     this.field19 = false;
                  }
               }
            }
         }
      }
   }

   private void method3(String var1, int var2) {
      if (this.field9.containsKey(var1)) {
         if (((ItemTracker.Data2)this.field9.get(var1)).method1(var2, this.field16)) {
            this.field9.remove(var1);
         }
      } else {
         this.field9.put(var1, new ItemTracker.Data2(var2, this.field16));
      }
   }

   private void method4(Itemtracker var1) {
      if (this.field14 == null) {
         this.field14 = var1;
      } else {
         Itemtracker var2 = new Itemtracker(var1);

         for (String var4 : this.field14.keySet()) {
            int var5 = this.field14.method2(var4, this.field15);
            int var6 = var2.method2(var4, this.field15);
            var2.remove(var4);
            if (var5 != var6 && this.field14.method1(var4) != var1.method1(var4)) {
               this.method3(var4, var6 - var5);
            }
         }

         for (String var8 : var2.keySet()) {
            int var9 = var2.method2(var8, this.field15);
            if (var9 != 0) {
               this.method3(var8, var9);
            }
         }

         this.field14 = var1;
      }
   }

   private Itemtracker method5(Bridge_24 var1) {
      int var2 = 1;
      Itemtracker var3 = new Itemtracker();
      var2 = this.method6(var3, var1.bridge$getMainInventory(), var2);
      var2 = this.method6(var3, var1.bridge$getOffhandInventory(), var2);
      this.method6(var3, var1.bridge$getArmorInventory(), var2);
      return var3;
   }

   private int method6(Itemtracker var1, List<ItemStackBridge> var2, int var3) {
      for (ItemStackBridge var5 : var2) {
         if (var5 != null) {
            String var6 = var5.bridge$getRawDisplayName().toLowerCase(Locale.ROOT);
            var1.method3(var6, var3, var5.bridge$getStackSize());
            if (!this.field13.containsKey(var6)) {
               if (this.field13.size() == 42) {
                  this.field13.removeFirst();
               }

               this.field13.put(var6, var5.bridge$getDisplayName());
            }
         }

         var3++;
      }

      return var3;
   }

   private int method7(String var1) {
      String[] var2 = var1.split(":", 3);

      return switch (var2[0]) {
         case "hotbar" -> var2.length > 1 ? Integer.parseInt(var2[1]) : -1;
         case "inv" -> var2.length > 2 ? Integer.parseInt(var2[1]) * 9 + Integer.parseInt(var2[2]) : -1;
         case "offhand" -> 37;
         case "boots" -> 38;
         case "leggings" -> 39;
         case "chestplate" -> 40;
         case "helmet" -> 41;
         default -> -1;
      };
   }

   @Override
   public void load(JsonObject var1) {
      this.reset();
      this.field15.clear();
      this.field12.get().clear();
      super.load(var1);
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT);
         this.method58(90.0F, 25.0F);
      }

      @Override
      public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         if (var4) {
            MixinHelper_4 var11 = var1.method2();
            var11.method19(ThreadModuleDump63.method10(), "§7[§a+§7]§a 1 §fCobblestone", var2, var3, -16711936, ItemTracker.this.field10.get());
            var11.method19(ThreadModuleDump63.method10(), "§7[§c-§7]§c 1 §fCobblestone", var2, var3 + 10.0F, -65536, ItemTracker.this.field10.get());
         } else {
            int var5 = 0;
            int var6 = this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH().name().toLowerCase(Locale.ROOT).contains("bottom_") ? -10 : 10;
            ObjectBidirectionalIterator var7 = ItemTracker.this.field9.object2ObjectEntrySet().iterator();

            while (var7.hasNext()) {
               Entry var8 = (Entry)var7.next();
               int var9 = ((ItemTracker.Data2)var8.getValue()).getAmount();
               String var10 = (String)var8.getKey();
               if (this.method5(var10)) {
                  if (ItemTracker.this.field13.containsKey(var10)) {
                     var10 = (String)ItemTracker.this.field13.get(var10);
                  } else {
                     var10 = this.method3(var10, 3);
                  }

                  var1.method2()
                     .method19(
                        ThreadModuleDump63.method10(),
                        var9 > 0 ? "§7[§a+§7]§a " + this.method4(var9) + " §f" + var10 : "§7[§c-§7]§c " + this.method4(var9) + " §f" + var10,
                        var2,
                        var3 + var5 * var6,
                        var9 > 0 ? -16711936 : -65536,
                        ItemTracker.this.field10.get()
                     );
                  if (++var5 > 5) {
                     break;
                  }
               }
            }
         }
      }

      @Override
      public boolean method4(boolean var1) {
         return var1 || !ItemTracker.this.field17 && !ItemTracker.this.field9.isEmpty();
      }

      private String method3(String var1, int var2) {
         String[] var3 = var1.split(" ");
         StringBuilder var4 = new StringBuilder();

         for (String var8 : var3) {
            if (var8.length() < var2) {
               var4.append(var8).append(" ");
            } else {
               var4.append(Character.toUpperCase(var8.charAt(0))).append(var8.substring(1)).append(" ");
            }
         }

         return var4.toString().trim();
      }

      private String method4(int var1) {
         var1 = Math.abs(var1);
         if (var1 < 1000) {
            return var1 + "";
         } else {
            return var1 < 1000000 ? String.format("%.2fK", var1 / 1000.0F) : String.format("%.2fM", var1 / 1000000.0F);
         }
      }

      private boolean method5(String var1) {
         return !ItemTracker.this.field12.get().contains(var1) && !var1.equals("air");
      }
   }

   private static class Data2 {
      private int amount;
      private int duration;

      public boolean method1(int var1, int var2) {
         this.duration = var2;
         return (this.amount += var1) == 0;
      }

      public boolean method2() {
         return --this.duration == 0;
      }

      @Generated
      public Data2(int var1, int var2) {
         this.amount = var1;
         this.duration = var2;
      }

      @Generated
      public int getAmount() {
         return this.amount;
      }

      @Generated
      public int getDuration() {
         return this.duration;
      }
   }
}
