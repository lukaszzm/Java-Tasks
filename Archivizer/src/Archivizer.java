import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Archivizer implements ArchivizerInterface {
    @Override
    public int compress(String dir, String filename) {
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(filename + ".zip"));
            File dirToCompress = new File(dir);
            File[] files = dirToCompress.listFiles();

            assert files != null;
            for (File subFile : files) {
                compressDir(subFile, subFile.getName(), out);
            }

            out.close();

            return getSize(filename);
        } catch (IOException ignored) { }
        return 0;
    }

    private int getSize(String filename) {
        File file = new File(filename + ".zip");
        long size = file.length();
        return (int) size;
    }

    private void compressDir(File file, String path, ZipOutputStream out) throws IOException {
        if (file.isDirectory()) {
            String newPath = path + "/";
            out.putNextEntry(new ZipEntry(newPath));
            out.closeEntry();
            File[] subFiles = file.listFiles();

            assert subFiles != null;
            for (File subFile : subFiles) {
                compressDir(subFile, newPath + subFile.getName(), out);
            }
            return;
        } else {
            compressFile(file, path, out);
        }
    }

    private void compressFile (File file, String path, ZipOutputStream out) throws IOException {
        FileInputStream fileToZip = new FileInputStream(file);
        ZipEntry entry = new ZipEntry(path);
        out.putNextEntry(entry);

        byte[] bytes = new byte[1024];
        int length;

        while ((length = fileToZip.read(bytes)) >= 0) {
            out.write(bytes, 0, length);
        }
        fileToZip.close();
    }


    @Override
    public void decompress(String filename, String dir) {
        try {
            byte[] bytes = new byte[1024];

            if(!filename.endsWith(".zip")) {
                filename = filename + ".zip";
            }
            if (!dir.endsWith("/")) {
                dir = dir + "/";
            }

            ZipInputStream in = new ZipInputStream(new FileInputStream(filename));
            ZipEntry entry = in.getNextEntry();

            while (entry != null) {
                File file = new File(dir + File.separator + entry.getName());
                if(entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    decompressFile(dir, bytes, entry, in);
                }
                entry = in.getNextEntry();
            }

            in.closeEntry();
            in.close();
        } catch (IOException ignored) { }

    }

    private void decompressFile(String filename, byte[] bytes, ZipEntry entry, ZipInputStream in) throws IOException {
        FileOutputStream out = new FileOutputStream(filename + entry.getName());
        int len;
        while ((len = in.read(bytes)) > 0) {
            out.write(bytes, 0, len);
        }
        out.close();
    }
}
