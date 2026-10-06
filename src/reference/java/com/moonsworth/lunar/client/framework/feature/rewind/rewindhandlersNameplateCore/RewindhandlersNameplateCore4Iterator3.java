package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.audio.Wasapi;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HRESULT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Objects;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.opus.Opus;

public class RewindhandlersNameplateCore4Iterator3 extends RewindhandlersNameplateCore4 {
   private static final GuiType2 field5 = GuiType2.MONO16;
   private final Wasapi.IMMDeviceEnumerator field6;
   private final Wasapi.IMMDevice field7;
   private final Wasapi.IAudioClient field8;
   private final Wasapi.IAudioCaptureClient field9;
   private final HANDLE field10;
   private final Pointer field11;
   private final Wasapi.WAVEFORMATEX field12;
   private final boolean field13;
   private HRESULT field14 = Ole32.INSTANCE.CoInitializeEx(null, 2);

   public RewindhandlersNameplateCore4Iterator3() {
      if (Objects.equals(this.field14, WinNT.S_OK)) {
         this.field13 = true;
      } else {
         if (!Objects.equals(this.field14, WinNT.S_FALSE)) {
            throw new RuntimeException("CoInitializeEx failed with HRESULT: 0x" + Long.toHexString(this.field14.longValue()));
         }

         this.field13 = false;
      }

      this.field10 = Wasapi.Kernel32Library.INSTANCE.CreateEvent(null, false, false, null);
      if (this.field10 != null && this.field10.getPointer() != null) {
         PointerByReference var1 = new PointerByReference();
         this.field14 = Ole32.INSTANCE.CoCreateInstance(Wasapi.CLSID_MMDeviceEnumerator, null, 23, Wasapi.IID_IMMDeviceEnumerator, var1);
         this.method1(this.field14, "CoCreateInstance");
         this.field6 = new Wasapi.IMMDeviceEnumerator(var1.getValue());
         PointerByReference var2 = new PointerByReference();
         this.field14 = this.field6.GetDefaultAudioEndpoint(0, 0, var2);
         this.method1(this.field14, "GetDefaultAudioEndpoint");
         this.field7 = new Wasapi.IMMDevice(var2.getValue());
         PointerByReference var3 = new PointerByReference();
         this.field14 = this.field7.Activate(Wasapi.IID_IAudioClient, 23, null, var3);
         this.method1(this.field14, "Activate");
         this.field8 = new Wasapi.IAudioClient(var3.getValue());
         PointerByReference var4 = new PointerByReference();
         this.field14 = this.field8.GetMixFormat(var4);
         this.method1(this.field14, "GetMixFormat");
         this.field11 = var4.getValue();
         this.field12 = new Wasapi.WAVEFORMATEX(this.field11);
         this.field12.read();
         int var5 = 393216;
         this.field14 = this.field8.Initialize(0, var5, 0L, 0L, this.field11, Guid.IID_NULL);
         this.method1(this.field14, "Initialize");
         IntByReference var6 = new IntByReference();
         this.field14 = this.field8.GetBufferSize(var6);
         this.method1(this.field14, "GetBufferSize");
         this.field14 = this.field8.SetEventHandle(this.field10);
         this.method1(this.field14, "SetEventHandle");
         PointerByReference var7 = new PointerByReference();
         this.field14 = this.field8.GetService(Wasapi.IID_IAudioCaptureClient, var7);
         this.method1(this.field14, "GetService");
         this.field9 = new Wasapi.IAudioCaptureClient(var7.getValue());
         this.field1.setChannels(field5.getChannels());
         this.field1.setFrequency(Math.min(48000, this.field12.nSamplesPerSec));
         this.field1.setFrameSize((int)(this.field1.getFrequency() * 0.02));
         this.field1.setBytesPerSample(field5.getBytesPerSample());
         this.method1();
      } else {
         throw new RuntimeException("Unable to create event.");
      }
   }

   @Override
   public void start() {
      int var1 = this.field1.getFrameSize() * this.field1.getChannels();
      int var2 = var1 * this.field1.getBytesPerSample();
      ByteBuffer var3 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      ByteBuffer var4 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      byte[] var5 = new byte[var2];
      double var6 = (double)this.field12.nSamplesPerSec / this.field1.getFrequency();
      int var8 = (int)(this.field1.getFrameSize() * this.field12.nChannels * this.field12.wBitsPerSample / 8.0 * var6);
      ByteBuffer var9 = BufferUtils.createByteBuffer(var8).order(ByteOrder.LITTLE_ENDIAN);
      ByteBuffer var10 = BufferUtils.createByteBuffer(var8).order(ByteOrder.LITTLE_ENDIAN);
      ShortBuffer var11 = var3.asShortBuffer();
      ByteBuffer var12 = ByteBuffer.allocate(var8 * 3).order(ByteOrder.LITTLE_ENDIAN);
      this.field14 = this.field8.Start();
      this.method1(this.field14, "Start");
      this.method4(
         () -> {
            while (!Thread.currentThread().isInterrupted()) {
               int var11x = Wasapi.Kernel32Library.INSTANCE.WaitForSingleObject(this.field10, 2000);
               if (var11x == 0) {
                  IntByReference var12x = new IntByReference();
                  this.field14 = this.field9.GetNextPacketSize(var12x);
                  this.method1(this.field14, "GetNextPacketSize");

                  while (var12x.getValue() > 0) {
                     PointerByReference var13 = new PointerByReference();
                     IntByReference var14 = new IntByReference();
                     IntByReference var15 = new IntByReference();
                     this.field14 = this.field9.GetBuffer(var13, var14, var15, null, null);
                     this.method1(this.field14, "GetBuffer");
                     int var16 = var14.getValue();
                     int var17 = var16 * this.field12.nBlockAlign;
                     if (var17 > 0) {
                        byte[] var18 = var13.getValue().getByteArray(0L, var17);
                        if (!this.isPaused()) {
                           var12.put(var18);
                        }

                        try {
                           while (var12.position() >= var8) {
                              var12.flip();
                              var10.clear().put(var12.array(), 0, var8).flip();
                              var12.position(var8).compact();
                              if (this.field12.wBitsPerSample == 32) {
                                 var9.clear().put(var10).flip();
                                 var10.clear();
                                 RewindhandlersNameplateCore2.method1(
                                    var9, var10, (int)(this.field1.getFrameSize() * var6), this.field12.nChannels
                                 );
                              }

                              if (this.field12.nSamplesPerSec != this.field1.getFrequency()) {
                                 var9.clear().put(var10).flip();
                                 var10.clear();
                                 RewindhandlersNameplateCore2.method2(
                                    var9,
                                    var10,
                                    this.field12.nSamplesPerSec,
                                    this.field1.getFrequency(),
                                    this.field1.getChannels(),
                                    this.field1.getFrameSize() * this.field1.getBytesPerSample()
                                 );
                              }

                              var3.clear().put(var10).flip();
                              var4.clear();
                              int var19 = Opus.opus_encode(this.field2, var11, this.field1.getFrameSize(), var4);
                              var4.get(var5, 0, var19);

                              for (DataOutputStream var21 : this.field4) {
                                 ByteBufLoader.method13(var21, var19);
                                 var21.write(var5, 0, var19);
                              }
                           }
                        } catch (IOException var22) {
                           Inventorymod2.method5(var22, "Rewind");
                        }
                     }

                     this.field14 = this.field9.ReleaseBuffer(var14.getValue());
                     this.method1(this.field14, "ReleaseBuffer");
                     this.field14 = this.field9.GetNextPacketSize(var12x);
                     this.method1(this.field14, "GetNextPacketSize");
                  }
               }
            }
         }
      );
   }

   @Override
   public void stop() {
      super.stop();
      if (this.field8 != null) {
         this.field8.Stop();
      }

      if (this.field9 != null) {
         this.field9.Release();
      }

      if (this.field8 != null) {
         this.field8.Release();
      }

      if (this.field7 != null) {
         this.field7.Release();
      }

      if (this.field6 != null) {
         this.field6.Release();
      }

      if (this.field10 != null) {
         Wasapi.Kernel32Library.INSTANCE.CloseHandle(this.field10);
      }

      if (this.field11 != null) {
         Ole32.INSTANCE.CoTaskMemFree(this.field11);
      }

      if (this.field13) {
         Ole32.INSTANCE.CoUninitialize();
      }
   }

   private void method1(HRESULT var1, String var2) {
      if (!Objects.equals(var1, WinNT.S_OK)) {
         throw new RuntimeException("Function " + var2 + " failed with HRESULT: 0x" + Long.toHexString(var1.longValue()));
      }
   }
}
