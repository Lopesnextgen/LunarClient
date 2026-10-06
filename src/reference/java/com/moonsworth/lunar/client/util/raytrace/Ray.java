package com.moonsworth.lunar.client.util.raytrace;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public interface Ray<T, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.raytrace.RaycastContext> {
   default void method1() {
   }

   Raycaster<T, ?, I, H, S> method2();

   @Nullable
   S method3();

   Vec3Bridge method4();

   Vec3Bridge method5();

   BiFunction<T, I, H> method6();

   default Supplier<H> method7() {
      return this.method2().method4();
   }

   default H method8(T value1) {
      return this.method2().method3().traverse(value1, this);
   }

   static <T, O, I, H extends Horsestats_3<H, S>, S extends H, E extends com.moonsworth.lunar.client.util.raytrace.RaycastContext> Ray.RayBuilder<T, O, I, H, S, E> method9(
      Raycaster<T, O, I, H, E> simpl0
   ) {
      return new Ray.RayBuilder<>(simpl0);
   }

   class Data<T, O, I, H extends Horsestats_3<H, S>, S extends H, E extends com.moonsworth.lunar.client.util.raytrace.RaycastContext>
      extends Ray.RayBuilder<T, O, I, H, S, E> {
      private Supplier<RaySegment> field7;

      protected Data(Raycaster<T, O, I, H, E> simpl1) {
         super(simpl1);
      }

      protected Data(Ray.RayBuilder<T, O, I, H, S, E> data21) {
         super(data21);
      }

      @Override
      public Ray.Data<T, O, I, H, S, E> method11(Supplier<RaySegment> supplier1) {
         this.method19();
         this.field7 = supplier1;
         return this;
      }

      @Override
      public Ray.Data<T, O, I, H, S, E> method12(BridgeExtension bridgeextension1, double value2) {
         return (Ray.Data<T, O, I, H, S, E>)this.method11(
            () -> {
               Vec3Bridge horsestats153 = bridgeextension1.bridge$getEyePosition();
               Vec3Bridge horsestats154 = bridgeextension1.method19();
               return RaySegment.method3(
                  horsestats153,
                  Vec3Bridge.method2(
                     horsestats153.bridge$xCoord() + horsestats154.bridge$xCoord() * value2,
                     horsestats153.bridge$yCoord() + horsestats154.bridge$yCoord() * value2,
                     horsestats153.bridge$zCoord() + horsestats154.bridge$zCoord() * value2
                  )
               );
            }
         );
      }

      @Override
      public Ray.RayBuilder<T, O, I, H, S, E> method13(BridgeExtension bridgeextension1, double value2, Vec3Bridge horsestats154) {
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method11(
            () -> {
               Vec3Bridge horsestats154x = bridgeextension1.bridge$getEyePosition();
               Vec3Bridge horsestats155 = Vec3Bridge.method2(
                  horsestats154x.bridge$xCoord() + horsestats154.bridge$xCoord(), horsestats154x.bridge$yCoord() + horsestats154.bridge$yCoord(), horsestats154x.bridge$zCoord() + horsestats154.bridge$zCoord()
               );
               Vec3Bridge horsestats156 = bridgeextension1.method19();
               return RaySegment.method3(
                  horsestats155,
                  Vec3Bridge.method2(
                     horsestats155.bridge$xCoord() + horsestats156.bridge$xCoord() * value2,
                     horsestats155.bridge$yCoord() + horsestats156.bridge$yCoord() * value2,
                     horsestats155.bridge$zCoord() + horsestats156.bridge$zCoord() * value2
                  )
               );
            }
         );
      }

      @Override
      public Ray<T, I, H, E> method18() {
         if (this.CIHRIHIIRHRCRCRCIIOCHCHRRCHORH == null) {
            throw new RuntimeException("RayContext must have at least one trigger!");
         } else if (this.HOIHCIICOIOICCCOCROIIHIRHROICO == null && this.CHHHRRRRROCICHOCOIIRRCRHIHCORO == null) {
            throw new RuntimeException("RayContext can only have a null predicate if a function is set!");
         } else {
            return (Ray<T, I, H, E>)this.CIHRIHIIRHRCRCRCIIOCHCHRRCHORH
               .method2(this.field7, this.OIIOIIIHOCOCOORCHRRICICHRCIOCO, this.HOIHCIICOIOICCCOCROIIHIRHROICO, this.CHHHRRRRROCICHOCOIIRRCRHIHCORO);
         }
      }

      @Override
      protected void method19() {
         super.method19();
         if (this.field7 != null) {
            throw new RuntimeException("Ray already has positions assigned! - RayPointSupplier");
         }
      }
   }

   class RayBuilder<T, O, I, H extends Horsestats_3<H, S>, S extends H, E extends com.moonsworth.lunar.client.util.raytrace.RaycastContext> {
      private Vec3Bridge field1;
      private Vec3Bridge field2;
      @Nullable
      protected E field3 = (E)null;
      protected BiPredicate<I, O> field4;
      @Nullable
      protected Raycaster.Extension<T, O, I, H, E> field5;
      protected final Raycaster<T, O, I, H, E> field6;

      protected RayBuilder(Raycaster<T, O, I, H, E> simpl1) {
         this.field6 = simpl1;
      }

      protected RayBuilder(Ray.RayBuilder<T, O, I, H, S, E> data21) {
         this.field1 = data21.field1;
         this.field2 = data21.field2;
         this.field3 = data21.field3;
         this.field4 = data21.field4;
         this.field5 = data21.field5;
         this.field6 = data21.field6;
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method1(E value1) {
         this.field3 = (E)value1;
         return this;
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method2(Vec3Bridge horsestats151, Vec3Bridge horsestats152) {
         this.method19();
         this.field1 = horsestats151;
         this.field2 = horsestats152;
         return this;
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method3(Vector3dc vector3dc1, Vector3dc vector3dc2) {
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method2(
            Vec3Bridge.method2(vector3dc1.x(), vector3dc1.y(), vector3dc1.z()), Vec3Bridge.method2(vector3dc2.x(), vector3dc2.y(), vector3dc2.z())
         );
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method4(Vec3Bridge horsestats151, Vec3Bridge horsestats152, double value3) {
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method2(
            horsestats151,
            Vec3Bridge.method2(
               horsestats151.bridge$xCoord() + horsestats152.bridge$xCoord() * value3,
               horsestats151.bridge$yCoord() + horsestats152.bridge$yCoord() * value3,
               horsestats151.bridge$zCoord() + horsestats152.bridge$zCoord() * value3
            )
         );
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method5(Vector3dc vector3dc1, Vector3dc vector3dc2, double value3) {
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method4(
            Vec3Bridge.method2(vector3dc1.x(), vector3dc1.y(), vector3dc1.z()), Vec3Bridge.method2(vector3dc2.x(), vector3dc2.y(), vector3dc2.z()), value3
         );
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method6(Vec3iBridge horsestats201, Vec3iBridge horsestats202) {
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method7(horsestats201, horsestats202, true);
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method7(Vec3iBridge horsestats201, Vec3iBridge horsestats202, boolean flag3) {
         return (Ray.RayBuilder<T, O, I, H, S, E>)(flag3
            ? this.method2(
               Vec3Bridge.method2(horsestats201.bridge$getX() + 0.5F, horsestats201.bridge$getY() + 0.5F, horsestats201.bridge$getZ() + 0.5F),
               Vec3Bridge.method2(horsestats202.bridge$getX() + 0.5F, horsestats202.bridge$getY() + 0.5F, horsestats202.bridge$getZ() + 0.5F)
            )
            : this.method2(
               Vec3Bridge.method2(horsestats201.bridge$getX(), horsestats201.bridge$getY(), horsestats201.bridge$getZ()),
               Vec3Bridge.method2(horsestats202.bridge$getX(), horsestats202.bridge$getY(), horsestats202.bridge$getZ())
            ));
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method8(BridgeExtension bridgeextension1, double value2, float value4) {
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method4(bridgeextension1.bridge$getEyePosition(value4), bridgeextension1.bridge$getViewVector(value4), value2);
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method9(BridgeExtension bridgeextension1, double value2, Vec3Bridge horsestats154, float value5) {
         Vec3Bridge horsestats156 = bridgeextension1.bridge$getEyePosition(value5);
         Vec3Bridge horsestats157 = Vec3Bridge.method2(
            horsestats156.bridge$xCoord() + horsestats154.bridge$xCoord(), horsestats156.bridge$yCoord() + horsestats154.bridge$yCoord(), horsestats156.bridge$zCoord() + horsestats154.bridge$zCoord()
         );
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method4(horsestats157, bridgeextension1.bridge$getViewVector(value5), value2);
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method10(BridgeExtension bridgeextension1, double value2, Vector3d vector3d4, float value5) {
         Vec3Bridge horsestats156 = bridgeextension1.bridge$getEyePosition(value5);
         Vec3Bridge horsestats157 = Vec3Bridge.method2(horsestats156.bridge$xCoord() + vector3d4.x(), horsestats156.bridge$yCoord() + vector3d4.y(), horsestats156.bridge$zCoord() + vector3d4.z());
         return (Ray.RayBuilder<T, O, I, H, S, E>)this.method4(horsestats157, bridgeextension1.bridge$getViewVector(value5), value2);
      }

      @Contract(pure = true)
      public Ray.Data<T, O, I, H, S, E> method11(Supplier<RaySegment> supplier1) {
         Ray.Data data2 = new Ray.Data<>(this);
         return (Ray.Data<T, O, I, H, S, E>)data2.method11(supplier1);
      }

      @Contract(pure = true)
      public Ray.Data<T, O, I, H, S, E> method12(BridgeExtension bridgeextension1, double value2) {
         Ray.Data data4 = new Ray.Data<>(this);
         return (Ray.Data<T, O, I, H, S, E>)data4.method12(bridgeextension1, value2);
      }

      @Contract(pure = true)
      public Ray.RayBuilder<T, O, I, H, S, E> method13(BridgeExtension bridgeextension1, double value2, Vec3Bridge horsestats154) {
         Ray.Data data5 = new Ray.Data<>(this);
         return (Ray.RayBuilder<T, O, I, H, S, E>)data5.method13(bridgeextension1, value2, horsestats154);
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method14(BiPredicate<I, O> bipredicate1) {
         this.field4 = bipredicate1;
         return this;
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method15(Predicate<O> predicate1) {
         this.field4 = (arg1x, arg2) -> predicate1.test(arg2);
         return this;
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method16(Raycaster.Extension<T, O, I, H, E> extension1) {
         this.method20();
         this.field5 = extension1;
         return this;
      }

      public Ray.RayBuilder<T, O, I, H, S, E> method17(BiConsumer<S, Ray<T, I, H, E>> biconsumer1) {
         this.method20();
         this.field5 = (arg2, arg3, arg4, arg5) -> {
            if (this.field4.test(arg4, arg5)) {
               Horsestats_3 horsestats_36 = this.field6.method6().create(arg2, arg3, arg4, arg5);
               if (horsestats_36 != null) {
                  horsestats_36.method5(arg2x -> biconsumer1.accept(arg2x, arg3));
               }
            }

            return null;
         };
         return this;
      }

      public Ray<T, I, H, E> method18() {
         if (this.field6 == null) {
            throw new RuntimeException("RayContext must have at least one trigger!");
         } else if (this.field4 == null && this.field5 == null) {
            throw new RuntimeException("RayContext can only have a null predicate if a function is set!");
         } else {
            return this.field6.method1(this.field1, this.field2, this.field3, this.field4, this.field5);
         }
      }

      protected void method19() {
         if (this.field1 != null) {
            throw new RuntimeException("Ray already has positions assigned! - [" + this.field1 + ", " + this.field2 + "]");
         }
      }

      private void method20() {
         if (this.field5 != null) {
            throw new RuntimeException("Ray already has a function assigned! - [" + this.field1 + ", " + this.field2 + "]");
         }
      }
   }
}
