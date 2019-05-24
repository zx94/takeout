package com.example.demo.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class Common {

    //size 转 KB、MB 表达式
    public static String parseSize(long size) {
        if (size < 1024) {
            return size + "B";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            return size + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            size = size * 100;
            return size / 100 + "." + size % 100 + "MB";
        } else {
            size = size * 100 / 1024;
            return size / 100 + "." + size % 100 + "GB";
        }
    }

    //获取图片长宽
    public static String getImageWh(File file) {
        try {
            final BufferedImage image = ImageIO.read(new FileInputStream(file));
            return image.getWidth() + "x" + image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
