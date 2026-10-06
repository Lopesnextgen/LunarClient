package com.moonsworth.lunar.client.framework.feature.hypixelbedwars.bedwars;

import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Generated;

public abstract class Bedwars {
   private static final ExecutorService field1 = Executors.newCachedThreadPool(new DefaultThreadFactory("lunar-bedwars-stats-thread", true));
   public Bedwars.Data field2 = new Bedwars.Data();
   public Bedwars.Data field3 = new Bedwars.Data();
   public Bedwars.Data field4 = new Bedwars.Data();
   public int field5;
   public int field6;
   private long field7;
   private long gameTime;
   private long field8 = -1L;
   protected boolean field9 = false;
   protected String field10 = null;
   protected String field11 = null;
   protected boolean field12 = false;
   protected final List<String> field13 = new ArrayList<>();
   private final List<String> field14 = new ArrayList<>();
   private final List<String> field15 = new ArrayList<>();
   protected String playerName;
   protected boolean field16 = false;
   private long lastUpdate = 0L;

   public Bedwars() {
   }

   private void method1() {
      this.field15.clear();
      this.field15.addAll(this.field14);
      this.field14.clear();

      for (PlayerInfoBridge bridge2_332 : Ref.method3().bridge$getPlayer().bridge$getSendQueue().bridge$getPlayerInfoMap()) {
         String text3 = bridge2_332.bridge$getGameProfile().getName();
         if (!text3.equalsIgnoreCase(this.playerName)) {
            this.field14.add(text3);
         }
      }

      this.field15.removeAll(this.field14);
      this.field13.addAll(this.field15);
      this.field13.addAll(this.field14);
   }

   public void method2() {
      this.field7 = 0L;
      if (this.field8 > 0L) {
         long number1 = Ref.method3().bridge$getSystemTime();
         this.gameTime = this.gameTime + (number1 - this.field8);
         this.field8 = number1;
      }
   }

   public long method3(long number1) {
      return this.field7 + (this.field8 > 0L ? number1 - this.field8 : 0L);
   }

   public long method4(long number1) {
      return this.gameTime + (this.field8 > 0L ? number1 - this.field8 : 0L);
   }

   public long method5(long number1) {
      return this.field6 == 0 ? this.method4(number1) : this.field7 / this.field6;
   }

   public void method6() {
      if (this.field9) {
         this.field9 = false;
         this.field2 = new Bedwars.Data();
         this.gameTime = 0L;
      }

      this.field16 = false;
   }

   public void method7(String text1) {
      if (text1 == null) {
         if (this.field8 > 0L) {
            long number2 = Ref.method3().bridge$getSystemTime();
            this.field7 = this.field7 + (number2 - this.field8);
            this.gameTime = this.gameTime + (number2 - this.field8);
         }

         this.field8 = -1L;
         this.field12 = false;
      } else {
         this.field12 = true;
         this.field11 = text1;
         if (this.field10 != null && !this.field10.equals(this.field11)) {
            this.field10 = null;
            this.method9();
         }
      }
   }

   public void method8() {
      if (this.field10 != null) {
         this.field10 = null;
         this.method9();
      }

      if (this.field8 > 0L) {
         long number1 = Ref.method3().bridge$getSystemTime();
         this.field7 = this.field7 + (number1 - this.field8);
         this.gameTime = this.gameTime + (number1 - this.field8);
      }

      this.field8 = -1L;
      this.field11 = null;
      this.field12 = false;
   }

   private void method9() {
      this.field2 = new Bedwars.Data();
      this.gameTime = 0L;
      this.field3.field7++;
      this.field4.field7++;
      this.field5 = 0;
      this.field6++;
   }

   private void method10() {
      if (this.field11 != null && this.field10 == null) {
         for (ItemStackBridge bridgeextension_42 : Ref.method3().bridge$getPlayer().bridge$getInventory().bridge$getArmorInventory()) {
            if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty()) {
               this.field10 = this.field11;
               break;
            }
         }
      }
   }

   public void method11() {
      if (Ref.method7() != null) {
         this.method10();
         if (Ref.method8() != null && this.field12 && this.field10 != null) {
            if (this.field8 <= 0L) {
               this.field8 = Ref.method3().bridge$getSystemTime();
            }
         } else if (this.field8 > 0L) {
            this.field7 = this.field7 + (Ref.method3().bridge$getSystemTime() - this.field8);
            this.field8 = -1L;
         }
      }
   }

   public void method12(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (this.field12) {
         this.method10();
         if (this.field10 != null) {
            field1.submit(() -> {
               long number2 = Ref.method3().bridge$getSystemTime();
               if (number2 - this.lastUpdate > 1000L) {
                  this.lastUpdate = number2;
                  this.method1();
                  this.playerName = Ref.method3().bridge$getPlayer().bridge$getName();
                  this.update();
               }

               switch (this.method13(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC())) {
                  case FINAL_KILL:
                     this.field2.field1++;
                     this.field3.field1++;
                     this.field4.field1++;
                     break;
                  case FINAL_DEATH:
                     this.field3.field5++;
                     this.field4.field5++;
                     break;
                  case KILL:
                     this.field2.field2++;
                     this.field3.field2++;
                     this.field4.field2++;
                     break;
                  case DEATH:
                     this.field2.deaths++;
                     this.field3.deaths++;
                     this.field4.deaths++;
                     break;
                  case BED_BREAK:
                     this.field2.field3++;
                     this.field3.field3++;
                     this.field4.field3++;
                     break;
                  case BED_LOSS:
                     this.field2.field6++;
                     this.field3.field6++;
                     this.field4.field6++;
                     break;
                  case WIN:
                     this.field9 = true;
                     this.field3.field4++;
                     this.field4.field4++;
                     this.field5++;
                     this.field6++;
                     this.field10 = null;
                     this.field11 = null;
                     break;
                  case LOSS:
                     this.field9 = true;
                     this.field3.field7++;
                     this.field4.field7++;
                     this.field5 = 0;
                     this.field6++;
                     this.field10 = null;
                     this.field11 = null;
               }
            });
         }
      }
   }

   public void update() {
   }

   public abstract Bedwars.Type method13(String text1);

   @Generated
   public boolean method14() {
      return this.field16;
   }

   @Generated
   public void method15(boolean flag1) {
      this.field16 = flag1;
   }

   public static class Data {
      private int field1;
      private int field2;
      private int field3;
      private int field4;
      private int field5;
      private int deaths;
      private int field6;
      private int field7;

      public Data() {
      }

      @Generated
      public int method1() {
         return this.field1;
      }

      @Generated
      public int method2() {
         return this.field2;
      }

      @Generated
      public int method3() {
         return this.field3;
      }

      @Generated
      public int method4() {
         return this.field4;
      }

      @Generated
      public int method5() {
         return this.field5;
      }

      @Generated
      public int method6() {
         return this.deaths;
      }

      @Generated
      public int method7() {
         return this.field6;
      }

      @Generated
      public int method8() {
         return this.field7;
      }

      @Generated
      public void method9(int number1) {
         this.field1 = number1;
      }

      @Generated
      public void method10(int number1) {
         this.field2 = number1;
      }

      @Generated
      public void method11(int number1) {
         this.field3 = number1;
      }

      @Generated
      public void method12(int number1) {
         this.field4 = number1;
      }

      @Generated
      public void method13(int number1) {
         this.field5 = number1;
      }

      @Generated
      public void method14(int number1) {
         this.deaths = number1;
      }

      @Generated
      public void method15(int number1) {
         this.field6 = number1;
      }

      @Generated
      public void method16(int number1) {
         this.field7 = number1;
      }
   }

   public enum Type {
      RESET_GAME,
      FINAL_KILL,
      KILL,
      BED_BREAK,
      WIN,
      FINAL_DEATH,
      DEATH,
      BED_LOSS,
      LOSS,
      NONE;

      Type() {
      }
   }
}
