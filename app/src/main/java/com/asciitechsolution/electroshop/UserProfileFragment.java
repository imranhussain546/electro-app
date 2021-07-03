package com.asciitechsolution.electroshop;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import static android.app.Activity.RESULT_OK;


public class UserProfileFragment extends Fragment {
private EditText name,email,phone,oldpass,newpass;
private ImageView profileimage,profileedit;
private FloatingActionButton imagebtn;
private Button changepass,save;
public static String PROFILE = "profile";
public int reqWritePermission = 2;
public static int SELECT_FILE = 110;
public static int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
final File output = null;
Uri fileUri;
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_user_profile, container, false);
        name=view.findViewById(R.id.profileName);
        email=view.findViewById(R.id.profileEmail);
        phone=view.findViewById(R.id.profilePno);
        oldpass=view.findViewById(R.id.profileOLDpass);
        newpass=view.findViewById(R.id.profileNEWpass);
        profileimage=view.findViewById(R.id.profileIV);
        profileedit=view.findViewById(R.id.profileIVedit);
        imagebtn=view.findViewById(R.id.profileFab);
        changepass=view.findViewById(R.id.profileChangepass);
        save=view.findViewById(R.id.profileSave);

        oldpass.setVisibility(view.GONE);
        newpass.setVisibility(view.GONE);

        name.setText("imran");
        email.setText("imran.hussain546@gmail.com");
        phone.setText("8700608166");
        name.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);
        imagebtn.setEnabled(false);
        changepass.setEnabled(false);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpass.setVisibility(view.VISIBLE);
                newpass.setVisibility(view.VISIBLE);
            }
        });

        profileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
                email.setEnabled(true);
                phone.setEnabled(true);
                changepass.setEnabled(true);
                imagebtn.setEnabled(true);

            }
        });

        //Profile image
        Picasso.with(getActivity())
                .load(PROFILE)
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .transform(new CircleTransform())
                .into(profileimage);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectProfileImage();
            }
        });


        return view;
    }
    public void SelectProfileImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, reqWritePermission);
            } else {
                selectDialog();
            }
        } else {
            selectDialog();
        }
    }
    private void selectDialog() {
        final CharSequence[] items = {"Choose from Library","Cancel"};
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_FILE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setOutputCompressQuality(80)
                    .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setAspectRatio(1, 1)
                    .start(getActivity());

        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            CropImage.activity(FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".provider", output)).start(getActivity());

        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                fileUri = result.getUri();
                //new UploadFileToServer().execute();
            }
        }
    }
}