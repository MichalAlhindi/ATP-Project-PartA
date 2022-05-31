package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int count;
    private int currNum;
    private byte[] byteArr;
    public MyCompressorOutputStream(OutputStream os) {
        out = os;
        count = 0;
        currNum = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if(b == currNum){
            count++;
        }
    }

/*    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }*/
}
