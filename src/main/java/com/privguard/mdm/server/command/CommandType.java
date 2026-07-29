package com.privguard.mdm.server.command;

public enum CommandType {

    NULL,
    REBOOT,
    LOCK_DEVICE,
    WAKE_SCREEN,
    VIBRATE_DEVICE,
    RING_DEVICE,
    CHANGE_HEARTBEAT,
    INSTALL_APP,
    UNINSTALL_APP,
    BULK_INSTALL_APP,
    BULK_UNINSTALL_APP
}