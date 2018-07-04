package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class PhotoToFile{
    private static int photoId = 0;	
    public static File decodePic( String encodedPhoto, String extension){
        File photoFile;
	byte[] decodedContent = Base64.getDecoder().decode(encodedPhoto);
        photoFile = new File( "src/resources/photo" + photoId + "." + extension.toLowerCase() );
        try{
            photoFile.createNewFile();
            OutputStream outStream = new FileOutputStream(photoFile,false);
            outStream.write(decodedContent);
	    outStream.close();
	    photoId += 1;
        }catch(IOException e){}
    	return photoFile;
    }

}
