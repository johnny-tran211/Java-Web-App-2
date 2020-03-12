/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.templetes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class UploadImage implements Serializable {

    public static String uploadImage(String image, String newAbsoluteDiskPath) throws FileNotFoundException, IOException {
        File file = new File(image);
        int index = image.lastIndexOf("\\");
        String nameImage = image.substring(index + 1);
        FileInputStream fis = new FileInputStream(file);
        int len = (int) file.length();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[len];
        int nRead = fis.read(data, 0, data.length);
        buffer.write(data, 0, nRead);
        OutputStream targetFile = new FileOutputStream(newAbsoluteDiskPath + "web/image/"+nameImage);
        targetFile.write(buffer.toByteArray());
        targetFile.close();

        return nameImage;
    }
}
