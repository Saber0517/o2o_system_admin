package com.jyhon.service;

import javax.swing.*;
import java.io.InputStream;

/**
 * Created by WhiteSaber on 15/8/9.
 */
public interface FileService {
    InputStream getFile(String fileName);
    byte[] getFileByByte(String fileName);
}
