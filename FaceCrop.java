import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceCrop
{

    public static void main(String[] args)
    {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        CascadeClassifier faceDetector = new CascadeClassifier();

 faceDetector.load("C:\\myproject\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
        System.out.println ( "Working" );
        // Input image
        Mat image = Imgcodecs.imread("D:\\obama.jpg");

        // Detecting faces
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        // Creating a rectangular box showing faces detected
        Rect rectCrop=null;
        for (Rect rect : faceDetections.toArray())
        {
            Imgproc.rectangle(image, new Point(rect.x, rect.y),
             new Point(rect.x + rect.width, rect.y + rect.height),
                                           new Scalar(0, 255, 0));
            rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
        }

        // Saving the output image
        String filename = "obama1.jpg";
        Imgcodecs.imwrite("D:\\"+filename, image);

        Mat markedImage = new Mat(image,rectCrop);
        Imgcodecs.imwrite("D:\\cropimage.jpg",markedImage );
    }
}
