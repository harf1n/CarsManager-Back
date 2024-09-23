package com.uedge.kursach.exception;

public class UpgradeNotFoundException extends RuntimeException {
    public UpgradeNotFoundException(Long id) {
        super("Couldn't found the upgrade with id " + id);
    }
}
