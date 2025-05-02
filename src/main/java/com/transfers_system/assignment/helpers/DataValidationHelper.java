package com.transfers_system.assignment.helpers;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */

import java.util.UUID;

public class DataValidationHelper {
    public static boolean isValidUUID(UUID uuid) {
        try {
            UUID uId = UUID.fromString(uuid.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
