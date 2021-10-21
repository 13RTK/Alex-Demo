import com.arcsoft.face.FaceEngine;

public class RunApplication {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        AlexFaceEngine.initEngine();
        FaceService.simpleCheck();
        System.out.println("==================");
        FaceService.compareTwoImage();
    }
}
