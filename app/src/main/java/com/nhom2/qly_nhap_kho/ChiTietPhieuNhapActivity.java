package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapter;
import com.nhom2.qly_nhap_kho.dao.TableData;
import com.nhom2.qly_nhap_kho.model.ChiTietPhieuNhap;
import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.PhieuNhap;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapActivity extends AppCompatActivity {

    NhapKhoHelper nhapKhoHelper;
    TextView txtTitle;
    Button buttonReport;

    String id = "";
    PhieuNhap phieuNhap;
    Kho kho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phieu_nhap);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("data");

        if (bundle != null) {
            id = bundle.getString("id");
            phieuNhap = (PhieuNhap) bundle.getSerializable("phieuNhap");
            kho = (Kho) bundle.getSerializable("kho");
        }

        txtTitle = findViewById(R.id.txtTitle);
        buttonReport = findViewById(R.id.buttonReport);
        txtTitle.setText("Thông tin vật tư nhập kho của phiếu số " + id);

        nhapKhoHelper = new NhapKhoHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

        TableViewAdapter adapter = new TableViewAdapter(getList(id));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        String finalId = id;
        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createInvoice(getList(finalId), phieuNhap, kho);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void createInvoice(List<TableData> list, PhieuNhap phieuNhap,Kho kho) throws IOException {
        int pageWidth = 1200;
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dp);
//        Bitmap bmpScale = Bitmap.createScaledBitmap(bmp, 1200, 518, false);

        PdfDocument myPdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint titlePaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo);
        Canvas canvas = myPage1.getCanvas();

//        canvas.drawBitmap(bmpScale,0,0,paint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setColor(Color.BLACK);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(90);
        canvas.drawText("THÔNG TIN NHẬP KHO", pageWidth / 2, 270, titlePaint);

        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setColor(Color.GRAY);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        titlePaint.setTextSize(35);
        canvas.drawText("Mã kho: " + kho.getMaKho(), 50, 350, titlePaint);
        canvas.drawText("Tên kho: " + kho.getTenKho(), 700, 350, titlePaint);
        canvas.drawText("Số phiếu nhập: " + id, 50, 420, titlePaint);
        canvas.drawText("Ngày lập phiếu: " + phieuNhap.NgayLap, 700, 420, titlePaint);

//

        //main
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(35);
        canvas.drawRect(20, 760, pageWidth - 20, 860, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("STT", 40, 830, paint);
        canvas.drawText("Mã VT", 130, 830, paint);
        canvas.drawText("Tên vật tư", 290, 830, paint);
        canvas.drawText("Xuất xứ", 610, 830, paint);
        canvas.drawText("ĐVT", 880, 830, paint);
        canvas.drawText("Số lượng", 1030, 830, paint);

        canvas.drawLine(110, 790, 110, 840, paint);
        canvas.drawLine(260, 790, 260, 840, paint);
        canvas.drawLine(580, 790, 580, 840, paint);
        canvas.drawLine(840, 790, 840, 840, paint);
        canvas.drawLine(1020, 790, 1020, 840, paint);

        int offsetY = 950;
        int total = 0;


        for (int i = 0; i < list.size(); i++) {
            TableData t = list.get(i);
            int tt = i+1;
            canvas.drawText(tt + "", 40, offsetY, paint);
            canvas.drawText(t.maVT, 130, offsetY, paint);
            canvas.drawText(t.tenVT, 290, offsetY, paint);
            canvas.drawText(t.xuatXu, 610, offsetY, paint);
            canvas.drawText(t.DVT, 880, offsetY, paint);
            canvas.drawText(t.soLuong + "", 1030, offsetY, paint);
            offsetY = offsetY + 60;
            total += t.soLuong;
        }

        canvas.drawLine(680, offsetY+80, pageWidth - 20, offsetY+80, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Tổng số lượng:", 700, offsetY+150, paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(total + "", pageWidth - 20, offsetY+150, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Số loại vật tư:", 700, offsetY+220, paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(list.size() + "", pageWidth - 20, offsetY+220, paint);

        myPdfDocument.finishPage(myPage1);

        String folder = Environment.getExternalStorageDirectory().getPath() + "/documents";
        File folderFile = new File(folder);
        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }
        String path = folder + "/Calcuradora_" + System.currentTimeMillis() + ".pdf";
        File myFile = new File(path);
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myPdfDocument.writeTo(fOut);
        myPdfDocument.close();
        myOutWriter.close();
        fOut.close();
        Toast.makeText(getBaseContext(), "File Saved on " + path, Toast.LENGTH_LONG).show();
        openPdfViewer(myFile);

    }

    private void openPdfViewer(File file) { //need to add provider in manifest and filepaths.xml
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID + ".provider", file), "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, 101);
    }


    private List<TableData> getList(String id) {
        List<TableData> list = new ArrayList<>();

        Cursor dataCTPN = nhapKhoHelper.GetData("SELECT * FROM ChiTietPhieuNhap WHERE SoPhieu = " + id);

        ChiTietPhieuNhap chiTietPhieuNhap;
        while (dataCTPN.moveToNext()) {
            chiTietPhieuNhap = new ChiTietPhieuNhap(
                    Integer.valueOf(dataCTPN.getString(0)),
                    dataCTPN.getString(1),
                    dataCTPN.getString(2),
                    Integer.valueOf(dataCTPN.getString(3)));
            Log.d("aaa", chiTietPhieuNhap.MaVT);
            if (chiTietPhieuNhap != null) {
                Cursor dataVT = nhapKhoHelper.GetData("SELECT * FROM VatTu WHERE MaVT = '" + chiTietPhieuNhap.MaVT + "'");
                VatTu vatTu;
                if (dataVT.moveToNext()) {
                    vatTu = new VatTu(dataVT.getString(0), dataVT.getString(1), dataVT.getString(2));
                    TableData tableData = new TableData(
                            vatTu.MaVT, vatTu.TenVt, vatTu.XuatXu, chiTietPhieuNhap.DVT, chiTietPhieuNhap.SoLuong
                    );

                    list.add(tableData);
                }
            }
        }
        return list;
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}