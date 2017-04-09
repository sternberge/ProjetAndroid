package com.ece.aurelien.androidproject.Camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ece.aurelien.androidproject.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Aurélien on 08/04/2017.
 */

public class CameraActivity extends AppCompatActivity{
    ImageView result;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mFileLocation = "";
    File photoFile;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (shouldAskPermissions()) {
            askPermissions();
        }
        setContentView(R.layout.camera);
        Button click = (Button)findViewById(R.id.button10);
        result = (ImageView) findViewById(R.id.imageView);
        context = getApplicationContext();
        click.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //File file = getFile();

        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getApplicationContext(), "com.Camera.android.fileprovider",file));

       // File photoFile = null;
        try{
            photoFile = createImageFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            try {
                FileOutputStream out = new FileOutputStream(photoFile);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.setImageBitmap(imageBitmap);
        }
    }


    /*private File getFile(){
        File folder = new File("sdcard/camera_app");
        if(!folder.exists()){
            folder.mkdir();
        }
        File imageFile = new File(folder,"cam_image.jpg");
        return imageFile;
    }*/

    File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFIleName = "IMAGE"+timeStamp+"_";

        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);//Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //File passToDirectory = new File(getExternalFilesDir(Environment.DIRECTORY_DCIM).toString()+ "/Camera");
        //File storageDirectory = context.getDir(Environment.DIRECTORY_PICTURES,Context.MODE_PRIVATE);
               File image = File.createTempFile(imageFIleName,".jpeg",storageDirectory);
        //File image = new File(storageDirectory.toString() + "/Camera",imageFIleName + ".jpg");
        mFileLocation = image.getAbsolutePath();

        return image.getAbsoluteFile();
    }


    //////trying
    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }
    
}

