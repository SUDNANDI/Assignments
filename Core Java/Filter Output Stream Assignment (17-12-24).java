package pack.cap.module8.io;

import java.io.*;

class UpperCaseFilterOutputStream extends FilterOutputStream {

    public UpperCaseFilterOutputStream(OutputStream out) {
        super(out);  
    }

    @Override
    public void write(int b) throws IOException {
     
        super.write(Character.toUpperCase(b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        
        for (int i = 0; i < len; i++) {
            b[off + i] = (byte) Character.toUpperCase(b[off + i]);
        }
        super.write(b, off, len); 
    }
}

public class FilterOutputStreamExample {
    public static void main(String[] args) {
    
        String inputFilePath = "C:/Users/SUDNANDI/Documents/input.txt";
        String outputFilePath = "C:/Users/SUDNANDI/Documents/output.txt";

        
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
             FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
             UpperCaseFilterOutputStream filterOutputStream = new UpperCaseFilterOutputStream(fileOutputStream)) {

           
            int byteData;
            while ((byteData = fileInputStream.read()) != -1) {
             
                filterOutputStream.write(byteData);
            }

            System.out.println("Data from '" + inputFilePath + "' has been processed and written to '" + outputFilePath + "' in uppercase.");
        } catch (IOException e) {
            System.out.println("An error occurred while processing the files.");
            e.printStackTrace();
        }
    }
}
