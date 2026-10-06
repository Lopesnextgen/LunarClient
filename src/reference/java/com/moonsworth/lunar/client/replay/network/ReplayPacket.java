package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class ReplayPacket {
   public ReplayPacket() {
   }

   public abstract void method1(ByteBufLoader bytebufloader1);

   public abstract void method2(ByteBufLoader bytebufloader1);

   public abstract void method3(ReplayContext nameplate41);

   public ReplayPacket method4(ReplayContext nameplate41) {
      return null;
   }

   public String name() {
      return this.getClass().getSimpleName();
   }

   public String data() {
      return this.method5(this);
   }

   protected String method5(Object obj1) {
      StringBuilder builder2 = new StringBuilder();
      ArrayList list3 = new ArrayList<>(List.of(obj1.getClass().getFields()));

      for (Field field7 : obj1.getClass().getDeclaredFields()) {
         if (!list3.contains(field7)) {
            list3.add(field7);
         }
      }

      for (Field field10 : list3) {
         field10.setAccessible(true);

         try {
            if (!field10.getName().toLowerCase().contains("codec")) {
               if (!builder2.isEmpty()) {
                  builder2.append(", ");
               }

               Object obj11 = field10.get(obj1);
               if (obj11 != null && obj11.toString().length() > 50) {
                  obj11 = "LARGE_DATA";
               }

               builder2.append(field10.getName()).append("=").append(obj11);
            }
         } catch (IllegalAccessException illegalaccessexception8) {
            builder2.append(field10.getName()).append("=ERROR");
         }
      }

      return builder2.toString();
   }
}
