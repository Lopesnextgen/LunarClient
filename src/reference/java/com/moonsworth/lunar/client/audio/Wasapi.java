package com.moonsworth.lunar.client.audio;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HRESULT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

@com.moonsworth.lunar.ichor.util.Annotation4
public interface Wasapi {
   int EDataFlow_eRender = 0;
   int ERole_eConsole = 0;
   int AUDCLNT_SHAREMODE_SHARED = 0;
   int AUDCLNT_STREAMFLAGS_LOOPBACK = 131072;
   int AUDCLNT_STREAMFLAGS_EVENTCALLBACK = 262144;
   int CLSCTX_ALL = 23;
   GUID CLSID_MMDeviceEnumerator = new GUID("BCDE0395-E52F-467C-8E3D-C4579291692E");
   GUID IID_IMMDeviceEnumerator = new GUID("A95664D2-9614-4F35-A746-DE8DB63617E6");
   GUID IID_IAudioClient = new GUID("1CB9AD4C-DBFA-4c32-B178-C2F568A703B2");
   GUID IID_IAudioCaptureClient = new GUID("C8ADBD64-E71E-48a0-A4DE-185C395CD317");

   @com.moonsworth.lunar.ichor.util.Annotation4
   class IAudioCaptureClient extends Unknown implements IUnknown {
      public IAudioCaptureClient(Pointer var1) {
         super(var1);
      }

      public HRESULT GetBuffer(PointerByReference var1, IntByReference var2, IntByReference var3, Pointer var4, Pointer var5) {
         return new HRESULT(this._invokeNativeInt(3, new Object[]{this.getPointer(), var1, var2, var3, var4, var5}));
      }

      public HRESULT ReleaseBuffer(int var1) {
         return new HRESULT(this._invokeNativeInt(4, new Object[]{this.getPointer(), var1}));
      }

      public HRESULT GetNextPacketSize(IntByReference var1) {
         return new HRESULT(this._invokeNativeInt(5, new Object[]{this.getPointer(), var1}));
      }
   }

   @com.moonsworth.lunar.ichor.util.Annotation4
   class IAudioClient extends Unknown implements IUnknown {
      public IAudioClient(Pointer var1) {
         super(var1);
      }

      public HRESULT Initialize(int var1, int var2, long var3, long var5, Pointer var7, GUID var8) {
         return new HRESULT(this._invokeNativeInt(3, new Object[]{this.getPointer(), var1, var2, var3, var5, var7, var8}));
      }

      public HRESULT GetBufferSize(IntByReference var1) {
         return new HRESULT(this._invokeNativeInt(4, new Object[]{this.getPointer(), var1}));
      }

      public HRESULT GetMixFormat(PointerByReference var1) {
         return new HRESULT(this._invokeNativeInt(8, new Object[]{this.getPointer(), var1}));
      }

      public HRESULT Start() {
         return new HRESULT(this._invokeNativeInt(10, new Object[]{this.getPointer()}));
      }

      public HRESULT Stop() {
         return new HRESULT(this._invokeNativeInt(11, new Object[]{this.getPointer()}));
      }

      public HRESULT SetEventHandle(HANDLE var1) {
         return new HRESULT(this._invokeNativeInt(13, new Object[]{this.getPointer(), var1}));
      }

      public HRESULT GetService(GUID var1, PointerByReference var2) {
         return new HRESULT(this._invokeNativeInt(14, new Object[]{this.getPointer(), var1, var2}));
      }
   }

   @com.moonsworth.lunar.ichor.util.Annotation4
   class IMMDevice extends Unknown implements IUnknown {
      public IMMDevice(Pointer var1) {
         super(var1);
      }

      public HRESULT Activate(GUID var1, int var2, Pointer var3, PointerByReference var4) {
         return new HRESULT(this._invokeNativeInt(3, new Object[]{this.getPointer(), var1, var2, var3, var4}));
      }
   }

   @com.moonsworth.lunar.ichor.util.Annotation4
   class IMMDeviceEnumerator extends Unknown implements IUnknown {
      public IMMDeviceEnumerator(Pointer var1) {
         super(var1);
      }

      public HRESULT GetDefaultAudioEndpoint(int var1, int var2, PointerByReference var3) {
         return new HRESULT(this._invokeNativeInt(4, new Object[]{this.getPointer(), var1, var2, var3}));
      }
   }

   @com.moonsworth.lunar.ichor.util.Annotation4
   interface Kernel32Library extends StdCallLibrary {
      Wasapi.Kernel32Library INSTANCE = (Wasapi.Kernel32Library)Native.load("kernel32", Wasapi.Kernel32Library.class, W32APIOptions.UNICODE_OPTIONS);

      HANDLE CreateEvent(Pointer var1, boolean var2, boolean var3, String var4);

      int WaitForSingleObject(HANDLE var1, int var2);

      boolean CloseHandle(HANDLE var1);
   }

   @FieldOrder({"wFormatTag", "nChannels", "nSamplesPerSec", "nAvgBytesPerSec", "nBlockAlign", "wBitsPerSample", "cbSize"})
   @com.moonsworth.lunar.ichor.util.Annotation4
   class WAVEFORMATEX extends Structure {
      public short wFormatTag;
      public short nChannels;
      public int nSamplesPerSec;
      public int nAvgBytesPerSec;
      public short nBlockAlign;
      public short wBitsPerSample;
      public short cbSize;

      public WAVEFORMATEX(Pointer var1) {
         super(var1);
         this.read();
      }
   }
}
