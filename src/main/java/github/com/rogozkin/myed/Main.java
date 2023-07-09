package github.com.rogozkin.myed;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Chat chat=new Chat();
        chat.start(1234);
    }

}
