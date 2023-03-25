package org.projects.development;

public class ExtraTask2 {
    public String longToIP(long ip) {
        if (ip < 0 || ip > 4294967295L) {
            return "Invalid input";
        }
        return String.format("%d.%d.%d.%d",
                (ip >> 24) & 0xFF,
                (ip >> 16) & 0xFF,
                (ip >> 8) & 0xFF,
                ip & 0xFF);
    }
}
