package com.jgarcia.ut1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class ej2 {
    public static void main(String args[]) throws IOException {
        // lee el fichero
        readFileChannel();
        // añade el contenido al fichero
        writeFileChannel(ByteBuffer.wrap("Welcome to DAM2".getBytes()));

    }
    public static void readFileChannel() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("raf.txt",
                "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        Charset charset = Charset.forName("US-ASCII");
        while (fileChannel.read(byteBuffer) > 0) {
            byteBuffer.rewind();
            System.out.print(charset.decode(byteBuffer));
            byteBuffer.flip();
        }
        fileChannel.close();
        randomAccessFile.close();
    }
    public static void writeFileChannel(ByteBuffer byteBuffer)throws IOException {
        Set<StandardOpenOption> options = new HashSet<>();
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.APPEND);
        Path path = Paths.get("temp.txt");
        FileChannel fileChannel = FileChannel.open(path, options);
        fileChannel.write(byteBuffer);
        fileChannel.close();
    }
}
