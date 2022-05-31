package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    public MyDecompressorInputStream(InputStream is) {
        in = is;
    }


    @Override
    public int read() throws IOException {
        return 0;
    }

/*    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }*/
}

