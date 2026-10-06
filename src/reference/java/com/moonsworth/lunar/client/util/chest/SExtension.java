package com.moonsworth.lunar.client.util.chest;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
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

public interface SExtension<T, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.chest.mixin.Chest> {
   default void refresh() {
   }

   SImpl<T, ?, I, H, S> getConfig();

   @Nullable
   S getContext();

   Vec3Bridge getStart();

   Vec3Bridge getEnd();

   BiFunction<T, I, H> getResultFunction();

   default Supplier<H> getMissSupplier() {
      return this.getConfig().method4();
   }

   default H trace(T var1) {
      return this.getConfig().method3().traverse(var1, this);
   }

   static <T, O, I, H extends Horsestats_3<H, S>, S extends H, E extends com.moonsworth.lunar.client.util.chest.mixin.Chest> SExtension.Data2<T, O, I, H, S, E> builder(
      SImpl<T, O, I, H, E> var0
   ) {
      return new SExtension.Data2<>(var0);
   }

   class Data<T, O, I, H extends Horsestats_3<H, S>, S extends H, E extends com.moonsworth.lunar.client.util.chest.mixin.Chest>
      extends SExtension.Data2<T, O, I, H, S, E> {
      private Supplier<Chest> field7;

      protected Data(SImpl<T, O, I, H, E> var1) {
         super(var1);
      }

      protected Data(SExtension.Data2<T, O, I, H, S, E> var1) {
         super(var1);
      }

      @Override
      public SExtension.Data<T, O, I, H, S, E> method11(Supplier<Chest> var1) {
         this.method19();
         this.field7 = var1;
         return this;
      }

      @Override
      public SExtension.Data<T, O, I, H, S, E> method12(BridgeExtension var1, double var2) {
         return (SExtension.Data<T, O, I, H, S, E>)this.method11(
            () -> {
               Vec3Bridge var3 = var1.bridge$getEyePosition();
               Vec3Bridge var4 = var1.method19();
               return Chest.of(
                  var3,
                  Vec3Bridge.method2(
                     var3.bridge$xCoord() + var4.bridge$xCoord() * var2,
                     var3.bridge$yCoord() + var4.bridge$yCoord() * var2,
                     var3.bridge$zCoord() + var4.bridge$zCoord() * var2
                  )
               );
            }
         );
      }

      @Override
      public SExtension.Data2<T, O, I, H, S, E> method13(BridgeExtension var1, double var2, Vec3Bridge var4) {
         return (SExtension.Data2<T, O, I, H, S, E>)this.method11(
            () -> {
               Vec3Bridge var4x = var1.bridge$getEyePosition();
               Vec3Bridge var5 = Vec3Bridge.method2(
                  var4x.bridge$xCoord() + var4.bridge$xCoord(), var4x.bridge$yCoord() + var4.bridge$yCoord(), var4x.bridge$zCoord() + var4.bridge$zCoord()
               );
               Vec3Bridge var6 = var1.method19();
               return Chest.of(
                  var5,
                  Vec3Bridge.method2(
                     var5.bridge$xCoord() + var6.bridge$xCoord() * var2,
                     var5.bridge$yCoord() + var6.bridge$yCoord() * var2,
                     var5.bridge$zCoord() + var6.bridge$zCoord() * var2
                  )
               );
            }
         );
      }

      @Override
      public SExtension<T, I, H, E> method18() {
         if (this.CIHRIHIIRHRCRCRCIIOCHCHRRCHORH == null) {
            throw new RuntimeException("RayContext must have at least one trigger!");
         } else if (this.HOIHCIICOIOICCCOCROIIHIRHROICO == null && this.CHHHRRRRROCICHOCOIIRRCRHIHCORO == null) {
            throw new RuntimeException("RayContext can only have a null predicate if a function is set!");
         } else {
            return (SExtension<T, I, H, E>)this.CIHRIHIIRHRCRCRCIIOCHCHRRCHORH
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

   class Data2<T, O, I, H extends Horsestats_3<H, S>, S extends H, E extends com.moonsworth.lunar.client.util.chest.mixin.Chest> {
      private Vec3Bridge field1;
      private Vec3Bridge field2;
      @Nullable
      protected E field3 = (E)null;
      protected BiPredicate<I, O> field4;
      @Nullable
      protected SImpl.Extension<T, O, I, H, E> field5;
      protected final SImpl<T, O, I, H, E> field6;

      protected Data2(SImpl<T, O, I, H, E> var1) {
         this.field6 = var1;
      }

      protected Data2(SExtension.Data2<T, O, I, H, S, E> var1) {
         this.field1 = var1.field1;
         this.field2 = var1.field2;
         this.field3 = var1.field3;
         this.field4 = var1.field4;
         this.field5 = var1.field5;
         this.field6 = var1.field6;
      }

      public SExtension.Data2<T, O, I, H, S, E> refresh(E var1) {
         this.field3 = (E)var1;
         return this;
      }

      public SExtension.Data2<T, O, I, H, S, E> getConfig(Vec3Bridge var1, Vec3Bridge var2) {
         this.method19();
         this.field1 = var1;
         this.field2 = var2;
         return this;
      }

      public SExtension.Data2<T, O, I, H, S, E> getContext(Vector3dc var1, Vector3dc var2) {
         return (SExtension.Data2<T, O, I, H, S, E>)this.getConfig(
            Vec3Bridge.method2(var1.x(), var1.y(), var1.z()), Vec3Bridge.method2(var2.x(), var2.y(), var2.z())
         );
      }

      public SExtension.Data2<T, O, I, H, S, E> getStart(Vec3Bridge var1, Vec3Bridge var2, double var3) {
         return (SExtension.Data2<T, O, I, H, S, E>)this.getConfig(
            var1,
            Vec3Bridge.method2(
               var1.bridge$xCoord() + var2.bridge$xCoord() * var3,
               var1.bridge$yCoord() + var2.bridge$yCoord() * var3,
               var1.bridge$zCoord() + var2.bridge$zCoord() * var3
            )
         );
      }

      public SExtension.Data2<T, O, I, H, S, E> getEnd(Vector3dc var1, Vector3dc var2, double var3) {
         return (SExtension.Data2<T, O, I, H, S, E>)this.getStart(
            Vec3Bridge.method2(var1.x(), var1.y(), var1.z()), Vec3Bridge.method2(var2.x(), var2.y(), var2.z()), var3
         );
      }

      public SExtension.Data2<T, O, I, H, S, E> getResultFunction(Vector3iBridge var1, Vector3iBridge var2) {
         return (SExtension.Data2<T, O, I, H, S, E>)this.getMissSupplier(var1, var2, true);
      }

      public SExtension.Data2<T, O, I, H, S, E> getMissSupplier(Vector3iBridge var1, Vector3iBridge var2, boolean var3) {
         return (SExtension.Data2<T, O, I, H, S, E>)(var3
            ? this.getConfig(
               Vec3Bridge.method2(var1.bridge$getX() + 0.5F, var1.bridge$getY() + 0.5F, var1.bridge$getZ() + 0.5F),
               Vec3Bridge.method2(var2.bridge$getX() + 0.5F, var2.bridge$getY() + 0.5F, var2.bridge$getZ() + 0.5F)
            )
            : this.getConfig(
               Vec3Bridge.method2(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ()),
               Vec3Bridge.method2(var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ())
            ));
      }

      public SExtension.Data2<T, O, I, H, S, E> trace(BridgeExtension var1, double var2, float var4) {
         return (SExtension.Data2<T, O, I, H, S, E>)this.getStart(var1.bridge$getEyePosition(var4), var1.bridge$getViewVector(var4), var2);
      }

      public SExtension.Data2<T, O, I, H, S, E> builder(BridgeExtension var1, double var2, Vec3Bridge var4, float var5) {
         Vec3Bridge var6 = var1.bridge$getEyePosition(var5);
         Vec3Bridge var7 = Vec3Bridge.method2(
            var6.bridge$xCoord() + var4.bridge$xCoord(), var6.bridge$yCoord() + var4.bridge$yCoord(), var6.bridge$zCoord() + var4.bridge$zCoord()
         );
         return (SExtension.Data2<T, O, I, H, S, E>)this.getStart(var7, var1.bridge$getViewVector(var5), var2);
      }

      public SExtension.Data2<T, O, I, H, S, E> method10(BridgeExtension var1, double var2, Vector3d var4, float var5) {
         Vec3Bridge var6 = var1.bridge$getEyePosition(var5);
         Vec3Bridge var7 = Vec3Bridge.method2(var6.bridge$xCoord() + var4.x(), var6.bridge$yCoord() + var4.y(), var6.bridge$zCoord() + var4.z());
         return (SExtension.Data2<T, O, I, H, S, E>)this.getStart(var7, var1.bridge$getViewVector(var5), var2);
      }

      @Contract(pure = true)
      public SExtension.Data<T, O, I, H, S, E> method11(Supplier<Chest> var1) {
         SExtension.Data var2 = new SExtension.Data<>(this);
         return (SExtension.Data<T, O, I, H, S, E>)var2.method11(var1);
      }

      @Contract(pure = true)
      public SExtension.Data<T, O, I, H, S, E> method12(BridgeExtension var1, double var2) {
         SExtension.Data var4 = new SExtension.Data<>(this);
         return (SExtension.Data<T, O, I, H, S, E>)var4.method12(var1, var2);
      }

      @Contract(pure = true)
      public SExtension.Data2<T, O, I, H, S, E> method13(BridgeExtension var1, double var2, Vec3Bridge var4) {
         SExtension.Data var5 = new SExtension.Data<>(this);
         return (SExtension.Data2<T, O, I, H, S, E>)var5.method13(var1, var2, var4);
      }

      public SExtension.Data2<T, O, I, H, S, E> method14(BiPredicate<I, O> var1) {
         this.field4 = var1;
         return this;
      }

      public SExtension.Data2<T, O, I, H, S, E> method15(Predicate<O> var1) {
         this.field4 = (var1x, var2) -> var1.test(var2);
         return this;
      }

      public SExtension.Data2<T, O, I, H, S, E> method16(SImpl.Extension<T, O, I, H, E> var1) {
         this.getConfig0();
         this.field5 = var1;
         return this;
      }

      public SExtension.Data2<T, O, I, H, S, E> method17(BiConsumer<S, SExtension<T, I, H, E>> var1) {
         this.getConfig0();
         this.field5 = (var2, var3, var4, var5) -> {
            if (this.field4.test(var4, var5)) {
               Horsestats_3 var6 = this.field6.method6().create(var2, var3, var4, var5);
               if (var6 != null) {
                  var6.method5(var2x -> var1.accept(var2x, var3));
               }
            }

            return null;
         };
         return this;
      }

      public SExtension<T, I, H, E> method18() {
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
