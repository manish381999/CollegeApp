package com.tie.collegeapp.ui.Ebook;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;




import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;
import com.tie.collegeapp.databinding.ActivityPdfViewerBinding;


import java.io.File;


public class PdfViewerActivity extends AppCompatActivity {
ActivityPdfViewerBinding binding;


    String pdfUrl,pdfTitle;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPdfViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        pdfUrl=getIntent().getStringExtra("pdfUrl");
       pdfTitle=getIntent().getStringExtra("pdfTitle");


loadPdfFile(pdfUrl);

    }

    private void loadPdfFile(String pdfUrl) {

        FileLoader.with(this)
                .load(pdfUrl,true) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory("test4", FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        binding.pb.setVisibility(View.GONE);
                        File loadedFile = response.getBody();
                        binding.idPDFView.fromFile(loadedFile)
                                .password(null)
                                .defaultPage(0)
                                .enableSwipe(true)
                                .swipeHorizontal(false)
                                .enableDoubletap(true)
                                .spacing(20)
                                .fitEachPage(true)
                                .load();
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Toast.makeText(PdfViewerActivity.this, "Error"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.pb.setVisibility(View.GONE);
                    }
                });
    }

}



