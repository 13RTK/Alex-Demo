package apps;

import JsoupUtils.JsoupUtils;

import java.io.IOException;
import java.util.Scanner;

public class JsoupApplication {
    public static void main(String[] args) throws IOException{
        run();
    }

    public static void run() throws IOException {
        System.out.println("请输入关键词: ");
        JsoupUtils.searchInJD(new Scanner(System.in).nextLine());
    }
}
