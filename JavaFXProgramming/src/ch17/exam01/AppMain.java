package ch17.exam01;

import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application {
    public AppMain() {
        System.out.println("Constructor");
    }

    @Override
    public void init() throws Exception {
        System.out.println("init");
        Parameters params = getParameters();
        
        /*
        List<String> list = params.getRaw();
        for(String param : list) { 
            System.out.println(param);
        }
        */
        
        //java AppMain --ip=192.168.3.35 --port=50001
        Map<String, String> map = params.getNamed();
        String ip = map.get("ip");
        String port = map.get("port");
        System.out.println(ip);
        System.out.println(port);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Thread.currentThread().getName());
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
