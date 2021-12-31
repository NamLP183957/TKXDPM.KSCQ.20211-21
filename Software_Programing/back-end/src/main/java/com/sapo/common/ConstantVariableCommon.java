package com.sapo.common;

public class ConstantVariableCommon {
    public static final int STATUS_VEHICLE_1 = 1;
    public static final int STATUS_VEHICLE_2 = 2;
    public static final int STATUS_VEHICLE_3 = 3;

    //Hàm chuyển status từ int sang string
    public static String statusVehicleIntToString(int status) {
        if (status == STATUS_VEHICLE_1) return "đang có người thuê";
        if (status == STATUS_VEHICLE_2) return "chưa có ";
        return "cccccc";
    }

    public static final int NOT_DONE_INVOICE = 1;
    public static final int DONE_INVOICE = 2;

}