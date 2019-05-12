package singleton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.swing.filechooser.FileSystemView;

@Singleton
@LocalBean
public class LogRegistrer {

    private List<String> registry;
    private List<String> oldRegistry;

    @PostConstruct
    public void init() {
        registry = new ArrayList<>();
        oldRegistry = new ArrayList<>();
    }

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void inactividad() {
        if(registry.size() == oldRegistry.size()) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            String inactivo = "INACTIVO:: " + "Hora: " + formatter.format(date);
            registry.add(inactivo);
            System.out.println(inactivo);
        }
        //System.out.println("old: "+oldRegistry.size()+" current: "+registry.size());
        oldRegistry.clear();
        oldRegistry.addAll(registry);
    }
    
    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void saveLog() throws IOException {
        //String ruta = "C:\\Users\\usuario\\Desktop\\archivo1.txt";
        File home = FileSystemView.getFileSystemView().getHomeDirectory();
        //System.out.println(home.getAbsolutePath() + "\\log.txt");
        File f = new File(home.getAbsolutePath() + "\\log.txt");
        FileWriter fw = new FileWriter(f);
        BufferedWriter escritura = new BufferedWriter(fw);
        for(int i=0;i<registry.size();i++){
            escritura.write(registry.get(i));
            escritura.newLine();

        }
        escritura.close();
    }

    public void log(String info) {
        registry.add(info);
    }

    public List<String> getRegistry() {
        return registry;
    }

}
