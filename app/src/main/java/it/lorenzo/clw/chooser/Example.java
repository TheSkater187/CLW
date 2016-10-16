package it.lorenzo.clw.chooser;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import it.lorenzo.clw.R;

/**
 * Created by lorenzo on 14/10/16.
 */

public class Example {
    public String caption;
    public int img;
    public int file;
    public File path;

    public static ArrayList<Example> createExamples(Context context) {
        ArrayList<Example> exampleList = new ArrayList<Example>();

        Example example = new Example();
        example.caption = "Hello_World_!";
        example.img = R.drawable.text;
        example.file = R.raw.text;
        example.saveToDisk(context);
        if (example.path != null && example.path.canRead())
            exampleList.add(example);


        example = new Example();
        example.caption = "Example";
        example.img = R.drawable.example_img;
        example.file = R.raw.example;
        example.saveToDisk(context);
        if (example.path != null && example.path.canRead())
            exampleList.add(example);

        example = new Example();
        example.caption = "Top";
        example.img = R.drawable.top;
        example.file = R.raw.top;
        example.saveToDisk(context);
        if (example.path != null && example.path.canRead())
            exampleList.add(example);

        example = new Example();
        example.caption = "Agenda";
        example.img = R.drawable.agenda;
        example.file = R.raw.agenda;
        example.saveToDisk(context);
        if (example.path != null && example.path.canRead())
            exampleList.add(example);

        example = new Example();
        example.caption = "PipBoy-HDPI-device";
        example.img = R.drawable.pipboy;
        example.file = R.raw.pipboy;
        example.saveToDisk(context);
        if (example.path != null && example.path.canRead())
            exampleList.add(example);


        return exampleList;
    }

    public void saveToDisk(Context context) {
        File clwDir = new File(Environment.getExternalStorageDirectory(), "CLW-examples");
        String filename = caption + ".txt";
        File file2 = new File(clwDir, filename);
        if (!file2.exists() && clwDir.isDirectory() || clwDir.mkdirs()) {
            try {
                CopyRAWtoSDCard(this.file, file2.getAbsolutePath(), context);
            } catch (IOException ioException) {
                CharSequence text = "Can't write example file: " + filename;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        path = file2.exists() && file2.canRead() ? file2 : null;
    }

    private void CopyRAWtoSDCard(int id, String path, Context context) throws IOException {
        InputStream in = context.getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}