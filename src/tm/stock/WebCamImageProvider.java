/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stock;

/**
 *
 * @author omarblythe
 */
import java.awt.image.BufferedImage;
import java.time.LocalTime;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class WebCamImageProvider extends Task<Void> {

	private boolean stopCamera = false;
	private BufferedImage grabbedImage;
	private Webcam webCam = null;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
	private StringProperty fps = new SimpleStringProperty();
	private String qrCode = "";

	public static String readQRCode(BufferedImage image) throws NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
		return qrCodeResult.getText();
	}

	@Override
	protected Void call() throws Exception {
 
                
		webCam = Webcam.getWebcams().get(0);
		webCam.open();

		int counter = 0;
		LocalTime ts = LocalTime.now();
		while (!stopCamera) {
			try {
				// First display the grabbed image
				if ((grabbedImage = webCam.getImage()) != null) {
					final Image mainiamge = SwingFXUtils.toFXImage(grabbedImage, null);
					Platform.runLater(() -> imageProperty.set(mainiamge));
					grabbedImage.flush();
				}
				
				// Analyse the image if a qr code could be found
				try {
					qrCode = readQRCode(grabbedImage);
                                        System.out.println(qrCode);
                                        Thread.sleep(1000);
                                        return null ;
                                        //Thread.sleep(10000);
				} catch (Exception e) {
					// if no code was found just do nothing
				}

				// Check the frame rate
				if (ts.getSecond() == LocalTime.now().getSecond()) {
					counter++;
				} else {
					ts = LocalTime.now();
					final int value = counter;
					counter = 0;
					Platform.runLater(() -> fps.set(value + ""));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		webCam.close();
		return null;
	}

	public StringProperty fpsProperty() {
		return fps;
	}

	public boolean isStopCamera() {
		return stopCamera;
	}

	public void stopCamera(boolean stopCamera) {
		this.stopCamera = stopCamera;
	}

	public ObjectProperty<Image> imageProperty() {
		return imageProperty;
	}

	public String getQrCode() {
		return qrCode;
	}
}