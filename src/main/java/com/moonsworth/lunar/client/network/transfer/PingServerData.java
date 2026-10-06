package com.moonsworth.lunar.client.network.transfer;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import lombok.Generated;

public class PingServerData {
   private volatile boolean completed;
   private volatile boolean field1;
   private final long field2;
   private ServerDataBridge field3;

   @Generated
   public boolean isCompleted() {
      return this.completed;
   }

   @Generated
   public boolean method2() {
      return this.field1;
   }

   @Generated
   public long method3() {
      return this.field2;
   }

   @Generated
   public ServerDataBridge method4() {
      return this.field3;
   }

   @Generated
   public void setCompleted(boolean flag1) {
      this.completed = flag1;
   }

   @Generated
   public void method6(boolean flag1) {
      this.field1 = flag1;
   }

   @Generated
   public void method7(ServerDataBridge bridge3_191) {
      this.field3 = bridge3_191;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof PingServerData nameplate22)) {
         return false;
      } else {
         if (!nameplate22.canEqual(this)) {
            return false;
         }

         if (this.isCompleted() != nameplate22.isCompleted()) {
            return false;
         }

         if (this.method2() != nameplate22.method2()) {
            return false;
         }

         if (this.method3() != nameplate22.method3()) {
            return false;
         }

         ServerDataBridge bridge3_193 = this.method4();
         ServerDataBridge bridge3_194 = nameplate22.method4();
         return bridge3_193 == null ? bridge3_194 == null : bridge3_193.equals(bridge3_194);
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof PingServerData;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.isCompleted() ? 79 : 97);
      number2 = number2 * 59 + (this.method2() ? 79 : 97);
      long number3 = this.method3();
      number2 = number2 * 59 + (int)(number3 >>> 32 ^ number3);
      ServerDataBridge bridge3_195 = this.method4();
      return number2 * 59 + (bridge3_195 == null ? 43 : bridge3_195.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "PingServerData(completed="
         + this.isCompleted()
         + ", timedOut="
         + this.method2()
         + ", endTime="
         + this.method3()
         + ", serverDataBridge="
         + this.method4()
         + ")";
   }

   @Generated
   public PingServerData(boolean flag1, boolean flag, long number3, ServerDataBridge bridge3_195) {
      this.completed = flag1;
      this.field1 = flag;
      this.field2 = number3;
      this.field3 = bridge3_195;
   }
}
