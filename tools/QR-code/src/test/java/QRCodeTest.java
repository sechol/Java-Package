import com.zoro.love.qr_code.factory.QRCodeFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: Java-Package
 * @description: test
 * @author: Zoro Li
 * @create: 2019-10-23 18:24
 */

public class QRCodeTest {


    @Before
    public void init() {

    }

    @After
    public void release() {

    }

    @Test
    public void generrateQRCode(){
        QRCodeFactory.generateQRCodeImage("https://10.86.17.36");
    }

}