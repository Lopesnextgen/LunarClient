package com.moonsworth.lunar.bridge.itemcounter;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType$Type;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import javax.annotation.Nullable;

@Annotation2(min = 6)
public interface Itemcounter_4 {
   @Nullable
   MissResult bridge$clip(Vec3Bridge var1, Vec3Bridge var2, Vector3iBridge var3);

   @Nullable
   Itemcounter_4 bridge$getFaceShape(HorsestatsType_2 var1);

   @Nullable
   Itemcounter_4 bridge$calculateFace(HorsestatsType_2 var1);

   void bridge$forAllEdges(Itemcounter$Extension var1);

   void bridge$forAllAxisEdges(Itemcounter$Type2 var1, Itemcounter$Extension var2);

   void bridge$forAllAxisEdgesExclude(Itemcounter$Type2 var1, Itemcounter$Extension var2);

   AxisAlignedBBBridge bridge$bounds();

   List<AxisAlignedBBBridge> bridge$toAabbs();

   Itemcounter_4 bridge$join(Itemcounter_4 var1);

   Itemcounter_4 bridge$move(double var1, double var3, double var5);

   boolean bridge$isEmpty();

   double bridge$max(HorsestatsType$Type var1);
}
