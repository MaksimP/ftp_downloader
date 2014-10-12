import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimerTask;

//таймер на 10 минут, с заданием, которое выполняется по таймеру
public class ScheduledTask extends TimerTask {
    private String core_dir_ftp = "//";
    private MyFTP myFTP = new MyFTP();
    private ArrayList<FTPFile> list_dir_one = new ArrayList<FTPFile>();
    static private ArrayList<FTPFile> list_dir_two = new ArrayList<FTPFile>();
    private ArrayList<FTPFile> list_files_one = new ArrayList<FTPFile>();  //список обновленных файлов
    private ArrayList<FTPFile> list_files_two = new ArrayList<FTPFile>(); //список файлов в новой папке

    //просмотр списка файлов на ftp и сравнение с предыдущим сохраненым
    @Override
    public void run() {
        Calendar calendar = null;
        System.out.println("Begin run");
        try {
            System.out.println("Begin run two");
            list_dir_one.addAll(Arrays.asList(myFTP.getListOfFile(core_dir_ftp)));
            for (FTPFile ftpFile : list_dir_two){
                System.out.println(ftpFile.getName() + " ___@___");
            }
            System.out.println("_______________________");
            System.out.println("-----------------------");
            if (list_dir_two.isEmpty())
            {
                System.out.println("empty");
            }
            for (FTPFile ftpFile : list_dir_two){
                System.out.println(ftpFile);
            }
            // buffer = list_files_one;
            if (list_dir_one.size() == list_dir_two.size()) {
                System.out.println("((((((((((((");
                for (int i = 0; i < list_dir_one.size(); i++){
                    if (list_dir_one.get(i).getTimestamp() == list_dir_two.get(i).getTimestamp()){
                        continue;
                    } else {
                        calendar = list_dir_one.get(i).getTimestamp();
                        myFTP.changeDirFtp(list_dir_one.get(i).toString());
                        list_files_one.addAll(Arrays.asList(myFTP.getListOfFile(core_dir_ftp + list_dir_one.get(i).getName())));
                        for (int k = 0; k < list_files_one.size(); k++){
                            if (list_files_one.get(k).getTimestamp() == calendar){
                                continue;
                            } else {
                                // myFTP.downloadFromFTP(list_files_one.get(k).getName(), list_dir_one.get(i).getName());
                            }
                        }
                    }
                }
            } else {
                list_dir_one.removeAll(list_dir_two);
                for (int m = 0; m < list_dir_one.size(); m++){
                    String name_dir = list_dir_one.get(m).getName();
                    System.out.println("current dir " + name_dir);
                    if (name_dir.indexOf(".") == -1){
                        try {
                            list_files_two.addAll(Arrays.asList(myFTP.getListOfFile(core_dir_ftp + name_dir)));
                        } catch (FTPConnectionClosedException e){
                            System.out.println("Closed connection");
                        }
                        // myFTP.changeDirFtp(name_dir);
                    } else {
                        continue;
                    }
                    for (int l = 2; l < list_files_two.size(); l++){
                        String name_file = list_files_two.get(l).getName();
                        if (name_file.endsWith(".rar") || name_file.endsWith(".tif")) {
                            // myFTP.downloadFromFTP(core_dir_ftp, list_dir_one.get(m).getName() + list_files_two.get(l).getName(), list_dir_one.get(m).getName());
                        }
                        System.out.println("current file " + name_file);
                    }
                    System.out.println();
                    list_files_two.clear();
                }
            }
            list_dir_two.clear();
            list_dir_two = list_dir_one;
            list_dir_one.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
