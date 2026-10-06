package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContextType;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.vecmath.Vector4f;
import lombok.Generated;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@FunctionalInterface
public interface ThreadModuleDump91 {
   static ThreadModuleDump91 combine(ThreadModuleDump91... var0) {
      return new ThreadModuleDump91.Data(Arrays.asList(var0));
   }

   static ThreadModuleDump91 rotate(float var0, float var1, float var2, float var3) {
      return new ThreadModuleDump91.RotateTransform(var0, var1, var2, var3, ThreadModuleDump91.Type.NONE);
   }

   static ThreadModuleDump91 rotateOnAxis(float var0, float var1, float var2) {
      return new ThreadModuleDump91.RotateTransform(0.0F, var0, var1, var2, ThreadModuleDump91.Type.NONE) {
         final float field3 = 0.0F;

         @Override
         public void transform(AbstractRenderContext var1, EntityPlayerBridge var2x, float var3) {
            var1.method4(
               (0.0F + var3) * (180.0F / (float)Math.PI),
               this.HIRIICCCCCRHOIOCCHHIHRHHIHCCOI[1],
               this.HIRIICCCCCRHOIOCCHHIHRHHIHCCOI[2],
               this.HIRIICCCCCRHOIOCCHHIHRHHIHCCOI[3]
            );
         }

         @Override
         public void transform(Matrix4f var1, EntityPlayerBridge var2x, float var3) {
            float var4 = (float)Math.toRadians((0.0F + var3) * (180.0F / (float)Math.PI));
            var1.rotate(new Quaternionf().rotationX(this.HIRIICCCCCRHOIOCCHHIHRHHIHCCOI[1] * var4));
            var1.rotate(new Quaternionf().rotationY(this.HIRIICCCCCRHOIOCCHHIHRHHIHCCOI[2] * var4));
            var1.rotate(new Quaternionf().rotationZ(this.HIRIICCCCCRHOIOCCHHIHRHHIHCCOI[3] * var4));
         }
      };
   }

   static ThreadModuleDump91 translate(Vector3f var0) {
      return new ThreadModuleDump91.TranslateTransform(var0, ThreadModuleDump91.Type.NONE);
   }

   static ThreadModuleDump91 translateToPlayersHead(float var0) {
      return translate(
         var1 -> var1 != null
            ? new Vector3f(0.0F, (float)(-(var1.bridge$getEyeHeight() + (var1.bridge$isVisiblyCrouching() ? 0.25 : 0.0))) * var0, 0.0F)
            : null,
         ThreadModuleDump91.Type.PLAYER
      );
   }

   static ThreadModuleDump91 translate(final Function<EntityPlayerBridge, Vector3f> var0, ThreadModuleDump91.Type var1) {
      return new ThreadModuleDump91.DynamicTransform(var0) {
         @Override
         public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, float var3) {
            Vector3f var4 = (Vector3f)var0.apply(var2);
            this.RCRRIRCOOHICRCCORCOCCOROIHIHHI = var4;
            var1.translate(var4.x, var4.y, var4.z);
         }

         @Override
         public ThreadModuleDump91.Type2 transformType() {
            return ThreadModuleDump91.Type2.TRANSLATE;
         }

         @Override
         public void transform(Matrix4f var1, EntityPlayerBridge var2, float var3) {
            Vector3f var4 = (Vector3f)var0.apply(var2);
            this.RCRRIRCOOHICRCCORCOCCOROIHIHHI = var4;
            var1.translate(var4.x, var4.y, var4.z);
         }
      };
   }

   static ThreadModuleDump91 scale(Vector3f var0) {
      return new ThreadModuleDump91.ScaleTransform(var0, ThreadModuleDump91.Type.NONE);
   }

   static ThreadModuleDump91 scale(final Function<EntityPlayerBridge, Vector3f> var0, ThreadModuleDump91.Type var1) {
      return new ThreadModuleDump91.DynamicTransform(var0) {
         @Override
         public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, float var3) {
            Vector3f var4 = (Vector3f)var0.apply(var2);
            this.RCRRIRCOOHICRCCORCOCCOROIHIHHI = var4;
            if (var4 != null) {
               var1.scale(var4.x, var4.y, var4.z);
            }
         }

         @Override
         public ThreadModuleDump91.Type2 transformType() {
            return ThreadModuleDump91.Type2.SCALE;
         }

         @Override
         public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, Bridge5_16 var3, float var4) {
            Vector3f var5 = (Vector3f)var0.apply(var2);
            this.RCRRIRCOOHICRCCORCOCCOROIHIHHI = var5;
            if (var5 != null) {
               var1.scale(var5.x, var5.y, var5.z);
            }
         }

         @Override
         public void transform(Matrix4f var1, EntityPlayerBridge var2, float var3) {
            Vector3f var4 = (Vector3f)var0.apply(var2);
            this.RCRRIRCOOHICRCCORCOCCOROIHIHHI = var4;
            if (var4 != null) {
               var1.scale(var4.x, var4.y, var4.z);
            }
         }
      };
   }

   static ThreadModuleDump91 rotateAndTranslateToHead(Vector3f var0) {
      return combine(
         translate(
            var1 -> {
               if (var1 == null || !var1.bridge$isVisiblyCrouching()) {
                  return var0;
               } else {
                  return !var1.bridge$isEmoting()
                     ? new Vector3f(0.0F, -var1.bridge$getEyeHeight() + 0.25F, 0.0F)
                     : new Vector3f(0.0F, -var1.bridge$getEyeHeight(), 0.0F);
               }
            },
            ThreadModuleDump91.Type.PLAYER
         ),
         rotate(180.0F, 0.0F, 1.0F, 0.0F)
      );
   }

   static ThreadModuleDump91 createModern(final BiConsumer<EntityPlayerBridge, Bridge5_16> var0, final BiConsumer<EntityPlayerBridge, Matrix4f> var1) {
      return new ThreadModuleDump91() {
         @Override
         public JsonObject save() {
            return null;
         }

         @Override
         public void load(JsonObject var1x) {
         }

         @Override
         public void transform(AbstractRenderContext var1x, EntityPlayerBridge var2, float var3) {
            var1x.method5(var2x -> var0.accept(var2, var2x.method51()));
         }

         @Override
         public void transform(AbstractRenderContext var1x, EntityPlayerBridge var2, Bridge5_16 var3, float var4) {
            var0.accept(var2, var3);
         }

         @Override
         public void transform(Matrix4f var1x, EntityPlayerBridge var2, float var3) {
            var1.accept(var2, var1x);
         }
      };
   }

   default JsonObject save() {
      return new JsonObject();
   }

   default void load(JsonObject var1) {
   }

   void transform(AbstractRenderContext var1, EntityPlayerBridge var2, float var3);

   default ThreadModuleDump91.Type2 transformType() {
      return ThreadModuleDump91.Type2.MIXED;
   }

   default void transform(AbstractRenderContext var1, EntityPlayerBridge var2, Bridge5_16 var3, float var4) {
   }

   default void transform(Matrix4f var1, EntityPlayerBridge var2, float var3) {
   }

   default ThreadModuleDump91.Type getCondition() {
      return ThreadModuleDump91.Type.NONE;
   }

   class Data implements ThreadModuleDump91 {
      private List<ThreadModuleDump91> field1 = new ArrayList<>();

      public Data(List<ThreadModuleDump91> var1) {
         this.field1 = var1;
      }

      public Data(JsonObject var1) {
         this.load(var1);
      }

      @Override
      public JsonObject save() {
         JsonArray var1 = new JsonArray();

         for (ThreadModuleDump91 var3 : this.field1) {
            var1.add(var3.save());
         }

         JsonObject var4 = new JsonObject();
         var4.add("transformations", var1);
         var4.addProperty("transformType", this.transformType().name().toLowerCase());
         var4.addProperty("condition", this.getCondition().name().toLowerCase());
         return var4;
      }

      @Override
      public void load(JsonObject var1) {
         for (JsonElement var4 : var1.get("transformations").getAsJsonArray()) {
            List var5 = CosmeticManager.method32(var4.getAsJsonObject());
            this.field1.addAll(var5);
         }
      }

      @Override
      public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, float var3) {
         for (ThreadModuleDump91 var5 : this.field1) {
            var5.transform(var1, var2, var3);
         }
      }

      @Override
      public void transform(Matrix4f var1, EntityPlayerBridge var2, float var3) {
         for (ThreadModuleDump91 var5 : this.field1) {
            var5.transform(var1, var2, var3);
         }
      }

      @Override
      public ThreadModuleDump91.Type2 transformType() {
         return ThreadModuleDump91.Type2.MIXED;
      }
   }

   abstract class AbstractTransform implements ThreadModuleDump91 {
      protected Vector3f field1;
      private ThreadModuleDump91.Type field2;

      public AbstractTransform(Vector3f var1, ThreadModuleDump91.Type var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public AbstractTransform(JsonObject var1, ThreadModuleDump91.Type var2) {
         this.load(var1);
         this.field2 = var2;
      }

      public Vector3f method1() {
         return this.field1;
      }

      public Vector4f method2() {
         return null;
      }

      @Override
      public JsonObject save() {
         JsonObject var1 = new JsonObject();
         JsonObject var2 = new JsonObject();
         var2.addProperty("x", this.field1.x);
         var2.addProperty("y", this.field1.y);
         var2.addProperty("z", this.field1.z);
         var1.addProperty("transformType", this.transformType().name().toLowerCase());
         var1.add("values", var2);
         if (this.getCondition() != ThreadModuleDump91.Type.NONE) {
            var1.addProperty("condition", this.getCondition().name().toLowerCase());
         }

         return var1;
      }

      @Override
      public void load(JsonObject var1) {
         JsonObject var2 = var1.get("values").getAsJsonObject();
         this.field1 = new Vector3f();
         this.field1.x = var2.get("x").getAsFloat();
         this.field1.y = var2.get("y").getAsFloat();
         this.field1.z = var2.get("z").getAsFloat();
      }

      @Override
      public ThreadModuleDump91.Type getCondition() {
         return this.field2;
      }
   }

   abstract class DynamicTransform extends ThreadModuleDump91.AbstractTransform {
      public static Bridge5Extension_5 field3 = null;
      private final Function<EntityPlayerBridge, Vector3f> field4;

      public DynamicTransform(Vector3f var1) {
         super(var1, ThreadModuleDump91.Type.NONE);
         this.field4 = var0 -> null;
      }

      public DynamicTransform(Function<EntityPlayerBridge, Vector3f> var1) {
         super((Vector3f)null, ThreadModuleDump91.Type.NONE);
         this.field4 = var1;
      }

      @Override
      public JsonObject save() {
         JsonObject var1 = new JsonObject();
         JsonObject var2 = new JsonObject();
         var1.addProperty("transformType", this.transformType().name().toLowerCase());
         JsonObject var3 = new JsonObject();
         Vector3f var4 = this.field4.apply(null);
         if (var4 != null) {
            var3.addProperty("x", var4.x);
            var3.addProperty("y", var4.y);
            var3.addProperty("z", var4.z);
            var2.add("gui", var3);
         }

         JsonObject var6 = new JsonObject();
         Vector3f var5 = this.field4.apply(field3);
         if (var5 != null) {
            var6.addProperty("x", var5.x);
            var6.addProperty("y", var5.y);
            var6.addProperty("z", var5.z);
            var2.add("player", var6);
         }

         var1.add("values", var2);
         return var1;
      }

      @Override
      public void load(JsonObject var1) {
      }
   }

   class ScaleTransform extends ThreadModuleDump91.AbstractTransform {
      public ScaleTransform(Vector3f var1, ThreadModuleDump91.Type var2) {
         super(var1, var2);
      }

      public ScaleTransform(JsonObject var1, ThreadModuleDump91.Type var2) {
         super(var1, var2);
      }

      @Override
      public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, float var3) {
         var1.scale(this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z);
      }

      @Override
      public ThreadModuleDump91.Type2 transformType() {
         return ThreadModuleDump91.Type2.SCALE;
      }

      @Override
      public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, Bridge5_16 var3, float var4) {
         var1.scale(this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z);
      }

      @Override
      public void transform(Matrix4f var1, EntityPlayerBridge var2, float var3) {
         var1.scale(this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z);
      }

      @Override
      public JsonObject save() {
         JsonObject var1 = new JsonObject();
         JsonObject var2 = new JsonObject();
         var2.addProperty("x", this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x);
         var2.addProperty("y", this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y);
         var2.addProperty("z", this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z);
         var1.addProperty("transformType", this.transformType().name().toLowerCase());
         var1.add("values", var2);
         if (this.getCondition() != ThreadModuleDump91.Type.NONE) {
            var1.addProperty("condition", this.getCondition().name().toLowerCase());
         }

         return var1;
      }

      @Override
      public void load(JsonObject var1) {
         JsonObject var2 = var1.get("values").getAsJsonObject();
         this.RCRRIRCOOHICRCCORCOCCOROIHIHHI = new Vector3f();
         this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x = var2.get("x").getAsFloat();
         this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y = var2.get("y").getAsFloat();
         this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z = var2.get("z").getAsFloat();
      }
   }

   class TranslateTransform extends ThreadModuleDump91.AbstractTransform {
      public TranslateTransform(Vector3f var1, ThreadModuleDump91.Type var2) {
         super(var1, var2);
      }

      public TranslateTransform(JsonObject var1, ThreadModuleDump91.Type var2) {
         super(var1, var2);
      }

      @Override
      public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, float var3) {
         var1.translate(this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z);
      }

      @Override
      public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, Bridge5_16 var3, float var4) {
         var1.translate(this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z);
      }

      @Override
      public void transform(Matrix4f var1, EntityPlayerBridge var2, float var3) {
         var1.translate(this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.x, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.y, this.RCRRIRCOOHICRCCORCOCCOROIHIHHI.z);
      }

      @Override
      public ThreadModuleDump91.Type2 transformType() {
         return ThreadModuleDump91.Type2.TRANSLATE;
      }
   }

   class RotateTransform implements ThreadModuleDump91 {
      protected float[] field1 = new float[4];
      private ThreadModuleDump91.Type field2;

      public RotateTransform(float var1, float var2, float var3, float var4, ThreadModuleDump91.Type var5) {
         this.field2 = var5;
         this.field1[0] = var1;
         this.field1[1] = var2;
         this.field1[2] = var3;
         this.field1[3] = var4;
      }

      public RotateTransform(JsonObject var1, ThreadModuleDump91.Type var2) {
         this.load(var1);
         this.field2 = var2;
      }

      @Override
      public JsonObject save() {
         JsonObject var1 = new JsonObject();
         JsonObject var2 = new JsonObject();
         var2.addProperty("angle", this.field1[0]);
         var2.addProperty("x", this.field1[1]);
         var2.addProperty("y", this.field1[2]);
         var2.addProperty("z", this.field1[3]);
         var1.addProperty("transformType", ThreadModuleDump91.Type2.ROTATE.name().toLowerCase());
         var1.addProperty("condition", this.getCondition().name().toLowerCase());
         var1.add("values", var2);
         return var1;
      }

      @Override
      public void load(JsonObject var1) {
         JsonObject var2 = var1.get("values").getAsJsonObject();
         this.field1[0] = var2.get("angle").getAsFloat();
         this.field1[1] = var2.get("x").getAsFloat();
         this.field1[2] = var2.get("y").getAsFloat();
         this.field1[3] = var2.get("z").getAsFloat();
      }

      @Override
      public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, float var3) {
         var1.method4(this.field1[0], this.field1[1], this.field1[2], this.field1[3]);
      }

      @Override
      public ThreadModuleDump91.Type2 transformType() {
         return ThreadModuleDump91.Type2.ROTATE;
      }

      @Override
      public void transform(AbstractRenderContext var1, EntityPlayerBridge var2, Bridge5_16 var3, float var4) {
         var1.method5(this.field1[0] * this.field1[1], this.field1[0] * this.field1[2], this.field1[0] * this.field1[3]);
      }

      @Override
      public void transform(Matrix4f var1, EntityPlayerBridge var2, float var3) {
         float var4 = (float)Math.toRadians(this.field1[0]);
         var1.rotate(new Quaternionf().rotationX(this.field1[1] * var4));
         var1.rotate(new Quaternionf().rotationY(this.field1[2] * var4));
         var1.rotate(new Quaternionf().rotationZ(this.field1[3] * var4));
      }

      @Override
      public ThreadModuleDump91.Type getCondition() {
         return this.field2;
      }

      public Vector4f method1() {
         return new Vector4f(this.field1[1], this.field1[2], this.field1[3], this.field1[0]);
      }
   }

   enum Type {
      WORLD(var0 -> var0.field1 == null, RenderContextType.IN_WORLD),
      GUI(var0 -> var0.field1 == null, RenderContextType.IN_GUI),
      PLAYER(var0 -> var0.field1 != null, RenderContextType.IN_PLAYER_MODEL),
      FIRST_PERSON(var0 -> var0.field1 != null && var0.field3, RenderContextType.IN_FIRST_PERSON),
      THIRD_PERSON(var0 -> var0.field1 != null && !var0.field3, RenderContextType.IN_PLAYER_MODEL),
      HELMET(armorPredicate(EquipmentSlotBridge.HEAD, ThreadModuleDump91.Type::shouldShowHatAboveHelmet), RenderContextType.IN_PLAYER_MODEL),
      CHESTPLATE(armorPredicate(EquipmentSlotBridge.CHEST, ThreadModuleDump91.Type::shouldShowOverChestplate), RenderContextType.IN_PLAYER_MODEL),
      LEGGINGS(armorPredicate(EquipmentSlotBridge.LEGS, ThreadModuleDump91.Type::shouldShowOverLeggings), RenderContextType.IN_PLAYER_MODEL),
      BOOTS(armorPredicate(EquipmentSlotBridge.FEET, ThreadModuleDump91.Type::shouldShowOverBoots), RenderContextType.IN_PLAYER_MODEL),
      SKIN_LAYER(
         var0 -> var0.field1 != null
            && !var0.field1.method2()
            && !HELMET.applies(var0.field1, var0.field2)
            && Pkg.method1(var0.field1)
            && shouldShowHatAboveSkinLayer(var0.field2),
         RenderContextType.IN_PLAYER_MODEL
      ),
      NONE(var0 -> true, var0 -> true);

      private final Predicate<ThreadModuleDump91.Type.Data> condition;
      private final Predicate<Set<RenderContextType>> contextCondition;

      Type(Predicate<ThreadModuleDump91.Type.Data> var3, RenderContextType var4) {
         this(var3, var1x -> var1x.contains(var4));
      }

      public boolean applies(ThreadModuleDump91.Type.Data var1) {
         return this.condition.test(var1);
      }

      public boolean applies(EntityPlayerBridge var1, CosmeticMetadata var2) {
         return this.condition.test(ThreadModuleDump91.Type.Data.method1(var1, var2));
      }

      public boolean applies(RenderContext var1, CosmeticMetadata var2) {
         return this.contextCondition.test(var1.method21()) && this.applies(var1.method1().orElse(null), var2);
      }

      private static Predicate<ThreadModuleDump91.Type.Data> armorPredicate(EquipmentSlotBridge var0, Predicate<CosmeticMetadata> var1) {
         return var2 -> var2.field1 != null && !var2.field1.method2() && var2.field1.bridge$getArmor(var0) != null && var1.test(var2.field2);
      }

      private static boolean shouldShowHatAboveHelmet(CosmeticMetadata var0) {
         return var0.method6().method4();
      }

      private static boolean shouldShowOverChestplate(CosmeticMetadata var0) {
         return var0.method6().isShowOverChestplate();
      }

      private static boolean shouldShowOverLeggings(CosmeticMetadata var0) {
         return var0.method6().isShowOverLeggings();
      }

      private static boolean shouldShowOverBoots(CosmeticMetadata var0) {
         return var0.method6().isShowOverBoots();
      }

      private static boolean shouldShowHatAboveSkinLayer(CosmeticMetadata var0) {
         return var0.method6().method5();
      }

      @Generated
      Type(Predicate<ThreadModuleDump91.Type.Data> var3, Predicate<Set<RenderContextType>> var4) {
         this.condition = var3;
         this.contextCondition = var4;
      }

      public class Data {
         private final EntityPlayerBridge field1;
         private final CosmeticMetadata field2;
         private final boolean field3;
         private final boolean field4;

         public Data(EntityPlayerBridge var1, CosmeticMetadata var2, boolean var3, boolean var4) {
            this.field1 = var1;
            this.field2 = var2;
            this.field3 = var3;
            this.field4 = var4;
         }

         public static ThreadModuleDump91.Type.Data method1(EntityPlayerBridge var0, CosmeticMetadata var1) {
            return new ThreadModuleDump91.Type.Data(var0, var1, false, false);
         }

         public static ThreadModuleDump91.Type.Data method2(EntityPlayerBridge var0, CosmeticMetadata var1, ItemTransformsBridge.Type var2) {
            return new ThreadModuleDump91.Type.Data(var0, var1, var2.firstPerson(), var2 == ItemTransformsBridge.Type.GUI);
         }

         public EntityPlayerBridge method3() {
            return this.field1;
         }

         public CosmeticMetadata method4() {
            return this.field2;
         }

         public boolean firstPerson() {
            return this.field3;
         }

         public boolean method5() {
            return this.field4;
         }
      }
   }

   enum Type2 {
      SCALE,
      ROTATE,
      TRANSLATE,
      MIXED;
   }
}
