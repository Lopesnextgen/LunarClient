package com.moonsworth.lunar.client.config;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SettingsTreeAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SetOption;
import com.moonsworth.lunar.client.config.option.MapOption;
import com.moonsworth.lunar.client.config.option.SetOption.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.nameplate.Nameplate;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.UUID;
import lombok.Generated;

public class InternalSettings extends com.moonsworth.lunar.client.config.SettingsContainer {
   private final ToggleOption field2 = (ToggleOption)OptionFactory.method7("editedEmotes").method31();
   private final SetOption<Integer, IntSet> field3 = (SetOption<Integer, IntSet>)((Data)OptionFactory.method35(
            "knownEmotes", Nameplate.field20
         )
         .method2(new IntOpenHashSet()))
      .method31();
   private final ToggleOption field4 = (ToggleOption)OptionFactory.method7("editedSprays").method31();
   private final SetOption<Integer, IntSet> field5 = (SetOption<Integer, IntSet>)((Data)OptionFactory.method35(
            "knownSprays", Nameplate.field20
         )
         .method2(new IntOpenHashSet()))
      .method31();
   private final SetOption<Long, LongSet> field6 = (SetOption<Long, LongSet>)((Data)OptionFactory.method35(
            "knownCosmetics", Nameplate.field21
         )
         .method2(new LongOpenHashSet()))
      .method31();
   private final MapOption<Integer, Integer, Int2IntMap> field7 = (MapOption<Integer, Integer, Int2IntMap>)((com.moonsworth.lunar.client.config.option.MapOption.Data)OptionFactory.method38(
            "lunarPlusColor", Nameplate.field23
         )
         .method2(new Int2IntArrayMap()))
      .method31();

   @Override
   protected void method10(SettingsTreeAssembler var1) {
      var1.method11(new ClientOption[]{this.field2, this.field7, this.field6, this.field3});
   }

   public boolean method23() {
      Client var1 = ThreadModuleDump63.method4();
      SettingsManager var2 = var1.method41();
      if (!(Boolean)var2.method6().method58().get()) {
         return false;
      }

      int var3 = ((IntSet)var2.method9().method18().get()).size();
      return var3 < var1.method45().method24().size();
   }

   public boolean method15() {
      Client var1 = ThreadModuleDump63.method4();
      SettingsManager var2 = var1.method41();
      if (!(Boolean)var2.method6().method58().get()) {
         return false;
      }

      int var3 = ((IntSet)var2.method9().method20().get()).size();
      return var3 < var1.method46().method41().size();
   }

   public boolean method16() {
      Client var1 = ThreadModuleDump63.method4();
      SettingsManager var2 = var1.method41();
      if (!(Boolean)var2.method6().method58().get()) {
         return false;
      }

      int var3 = ((LongSet)var2.method9().method21().get()).size();
      return var3 < var1.method53().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size();
   }

   @Override
   public String method5() {
      return "internal.json";
   }

   @Override
   public boolean method11() {
      return true;
   }

   public int method7(UUID var1) {
      return var1 == null ? -1 : ((Int2IntMap)this.field7.get()).get(var1.hashCode());
   }

   public void method8(UUID var1, int var2) {
      int var3 = var2 | 0xFF000000;
      ThreadModuleDump63.method4().method53().method63().computeIfPresent(var1, (var1x, var2x) -> var2x.method2(var3));
      this.field7.method1(var1.hashCode(), var2);
   }

   @Generated
   public ToggleOption method17() {
      return this.field2;
   }

   @Generated
   public SetOption<Integer, IntSet> method18() {
      return this.field3;
   }

   @Generated
   public ToggleOption method19() {
      return this.field4;
   }

   @Generated
   public SetOption<Integer, IntSet> method20() {
      return this.field5;
   }

   @Generated
   public SetOption<Long, LongSet> method21() {
      return this.field6;
   }

   @Generated
   public MapOption<Integer, Integer, Int2IntMap> method22() {
      return this.field7;
   }
}
