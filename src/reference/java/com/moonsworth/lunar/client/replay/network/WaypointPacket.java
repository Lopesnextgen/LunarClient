package com.moonsworth.lunar.client.replay.network;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public class WaypointPacket extends ReplayPacket {
   private Set<Waypoint> field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = new HashSet<>();
      int number2 = bytebufloader1.readVarInt();

      for (int index3 = 0; index3 < number2; index3++) {
         Waypoint guihandler24 = new Waypoint();
         guihandler24.setDistance(bytebufloader1.readDouble());
         guihandler24.setName(bytebufloader1.readString());
         guihandler24.method20(Vec3Bridge.method2(bytebufloader1.readDouble(), bytebufloader1.readDouble(), bytebufloader1.readDouble()));
         guihandler24.method21(bytebufloader1.readString());
         guihandler24.setDimension(bytebufloader1.readVarInt());
         guihandler24.method23(bytebufloader1.method9(SkyblockIsland.class));
         guihandler24.method24(bytebufloader1.readBoolean());
         guihandler24.method25(bytebufloader1.readBoolean());
         String text5 = bytebufloader1.readString();
         guihandler24.method26(text5.isEmpty() ? null : text5);
         guihandler24.setVisible(bytebufloader1.readBoolean());
         boolean flag6 = bytebufloader1.readBoolean();
         boolean flag7 = bytebufloader1.readBoolean();
         guihandler24.method27(bytebufloader1.readBoolean());
         guihandler24.method28(bytebufloader1.readString());
         guihandler24.method29(bytebufloader1.readBoolean());
         guihandler24.method30(bytebufloader1.readLong());
         String text8 = bytebufloader1.readString();
         boolean flag9 = false;
         boolean flag10 = false;
         if (bytebufloader1.isReadable(1)) {
            flag9 = bytebufloader1.readBoolean();
            flag10 = bytebufloader1.readBoolean();
         }

         guihandler24.method34(new GuiLoader(flag6, PhosphorIcon.PI_MAP_PIN_SOLID, flag9, flag7, flag10));
         guihandler24.method46().method4().load((JsonObject)LunarConstants.field22.fromJson(text8, JsonObject.class));
         this.field1.add(guihandler24);
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1.size());

      for (Waypoint guihandler23 : this.field1) {
         bytebufloader1.writeDouble(guihandler23.getDistance());
         bytebufloader1.method1(guihandler23.getName());
         bytebufloader1.writeDouble(guihandler23.method35().bridge$xCoord());
         bytebufloader1.writeDouble(guihandler23.method35().bridge$yCoord());
         bytebufloader1.writeDouble(guihandler23.method35().bridge$zCoord());
         bytebufloader1.method1(guihandler23.getWorld());
         bytebufloader1.method11(guihandler23.getDimension());
         bytebufloader1.method10(guihandler23.method36());
         bytebufloader1.writeBoolean(guihandler23.method37());
         bytebufloader1.writeBoolean(guihandler23.method38());
         bytebufloader1.method1(guihandler23.method39() != null ? guihandler23.method39() : "");
         bytebufloader1.writeBoolean(guihandler23.isVisible());
         bytebufloader1.writeBoolean(guihandler23.method46().isShowBeam());
         bytebufloader1.writeBoolean(guihandler23.method46().isShowText());
         bytebufloader1.writeBoolean(guihandler23.method40());
         bytebufloader1.method1(guihandler23.getServer());
         bytebufloader1.writeBoolean(guihandler23.method41());
         bytebufloader1.writeLong(guihandler23.method42());
         JsonObject json4 = new JsonObject();
         guihandler23.method46().method4().HRICOROOOCCOCOROCRHHCRRIRCOICO(json4);
         bytebufloader1.method1(json4.toString());
         bytebufloader1.writeBoolean(guihandler23.method46().isHighlightBlock());
         bytebufloader1.writeBoolean(guihandler23.method46().isShowDistance());
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method4().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH().clear();
      Ref.method4().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH().addAll(this.field1);
   }

   @Generated
   public WaypointPacket(Set<Waypoint> set1) {
      this.field1 = set1;
   }

   @Generated
   public WaypointPacket() {
   }

   @Generated
   public Set<Waypoint> method4() {
      return this.field1;
   }
}
