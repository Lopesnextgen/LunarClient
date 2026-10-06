package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public class Nameplate2Iterator2 extends Nameplate2 {
   private Set<GuiHandler2> field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = new HashSet<>();
      int var2 = var1.readVarInt();

      for (int var3 = 0; var3 < var2; var3++) {
         GuiHandler2 var4 = new GuiHandler2();
         var4.setDistance(var1.readDouble());
         var4.setName(var1.readString());
         var4.method20(Vec3Bridge.method2(var1.readDouble(), var1.readDouble(), var1.readDouble()));
         var4.method21(var1.readString());
         var4.setDimension(var1.readVarInt());
         var4.method23(var1.method9(Gui2Extension3.class));
         var4.method24(var1.readBoolean());
         var4.method25(var1.readBoolean());
         String var5 = var1.readString();
         var4.method26(var5.isEmpty() ? null : var5);
         var4.setVisible(var1.readBoolean());
         boolean var6 = var1.readBoolean();
         boolean var7 = var1.readBoolean();
         var4.method27(var1.readBoolean());
         var4.method28(var1.readString());
         var4.method29(var1.readBoolean());
         var4.method30(var1.readLong());
         String var8 = var1.readString();
         boolean var9 = false;
         boolean var10 = false;
         if (var1.isReadable(1)) {
            var9 = var1.readBoolean();
            var10 = var1.readBoolean();
         }

         var4.method34(new GuiLoader(var6, PhosphorIconLegacy.PI_MAP_PIN_SOLID, var9, var7, var10));
         var4.method46().method4().load((JsonObject)ThreadModuleDump48.field22.fromJson(var8, JsonObject.class));
         this.field1.add(var4);
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1.size());

      for (GuiHandler2 var3 : this.field1) {
         var1.writeDouble(var3.getDistance());
         var1.method1(var3.getName());
         var1.writeDouble(var3.method35().bridge$xCoord());
         var1.writeDouble(var3.method35().bridge$yCoord());
         var1.writeDouble(var3.method35().bridge$zCoord());
         var1.method1(var3.getWorld());
         var1.method11(var3.getDimension());
         var1.method10(var3.method36());
         var1.writeBoolean(var3.method37());
         var1.writeBoolean(var3.method38());
         var1.method1(var3.method39() != null ? var3.method39() : "");
         var1.writeBoolean(var3.isVisible());
         var1.writeBoolean(var3.method46().isShowBeam());
         var1.writeBoolean(var3.method46().isShowText());
         var1.writeBoolean(var3.method40());
         var1.method1(var3.getServer());
         var1.writeBoolean(var3.method41());
         var1.writeLong(var3.method42());
         JsonObject var4 = new JsonObject();
         var3.method46().method4().load(var4);
         var1.method1(var4.toString());
         var1.writeBoolean(var3.method46().isHighlightBlock());
         var1.writeBoolean(var3.method46().isShowDistance());
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method4().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH().clear();
      ThreadModuleDump63.method4().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH().addAll(this.field1);
   }

   @Generated
   public Nameplate2Iterator2(Set<GuiHandler2> var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Iterator2() {
   }

   @Generated
   public Set<GuiHandler2> method4() {
      return this.field1;
   }
}
