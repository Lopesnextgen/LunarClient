package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_61;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.render.turbo.PathEntity;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationTaskEntry;
import com.moonsworth.lunar.client.cosmetics.molang.MolangRuntime;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.floats.FloatArraySet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;
import org.joml.Vector3d;
import com.moonsworth.lunar.client.inactive.Inactive;

public class EmoteDefinition implements Bridge_61, PathEntity {
   private final CosmeticMetadata field1;
   private final EmoteModel field2;
   @NotNull
   private final Bridge5_11 field3;
   private final MolangRuntime field4;
   private final Inactive field5;
   private final Vector3d field6 = new Vector3d();
   private final Vector3d field7 = new Vector3d();
   private final Vector3d field8 = new Vector3d();
   private final Random field9;
   private float yaw;
   private float field10;
   private float field11;
   @Nullable
   private Vector3d field12 = null;
   private float field13 = Float.MAX_VALUE;
   private Horsestats20Extension2 field14;
   private Bridge2_17 field15;
   private double field16;
   private int field17;
   private float fallDistance;
   private boolean onGround;
   private int field18;
   private boolean field19 = true;
   private int field20;
   @Nullable
   private AnimationTaskEntry field21;
   @Nullable
   private AnimationTaskEntry field22;

   public EmoteDefinition(@NotNull Bridge5_11 var1, CosmeticMetadata var2, EmoteModel var3, MolangRuntime var4, Inactive var5) {
      this.field1 = var2;
      this.field2 = var3;
      this.field3 = var1;
      this.field4 = var4;
      this.field5 = var5;
      this.field9 = new Random(0L);
      this.method1(true);
   }

   public void method1(boolean var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null) {
         long var3 = var2.bridge$getGameTime() >> 7;
         if (!var1 && var3 % 10L != 5L) {
            return;
         }

         this.field9.setSeed(Objects.hash(this.field2.method9(), var3, this.field3.hashCode()));
      } else if (var1) {
         this.field9.setSeed(ThreadLocalRandom.current().nextLong());
      }
   }

   public void method5() {
      this.fallDistance = 0.0F;
   }

   public void method3(PathFilter var1) {
      if (this.field19) {
         if (ThreadModuleDump63.method8() != null
            && ThreadModuleDump63.method8().bridge$getGameTime() % 4L == 0L
            && this.method25(var1, ThreadModuleDump63.method8(), this.field3, 0, 4, 2)) {
            this.field19 = false;
         }
      } else {
         this.field17++;
         this.method1(false);
         if (this.field18 > 0) {
            this.field18--;
         }

         this.field10 = this.yaw;
         if (this.field13 != Float.MAX_VALUE) {
            float var2 = wrapDegrees(this.field13 - this.yaw);
            if (Math.abs(var2) > 0.001F) {
               float var3 = java.lang.Math.copySign(this.field11, var2);
               if (Math.abs(var3) >= Math.abs(var2)) {
                  this.yaw = wrapDegrees(this.field13);
                  this.field13 = Float.MAX_VALUE;
               } else {
                  this.yaw = wrapDegrees(this.yaw + var3);
               }
            } else {
               this.yaw = wrapDegrees(this.field13);
               this.field13 = Float.MAX_VALUE;
            }
         }

         if (this.field12 != null) {
            Vector3d var42 = new Vector3d(this.field12).sub(this.field6);
            if (this.field5.method9()) {
               double var44 = Math.sqrt(var42.x * var42.x + var42.z * var42.z);
               if (var44 > 0.1) {
                  this.field8.x = var42.x / var44 * this.field16;
                  this.field8.z = var42.z / var44 * this.field16;
               } else if (var44 <= 1.0E-5) {
                  this.field12 = null;
               }
            } else {
               this.field8.set(var42.normalize().mul(this.field16));
            }
         }

         Itemcounter6Extension var43 = ThreadModuleDump63.method8();
         if (var43 == null || !var43.bridge$isBlockLoaded(this.field14)) {
            this.field19 = true;
         }

         if (!this.field5.method9()) {
            this.method9(this.field8);
            this.field8.set(0.0);
         } else {
            switch (this.field5.method12()) {
               case GROUND:
                  if (!this.method6()) {
                     this.field8.y -= 0.08;
                  } else {
                     this.method5(var43);
                  }
                  break;
               case FLYING:
                  if (this.method6()) {
                     this.method5(var43);
                  }
            }

            Vector3d var45 = this.field8;
            double var4 = var45.lengthSquared();
            if (var4 > 1.0E-6 || this.field8.lengthSquared() - var4 < 1.0E-6) {
               if (var43 == null) {
                  this.method9(var45);
               } else {
                  double var6 = var45.x;
                  double var8 = var45.y;
                  double var10 = var45.z;
                  AxisAlignedBBBridge var12 = this.method7();
                  AxisAlignedBBBridge var13 = var12.bridge$expand(var6, var8, var10);
                  if (ThreadModuleDump63.MC_VERSION >= 1 && ThreadModuleDump63.MC_VERSION <= 5) {
                     var13 = var13.bridge$expand(0.0, -1.0, 0.0);
                     if (ThreadModuleDump63.MC_VERSION == 1) {
                        var13 = var13.method11(0.5);
                     }
                  }

                  List var14 = var43.bridge$getBlockCollisionBoxes(null, var13);
                  double var15 = var6;
                  double var17 = var8;
                  double var19 = var10;

                  for (AxisAlignedBBBridge var22 : var14) {
                     var8 = var22.bridge$calculateYOffset(var12, var8);
                  }

                  var12 = var12.bridge$offset(0.0, var8, 0.0);

                  for (AxisAlignedBBBridge var52 : var14) {
                     var6 = var52.bridge$calculateXOffset(var12, var6);
                  }

                  var12 = var12.bridge$offset(var6, 0.0, 0.0);

                  for (AxisAlignedBBBridge var53 : var14) {
                     var10 = var53.bridge$calculateZOffset(var12, var10);
                  }

                  var12 = var12.bridge$offset(0.0, 0.0, var10);
                  double var51 = this.bridge$getWidth() * 0.5;
                  double var23 = var12.bridge$getMinX() + var51;
                  double var25 = var12.bridge$getMinY();
                  double var27 = var12.bridge$getMinZ() + var51;
                  this.method7(var23, var25, var27);
                  boolean var29 = var17 != var8;
                  boolean var30 = var15 != var6 || var19 != var10;
                  if (var29) {
                     this.field8.y = 0.0;
                     this.onGround = var17 <= 0.0;
                     if (this.onGround) {
                        this.method5();
                     }
                  } else {
                     this.onGround = false;
                     if (var8 < 0.0) {
                        this.fallDistance += (float)(-var8);
                     }
                  }

                  if (var30 && this.field12 != null) {
                     if (this.field18 != 0 || !this.field5.method11() || !(this.field12.y >= this.field6.y + 0.1) || !this.onGround && !this.method6()) {
                        if (this.method3() > 0.0F && this.onGround) {
                           AxisAlignedBBBridge var31 = var12.bridge$expand(this.field8.x, this.method3(), this.field8.z);
                           List var32 = var43.bridge$getBlockCollisionBoxes(null, var31);
                           float[] var33 = this.method4(var31, var32, this.method3());

                           for (float var37 : var33) {
                              AxisAlignedBBBridge var38 = var12.bridge$offset(this.field8.x, var37, this.field8.z);
                              boolean var39 = true;

                              for (AxisAlignedBBBridge var41 : var32) {
                                 if (var38.bridge$intersectsWith(var41)) {
                                    var39 = false;
                                    break;
                                 }
                              }

                              if (var39) {
                                 this.method7(this.field6.x + this.field8.x, this.field6.y + var37, this.field6.z + this.field8.z);
                                 break;
                              }
                           }
                        }
                     } else {
                        this.method18();
                     }
                  }

                  if (var15 != var6) {
                     this.field8.x = 0.0;
                  }

                  if (var19 != var10) {
                     this.field8.z = 0.0;
                  }
               }
            }

            this.field8.mul(this.field5.method6());
            if (this.field8.length() <= 1.0E-5) {
               this.field8.set(0.0);
            }
         }
      }
   }

   private float[] method4(AxisAlignedBBBridge var1, List<AxisAlignedBBBridge> var2, float var3) {
      FloatArraySet var4 = new FloatArraySet(2);

      for (AxisAlignedBBBridge var6 : var2) {
         float var7 = (float)(var6.bridge$getMaxY() - var1.bridge$getMinY());
         if (var7 > 0.0F && var7 <= var3) {
            var4.add(var7);
         }
      }

      return var4.toFloatArray();
   }

   private void method5(Itemcounter6 var1) {
      if (var1 != null && this.field14 != null && var1.method4(this.field14.bridge$above()).bridge$isWater()) {
         this.field8.y += 0.02;
      } else if (this.field6.y - (int)this.field6.y <= 1.0F - this.bridge$getEyeHeight()) {
         this.field8.y += 0.005;
      }
   }

   public void method6(float var1) {
      this.field13 = wrapDegrees(var1);
   }

   public void method7(double var1, double var3, double var5) {
      if (this.field6.x != var1 || this.field6.y != var3 || this.field6.z != var5 || this.field14 == null) {
         this.field7.set(this.field6);
         this.field6.set(var1, var3, var5);
         this.method10(var1, var3, var5);
      } else if (this.field7.x != var1 || this.field7.y != var3 || this.field7.z != var5) {
         this.field7.set(var1, var3, var5);
      }
   }

   public void setPos(double var1, double var3, double var5) {
      if (this.field6.x != var1 || this.field6.y != var3 || this.field6.z != var5 || this.field14 == null) {
         this.field6.set(var1, var3, var5);
         this.field7.set(this.field6);
         this.method10(var1, var3, var5);
      }
   }

   public void method8(double var1, double var3, double var5) {
      this.setPos(this.field6.x + var1, this.field6.y + var3, this.field6.z + var5);
   }

   public void method9(Vector3d var1) {
      this.setPos(this.field6.x + var1.x, this.field6.y + var1.y, this.field6.z + var1.z);
   }

   private void method10(double var1, double var3, double var5) {
      int var7 = (int)Math.floor(var1);
      int var8 = (int)Math.floor(var3);
      int var9 = (int)Math.floor(var5);
      if (this.field14 == null || var7 != this.field14.bridge$getX() || var8 != this.field14.bridge$getY() || var9 != this.field14.bridge$getZ()) {
         this.field14 = Bridge.method8().method4(var7, var8, var9);
         this.field15 = null;
      }
   }

   public float bridge$ageInTicks() {
      return this.field17;
   }

   public double method11() {
      return this.field7.x;
   }

   public double method12() {
      return this.field7.y;
   }

   public double method13() {
      return this.field7.z;
   }

   public double bridge$getPosX() {
      return this.field6.x;
   }

   public double bridge$getPosY() {
      return this.field6.y;
   }

   public double bridge$getPosZ() {
      return this.field6.z;
   }

   public double bridge$getMotionX() {
      return this.field8.x;
   }

   public double bridge$getMotionY() {
      return this.field8.y;
   }

   public double bridge$getMotionZ() {
      return this.field8.z;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return 0.0F;
   }

   public float bridge$getEyeHeight() {
      return this.field5.method4();
   }

   public double bridge$distanceToCameraSq() {
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         Bridge2_19 var1 = (Bridge2_19)ThreadModuleDump63.method13().bridge$getCamera().orElse(null);
         if (var1 != null) {
            return this.getDistanceSq(var1.bridge$getPosX(), var1.bridge$getPosY(), var1.bridge$getPosZ());
         }
      }

      return this.method26(ThreadModuleDump63.method7());
   }

   @Nullable
   public Vec3Bridge bridge$getPassengerOffset(@Nullable BridgeExtension var1) {
      return null;
   }

   public boolean bridge$isInvisible() {
      return this.field19 || this.field3.bridge$isInvisible();
   }

   public float bridge$getWidth() {
      return this.field5.method2();
   }

   public float bridge$getHeight() {
      return this.field5.method1();
   }

   public boolean bridge$isVisiblyCrouching() {
      return false;
   }

   public boolean bridge$isDiscrete() {
      return false;
   }

   @Nullable
   public Component bridge$getCustomName() {
      return null;
   }

   @Nullable
   public Vec3Bridge bridge$getNameTagAttachment() {
      return null;
   }

   public void bridge$extractRenderStates$Entity(BridgeExtension var1) {
   }

   @Nullable
   public Horsestats20Extension2 method8() {
      return this.field14;
   }

   private boolean method15() {
      if (this.field15 == null) {
         Itemcounter6Extension var1 = ThreadModuleDump63.method8();
         if (var1 == null || this.field14 == null) {
            return true;
         }

         this.field15 = var1.method2(this.field14);
      }

      return false;
   }

   public boolean bridge$isOnFire() {
      return this.method15() ? false : this.field15.bridge$getBlock().bridge$isFire();
   }

   public int bridge$getEntityId() {
      return this instanceof BridgeExtension var1 ? var1.bridge$getEntityId() : -1;
   }

   public boolean isInWater() {
      return this.method15() ? false : this.field15.bridge$getBlock().bridge$isWater();
   }

   public boolean method6() {
      if (this.method15()) {
         return false;
      }

      Bridge3_23 var1 = this.field15.bridge$getBlock();
      return var1.bridge$isWater() || var1.bridge$isBubbleColumn();
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public void method18() {
      if (this.field5.method9()) {
         this.field8.y = this.method6() ? 0.2 : 0.6;
         this.onGround = false;
         this.method5();
         this.field18 = 20;
      }
   }

   public float method3() {
      return this.field5.method3();
   }

   public float method4() {
      return this.field5.method5();
   }

   public double method2() {
      return this.field16;
   }

   public void method22(Vector3d var1) {
      this.method23(var1.x, var1.z);
   }

   public void method23(double var1, double var3) {
      double var5 = this.field6.x() - var1;
      double var7 = this.field6.z() - var3;
      double var9 = wrapDegrees((float)Math.toDegrees(Math.atan2(var7, var5)) + 90.0F);
      this.method6((float)(360.0 - var9));
   }

   public boolean method24(Itemcounter6 var1, Horsestats20Extension2 var2) {
      return !var1.bridge$isChunkLoaded(var2.bridge$getX() >> 4, var2.bridge$getZ() >> 4) ? false : var1.method2(var2).bridge$getBlock().bridge$isAir();
   }

   public boolean method25(PathFilter var1, Itemcounter6 var2, Bridge6_10 var3, int var4, int var5, int var6) {
      boolean var7 = var3.bridge$getUniqueID().getLeastSignificantBits() == 0L;
      if ((var7 || var3.bridge$isOnGround()) && (this.field5.method12() == Inactive.Type.FLYING || !var3.bridge$isFlying())) {
         Horsestats20Extension2 var8 = var3.bridge$getBlockPos();

         for (int var9 = 0; var9 < 6; var9++) {
            Horsestats20Extension2 var10 = WanderPositionResolver.method2(this, var1, var8, var4, var5, var6);
            if (var10 != null && this.method24(var2, var10)) {
               this.setPos(var10.bridge$getX() + 0.5, var10.bridge$getY(), var10.bridge$getZ() + 0.5);
               this.field8.set(0.0);
               this.field12 = null;
               return true;
            }
         }
      }

      return false;
   }

   public double method26(BridgeExtension var1) {
      double var2 = this.bridge$getPosX() - var1.bridge$getPosX();
      double var4 = this.bridge$getPosY() - var1.bridge$getPosY();
      double var6 = this.bridge$getPosZ() - var1.bridge$getPosZ();
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public double getDistanceSq(double var1, double var3, double var5) {
      double var7 = this.bridge$getPosX() - var1;
      double var9 = this.bridge$getPosY() - var3;
      double var11 = this.bridge$getPosZ() - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public static float wrapDegrees(float var0) {
      return ((var0 + 180.0F) % 360.0F + 360.0F) % 360.0F - 180.0F;
   }

   @Generated
   public CosmeticMetadata method27() {
      return this.field1;
   }

   @Generated
   public EmoteModel method28() {
      return this.field2;
   }

   @NotNull
   @Generated
   public Bridge5_11 method29() {
      return this.field3;
   }

   @Generated
   public MolangRuntime method30() {
      return this.field4;
   }

   @Generated
   public Inactive method31() {
      return this.field5;
   }

   @Generated
   public Vector3d method32() {
      return this.field8;
   }

   @Generated
   public Random method9() {
      return this.field9;
   }

   @Generated
   public float method34() {
      return this.field10;
   }

   @Generated
   public float method35() {
      return this.field11;
   }

   @Generated
   public void method36(float var1) {
      this.field11 = var1;
   }

   @Generated
   public void method10(@Nullable Vector3d var1) {
      this.field12 = var1;
   }

   @Nullable
   @Generated
   public Vector3d method38() {
      return this.field12;
   }

   @Generated
   public void method39(float var1) {
      this.field13 = var1;
   }

   @Generated
   public float method40() {
      return this.field13;
   }

   @Generated
   public void method41(double var1) {
      this.field16 = var1;
   }

   @Generated
   public float getFallDistance() {
      return this.fallDistance;
   }

   @Generated
   public int method43() {
      return this.field20;
   }

   @Generated
   public void method44(int var1) {
      this.field20 = var1;
   }

   @Nullable
   @Generated
   public AnimationTaskEntry method45() {
      return this.field21;
   }

   @Generated
   public void method46(@Nullable AnimationTaskEntry var1) {
      this.field21 = var1;
   }

   @Nullable
   @Generated
   public AnimationTaskEntry method47() {
      return this.field22;
   }

   @Generated
   public void method48(@Nullable AnimationTaskEntry var1) {
      this.field22 = var1;
   }
}
