package org.example.vendor;

public class VendorALightAPI {
    private boolean state;
    public void power(boolean on) { state = on; System.out.println("[VendorA Light] power(" + on + ")"); }
    public void bright(int level) { System.out.println("[VendorA Light] brightness=" + level); }
}
