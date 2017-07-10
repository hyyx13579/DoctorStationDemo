package com.founder.ihospital_bdrm_doctor_station_offline.zxing.encode;

import java.net.URLEncoder;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;

import android.graphics.Bitmap;

public class CodeCreator {

	/**
	 * 生成QRCode（二维码）
	 * 
	 * @param str
	 * @return
	 * @throws WriterException
	 */
	public static Bitmap createQRCode(String url) throws WriterException {

		if (url == null || url.equals("")) {
			return null;
		}

		// 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
		BitMatrix matrix = new MultiFormatWriter().encode(url,
				BarcodeFormat.QR_CODE, 300, 300);

		int width = matrix.getWidth();
		int height = matrix.getHeight();

		// 二维矩阵转为一维像素数组,也就是一直横着排了
		int[] pixels = new int[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}

			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;
 
    /**
     * @param args
     * @throws WriterException
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        PDF417Writer pdf417Writer = new PDF417Writer();
        //注意中文乱码问题
        BitMatrix bitMatrix = pdf417Writer.encode(URLEncoder.encode("我是中国人","UTF-8"),
                BarcodeFormat.PDF_417, 100, 50);
         
//        writeToFile(bitMatrix,"png",new File("E:\\work\\all_workspace\\wp_zxing\\barcode4jTest\\src\\test\\helloworld.png"));
    }
 
//    public static void writeToFile(BitMatrix matrix, String format, File file)
//            throws IOException {
//        BufferedImage image = toBufferedImage(matrix);
//        ImageIO.write(image, format, file);
//    }
// 
//    public static BufferedImage toBufferedImage(BitMatrix matrix) {
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        BufferedImage image = new BufferedImage(width, height,
//                BufferedImage.TYPE_INT_ARGB);
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
//            }
//        }
//        return image;
//    }
// 
}
