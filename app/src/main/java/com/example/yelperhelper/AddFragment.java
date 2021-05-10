package com.example.yelperhelper;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.PathUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AddFragment extends Fragment {
    private View view;
    private EditText editText_foodItem;
    private EditText editText_restaurant;
    private EditText editText_review;
    private RatingBar ratingBar;
    private Button buttonSave;
    private Button button_addPhoto;
    private ImageView imageView_camera;
    private String image_path;

    private ReviewViewModel reviewViewModel;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap bitmap;

    private static int CAMERA_PERMISSION_CODE = 1;
    private static int CAMERA = 2;
    private static int STORAGE_PERMISSION_CODE = 23;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    String currentPhotoPath;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        reviewViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);


        editText_foodItem = view.findViewById(R.id.editText_foodItem);
        editText_restaurant = view.findViewById(R.id.editText_restaurant);
        editText_review = view.findViewById(R.id.editText_review);
        ratingBar = view.findViewById(R.id.addratingBar);
        buttonSave = view.findViewById(R.id.button_save);
        button_addPhoto = view.findViewById(R.id.button_addPhoto);
        imageView_camera = view.findViewById(R.id.imageView_camera);
        
        button_addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getContext(),
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA);
                } else {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String [] {Manifest.permission.CAMERA},
                            CAMERA_PERMISSION_CODE
                            );

                }
            }
        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("button clicked", "save");
                // need to find the food_id first
                // if it does not exist, then we create one

                if (TextUtils.isEmpty(editText_foodItem.getText())){
                    makeToast("Missing Field: Food Item");
                }
                else if (TextUtils.isEmpty(editText_restaurant.getText())){
                    makeToast("Missing Field: Missing Field: Restaurant name");
                }
                else if (TextUtils.isEmpty(editText_review.getText())){
                    makeToast("Missing Field: Review");
                }
                else if (ratingBar.getRating() == 0.0){
                    makeToast("Missing: Rating");
                }
                else{
                    String foodItem = editText_foodItem.getText().toString();
                    // make sure to standardize foodItem because it needs to be categorized
                    foodItem = foodItem.substring(0, 1).toUpperCase() + foodItem.substring(1).toLowerCase();

                    String restaurant = editText_restaurant.getText().toString();
                    String review = editText_review.getText().toString();
                    float rating = ratingBar.getRating();

                    Log.e("fooditem", foodItem);
                    Log.e("restaurant", restaurant);
                    Log.e("review", review);
                    Log.e("rating", String.valueOf(rating));

                    Review reviewObj = new Review(foodItem, restaurant, review, rating, image_path);
                    reviewViewModel.insertReview(reviewObj);

                    makeToast("Saved!");

                }


            }
        });

        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE){
            if ((grantResults.length != 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA);

            } else{
                makeToast("Request was denied");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("whats the prob", String.valueOf(resultCode));
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CAMERA){
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView_camera.setImageBitmap(imageBitmap);

                Uri uri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                image_path = uri.toString();
                Log.e("image path", image_path);


            } else{
                makeToast("problem");
            }
        } else{
            makeToast("WHY");
        }
    }



    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }



    public void makeToast(String message){
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
