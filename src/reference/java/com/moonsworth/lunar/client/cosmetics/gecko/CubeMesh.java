package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.cosmetics.gecko.VectorConverter;
import org.joml.Vector3d;
import org.joml.Vector3f;
import com.moonsworth.lunar.client.inactive.rewindhandlers.Rewindhandlers9;

public class CubeMesh {
   public ModelQuad[] field1 = new ModelQuad[6];
   public Vector3f field2;
   public Vector3f field3;
   public Vector3f field4 = new Vector3f();
   public double field5;
   public Boolean field6;

   private CubeMesh(double[] items1) {
      if (items1.length >= 3) {
         this.field4.set((float)items1[0], (float)items1[1], (float)items1[2]);
      }
   }

   public static CubeMesh method1(ModelCube rewindhandlers50, ModelDescription rewindhandlers61, Double value2, Boolean flag3) {
      CubeMesh rewindhandlers_24 = new CubeMesh(rewindhandlers50.method11());
      Rewindhandlers9 rewindhandlers95 = rewindhandlers50.method13();
      ModelFaceTextures rewindhandlers86 = rewindhandlers95.field2;
      boolean flag7 = rewindhandlers95.field3;
      rewindhandlers_24.field6 = rewindhandlers50.method3();
      rewindhandlers_24.field5 = rewindhandlers50.method1() == null ? (value2 == null ? 0.0 : value2) : rewindhandlers50.method1() / 16.0;
      float value8 = rewindhandlers61.method23().floatValue();
      float value9 = rewindhandlers61.method25().floatValue();
      Vector3d vector3d10 = VectorConverter.method1(rewindhandlers50.method11());
      Vector3d vector3d11 = VectorConverter.method1(rewindhandlers50.method5());
      vector3d11 = new Vector3d(-(vector3d11.x + vector3d10.x) / 16.0, vector3d11.y / 16.0, vector3d11.z / 16.0);
      vector3d10 = vector3d10.mul(0.0625, 0.0625, 0.0625);
      Vector3f vector3f12 = VectorConverter.method3(VectorConverter.method1(rewindhandlers50.method9()));
      vector3f12.mul(-1.0F, -1.0F, 1.0F);
      vector3f12.x = (float)Math.toRadians(vector3f12.x());
      vector3f12.y = (float)Math.toRadians(vector3f12.y());
      vector3f12.z = (float)Math.toRadians(vector3f12.z());
      Vector3f vector3f13 = VectorConverter.method3(VectorConverter.method1(rewindhandlers50.method7()));
      vector3f13.mul(-1.0F, 1.0F, 1.0F);
      rewindhandlers_24.field2 = vector3f13;
      rewindhandlers_24.field3 = vector3f12;
      ModelVertex rewindhandlers414 = new ModelVertex(vector3d11.x - rewindhandlers_24.field5, vector3d11.y - rewindhandlers_24.field5, vector3d11.z - rewindhandlers_24.field5);
      ModelVertex rewindhandlers415 = new ModelVertex(vector3d11.x - rewindhandlers_24.field5, vector3d11.y - rewindhandlers_24.field5, vector3d11.z + vector3d10.z + rewindhandlers_24.field5);
      ModelVertex rewindhandlers416 = new ModelVertex(vector3d11.x - rewindhandlers_24.field5, vector3d11.y + vector3d10.y + rewindhandlers_24.field5, vector3d11.z - rewindhandlers_24.field5);
      ModelVertex rewindhandlers417 = new ModelVertex(vector3d11.x - rewindhandlers_24.field5, vector3d11.y + vector3d10.y + rewindhandlers_24.field5, vector3d11.z + vector3d10.z + rewindhandlers_24.field5);
      ModelVertex rewindhandlers418 = new ModelVertex(vector3d11.x + vector3d10.x + rewindhandlers_24.field5, vector3d11.y - rewindhandlers_24.field5, vector3d11.z - rewindhandlers_24.field5);
      ModelVertex rewindhandlers419 = new ModelVertex(vector3d11.x + vector3d10.x + rewindhandlers_24.field5, vector3d11.y - rewindhandlers_24.field5, vector3d11.z + vector3d10.z + rewindhandlers_24.field5);
      ModelVertex rewindhandlers420 = new ModelVertex(vector3d11.x + vector3d10.x + rewindhandlers_24.field5, vector3d11.y + vector3d10.y + rewindhandlers_24.field5, vector3d11.z - rewindhandlers_24.field5);
      ModelVertex rewindhandlers421 = new ModelVertex(vector3d11.x + vector3d10.x + rewindhandlers_24.field5, vector3d11.y + vector3d10.y + rewindhandlers_24.field5, vector3d11.z + vector3d10.z + rewindhandlers_24.field5);
      ModelQuad rewindhandlers322;
      ModelQuad rewindhandlers323;
      ModelQuad rewindhandlers324;
      ModelQuad rewindhandlers325;
      ModelQuad rewindhandlers326;
      ModelQuad rewindhandlers327;
      if (!flag7) {
         FaceTexture rewindhandlers728 = rewindhandlers86.method11();
         FaceTexture rewindhandlers729 = rewindhandlers86.method3();
         FaceTexture rewindhandlers730 = rewindhandlers86.method5();
         FaceTexture rewindhandlers731 = rewindhandlers86.method7();
         FaceTexture rewindhandlers732 = rewindhandlers86.method9();
         FaceTexture rewindhandlers733 = rewindhandlers86.method1();
         rewindhandlers322 = rewindhandlers728 == null
            ? null
            : new ModelQuad(
               new ModelVertex[]{rewindhandlers417, rewindhandlers416, rewindhandlers414, rewindhandlers415}, rewindhandlers728.method3(), rewindhandlers728.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.WEST
            );
         rewindhandlers323 = rewindhandlers729 == null
            ? null
            : new ModelQuad(
               new ModelVertex[]{rewindhandlers420, rewindhandlers421, rewindhandlers419, rewindhandlers418}, rewindhandlers729.method3(), rewindhandlers729.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.EAST
            );
         rewindhandlers324 = rewindhandlers730 == null
            ? null
            : new ModelQuad(
               new ModelVertex[]{rewindhandlers416, rewindhandlers420, rewindhandlers418, rewindhandlers414}, rewindhandlers730.method3(), rewindhandlers730.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.NORTH
            );
         rewindhandlers325 = rewindhandlers731 == null
            ? null
            : new ModelQuad(
               new ModelVertex[]{rewindhandlers421, rewindhandlers417, rewindhandlers415, rewindhandlers419}, rewindhandlers731.method3(), rewindhandlers731.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.SOUTH
            );
         rewindhandlers326 = rewindhandlers732 == null
            ? null
            : new ModelQuad(
               new ModelVertex[]{rewindhandlers417, rewindhandlers421, rewindhandlers420, rewindhandlers416}, rewindhandlers732.method3(), rewindhandlers732.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.UP
            );
         rewindhandlers327 = rewindhandlers733 == null
            ? null
            : new ModelQuad(
               new ModelVertex[]{rewindhandlers414, rewindhandlers418, rewindhandlers419, rewindhandlers415}, rewindhandlers733.method3(), rewindhandlers733.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.DOWN
            );
         if (rewindhandlers50.method3() == Boolean.TRUE || flag3 == Boolean.TRUE) {
            rewindhandlers322 = rewindhandlers728 == null
               ? null
               : new ModelQuad(
                  new ModelVertex[]{rewindhandlers420, rewindhandlers421, rewindhandlers419, rewindhandlers418}, rewindhandlers728.method3(), rewindhandlers728.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.WEST
               );
            rewindhandlers323 = rewindhandlers729 == null
               ? null
               : new ModelQuad(
                  new ModelVertex[]{rewindhandlers417, rewindhandlers416, rewindhandlers414, rewindhandlers415}, rewindhandlers729.method3(), rewindhandlers729.method5(), value9, value8, rewindhandlers50.method3(), HorsestatsType_2.EAST
               );
         }
      } else {
         double[] items36 = rewindhandlers50.method13().field1;
         Vector3d vector3d37 = VectorConverter.method1(rewindhandlers50.method11());
         vector3d37 = new Vector3d(Math.floor(vector3d37.x), Math.floor(vector3d37.y), Math.floor(vector3d37.z));
         rewindhandlers322 = new ModelQuad(
            new ModelVertex[]{rewindhandlers417, rewindhandlers416, rewindhandlers414, rewindhandlers415},
            new double[]{items36[0] + vector3d37.z + vector3d37.x, items36[1] + vector3d37.z},
            new double[]{vector3d37.z, vector3d37.y},
            value9,
            value8,
            rewindhandlers50.method3(),
            HorsestatsType_2.WEST
         );
         rewindhandlers323 = new ModelQuad(
            new ModelVertex[]{rewindhandlers420, rewindhandlers421, rewindhandlers419, rewindhandlers418},
            new double[]{items36[0], items36[1] + vector3d37.z},
            new double[]{vector3d37.z, vector3d37.y},
            value9,
            value8,
            rewindhandlers50.method3(),
            HorsestatsType_2.EAST
         );
         rewindhandlers324 = new ModelQuad(
            new ModelVertex[]{rewindhandlers416, rewindhandlers420, rewindhandlers418, rewindhandlers414},
            new double[]{items36[0] + vector3d37.z, items36[1] + vector3d37.z},
            new double[]{vector3d37.x, vector3d37.y},
            value9,
            value8,
            rewindhandlers50.method3(),
            HorsestatsType_2.NORTH
         );
         rewindhandlers325 = new ModelQuad(
            new ModelVertex[]{rewindhandlers421, rewindhandlers417, rewindhandlers415, rewindhandlers419},
            new double[]{items36[0] + vector3d37.z + vector3d37.x + vector3d37.z, items36[1] + vector3d37.z},
            new double[]{vector3d37.x, vector3d37.y},
            value9,
            value8,
            rewindhandlers50.method3(),
            HorsestatsType_2.SOUTH
         );
         rewindhandlers326 = new ModelQuad(
            new ModelVertex[]{rewindhandlers417, rewindhandlers421, rewindhandlers420, rewindhandlers416},
            new double[]{items36[0] + vector3d37.z, items36[1]},
            new double[]{vector3d37.x, vector3d37.z},
            value9,
            value8,
            rewindhandlers50.method3(),
            HorsestatsType_2.UP
         );
         rewindhandlers327 = new ModelQuad(
            new ModelVertex[]{rewindhandlers414, rewindhandlers418, rewindhandlers419, rewindhandlers415},
            new double[]{items36[0] + vector3d37.z + vector3d37.x, items36[1] + vector3d37.z},
            new double[]{vector3d37.x, -vector3d37.z},
            value9,
            value8,
            rewindhandlers50.method3(),
            HorsestatsType_2.DOWN
         );
         if (rewindhandlers50.method3() == Boolean.TRUE || flag3 == Boolean.TRUE) {
            rewindhandlers322 = new ModelQuad(
               new ModelVertex[]{rewindhandlers420, rewindhandlers421, rewindhandlers419, rewindhandlers418},
               new double[]{items36[0] + vector3d37.z + vector3d37.x, items36[1] + vector3d37.z},
               new double[]{vector3d37.z, vector3d37.y},
               value9,
               value8,
               rewindhandlers50.method3(),
               HorsestatsType_2.WEST
            );
            rewindhandlers323 = new ModelQuad(
               new ModelVertex[]{rewindhandlers417, rewindhandlers416, rewindhandlers414, rewindhandlers415},
               new double[]{items36[0], items36[1] + vector3d37.z},
               new double[]{vector3d37.z, vector3d37.y},
               value9,
               value8,
               rewindhandlers50.method3(),
               HorsestatsType_2.EAST
            );
         }
      }

      rewindhandlers_24.field1[0] = rewindhandlers322;
      rewindhandlers_24.field1[1] = rewindhandlers323;
      rewindhandlers_24.field1[2] = rewindhandlers324;
      rewindhandlers_24.field1[3] = rewindhandlers325;
      rewindhandlers_24.field1[4] = rewindhandlers326;
      rewindhandlers_24.field1[5] = rewindhandlers327;
      return rewindhandlers_24;
   }
}
