package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import lombok.Generated;

public enum DungeonFloor {
   NONE(-1, -1.0F, -1, null, ""),
   E(0, 0.3F, 600, null, "Entrance"),
   F1(1, 0.3F, 600, pos(-72, 146, -40), "Floor One"),
   F2(2, 0.4F, 600, pos(-40, 99, -40), "Floor Two"),
   F3(3, 0.5F, 600, pos(-40, 118, -40), "Floor Three"),
   F4(4, 0.6F, 720, pos(-40, 112, -40), "Floor Four"),
   F5(5, 0.7F, 600, pos(-40, 112, -8), "Floor Five"),
   F6(6, 0.85F, 720, pos(-40, 110, -8), "Floor Six"),
   F7(7, 1.0F, 840, pos(-8, 254, -8), "Floor Seven"),
   M1(1, 1.0F, 480, pos(-72, 146, -40), "Master Floor One"),
   M2(2, 1.0F, 480, pos(-40, 99, -40), "Master Floor Two"),
   M3(3, 1.0F, 480, pos(-40, 118, -40), "Master Floor Three"),
   M4(4, 1.0F, 480, pos(-40, 112, -40), "Master Floor Four"),
   M5(5, 1.0F, 480, pos(-40, 112, -8), "Master Floor Five"),
   M6(6, 1.0F, 600, pos(-40, 110, -8), "Master Floor Six"),
   M7(7, 1.0F, 840, pos(-8, 254, -8), "Master Floor Seven");

   public static final int MAX_BOSS_FLOOR = 7;
   private final int number;
   private final float requiredSecretPercentage;
   private final int timeLimit;
   private final Vec3iBridge bossRoomPos;
   private final String prettyName;

   private static Vec3iBridge pos(int number0, int value, int value2) {
      return Bridge.method8().method4(number0, value, value2);
   }

   public static boolean isValidBossFloor(int number0) {
      return number0 >= 1 && number0 <= 7;
   }

   public boolean isBossFloor() {
      return this.number >= 1;
   }

   public static String getBossMessage(int number0) {
      return switch (number0) {
         case 1 -> "[BOSS] Bonzo: Gratz for making it this far, but I'm basically unbeatable.";
         case 2 -> "[BOSS] Scarf: This is where the journey ends for you, Adventurers.";
         case 3 -> "[BOSS] The Professor: I was burdened with terrible news recently...";
         case 4 -> "[BOSS] Thorn: Welcome Adventurers! I am Thorn, the Spirit! And host of the Vegan Trials!";
         case 5 -> "[BOSS] Livid: Welcome, you've arrived right on time. I am Livid, the Master of Shadows.";
         case 6 -> "[BOSS] Sadan: So you made it all the way here... Now you wish to defy me? Sadan?!";
         case 7 -> "[BOSS] Maxor: WELL! WELL! WELL! LOOK WHO'S HERE!";
         default -> throw new IllegalArgumentException("not a valid boss floor number: " + number0);
      };
   }

   @Generated
   public int getNumber() {
      return this.number;
   }

   @Generated
   public float getRequiredSecretPercentage() {
      return this.requiredSecretPercentage;
   }

   @Generated
   public int getTimeLimit() {
      return this.timeLimit;
   }

   @Generated
   public Vec3iBridge getBossRoomPos() {
      return this.bossRoomPos;
   }

   @Generated
   public String getPrettyName() {
      return this.prettyName;
   }

   @Generated
   DungeonFloor(int value, float value2, int value3, Vec3iBridge horsestats206, String text) {
      this.number = value;
      this.requiredSecretPercentage = value2;
      this.timeLimit = value3;
      this.bossRoomPos = horsestats206;
      this.prettyName = text;
   }
}
